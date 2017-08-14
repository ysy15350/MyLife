package com.ysy15350.mylife.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy15350.mylife.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import base.MVPBaseActivity;
import common.CommFunAndroid;

@ContentView(R.layout.activity_login)
public class LoginActivity extends MVPBaseActivity<LoginViewInterface, LoginPresenter> implements LoginViewInterface {


    @Override
    protected LoginPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new LoginPresenter(LoginActivity.this);


    }



    @Event(value = R.id.btn_login)
    private void btn_loginClick(View view) {

        String userName = mHolder.getViewText(R.id.et_userName);
        String password = mHolder.getViewText(R.id.et_password);

        if (CommFunAndroid.isNullOrEmpty(userName)) {
            showMsg("请输入用户名");
            return;
        }
        if (CommFunAndroid.isNullOrEmpty(password)) {
            showMsg("请输入密码");
            return;
        }

        mPresenter.userLogin(userName, password);

    }

    @Override
    public void loginCallback(int ret, String result) {

        if (ret == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        showMsg(result);
    }

}
