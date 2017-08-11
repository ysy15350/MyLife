package base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * Created by yangshiyou on 2016/11/29.
 */

public class BaseFragment extends Fragment implements IView {

    private boolean injected = false;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
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
