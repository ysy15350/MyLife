package com.ysy15350.mylife.fragment.tabs;

import com.ysy15350.mylife.R;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;

import java.util.List;

import base.MVPBaseFragment;
import base.MVPBaseListViewFragment;

@ContentView(R.layout.activity_main_tab3)
public class MainTab3Fragment extends MVPBaseListViewFragment<MainTab3ViewInterface, MainTab3Presenter>
        implements MainTab3ViewInterface {


    public MainTab3Fragment() {
    }

    @Override
    public MainTab3Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab3Presenter(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        initData(page, pageSize);

    }

    @Override
    public void initData(int page, int pageSize) {
        mPresenter.getData();
        showMsg("initData:page=" + page + ",pageSize=" + pageSize);
    }

    @Override
    public void bindData(List<String> list) {


        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        bindListView(mAdapter);

        if (list != null && list.size() > 0) {
            page++;
        }
    }
}

