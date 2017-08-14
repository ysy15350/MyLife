package com.ysy15350.mylife.fragment.tabs;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import base.BasePresenter;

public class MainTab3Presenter extends BasePresenter<MainTab3ViewInterface> {

    public MainTab3Presenter() {
    }

    public MainTab3Presenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void getData() {
        List<String> list = new ArrayList<String>();
        list.add("test");
        mView.bindData(list);
    }


}
