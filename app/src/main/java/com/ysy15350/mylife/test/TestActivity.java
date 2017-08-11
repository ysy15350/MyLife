package com.ysy15350.mylife.test;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.ysy15350.mylife.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.MVPBaseActivity;

/**
 * Created by yangshiyou on 2016/11/23.
 * <p>
 * xUtils3 github网址
 * https://github.com/wyouflf/xUtils3/tree/master
 */

@ContentView(R.layout.activity_test)
public class TestActivity extends MVPBaseActivity<TestViewInterface, TestPresenter> implements TestViewInterface {


    @ViewInject(R.id.img_test)
    private ImageView imageTest;


    @ViewInject(R.id.cb_cache)
    private CheckBox checkBox;

    ImageOptions imageOptions;


    @Override
    protected TestPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new TestPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        bindImage();
    }


    private void init() {

        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) //是否对图片进行裁剪,很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                // 是否忽略GIF格式的图片
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();


    }

    /**
     * 1. 方法必须私有限定,
     * 2. 方法参数形式必须和type对应的Listener接口一致.
     * 3. 注解参数value支持数组: value={id1, id2, id3}
     * 4. 其它参数说明见{@link org.xutils.event.annotation.Event}类的说明.
     **/
    @Event(value = R.id.img_test,
            type = View.OnClickListener.class/*可选参数, 默认是View.OnClickListener.class*/)
    private void onImageClick(View view) {
        //

        boolean useCache= checkBox.isChecked();

        mPresenter.getData(useCache);

    }

    int i = 0;


    private void bindImage() {

        // x.image().bind(imageTest,"https://file.longkin.net/Uploads/User/-1/c8b6ff92-4e63-4f39-b664-819b3acf4ec2.jpg", imageOptions);

        x.image().bind(imageTest,
                "assets://test.gif",
                imageOptions);


    }


    /**
     * 1. 方法必须私有限定,
     * 2. 方法参数形式必须和type对应的Listener接口一致.
     * 3. 注解参数value支持数组: value={id1, id2, id3}
     * 4. 其它参数说明见{@link org.xutils.event.annotation.Event}类的说明.
     **/
    @Event(value = R.id.btn_test,
            type = View.OnClickListener.class/*可选参数, 默认是View.OnClickListener.class*/)
    private void onTestBaidu1Click(View view) {
        //

        mPresenter.test();

    }

    @Override
    public void test() {

        Toast.makeText(this, "test1", Toast.LENGTH_SHORT).show();

    }
}
