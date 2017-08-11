package base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import org.xutils.x;

/**
 * Created by yangshiyou on 2016/11/29.
 */

public class BaseActivity extends AppCompatActivity implements IView{


    protected Context mContext;

    protected LayoutInflater mInflater;

    /**
     * 控件ViewGroup
     */
    protected View mContentView;

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
    }

    public void init(Context context, View contentView, String title, boolean isNeedLogin) {
        mContext = context;
        mContentView = contentView;
        mTitle = title;
        mNeedLogin = isNeedLogin;

        mInflater = LayoutInflater.from(context);

        initView();

        readCahce();

        loadData();

        bindData();
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
}