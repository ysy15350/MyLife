package com.ysy15350.mylife.voice;

import android.content.Context;

import base.BasePresenter;

public class VoicePresenter extends BasePresenter<VoiceViewInterface> {

	public VoicePresenter() {
	}

	public VoicePresenter(Context context) {
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
	public VoicePresenter setPage(int page) {
		this.page = page;
		return this;
	}




}
