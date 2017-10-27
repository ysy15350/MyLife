package com.ysy15350.mylife.fragment.tabs;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.ysy15350.mylife.MainActivity;
import com.ysy15350.mylife.R;
import com.ysy15350.mylife.animation.AnimationFragment;
import com.ysy15350.mylife.chat.LoginActivity;
import com.ysy15350.mylife.fragment.FragmentMainActivity;
import com.ysy15350.mylife.fragment.FragmentPagerActivity;
import com.ysy15350.mylife.recyclerview.RecyclerviewActivity;
import com.ysy15350.mylife.recyclerview_1.Recycler3Activity;
import com.ysy15350.mylife.video.VideoActivity;
import com.ysy15350.mylife.video.VideoFragment;
import com.ysy15350.mylife.voice.VoiceFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import base.MVPBaseFragment;
import common.CommFunMessage;
import custom_view.pop.PopShare;

@ContentView(R.layout.activity_main_tab4)
public class MainTab4Fragment extends MVPBaseFragment<MainTab4ViewInterface, MainTab4Presenter>
        implements MainTab4ViewInterface {


    @Override
    public MainTab4Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab4Presenter(getActivity());
    }


    @Override
    public void initView() {
        super.initView();
        //设置布局动画
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mContentView.setLayoutAnimation(lac);
        mContentView.startLayoutAnimation();

    }

    /**
     * 视频
     */
    private VideoFragment videoFragment;

    @Event(value = R.id.img_user)
    private void img_userClick(View view) {

        Intent intent = new Intent(mContext, VideoActivity.class);
        startActivity(intent);

//        if (videoFragment == null)
//            videoFragment = new VideoFragment();
//
//
//        MainActivity mainActivity = (MainActivity) getActivity();
//        mainActivity.showFragment(videoFragment, true);
    }

    @Event(value = R.id.ll_menu1)
    private void ll_menu1Click(View view) {
        Intent intent = new Intent(getActivity(), FragmentPagerActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu2)
    private void ll_menu2Click(View view) {
        Intent intent = new Intent(getActivity(), FragmentMainActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu3)
    private void ll_menu3Click(View view) {
        Intent intent = new Intent(getActivity(), RecyclerviewActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu4)
    private void ll_menu4Click(View view) {
        Intent intent = new Intent(getActivity(), Recycler3Activity.class);
        startActivity(intent);
    }

    @Event(value = R.id.ll_menu5)
    private void ll_menu5Click(View view) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    VoiceFragment voiceFragment;

    /**
     * 动画
     */
    private AnimationFragment animationFragment;

    @Event(value = R.id.ll_menu6)
    private void ll_menu6Click(View view) {
        if (animationFragment == null)
            animationFragment = new AnimationFragment();


        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.showFragment(animationFragment, true);
    }

    @Event(value = R.id.btn_share)
    private void btn_shareClick(View view) {
        PopShare popShare = new PopShare(getActivity());
        popShare.showPop(mContentView, Gravity.BOTTOM);
    }

    @Event(value = R.id.btn_logout)
    private void btn_logoutClick(View view) {

        CommFunMessage.showCustomConfirm(mContext, "确认注销？", new CommFunMessage.CustomConfirmListenser() {
            @Override
            public void okClick() {
                showMsg("确认");
            }

            @Override
            public void cancelClick() {
                showMsg("取消");
            }
        });
    }


    //


}

