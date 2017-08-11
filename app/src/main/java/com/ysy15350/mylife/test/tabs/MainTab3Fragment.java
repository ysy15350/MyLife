package com.ysy15350.mylife.test.tabs;

import com.ysy15350.mylife.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;

import base.MVPBaseFragment;
import common.CommFunAndroid;

@ContentView(R.layout.activity_main_tab3)
public class MainTab3Fragment extends MVPBaseFragment<MainTab3ViewInterface, MainTab3Presenter>
        implements MainTab3ViewInterface {


    public MainTab3Fragment() {
    }

    @Override
    public MainTab3Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab3Presenter(getActivity());
    }

    private View mBaseView;

}

