package com.ysy15350.mylife.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.fragment.tabs.MainTab1Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab2Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab3Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab4Fragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashSet;

import base.BaseFragmentActivity;
import common.CommFunAndroid;

/**
 * Created by yangshiyou on 2017/8/12.
 */

@ContentView(R.layout.activity_fragment)
public class FragmentMainActivity extends BaseFragmentActivity {

    // private GestureDetectorCompat mDetector;

    /**
     * 当前Activity是否为活跃状态
     */
    public static boolean isForeground = false;

    /**
     * 广播Action
     */
    public static final String MESSAGE_RECEIVED_ACTION = "com.ysy15350.mylife.MESSAGE_RECEIVED_ACTION";

    private static final String TAG = "TestMainActivity";

    /**
     * 广播接收
     */
    private MessageReceiver mMessageReceiver;


    // @ViewInject(id = R.id.form_head)
    // private View form_head;


    @ViewInject(R.id.form_bottom)
    private View form_bottom;


    /**
     * 消息圆点
     */
    @ViewInject(R.id.img_tab3_dot)
    private View img_tab3_dot;


    GestureDetectorCompat mDetector;


    @Override
    public void initData() {
        super.initData();

        registerMessageReceiver();

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    protected void onResume() {
        super.onResume();

        checkMsgCount();

        isForeground = true;

        String token = CommFunAndroid.getSharedPreferences("token");
        if (CommFunAndroid.isNullOrEmpty(token)) {
            if (tab_position >= 2)
                tab_position = 0;
            setSelect(tab_position);
            CommFunAndroid.setSharedPreferences("JSESSIONID", "");
        }


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        isForeground = false;

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    public void registerMessageReceiver() {
        mMessageReceiver = new FragmentMainActivity.MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                // String messge = intent.getStringExtra(KEY_MESSAGE);
                // String extras = intent.getStringExtra(KEY_EXTRAS);
                // StringBuilder showMsg = new StringBuilder();
                // showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                // if (!SysFunction.isEmpty(extras)) {
                // showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                // }
                // setCostomMsg(showMsg.toString());

                // msgIntent.putExtra("name", "mylike");
                // msgIntent.putExtra("operation", "add");

                String name = intent.getStringExtra("name");
                String operation = intent.getStringExtra("operation");

                String msg = intent.getStringExtra("msg");

                if (!CommFunAndroid.isNullOrEmpty(msg)) {

                    // ll_message.setVisibility(View.VISIBLE);
                    //
                    // tv_msg.setText(msg);
                }

                String activityName = intent.getStringExtra("activityName");

                if (!CommFunAndroid.isNullOrEmpty(activityName)) {
                    // tv_msg.setTag(activityName);
                }

                if (!CommFunAndroid.isNullOrEmpty(msg)) {

                }

                changeHashSet(name, operation);

                checkMsgCount();

            }
        }
    }

    /**
     * 有新消息的模块名称
     */
    private static HashSet<String> hashSet = new HashSet<String>();

    public static void setHashSet(String name) {
        if (hashSet == null)
            hashSet = new HashSet<String>();
        if (!CommFunAndroid.isNullOrEmpty(name)) {
            hashSet.add(name);
        }
    }

    public static void removieHashSet(String name) {
        if (hashSet != null && !CommFunAndroid.isNullOrEmpty(name)) {
            if (hashSet.contains(name))
                hashSet.remove(name);
        }
    }

    public static HashSet<String> getHashSet() {
        return hashSet;
    }

    private void changeHashSet(String name, String operation) {
        if (operation.equals("add")) {
            hashSet.add(name);
        } else if (operation.equals("delete")) {
            hashSet.remove(name);
        }
    }

    /**
     * 检查消息数量
     */
    private void checkMsgCount() {
        if (hashSet == null || hashSet.size() == 0) {
            img_tab3_dot.setVisibility(View.GONE);
        } else
            img_tab3_dot.setVisibility(View.VISIBLE);
    }

    /**
     * 手势
     *
     * @author yangshiyou
     */
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // Log.d(DEBUG_TAG, "onFling: " + event1.toString() +
            // event2.toString());
            if ((e1.getX() - e2.getX() > 120) && Math.abs(velocityX) > 200) {
                tab_position = tab_position + 1 > 3 ? tab_position : tab_position + 1;
                setSelect(tab_position);// 设置默认显示fragment
                return true;
            } else if ((e2.getX() - e1.getX() > 120) && Math.abs(velocityX) > 200) {
                tab_position = tab_position - 1 < 0 ? tab_position : tab_position - 1;
                setSelect(tab_position);// 设置默认显示fragment
                return true;
            }
            return false;
        }
    }

}