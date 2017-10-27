package com.ysy15350.mylife.data_binding;


import android.content.Context;

import base.BasePresenter;
import model.UserInfo;


/**
 * Created by yangshiyou on 2016/11/23.
 */

public class DataBindingPresenter extends BasePresenter<DataBindingViewInterface> {

    public DataBindingPresenter() {
    }

    public DataBindingPresenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

    }


    /**
     * 用户登录
     *
     * @param username
     * @param password
     */
    public void getUser(String username, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);

        mView.bindUserInfo(userInfo);

    }


}