package com.ysy15350.mylife.chat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import api.ApiCallBack;
import api.IUser;
import api.impl.UserApi;
import api.model.Response;
import base.BasePresenter;
import common.utils.JsonConvertor;
import common.utils.StringUtils;

/**
 * Created by yangshiyou on 2016/11/23.
 */

public class MainPresenter extends BasePresenter<MainViewInterface> implements EMMessageListener {

    public MainPresenter() {
    }

    public MainPresenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    private int page = 1;

    private int pageSize = 10;

    Type dataType;

    public void test() {


        Type genType = this.getClass().getGenericSuperclass();

        try {
            if (genType != null) {
                Type[] types = ((ParameterizedType) genType).getActualTypeArguments();

                if (types != null && types.length > 0)
                    dataType = types[0];
            }

        } catch (Exception e) {

        }

        if (dataType != null) {

            String typeString = dataType.toString();

            if ("class java.lang.Object".equals(typeString) || typeString.contains("Response")) {
                dataType = null;// 默认Response(class
                // framework_movie_friend.Response)
            }
        }

        IUser userApi = new UserApi();


        userApi.userLogin("admin", "123456", new ApiCallBack() {


            @Override
            public void onSuccess(boolean isCache, String data) {

                if (!StringUtils.isEmpty(data)) {


                    Response response = JsonConvertor.fromJson(data, Response.class);

                    if (response != null) {

                        mView.loginSuccess(isCache + "," + response.getResult());
                    }
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });


    }

    private static String TAG = "mylife";

    /**
     * 注册
     */
    public void signup(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username, password);
                    Log.e(TAG, "注册成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();

                    Log.e(TAG, "注册失败" + e.getErrorCode() + "," + e.getMessage());
                }
            }
        }).start();

    }

    public void signin(String username, String password) {
        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {

                Log.e(TAG, "登录成功");

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    public void sendMessage(String msg, String toUserName) {
        EMMessage message = EMMessage.createTxtSendMessage(msg, toUserName);
        message.setChatType(EMMessage.ChatType.Chat);
        EMClient.getInstance().chatManager().sendMessage(message);
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }


    @Override
    public void onMessageReceived(List<EMMessage> list) {
        for (final EMMessage message :
                list) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mView.setText(((EMTextMessageBody) message.getBody()).getMessage());
                }
            });
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }
}