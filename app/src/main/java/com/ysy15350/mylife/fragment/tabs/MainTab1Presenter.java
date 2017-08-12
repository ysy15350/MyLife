package com.ysy15350.mylife.fragment.tabs;

import android.content.Context;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import api.ApiCallBack;
import api.IUser;
import api.impl.UserApi;
import api.model.Response;
import base.BasePresenter;
import common.utils.JsonConvertor;
import common.utils.StringUtils;

public class MainTab1Presenter extends BasePresenter<MainTab1ViewInterface> {

    public MainTab1Presenter() {
    }

    public MainTab1Presenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void getData(String url, String param, int cacheTime, final boolean useCache) {


        RequestParams params = new RequestParams(url);
        //params.setSslSocketFactory(...); // 设置ssl

        params.addQueryStringParameter("param", param);

        params.setCacheMaxAge(cacheTime * 1000);//缓存时间(秒)


        x.http().post(params, new Callback.CacheCallback<String>() {

            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) {
                // 得到缓存数据, 缓存过期后不会进入这个方法.
                // 如果服务端没有返回过期时间, 参考params.setCacheMaxAge(maxAge)方法.
                //
                // * 客户端会根据服务端返回的 header 中 max-age 或 expires 来确定本地缓存是否给 onCache 方法.
                //   如果服务端没有返回 max-age 或 expires, 那么缓存将一直保存, 除非这里自己定义了返回false的
                //   逻辑, 那么xUtils将请求新数据, 来覆盖它.
                //
                // * 如果信任该缓存返回 true, 将不再请求网络;
                //   返回 false 继续请求网络, 但会在请求头中加上ETag, Last-Modified等信息,
                //   如果服务端返回304, 则表示数据没有更新, 不继续加载数据.
                //

                this.result = result;
                return useCache; // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
            }

            @Override
            public void onSuccess(String result) {
                // 注意: 如果服务返回304 或 onCache 选择了信任缓存, 这时result为null.
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    // ...
                } else { // 其他错误
                    // ...
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    // 成功获取数据
                    //Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                    mView.bindData(result);
                }
            }
        });
    }


//    private int page = 1;
//
//    private int pageSize = 10;
//
//    Type dataType;
//
//    public void test() {
//
//
//        Type genType = this.getClass().getGenericSuperclass();
//
//        try {
//            if (genType != null) {
//                Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
//
//                if (types != null && types.length > 0)
//                    dataType = types[0];
//            }
//
//        } catch (Exception e) {
//
//        }
//
//        if (dataType != null) {
//
//            String typeString = dataType.toString();
//
//            if ("class java.lang.Object".equals(typeString) || typeString.contains("Response")) {
//                dataType = null;// 默认Response(class
//                // framework_movie_friend.Response)
//            }
//        }
//
//        IUser userApi = new UserApi();
//
//
//        userApi.userLogin("admin", "123456", new ApiCallBack() {
//
//
//            @Override
//            public void onSuccess(boolean isCache, String data) {
//
//                if (!StringUtils.isEmpty(data)) {
//
//
//                    Response response = JsonConvertor.fromJson(data, Response.class);
//
//                    if (response != null) {
//
//                        mView.loginSuccess(isCache + "," + response.getResult());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailed(String msg) {
//
//            }
//        });
//
//
//    }


}
