package base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ysy15350.mylife.MainActivity;
import com.ysy15350.mylife.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

import base.adapters.ViewHolder;
import common.CommFunAndroid;
import common.CommFunMessage;

/**
 * Created by yangshiyou on 2016/11/29.
 */

public class BaseFragment extends Fragment implements IView {

    private boolean injected = false;

    protected Context mContext;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;

        mContext = getActivity();

        mContentView = x.view().inject(this, inflater, container);

        mHolder = ViewHolder.get(getActivity(), mContentView);

        init();

        return mContentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    /**
     * 初始化，1：initView；2：readCahce；3：loadData；4：bindData
     */
    private void init() {

        init("");

        initData();

        initView();

        readCahce();

        loadData();

        bindData();
    }

    public void init(String title) {
        init(title, false);
    }

    /**
     * 初始化，1：initView；2：readCahce；3：loadData；4：bindData
     *
     * @param context
     * @param contentView
     * @param title
     * @param isNeedLogin
     */
    public void init(String title, boolean isNeedLogin) {

        setTitle(title);

        mNeedLogin = isNeedLogin;

        initData();

        initView();

        readCahce();

        loadData();

        bindData();
    }

    /**
     * 设置头部
     *
     * @param title
     * @param isBack
     */
    protected void setFormHead(String title, boolean isBack) {
        setTitle(title);
        setBtnBack(isBack);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        if (!CommFunAndroid.isNullOrEmpty(title))
            mHolder.setText(R.id.tv_form_title, title);
    }

    protected void setBtnBack(boolean isBack) {
        if (isBack)
            mHolder.setVisibility_VISIBLE(R.id.btn_back);
        else
            mHolder.setVisibility_GONE(R.id.btn_back);
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

    /**
     * 返回
     *
     * @param view
     */
    @Event(value = R.id.btn_back)
    private void btn_backClick(View view) {

        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.backFragment();
        }
    }

}
