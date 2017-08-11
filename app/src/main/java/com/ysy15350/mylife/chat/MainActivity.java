package com.ysy15350.mylife.chat;

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

@ContentView(R.layout.activity_chat)
public class MainActivity extends MVPBaseActivity<MainViewInterface, MainPresenter> implements MainViewInterface {


    @ViewInject(R.id.tv_test)
    private TextView tv_test;

    @ViewInject(R.id.et_userName)
    private EditText et_userName;

    @ViewInject(R.id.et_password)
    private EditText et_password;


    @ViewInject(R.id.et_message)
    private EditText et_message;

    @ViewInject(R.id.et_toUserName)
    private EditText et_toUserName;


    @Override
    protected MainPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainPresenter(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 1. 方法必须私有限定,
     * 2. 方法参数形式必须和type对应的Listener接口一致.
     * 3. 注解参数value支持数组: value={id1, id2, id3}
     * 4. 其它参数说明见{@link org.xutils.event.annotation.Event}类的说明.
     **/
    @Event(value = R.id.btn_test,
            type = View.OnClickListener.class/*可选参数, 默认是View.OnClickListener.class*/)
    private void onTestBaidu1Click(View view) {


        //mPresenter.signin();

    }

    @Event(value = R.id.btn_signup)
    private void btn_signupClick(View view) {
        //

        String userName = et_userName.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        mPresenter.signup(userName, password);

    }

    @Event(value = R.id.btn_signin)
    private void btn_signinClick(View view) {
        //

        String userName = et_userName.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        mPresenter.signin(userName, password);

    }

    @Event(value = R.id.btn_send)
    private void btn_sendClick(View view) {
        //

        String message = et_message.getText().toString().trim();

        String userName = et_toUserName.getText().toString().trim();

        mPresenter.sendMessage(message, userName);

    }

    @Event(value = R.id.btn_test1)
    private void btn_testClick(View view) {
        //

       Toast.makeText(this,"呵呵",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setText(String text) {
        tv_test.setText(text);
    }

    @Override
    public void test() {

        Toast.makeText(this, "test1", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }



}
