package com.ysy15350.mylife.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy15350.mylife.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.MVPBaseActivity;
import common.CommFunAndroid;

@ContentView(R.layout.activity_chat)
public class MainActivity extends MVPBaseActivity<MainViewInterface, MainPresenter> implements MainViewInterface {


    /**
     * 聊天记录
     */
    @ViewInject(R.id.lv_chat)
    private ListView lv_chat;

    ArrayAdapter<String> mAdapter;

    SimpleAdapter simpleAdapter;


    @Override
    protected MainPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainPresenter(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initData() {
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        lv_chat.setAdapter(mAdapter);
    }

    @Event(value = R.id.btn_send)
    private void btn_sendClick(View view) {

        String message = mHolder.getViewText(R.id.et_message);

        String userName = mHolder.getViewText(R.id.et_toUserName);

        if (CommFunAndroid.isNullOrEmpty(message)) {
            showMsg("请输入消息内容");
            return;
        }
        if (CommFunAndroid.isNullOrEmpty(userName)) {
            showMsg("请输入对方用户名");
            return;
        }

        mPresenter.sendMessage(message, userName);

    }


    @Override
    public void setText(String text) {


        HashMap<String, Object> map = new HashMap<>();
        map.put("name", text);
        String time = CommFunAndroid.getDateString("yyyy-MM-dd HH:mm:ss");
        map.put("description", time);
        map.put("icon", R.mipmap.icon_time);
        mapList.add(0, map);

        list.add(0, text);
        bindMsg();
        mHolder.setText(R.id.tv_msg, text);
    }


    List<String> list = new ArrayList<>();

    List<Map<String, Object>> mapList = new ArrayList<>();

    private void bindMsg() {

//        SimpleAdapter simplead = new SimpleAdapter(this, listems,
//                R.layout.simple_item, new String[] { "name", "head", "desc" },
//                new int[] {R.id.name,R.id.head,R.id.desc});

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        //每个数据项对应一个Map，from表示的是Map中key的数组
        String[] from = {"name", "description", "icon"};

        //数据项Map中的每个key都在layout中有对应的View，
        //to表示数据项对应的View的ID数组
        int[] to = {R.id.name, R.id.description, R.id.icon};


        SimpleAdapter adapter = new SimpleAdapter(this, mapList, R.layout.listview_item_simple, from, to);

        lv_chat.setAdapter(adapter);

    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.loginOut();
    }

    @Override
    public void loginOutCallback(int ret, String result) {
        showMsg(result);
    }
}
