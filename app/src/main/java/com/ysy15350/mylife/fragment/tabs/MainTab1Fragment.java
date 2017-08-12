package com.ysy15350.mylife.fragment.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy15350.mylife.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.MVPBaseFragment;
import common.CommFunAndroid;
import custom_view.scroll_view.PullDownElasticImp;
import custom_view.scroll_view.PullDownScrollView;
import custom_view.scroll_view.PullDownScrollView.RefreshListener;

@ContentView(R.layout.activity_main_tab1)
public class MainTab1Fragment extends MVPBaseFragment<MainTab1ViewInterface, MainTab1Presenter>
        implements MainTab1ViewInterface, RefreshListener {


    public MainTab1Fragment() {
    }

    @Override
    public MainTab1Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab1Presenter(getActivity());
    }


    @ViewInject(R.id.scrollview)
    private PullDownScrollView mPullDownScrollView;

    String date = "";

    @Override
    public void initView() {

        setTitle("首页");

        showMsg((mPullDownScrollView == null) + "");

        mPullDownScrollView.setRefreshListener(this);
        mPullDownScrollView.setPullDownElastic(new PullDownElasticImp(getActivity()));

        updateScrollverHeader();
    }

    @Override
    public void onRefresh(PullDownScrollView view) {

        updateScrollverHeader();
    }

    private void updateScrollverHeader() {

        date = CommFunAndroid.getDateString("yyyy-MM-dd HH:mm:ss");

        showMsg(date);

        mPullDownScrollView.finishRefresh("上次刷新时间:" + date);
    }


    /**
     * 接口地址
     */
    @ViewInject(R.id.et_url)
    private EditText et_url;

    /**
     * 接口参数
     */
    @ViewInject(R.id.et_param)
    private EditText et_param;

    /**
     * 缓存事件(s)
     */
    @ViewInject(R.id.et_cache_time)
    private EditText et_cache_time;


    /**
     * 是否缓存
     */
    @ViewInject(R.id.cb_cache)
    private CheckBox checkBox;


    /**
     * 结果
     */
    @ViewInject(R.id.tv_result)
    private TextView tv_result;

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

    @Override
    public void bindData(String data) {
        String time = CommFunAndroid.getDateString("yyyy-MM-dd HH:mm:ss");

        setViewText(R.id.tv_response_time, time);//单个

        mHolder.setText(R.id.tv_result, data);//多个

        showMsg(data);
    }


}
