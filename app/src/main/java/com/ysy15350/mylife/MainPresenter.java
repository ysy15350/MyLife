package com.ysy15350.mylife;


import android.content.Context;
import base.BasePresenter;

/**
 * Created by yangshiyou on 2016/11/23.
 */

public class MainPresenter extends BasePresenter<MainViewInterface> {

    public MainPresenter() {
    }

    public MainPresenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub


    }

    private int page = 1;

    private int pageSize = 10;

    private static String TAG = "mylife";




}