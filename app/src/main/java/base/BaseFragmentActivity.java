package base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

import com.ysy15350.mylife.R;
import com.ysy15350.mylife.fragment.tabs.MainTab1Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab2Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab3Fragment;
import com.ysy15350.mylife.fragment.tabs.MainTab4Fragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import base.adapters.ViewHolder;
import common.CommFunAndroid;
import common.CommFunMessage;
import common.ExitApplication;
import common.model.ScreenInfo;

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

    private static Fragment fragmentTab1;
    private static Fragment fragmentTab2;
    private static Fragment fragmentTab3;
    private static Fragment fragmentTab4;


    /**
     * 显示指定选项卡
     */
    public static int tab_position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        ExitApplication.getInstance().addActivity(this);// 添加当前Activity

        mContext = this;

        mContentView = getWindow().getDecorView();

        mHolder = ViewHolder.get(this, mContentView);

        String className = this.getClass().getName();

        showMsg(className);


        init();

    }


    @Override
    protected void onResume() {
        super.onResume();

        setSelect(tab_position);

        //showMsg("currentFragment==null?" + (currentFragment == null));

        if (currentFragment != null)
            showFragment(currentFragment, mIsHideBottom);
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
        CommFunMessage.showMsgBox(mContext, msg);
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

    FragmentTransaction transaction;

    /**
     * 点击事件1:设置tab(改变图片和字体)和2:切换fragment
     *
     * @param position
     */
    protected void setSelect(int position) {

        mHolder.setVisibility_VISIBLE(R.id.form_bottom);//显示底部菜单

        tab_position = position;// 记录已打开选项卡位置，当跳转到登录界面或者其他界面，显示此界面

        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        List<Fragment> fm_fragments = fm.getFragments();


        hideFragment(fm_fragments, transaction);


        switch (position) {
            case 0:
                if (fragmentTab1 == null) {
                    fragmentTab1 = new MainTab1Fragment();
                    transaction.add(R.id.id_content, fragmentTab1);
                } else {
                    transaction.show(fragmentTab1);
                    fragmentTab1.onResume();
                }
                break;
            case 1:
                if (fragmentTab2 == null) {
                    fragmentTab2 = new MainTab2Fragment();
                    transaction.add(R.id.id_content, fragmentTab2);
                } else {
                    transaction.show(fragmentTab2);
                    fragmentTab2.onResume();
                }
                break;
            case 2:
                if (fragmentTab3 == null) {
                    fragmentTab3 = new MainTab3Fragment();
                    transaction.add(R.id.id_content, fragmentTab3);
                } else {
                    transaction.show(fragmentTab3);
                    fragmentTab3.onResume();
                }

                break;
            case 3:
                if (fragmentTab4 == null) {
                    fragmentTab4 = new MainTab4Fragment();
                    transaction.add(R.id.id_content, fragmentTab4);
                } else {
                    transaction.show(fragmentTab4);
                    fragmentTab4.onResume();
                }

                break;

            default:
                break;
        }

        transaction.commit();

        setTab(position);
    }

    private void hideFragment(List<Fragment> fragments, FragmentTransaction transaction) {

        if (fragments != null && !fragments.isEmpty()) {
            showMsg("fragments:" + fragments.size());
            for (Fragment fragment : fragments) {
                transaction.hide(fragment);
            }
        }
    }

    /**
     * 显示指定Fragment
     *
     * @param fragment
     */
    public void showFragment(BaseFragment fragment) {
        try {

            currentFragment = fragment;


            /**
             * 1、获取fragment类名；
             * 2、在FragmentManager中查找对应的tag名称；
             * 3、如果找到对应的fragment，则无需添加新的fragment；
             * 4、如果未找到，则添加新的fragment;
             */

            String fragmentClassName = fragment.getClass().getName();//1、获取fragment类名；

            FragmentManager fm = getSupportFragmentManager();

            List<Fragment> fm_fragments = fm.getFragments();//获取当前所有的fragment集合

            Fragment fm_fragment = fm.findFragmentByTag(fragmentClassName);//2、在FragmentManager中查找对应的tag名称；

            if (fm_fragment == null)
                fm_fragment = fragment;//4、如果未找到，则添加新的fragment

            transaction = fm.beginTransaction();
            hideFragment(fm_fragments, transaction);

            if (!fm_fragments.contains(fm_fragment)) {
                showMsg("添加" + fragmentClassName);
                //transaction.add(R.id.id_content, fragment);
                transaction.add(R.id.id_content, fm_fragment, fm_fragment.getClass().getName());//把类名作为tag名称
            } else {
                transaction.show(fm_fragment);
                fm_fragment.onResume();
            }

            transaction.commit();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static BaseFragment currentFragment;

    private static boolean mIsHideBottom;

    /**
     * 显示指定Fragment
     *
     * @param fragment
     * @param hiddenBottom 是否隐藏底部，true:隐藏
     */
    public void showFragment(BaseFragment fragment, boolean hiddenBottom) {

        showFragment(fragment);

        mIsHideBottom = hiddenBottom;
        if (mIsHideBottom)
            mHolder.setVisibility_GONE(R.id.form_bottom);
        else
            mHolder.setVisibility_VISIBLE(R.id.form_bottom);
    }

    /**
     * 返回到主界面，指定选项卡
     *
     * @param index 选项卡
     */
    public void backFragment() {
        currentFragment = null;//当前显示fragment置空
        mHolder.setVisibility_VISIBLE(R.id.form_bottom);//显示底部菜单
        setSelect(tab_position);
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


    /**
     * 点击返回按钮间隔时间
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            ScreenInfo screenInfo = CommFunAndroid.GetInstance(this).getScreenInfo();
            showMsg(String.format("%f,%d,%d", screenInfo.getDensity(), screenInfo.getWidth(), screenInfo.getHeight()));
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                if (currentFragment != null)
                    backFragment();
                else {
                    showMsg("再按一次退出程序");
                    exitTime = System.currentTimeMillis();
                }

            } else {
                // stopService(new Intent(getApplicationContext(),
                // TestService.class));// stop

                // CommFunAndroid.setSharedPreferences("JSESSIONID", "");

                ExitApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
