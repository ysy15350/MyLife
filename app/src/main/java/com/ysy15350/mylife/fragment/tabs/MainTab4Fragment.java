package com.ysy15350.mylife.fragment.tabs;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.chat.LoginActivity;
import com.ysy15350.mylife.fragment.FragmentMainActivity;
import com.ysy15350.mylife.fragment.FragmentPagerActivity;
import com.ysy15350.mylife.recyclerview.RecyclerviewActivity;
import com.ysy15350.mylife.recyclerview_1.Recycler3Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import base.MVPBaseFragment;
import common.CommFunAndroid;

@ContentView(R.layout.activity_main_tab4)
public class MainTab4Fragment extends MVPBaseFragment<MainTab4ViewInterface, MainTab4Presenter>
        implements MainTab4ViewInterface {


    public MainTab4Fragment() {
    }

    @Override
    public MainTab4Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab4Presenter(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(value = R.id.img_user)
    private void img_userClick(View view) {


    }

    @Event(value = R.id.ll_menu1)
    private void ll_menu1Click(View view) {
        Intent intent = new Intent(getActivity(), FragmentPagerActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu2)
    private void ll_menu2Click(View view) {
        Intent intent = new Intent(getActivity(), FragmentMainActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu3)
    private void ll_menu3Click(View view) {
        Intent intent = new Intent(getActivity(), RecyclerviewActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu4)
    private void ll_menu4Click(View view) {
        Intent intent = new Intent(getActivity(), Recycler3Activity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu5)
    private void ll_menu5Click(View view) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }


}

