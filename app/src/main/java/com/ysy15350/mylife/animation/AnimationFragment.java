package com.ysy15350.mylife.animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.chat.LoginActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import base.BaseFragment;

import static com.ysy15350.mylife.R.anim.translate;

/**
 * Created by yangshiyou on 2017/8/15.
 */

@ContentView(R.layout.fragment_animation)
public class AnimationFragment extends BaseFragment {

    @Override
    public void initView() {
        super.initView();
        setFormHead("动画", true);
    }


    /**
     * 需要选择的图片
     */
    @ViewInject(R.id.img_animation)
    private ImageView image;


    Animation loadAnimation;

    /**
     * 缩放动画
     *
     * @param view
     */
    @Event(value = R.id.btn_Scale)
    private void btn_ScaleClick(View view) {

        loadAnimation = AnimationUtils.loadAnimation(mContext, R.anim.scale);
        image.startAnimation(loadAnimation);

    }

    /**
     * 透明动画
     *
     * @param view
     */
    @Event(value = R.id.btn_Alpa)
    private void btn_AlpaClick(View view) {

        loadAnimation = AnimationUtils.loadAnimation(mContext, R.anim.alpha);
        image.startAnimation(loadAnimation);

    }

    /**
     * 旋转动画
     *
     * @param view
     */
    @Event(value = R.id.btn_Rotate)
    private void btn_RotateClick(View view) {

        loadAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
        image.startAnimation(loadAnimation);

    }

    /**
     * 位移动画
     *
     * @param view
     */
    @Event(value = R.id.btn_Translate)
    private void btn_TranslateClick(View view) {

//        <translate
//        android:duration="1000"
//        android:fromXDelta="10"
//        android:fromYDelta="10"
//        android:toXDelta="100"
//        android:toYDelta="100" />

        loadAnimation = AnimationUtils.loadAnimation(mContext, translate);
        image.startAnimation(loadAnimation);

    }

    /**
     * 位移动画
     *
     * @param view
     */
    @Event(value = R.id.btn_Translate_1)
    private void btn_Translate_1Click(View view) {

        TranslateAnimation translate = new TranslateAnimation(-50, 50,
                0, 0);
        translate.setDuration(1000);
        translate.setRepeatCount(Animation.INFINITE);//-1，无限制移动
        translate.setRepeatMode(Animation.REVERSE);//倒序重复REVERSE  正序重复RESTART
        image.startAnimation(translate);

    }


    /**
     * 连续动画：动画配置
     *
     * @param view
     */
    @Event(value = R.id.btn_continue)
    private void btn_continueClick(View view) {
        loadAnimation = AnimationUtils.loadAnimation(mContext,
                R.anim.continue_anim);
        image.startAnimation(loadAnimation);
    }


    /**
     * 设置动画重复次数
     *
     * @param view
     */
    @Event(value = R.id.btn_continue_repeat)
    private void btn_continue_repeatClick(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(100);//设置动画持续时间100毫秒
        alphaAnimation.setRepeatCount(10);//设置动画重复10次

        alphaAnimation.setRepeatMode(Animation.REVERSE);//倒序重复REVERSE  正序重复RESTART
        image.startAnimation(alphaAnimation);
    }


    /**
     * 连续动画
     *
     * @param view
     */
    @Event(value = R.id.btn_continue_listener)
    private void btn_continue_listenerClick(View view) {

        loadAnimation = AnimationUtils.loadAnimation(mContext, translate);
        image.startAnimation(loadAnimation);

        final Animation loadAnimation2 = AnimationUtils.loadAnimation(mContext, R.anim.rotate);


        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showMsg("动画监听，动画结束，开始第二个动画");
                image.startAnimation(loadAnimation2);//动画结束时开始下一个动画
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    /**
     * Activity切换动画
     *
     * @param view
     */
    @Event(value = R.id.btn_change_activity)
    private void btn_change_activityClick(View view) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }


    @Event(value = R.id.btn_frame)
    private void btn_frameClick(View view) {
        image.setImageResource(R.drawable.anim_list);
        AnimationDrawable drawable = (AnimationDrawable)image.getDrawable();
        drawable.start();
    }


}
