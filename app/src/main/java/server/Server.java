package server;

import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;
import java.util.Objects;

import api.ApiCallBack;
import api.Config;
import api.model.Response;
import common.CommFunAndroid;
import common.utils.JsonConvertor;
import common.utils.StringUtils;

/**
 * Created by yangshiyou on 2016/12/20.
 */

public class Server implements IServer {

    /**
     * 请求地址
     */
    private String mUrl;

    private String methodName;

    private Map<String, Object> mParams;

    RequestParams mRequestParams;

    /**
     * 是否使用缓存
     */
    boolean useCache;

    /**
     * 缓存时间(s)
     */
    int cacheMaxAge;

    /**
     * 自定义回调
     */
    private ApiCallBack mApiCallBack;

    @Override
    public void setUrl(String url) {
        this.mUrl = url;
    }

    @Override
    public void setMethodName(String methodName) {
        this.methodName = methodName;

        this.mUrl = Config.getServer_url() + methodName;
    }


    @Override
    public void setParam(String key, Object value) {
        if (mParams == null)
            mParams = new HashMap<String, Object>();
        mParams.put(key, value);
        //mRequestParams.addQueryStringParameter(key, String.valueOf(value));
    }

    public void setApiCallBack(ApiCallBack apiCallBack) {
        this.mApiCallBack = apiCallBack;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public void setUseCache(boolean useCache, int cacheTime) {
        this.useCache = useCache;
        this.cacheMaxAge = cacheTime * 1000;
    }

    /**
     * 执行请求
     */
    @Override
    public void request() {

        if (!StringUtils.isEmpty(mUrl)) {

            mRequestParams = getRequestParams();

            x.http().post(mRequestParams, cacheCallback);
        }
    }

    private RequestParams getRequestParams() {

        mRequestParams = null;

        if (!StringUtils.isEmpty(mUrl)) {

            mRequestParams = new RequestParams(mUrl);

            if (mParams != null) {

                // 请求接口数据
                List<Map.Entry<String, Object>> mappingList = new ArrayList<Map.Entry<String, Object>>(mParams.entrySet());


                try {
                    if (mappingList != null) {

                        for (Map.Entry<String, Object> entry : mappingList) {
                            String key = entry.getKey();

                            Object value = entry.getValue();
                            mRequestParams.addQueryStringParameter(key, String.valueOf(value));
                        }
                    }
                } catch (Exception e) {

                }
            }

            if (this.useCache) {

                if (cacheMaxAge == 0)
                    cacheMaxAge = Config.getCacheMaxAge();

                mRequestParams.setCacheMaxAge(cacheMaxAge);//缓存时间(秒)
            }

        }

        return mRequestParams;
    }

    /**
     * 框架回调
     */
    Callback.CacheCallback<String> cacheCallback = new Callback.CacheCallback<String>() {

        /**
         *
         */
        private boolean hasError = false;

        private boolean isCache = false;

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

            this.isCache = true;

            this.result = result;
            return useCache; // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
        }

        @Override
        public void onSuccess(String result) {
            // 注意: 如果服务返回304 或 onCache 选择了信任缓存, 这时result为null.
            if (result != null) {
                this.isCache = false;
                this.result = result;
            }
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            hasError = true;
//            Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
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

//            Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFinished() {
            if (!hasError && result != null) {
                // 成功获取数据
                if (mApiCallBack != null) {
                    //mApiCallBack.onSuccess(result);

                    mApiCallBack.onSuccess(isCache, result);
                }
            }
        }
    };

}
