package com.ysy15350.mylife.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.test.tabs.MainTab1Fragment;
import com.ysy15350.mylife.test.tabs.MainTab2Fragment;
import com.ysy15350.mylife.test.tabs.MainTab3Fragment;
import com.ysy15350.mylife.test.tabs.MainTab4Fragment;

import common.CommFunAndroid;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashSet;

/**
 * Created by yangshiyou on 2016/11/26.
 */

@ContentView(R.layout.activity_main_test)
public class TestMainActivity extends FragmentActivity {

    // private GestureDetectorCompat mDetector;

    /**
     * 当前Activity是否为活跃状态
     */
    public static boolean isForeground = false;

    public static final String MESSAGE_RECEIVED_ACTION = "com.ysy15350.mylife.MESSAGE_RECEIVED_ACTION";

    private static final String TAG = "TestMainActivity";

    /**
     * 广播接收
     */
    private MessageReceiver mMessageReceiver;

    Fragment fragmentTab1;
    Fragment fragmentTab2;
    Fragment fragmentTab3;
    Fragment fragmentTab4;

    // @ViewInject(id = R.id.form_head)
    // private View form_head;


    @ViewInject(R.id.form_bottom)
    private View form_bottom;

    @ViewInject(R.id.ll_tab1)
    private View ll_tab1;

    @ViewInject(R.id.ll_tab2)
    private View ll_tab2;

    @ViewInject(R.id.ll_tab3)
    private View ll_tab3;

    @ViewInject(R.id.ll_tab4)
    private View ll_tab4;

    @ViewInject(R.id.img_tab1)
    private View img_tab1;
    @ViewInject(R.id.img_tab2)
    private View img_tab2;
    @ViewInject(R.id.img_tab3)
    private View img_tab3;
    @ViewInject(R.id.img_tab4)
    private View img_tab4;

    /**
     * 消息圆点
     */
    @ViewInject(R.id.img_tab3_dot)
    private View img_tab3_dot;

    /**
     * 显示指定选项卡
     */
    public static int tab_position = 0;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);

        x.view().inject(this);

        View contentView = getWindow().getDecorView();

        setSelect(tab_position);// 设置默认显示fragment

        initView();

        registerMessageReceiver();

        // mDetector = new GestureDetectorCompat(this, new MyGestureListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        // this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void initView() {


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

        // Lotuseed.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        isForeground = false;
        // Lotuseed.onPause(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Event(value = R.id.ll_tab1)
    private void ll_tab1Click(View view) {
        setSelect(0);

    }

    @Event(value = R.id.ll_tab2)
    private void ll_tab2Click(View view) {
        setSelect(1);
    }

    @Event(value = R.id.ll_tab3)
    private void ll_tab3Click(View view) {
        // String token = CommFunAndroid.getSharedPreferences("token");
        // if (CommFunAndroid.isNullOrEmpty(token)) {
        //
        // Intent intent = new Intent(this, LoginActivity.class);
        //
        // startActivity(intent);
        //
        // return;
        // }
        setSelect(2);
    }

    @Event(value = R.id.ll_tab4)
    private void ll_tab4Click(View view) {
        // String token = CommFunAndroid.getSharedPreferences("token");
        // if (CommFunAndroid.isNullOrEmpty(token)) {
        //
        // Intent intent = new Intent(this, LoginActivity.class);
        //
        // startActivity(intent);
        //
        // return;
        // }
        setSelect(3);
    }

    /**
     * 点击事件1:设置tab(改变图片和字体)和2:切换fragment
     *
     * @param position
     */
    private void setSelect(int position) {

        Log.i(TAG, "setSelect: "+position);

        setTab(position);

        tab_position = position;// 记录已打开选项卡位置，当跳转到登录界面或者其他界面，显示此界面

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);

        switch (position) {
            case 0:
                if (fragmentTab1 == null) {
                    fragmentTab1 = new MainTab1Fragment();
                    transaction.add(R.id.id_content, fragmentTab1);
                } else {
                    transaction.show(fragmentTab1);
                }
                fragmentTab1.onResume();
                break;
            case 1:
                if (fragmentTab2 == null) {
                    fragmentTab2 = new MainTab2Fragment();
                    transaction.add(R.id.id_content, fragmentTab2);
                } else {
                    transaction.show(fragmentTab2);
                }
                break;
            case 2:
                if (fragmentTab3 == null) {
                    fragmentTab3 = new MainTab3Fragment();
                    transaction.add(R.id.id_content, fragmentTab3);
                } else {
                    transaction.show(fragmentTab3);
                }
                fragmentTab3.onResume();
                break;
            case 3:
                if (fragmentTab4 == null) {
                    fragmentTab4 = new MainTab4Fragment();
                    transaction.add(R.id.id_content, fragmentTab4);
                } else {
                    transaction.show(fragmentTab4);
                }
                fragmentTab4.onResume();
                break;

            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentTab1 != null) {
            transaction.hide(fragmentTab1);
        }
        if (fragmentTab2 != null) {
            transaction.hide(fragmentTab2);
        }
        if (fragmentTab3 != null) {
            transaction.hide(fragmentTab3);
        }
        if (fragmentTab4 != null) {
            transaction.hide(fragmentTab4);
        }
    }

    /**
     * 滑动viewpager时设置tab(改变图片和字体)
     *
     * @param position
     */
    private void setTab(int position) {
        switch (position) {
            case 0:
                setViewImage(img_tab1);
                break;
            case 1:
                setViewImage(img_tab2);
                break;
            case 2:
                setViewImage(img_tab3);
                break;
            case 3:
                setViewImage(img_tab4);
                break;
            default:
                break;
        }
    }

    // ----------------------------------------
    // 切换图片方式二：xml配置 drawable selector

    /**
     * 记录当前view（图片切换）
     */
    private View currentView;
    /**
     * 记录当前textview(切换字体颜色)
     */
    private View currentTeView;

    /**
     * 切换图片（background 设置背景：xml->selector）
     *
     * @param v
     */
    private void setViewImage(View v) {
        if (currentView != null && currentView.getId() != v.getId()) {
            currentView.setEnabled(true);
            // ((View) currentView.getParent())
            // .setBackgroundColor(v.getContext().getResources().getColor(R.color.formbottom_selected_bgColor));
        }
        v.setEnabled(false);
        // ((View) v.getParent())
        // .setBackgroundColor(v.getContext().getResources().getColor(R.color.formbottom_unselected_bgColor));
        currentView = v;
    }


    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
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

    // /**
    // * 手势
    // *
    // * @author yangshiyou
    // *
    // */
    // class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    // private static final String DEBUG_TAG = "Gestures";
    //
    // @Override
    // public boolean onDown(MotionEvent event) {
    // Log.d(DEBUG_TAG, "onDown: " + event.toString());
    // return true;
    // }
    //
    // @Override
    // public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
    // float velocityY) {
    // // Log.d(DEBUG_TAG, "onFling: " + event1.toString() +
    // // event2.toString());
    // if ((e1.getX() - e2.getX() > 120) && Math.abs(velocityX) > 200) {
    // tab_position = tab_position + 1 > 3 ? tab_position : tab_position + 1;
    // setSelect(tab_position);// 设置默认显示fragment
    // return true;
    // } else if ((e2.getX() - e1.getX() > 120) && Math.abs(velocityX) > 200) {
    // tab_position = tab_position - 1 < 0 ? tab_position : tab_position - 1;
    // setSelect(tab_position);// 设置默认显示fragment
    // return true;
    // }
    // return false;
    // }
    // }

}