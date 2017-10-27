package base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.ysy15350.mylife.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.adapters.ViewHolder;
import common.CommFunAndroid;
import custom_view.xlistview.XListView;

/**
 * 通过这个基类的生命周期控制与Presenter的关系
 *
 * @param <V>
 * @param <T>
 * @author yangshiyou
 */
public abstract class MVPBaseListViewFragment<V, T extends BasePresenter<V>> extends BaseFragment
        implements XListView.IXListViewListener, OnItemClickListener {

    protected T mPresenter;//

    protected ViewHolder mHolder;

    protected View mContentView;


    /**
     * 下拉刷新列表
     */
    @ViewInject(R.id.xListView)
    protected XListView xListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);

        mPresenter = createPresenter();

        mPresenter.attachView((V) this);

        mContentView = x.view().inject(this, inflater, container);

        mHolder = ViewHolder.get(getActivity(), mContentView);

        initview();

        return mContentView;
    }

    /**
     * 初始化控件
     */
    protected void initview() {

        if (xListView != null) {

            xListView.setDivider(new ColorDrawable(getResources().getColor(R.color.devider_color))); // 设置间距颜色
            xListView.setDividerHeight(10); // 设置间距高度(此必须设置在setDivider（）之后，否则无效果)

            xListView.setPullLoadEnable(true);

            xListView.setXListViewListener(this);

            xListView.setOnItemClickListener(this);
        }

    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    protected int page = 1, pageSize = 10;

    public abstract void initData(int page, int pageSize);

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        page = 1;
        initData(page, pageSize);
    }

    @Override
    public void onLoadMore() {
        initData(page, pageSize);
    }

    /**
     * 绑定下拉列表
     */
    protected void bindListView(BaseAdapter mAdapter) {
        // TODO Auto-generated method stub

        if (mAdapter != null) {
            if (page == 1) {

                String timeStr = CommFunAndroid.getDateString("yyyy-MM-dd HH:mm:ss");
                xListView.setRefreshTime(timeStr);

                xListView.setAdapter(mAdapter);
                xListView.stopRefresh();
            } else {
                mAdapter.notifyDataSetChanged();
                xListView.stopLoadMore();
            }

            //listview布局动画
            if (mContext != null) {
                LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
                lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
                xListView.setLayoutAnimation(lac);
                xListView.startLayoutAnimation();
            }
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub

    }

}
