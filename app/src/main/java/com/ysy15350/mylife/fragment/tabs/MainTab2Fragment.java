package com.ysy15350.mylife.fragment.tabs;

import android.view.View;
import android.widget.ImageView;

import com.ysy15350.mylife.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.MVPBaseFragment;

@ContentView(R.layout.activity_main_tab2)
public class MainTab2Fragment extends MVPBaseFragment<MainTab2ViewInterface, MainTab2Presenter>
        implements MainTab2ViewInterface {


    public MainTab2Fragment() {
    }

    @Override
    public MainTab2Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab2Presenter(getActivity());
    }


    /**
     * 本地图片
     */
    @ViewInject(R.id.img_local)
    private ImageView img_local;

    /**
     * 本地图片
     */
    @ViewInject(R.id.img_web)
    private ImageView img_web;


    /**
     * 本地gif
     */
    @ViewInject(R.id.img_gif)
    private ImageView img_gif;


    /**
     * 网络gif
     */
    @ViewInject(R.id.img_web_gif)
    private ImageView img_web_gif;


    /**
     * 根据level切换图片
     */
    @ViewInject(R.id.img_level)
    private ImageView img_level;

    ImageOptions imageOptions;

    @Override
    public void initData() {


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


        //通过ImageOptions.Builder().set方法设置图片的属性
//        ImageOptions imageOptions = new ImageOptions.Builder().setFadeIn(true).build(); //淡入效果
////ImageOptions.Builder()的一些其他属性：
//.setCircular(true) //设置图片显示为圆形
//                .setSquare(true) //设置图片显示为正方形
//                .setCrop(true).setSize(200, 200) //设置大小
//                .setAnimation(animation) //设置动画
//                .setFailureDrawable(Drawable failureDrawable) //设置加载失败的动画
//                .setFailureDrawableId( int failureDrawable) //以资源id设置加载失败的动画
//.setLoadingDrawable(Drawable loadingDrawable) //设置加载中的动画
//                .setLoadingDrawableId( int loadingDrawable) //以资源id设置加载中的动画
//.setIgnoreGif(false) //忽略Gif图片
//                .setParamsBuilder(ParamsBuilder paramsBuilder) //在网络请求中添加一些参数
//                .setRaduis( int raduis) //设置拐角弧度
//.setUseMemCache(true) //设置使用MemCache，默认true


    }

    @Override
    public void bindData() {

        bindImage();
    }

    /**
     * 加载图片
     */
    private void bindImage() {

//        // assets file
//        x.image().bind(imageView, "assets://test.gif", imageOptions);
//
//// local file
//        x.image().bind(imageView, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
//        x.image().bind(imageView, "/sdcard/test.gif", imageOptions);
//        x.image().bind(imageView, "file:///sdcard/test.gif", imageOptions);
//        x.image().bind(imageView, "file:/sdcard/test.gif", imageOptions);
//
//        x.image().bind(imageView, url, imageOptions, new Callback.CommonCallback<Drawable>() {
//            @Override
//            public void onSuccess(Drawable result) {
//            }
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//            }
//            @Override
//            public void onCancelled(CancelledException cex) {
//            }
//            @Override
//            public void onFinished() {
//            }

        // x.image().bind(imageTest,"https://file.longkin.net/Uploads/User/-1/c8b6ff92-4e63-4f39-b664-819b3acf4ec2.jpg", imageOptions);


        x.image().bind(img_local, "assets://bg_main_top.png", imageOptions);//本地图片


        x.image().bind(img_gif,
                "assets://test.gif",
                imageOptions);//本地gif


        x.image().bind(img_web_gif,
                "http://img3.3lian.com/2006/013/08/20051103121023912.gif",
                imageOptions);//网络gif


        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5)).setCircular(true)
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

        x.image().bind(img_web, "http://www.ysy15350.com:80/uploadFiles/uploadImgs/20170719/f0c2db57c552470ca24965108963c964.jpg", imageOptions);//本地图片


    }

    @Event(value = R.id.img_level)
    private void img_levelClick(View view) {
        changeImg();
    }

    int level = 0;

    /**
     * 根据level切换图片
     */
    private void changeImg() {
        if (level == 4)
            level = 0;
        level++;
        img_level.setImageLevel(level);// 1:待机；2：选中；3：运行；4：未连接
        //img_level.getDrawable().setLevel(level);// 1:待机；2：选中；3：运行；4：未连接


    }


}

