package com.ysy15350.mylife.chat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
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


    public void loginOut() {
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                Message message = new Message();
                message.what = 200;

                Bundle data = new Bundle();
                data.putString("msg", "注销成功");

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
        });//登录前先注销登录，因为登录其他账户会提示User is already login
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

                    EMMessageBody msgBody = message.getBody();

                    if(msgBody instanceof  EMTextMessageBody){

                        EMTextMessageBody textMessageBody=(EMTextMessageBody)msgBody;

                    }

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
                    mView.loginOutCallback(type, "成功：" + message);
                    break;
                case 201:

                    mView.loginOutCallback(type, "失败：" + message);
                    break;
                default:
                    break;
            }
        }
    };
}