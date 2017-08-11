package com.ysy15350.mylife.test.tabs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ysy15350.mylife.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import org.xutils.view.annotation.ContentView;

import base.MVPBaseFragment;
import model.UserInfo;

@ContentView(R.layout.activity_main_tab2)
public class MainTab2Fragment extends MVPBaseFragment<MainTab2ViewInterface, MainTab2Presenter>
        implements MainTab1ViewInterface {


    public MainTab2Fragment() {
    }

    @Override
    public MainTab2Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab2Presenter(getActivity());
    }

    private View mBaseView;


}

