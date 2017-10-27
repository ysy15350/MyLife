package com.ysy15350.mylife.video;

import android.view.View;

import com.ysy15350.mylife.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import base.BaseFragment;

/**
 * Created by yangshiyou on 2017/8/16.
 */

@ContentView(R.layout.fragment_video)
public class VideoFragment extends BaseFragment {

    @Override
    public void initView() {
        setFormHead("视频播放", true);
    }

    /**
     * 确认按钮点击事件
     *
     * @param view
     */
    @Event(value = R.id.btn_ok)
    private void btn_okClick(View view) {

        //VideoView videoView = mHolder.getView(R.id.vv_test);

        //String url = getViewText(R.id.et_url);

       // Uri uri=Uri.parse(url);

       // videoView.setVideoURI(uri);
       // videoView.start();


    }

}
