package com.ysy15350.mylife.video;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;
import com.ysy15350.mylife.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import base.BaseActivity;

/**
 * Created by yangshiyou on 2017/8/16.
 */

@ContentView(R.layout.fragment_video)
public class VideoActivity extends BaseActivity implements UniversalVideoView.VideoViewCallback {


    /**
     * 项目地址: https://github.com/linsea/UniversalVideoView
     * <p>
     * UniversalVideoView 没有保存播放的状态,如播放到第几分钟了,所以需要应用自己保存这些状态并恢复.
     * 如果为了避免在旋转屏幕时系统重启Activity,需要添加Activity的属性:
     */

    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    //private static String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private static String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";//http://192.168.31.176:8080/interest/video1.mp4";

    @ViewInject(R.id.videoView)
    private UniversalVideoView mVideoView;


    @ViewInject(R.id.media_controller)
    private UniversalMediaController mMediaController;

    @ViewInject(R.id.bottom_layout)
    private View mBottomLayout;

    @ViewInject(R.id.video_layout)
    private View mVideoLayout;


    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;


    @Override
    public void initView() {
        super.initView();
        //mVideoLayout = findViewById(R.id.video_layout);
        //mBottomLayout = findViewById(R.id.bottom_layout);
        //mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        //mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);

        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);


        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Log.d(TAG, "onCompletion ");
            }
        });

        mMediaController.showComplete();


//        String url = getViewText(R.id.et_url);
//
//        VIDEO_URL = url;
//
//
//        if (mSeekPosition > 0) {
//            mVideoView.seekTo(mSeekPosition);
//        }
//        mVideoView.start();
//        mMediaController.setTitle("Big Buck Bunny");

    }

    /**
     * 确认按钮点击事件
     *
     * @param view
     */
    @Event(value = R.id.btn_ok)
    private void btn_okClick(View view) {

        mHolder.setVisibility_GONE(R.id.rl_mask);

        String url = getViewText(R.id.et_url);

        VIDEO_URL = url;


        if (mSeekPosition > 0) {
            mVideoView.seekTo(mSeekPosition);
        }
        mVideoView.start();
        mMediaController.setTitle(VIDEO_URL);
        setVideoAreaSize();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause ");
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            Log.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
            mVideoView.pause();
        }
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Position=" + mVideoView.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
        Log.d(TAG, "onRestoreInstanceState Position=" + mSeekPosition);
    }


    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.VISIBLE);
        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPause UniversalVideoView callback");
        //mHolder.setVisibility_VISIBLE(R.id.btn_ok);
        mMediaController.showComplete();
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onStart UniversalVideoView callback");
        //mHolder.setVisibility_GONE(R.id.btn_ok);
        mMediaController.hideComplete();
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingStart UniversalVideoView callback");
        showMsg("onBufferingStart");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingEnd UniversalVideoView callback");

        showMsg("onBufferingEnd");
    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            mVideoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

}
