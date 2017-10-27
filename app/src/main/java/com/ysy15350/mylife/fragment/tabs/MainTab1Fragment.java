package com.ysy15350.mylife.fragment.tabs;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.data_binding.DataBindingActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import base.MVPBaseFragment;
import common.CommFunAndroid;
import common.CommFunMessage;
import custom_view.avi_loading.AVLoadingIndicatorView;

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


    /**
     * 本地图片
     */
    @ViewInject(R.id.loading_1)
    private AVLoadingIndicatorView loading_1;


    @Override
    public void initView() {

        setTitle("首页");


    }

    /**
     * 显示键盘
     *
     * @param view
     */
    @Event(value = R.id.btn_showSoftInput)
    private void btn_showSoftInputClick(View view) {
        showMsg("显示键盘");

        CommFunAndroid.showSoftInput(mHolder.getView(R.id.et_url));
    }

    /**
     * 隐藏键盘
     *
     * @param view
     */
    @Event(value = R.id.btn_hideSoftInput)
    private void btn_hideSoftInputClick(View view) {
        showMsg("隐藏键盘");
        CommFunAndroid.hideSoftInput(view);
    }

    /**
     * 是否缓存
     */
    @ViewInject(R.id.cb_cache)
    private CheckBox checkBox;


    /**
     * 确认按钮点击事件
     *
     * @param view
     */
    @Event(value = R.id.btn_ok)
    private void btn_okClick(View view) {

        String et_cache_time_text = getViewText(R.id.et_cache_time);
        int cache_time = CommFunAndroid.toInt32(et_cache_time_text, 0);

        boolean useCache = checkBox.isChecked();

        String url = getViewText(R.id.et_url);

        String param = getViewText(R.id.et_param);

        mPresenter.getData(url, param, cache_time, useCache);


    }

    @Event(value = R.id.btn_data_binding)
    private void btn_data_bindingClick(View view) {
        startActivity(new Intent(getActivity(), DataBindingActivity.class));
    }


    @Override
    public void bindData(String data) {

        CommFunMessage.showCustomMsg(mContext, R.mipmap.icon_success, "成功");

        String time = CommFunAndroid.getDateString("yyyy-MM-dd HH:mm:ss");

        setViewText(R.id.tv_response_time, time);//单个

        mHolder.setText(R.id.tv_result, data);//多个

        //showMsg(data);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        CommFunAndroid.hideSoftInput(mContentView);
    }
}
