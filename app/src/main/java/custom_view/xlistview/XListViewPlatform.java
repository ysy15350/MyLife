package custom_view.xlistview;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import common.CommFunAndroid;

public class XListViewPlatform implements XListView.IXListViewListener, OnItemClickListener {
	
	XListView xListView;
	
	int page = 1, pageSize = 5;

	public XListViewPlatform(XListView xListView,int page,int pageSize){
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

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
		}

	}
	
	public interface XListViewPlatformListener{
		public void initData(int page, int pageSize);
	}

}
