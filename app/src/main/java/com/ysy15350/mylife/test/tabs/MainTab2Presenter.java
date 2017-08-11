package com.ysy15350.mylife.test.tabs;

import android.content.Context;
import base.BasePresenter;

public class MainTab2Presenter extends BasePresenter<MainTab2ViewInterface> {

	public MainTab2Presenter() {
	}

	public MainTab2Presenter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private int page = 1;

	private int pageSize = 20;

	/**
	 * 设置查询页码
	 * 
	 * @param page
	 * @return
	 */
	public MainTab2Presenter setPage(int page) {
		this.page = page;
		return this;
	}


}
