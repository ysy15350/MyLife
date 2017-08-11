package com.ysy15350.mylife.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.test.tabs.MainTab1Fragment;
import com.ysy15350.mylife.test.tabs.MainTab2Fragment;
import com.ysy15350.mylife.test.tabs.MainTab3Fragment;
import com.ysy15350.mylife.test.tabs.MainTab4Fragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import base.BaseActivity;

/**
 * Created by yangshiyou on 2016/11/29.
 */
@ContentView(R.layout.activity_main_test1)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.container)
    private ViewPager mViewPager;


    @ViewInject(R.id.form_bottom)
    private View form_bottom;


    @ViewInject(R.id.img_tab1)
    private View img_tab1;
    @ViewInject(R.id.img_tab2)
    private View img_tab2;
    @ViewInject(R.id.img_tab3)
    private View img_tab3;
    @ViewInject(R.id.img_tab4)
    private View img_tab4;

    /**
     * 显示指定选项卡
     */
    public static int tab_position = 0;


    private SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //tabLayout.setupWithViewPager(mViewPager);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTab(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //mViewPager.setCurrentItem(tab_position);
    }

    @Event(value = R.id.ll_tab1)
    private void ll_tab1Click(View view) {
        setSelect(0);
    }

    @Event(value = R.id.ll_tab2)
    private void ll_tab2Click(View view) {
        setSelect(1);
    }

    @Event(value = R.id.ll_tab3)
    private void ll_tab3Click(View view) {
        setSelect(2);
    }

    @Event(value = R.id.ll_tab4)
    private void ll_tab4Click(View view) {
        setSelect(3);
    }

    /**
     * 点击事件1:设置tab(改变图片和字体)和2:切换fragment
     *
     * @param position
     */
    private void setSelect(int position) {
        mViewPager.setCurrentItem(position);
    }

    /**
     * 滑动viewpager时设置tab(改变图片和字体)
     *
     * @param position
     */
    private void setTab(int position) {
        tab_position = position;// 记录已打开选项卡位置，当跳转到登录界面或者其他界面，显示此界面
        switch (position) {
            case 0:
                setViewImage(img_tab1);
                break;
            case 1:
                setViewImage(img_tab2);
                break;
            case 2:
                setViewImage(img_tab3);
                break;
            case 3:
                setViewImage(img_tab4);
                break;
            default:
                break;
        }
    }

    /**
     * 记录当前view（图片切换）
     */
    private View currentView;
    /**
     * 记录当前textview(切换字体颜色)
     */
    private View currentTeView;

    /**
     * 切换图片（background 设置背景：xml->selector）
     *
     * @param v
     */
    private void setViewImage(View v) {
        if (currentView != null && currentView.getId() != v.getId()) {
            currentView.setEnabled(true);
            // ((View) currentView.getParent())
            // .setBackgroundColor(v.getContext().getResources().getColor(R.color.formbottom_selected_bgColor));
        }
        v.setEnabled(false);
        // ((View) v.getParent())
        // .setBackgroundColor(v.getContext().getResources().getColor(R.color.formbottom_unselected_bgColor));
        currentView = v;
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new MainTab1Fragment();
                }
                case 1: {
                    return new MainTab2Fragment();
                }
                case 2: {
                    return new MainTab3Fragment();
                }
                case 3: {
                    return new MainTab4Fragment();
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

}
