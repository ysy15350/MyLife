package base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import org.xutils.x;

import base.adapters.ViewHolder;
import common.CommFunMessage;
import common.ExitApplication;

/**
 * Created by yangshiyou on 2016/11/29.
 */

public class BaseActivity extends AppCompatActivity implements IView {


    protected Context mContext;

    protected LayoutInflater mInflater;

    /**
     * 控件ViewGroup
     */
    protected View mContentView;

    protected ViewHolder mHolder;

    /**
     * 界面标题
     */
    protected String mTitle = "";

    /**
     * 是否需要登录
     */
    boolean mNeedLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        ExitApplication.getInstance().addActivity(this);// 添加当前Activity

        mContext = this;

        mContentView = getWindow().getDecorView();

        mHolder = ViewHolder.get(this, mContentView);

        init();

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

    /**
     * 初始化，1：initView；2：readCahce；3：loadData；4：bindData
     *
     * @param context
     * @param contentView
     * @param title
     * @param isNeedLogin
     */
    public void init(Context context, View contentView, String title, boolean isNeedLogin) {
        mContext = context;
        mContentView = contentView;
        mTitle = title;
        mNeedLogin = isNeedLogin;

        mInflater = LayoutInflater.from(context);

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

}