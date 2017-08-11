package com.ysy15350.mylife.test.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysy15350.mylife.R;

import org.xutils.view.annotation.ContentView;

import base.MVPBaseFragment;
import common.CommFunAndroid;
import common.model.ScreenInfo;

@ContentView(R.layout.activity_main_tab1)
public class MainTab1Fragment extends MVPBaseFragment<MainTab1ViewInterface, MainTab1Presenter>
        implements MainTab1ViewInterface {


    public MainTab1Fragment() {
    }

    @Override
    public MainTab1Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab1Presenter(getActivity());
    }

}
