package com.ysy15350.mylife;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import base.MVPBaseActivity;

@ContentView(R.layout.activity_main)
public class MainActivity extends MVPBaseActivity<MainViewInterface, MainPresenter> implements MainViewInterface {


    @Override
    protected MainPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainPresenter(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("main", "onCreate");

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

        Toast.makeText(MainActivity.this, "hehe", Toast.LENGTH_SHORT).show();
        //mPresenter.signin();

    }

}
