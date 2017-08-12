package com.ysy15350.mylife.chat;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import base.BasePresenter;


/**
 * Created by yangshiyou on 2016/11/23.
 */

public class LoginPresenter extends BasePresenter<LoginViewInterface> {

    public LoginPresenter() {
    }

    public LoginPresenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

    }


    /**
     * 用户登录
     *
     * @param username
     * @param password
     */
    public void userLogin(String username, String password) {


        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                Message message = new Message();
                message.what = 200;

                Bundle data = new Bundle();
                data.putInt("type", 1);
                data.putString("msg", "登录成功");

                message.setData(data);
                mHandler.sendMessage(message);

            }

            @Override
            public void onError(int i, String s) {
                Message message = new Message();
                message.what = 201;

                Bundle data = new Bundle();
                data.putString("msg", i + s);

                message.setData(data);
                mHandler.sendMessage(message);

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    /**
     * 注册
     */
    public void createAccount(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username, password);
                } catch (HyphenateException e) {
                    e.printStackTrace();

                    String msg = "注册失败" + e.getErrorCode() + "," + e.getMessage();

                    Message message = new Message();
                    message.what = 201;

                    Bundle data = new Bundle();
                    data.putString("msg", msg);

                    message.setData(data);
                    mHandler.sendMessage(message);

                }
            }
        }).start();

    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg == null)
                return;

            Bundle data = msg.getData();

            int type = 0;
            String message = "";

            if (data != null) {
                type = data.getInt("type");
                message = data.getString("msg");
            }

            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    mView.loginCallback(type, "成功：" + message);
                    break;
                case 201:

                    mView.loginCallback(type, "失败：" + message);
                    break;
                default:
                    break;
            }
        }
    };


}