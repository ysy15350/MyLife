package com.ysy15350.mylife.voice;

import android.view.View;
import android.widget.RadioGroup;

import com.ysy15350.mylife.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import base.MVPBaseFragment;

@ContentView(R.layout.fragment_voice)
public class VoiceFragment extends MVPBaseFragment<VoiceViewInterface, VoicePresenter>
        implements VoiceViewInterface {


//    查看Key×
//    App ID: 10008511
//
//    API Key: PP1wUXIME3l5dZNZeV4hs3Fa
//
//    Secret Key: 80f28e2d69f7b79d11147cb9e3d55eed

    //private static VoiceBroadcast mVoiceBroadcast;


    public VoiceFragment() {
    }

    @Override
    public VoicePresenter createPresenter() {
        // TODO Auto-generated method stub
        return new VoicePresenter(getActivity());
    }

    @Override
    public void initData() {
        super.initData();
        showMsg("a");
       // mVoiceBroadcast = VoiceBroadcast.getInstance(getActivity());
        showMsg("a1");
    }

    @Override
    public void initView() {
        setFormHead("文字语音识别", true);
    }

    /**
     * 文本内容
     */
    @ViewInject(R.id.rg_sex)
    private RadioGroup rg_sex;


    @Event(value = R.id.btn_ok)
    private void btn_okClick(View view) {
        String text = mHolder.getViewText(R.id.et_text);
        int checkedId = rg_sex.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_man:
                showMsg("男");
                //mVoiceBroadcast.setSoundType(1);
                break;
            case R.id.rb_woman:
                showMsg("女");
               // mVoiceBroadcast.setSoundType(0);
                break;
        }

        //mVoiceBroadcast.playVoice(text);
        showMsg(text);
    }

//    private void batchSpeak() {
//        List<SpeechSynthesizeBag> bags = new ArrayList<SpeechSynthesizeBag>();
//        bags.add(mVoiceBroadcast.getSpeechSynthesizeBag("123456"));
//        bags.add(mVoiceBroadcast.getSpeechSynthesizeBag("你好"));
//        bags.add(mVoiceBroadcast.getSpeechSynthesizeBag("使用百度语音合成SDK"));
//        bags.add(mVoiceBroadcast.getSpeechSynthesizeBag("hello"));
//        bags.add(mVoiceBroadcast.getSpeechSynthesizeBag("这是一个demo工程"));
//        mVoiceBroadcast.batchSpeak(bags);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mVoiceBroadcast.release();
    }
}

