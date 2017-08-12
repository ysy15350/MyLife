package base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.fragment.tabs.MainTab1Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab2Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab3Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab4Fragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.adapters.ViewHolder;
import common.CommFunMessage;

/**
 * Created by yangshiyou on 2017/8/12.
 */

public class BaseFragmentActivity extends FragmentActivity implements IView {


    protected Context mContext;


    /**
     * 控件ViewGroup
     */
    protected View mContentView;

    protected ViewHolder mHolder;


    @ViewInject(R.id.img_tab1)
    private View img_tab1;

    @ViewInject(R.id.img_tab2)
    private View img_tab2;

    @ViewInject(R.id.img_tab3)
    private View img_tab3;

    @ViewInject(R.id.img_tab4)
    private View img_tab4;

    Fragment fragmentTab1;
    Fragment fragmentTab2;
    Fragment fragmentTab3;
    Fragment fragmentTab4;

    /**
     * 显示指定选项卡
     */
    public static int tab_position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        mContext=this;

        mContentView = getWindow().getDecorView();

        mHolder = ViewHolder.get(this, mContentView);


        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelect(tab_position);
    }

    /**
     * 初始化，1：initView；2：readCahce；3：loadData；4：bindData
     */
    private void init() {

        initData();

        initView();

        readCahce();

        loadData();

        bindData();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void readCahce() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void bindData() {

    }

    @Override
    public void showMsg(String msg) {
        if (msg == null)
            return;
        CommFunMessage.ShowMsgBox(mContext, msg);
    }

    @Override
    public void setViewText(int id, CharSequence text) {
        if (mHolder != null)
            mHolder.setText(id, text);
    }

    @Override
    public String getViewText(int id) {
        if (mHolder != null)
            return mHolder.getViewText(id);
        return "";
    }

    @Override
    public void setTextColor(int id, int color) {
        if (mHolder != null)
            mHolder.setTextColor(id, color);
    }

    @Override
    public void setBackgroundColor(int id, int color) {
        if (mHolder != null)
            mHolder.setBackgroundColor(id, color);
    }

    @Override
    public void setVisibility_GONE(int id) {
        if (mHolder != null)
            mHolder.setVisibility_GONE(id);
    }

    @Override
    public void setVisibility_VISIBLE(int id) {
        if (mHolder != null)
            mHolder.setVisibility_VISIBLE(id);

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
    protected void setSelect(int position) {

        setTab(position);

        tab_position = position;// 记录已打开选项卡位置，当跳转到登录界面或者其他界面，显示此界面

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);

        switch (position) {
            case 0:
                if (fragmentTab1 == null) {
                    fragmentTab1 = new MainTab1Fragment();
                    transaction.add(R.id.id_content, fragmentTab1);
                } else {
                    transaction.show(fragmentTab1);
                }
                fragmentTab1.onResume();
                break;
            case 1:
                if (fragmentTab2 == null) {
                    fragmentTab2 = new MainTab2Fragment();
                    transaction.add(R.id.id_content, fragmentTab2);
                } else {
                    transaction.show(fragmentTab2);
                }
                break;
            case 2:
                if (fragmentTab3 == null) {
                    fragmentTab3 = new MainTab3Fragment();
                    transaction.add(R.id.id_content, fragmentTab3);
                } else {
                    transaction.show(fragmentTab3);
                }
                fragmentTab3.onResume();
                break;
            case 3:
                if (fragmentTab4 == null) {
                    fragmentTab4 = new MainTab4Fragment();
                    transaction.add(R.id.id_content, fragmentTab4);
                } else {
                    transaction.show(fragmentTab4);
                }
                fragmentTab4.onResume();
                break;

            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentTab1 != null) {
            transaction.hide(fragmentTab1);
        }
        if (fragmentTab2 != null) {
            transaction.hide(fragmentTab2);
        }
        if (fragmentTab3 != null) {
            transaction.hide(fragmentTab3);
        }
        if (fragmentTab4 != null) {
            transaction.hide(fragmentTab4);
        }
    }

    /**
     * 滑动viewpager时设置tab(改变图片和字体)
     *
     * @param position
     */
    private void setTab(int position) {
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

    // ----------------------------------------
    // 切换图片方式二：xml配置 drawable selector

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

}
