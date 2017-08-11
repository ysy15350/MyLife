package com.ysy15350.mylife.test.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ysy15350.mylife.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import org.xutils.view.annotation.ContentView;

import base.MVPBaseFragment;
import common.CommFunAndroid;
import common.model.ScreenInfo;
import model.Active;
import model.UserInfo;

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

    private View mBaseView;


}

