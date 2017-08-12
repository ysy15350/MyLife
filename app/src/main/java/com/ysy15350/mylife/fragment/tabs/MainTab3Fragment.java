package com.ysy15350.mylife.fragment.tabs;

import com.ysy15350.mylife.R;

import android.view.View;

import org.xutils.view.annotation.ContentView;

import base.MVPBaseFragment;

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

