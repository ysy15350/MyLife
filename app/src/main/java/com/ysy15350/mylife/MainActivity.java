package com.ysy15350.mylife;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ysy15350.mylife.fragment.FragmentMainActivity;
import com.ysy15350.mylife.fragment.FragmentPagerActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import base.BaseActivity;
import base.BaseFragmentActivity;
import base.MVPBaseActivity;
import common.CommFunAndroid;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity {

    @Override
    public void initView() {


        //int height = CommFunAndroid.getStatusBarHeight(this);//获取状态栏高度

        //代码方式填满状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }

        CommFunAndroid.fullScreenStatuBar(this);


    }


}
