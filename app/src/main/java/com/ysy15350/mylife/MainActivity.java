package com.ysy15350.mylife;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import common.CommFunMessage;
import common.ExitApplication;
import common.model.ScreenInfo;

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

        //CommFunAndroid.fullScreenStatuBar(this);

        ExitApplication.getInstance().addActivity(this);// 添加当前Activity


    }

    /**
     * 返回到主界面，指定选项卡
     *
     * @param index 选项卡
     */
    public void backFragment(int index) {
        setSelect(index);
    }

    /**
     * 点击返回按钮间隔时间
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            ScreenInfo screenInfo = CommFunAndroid.GetInstance(this).getScreenInfo();
            CommFunMessage.ShowMsgBox(this,
                    String.format("%f,%d,%d", screenInfo.getDensity(), screenInfo.getWidth(), screenInfo.getHeight()));
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                CommFunMessage.ShowMsgBox(MainActivity.this, "再按一次退出程序");

                exitTime = System.currentTimeMillis();
            } else {
                // stopService(new Intent(getApplicationContext(),
                // TestService.class));// stop

                // CommFunAndroid.setSharedPreferences("JSESSIONID", "");

                ExitApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
