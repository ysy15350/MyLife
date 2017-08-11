package common;

import java.io.ObjectStreamException;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import common.image.ImageLoader;
import common.image.ImageLoader_Final;
import common.image.ImageUtil;
import common.model.ScreenInfo;

/**
 * Created by yangshiyou on 2016/11/23.
 */

public class ViewCommon {

    /**
     * 上下文
     */
    private static Context mContext;

    /**
     * 父视图
     */
    private static View mContentView;

    private ScreenInfo screenInfo;

    /**
     * 图片加载类
     */
    public static ImageLoader imageLoader;

    /**
     * 图片工具类
     */
    public static ImageUtil imageUtil;

    private ViewCommon() {
    }

    public static ViewCommon getInstance() {
        init();
        return ViewCommonHolder.sInstance;
    }

    public static ViewCommon getInstance(Context context, View contentView) {
        if (context != null)
            mContext = context;

        if (contentView != null)
            mContentView = contentView;

        init();

        return ViewCommonHolder.sInstance;
    }

    private static class ViewCommonHolder {
        private static final ViewCommon sInstance = new ViewCommon();
    }

    // 杜绝单例对象在反序列化时重新生成对象
    private Object readResolve() throws ObjectStreamException {
        return ViewCommonHolder.sInstance;
    }

    /**
     * 初始化成员变量
     */
    private static void init() {
        if (imageUtil == null)
            imageUtil = ImageUtil.getInstance(mContext);
        if (imageLoader == null)
            imageLoader = ImageLoader_Final.GetInstance(mContext);
    }

    /**
     * 默认为ImageLoader_Universal，如果要使用其他ImageLoader，调用此方法
     *
     * @param iImageLoader
     */
    public void setImageLoader(ImageLoader iImageLoader) {
        imageLoader = iImageLoader;
    }

    /**
     * 设置View文本
     *
     * @param viewId
     * @param text
     */
    public void setViewText(int viewId, String text) {
        try {
            if (mContentView != null) {
                View view = mContentView.findViewById(viewId);

                if (view != null) {
                    setViewText(view, text);
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 设置View文本
     *
     * @param viewId
     * @param text
     */
    public void setViewText(View view, String text) {
        try {
            if (view != null) {
                if (view instanceof TextView) {
                    setTextViewText((TextView) view, text);
                }
                if (view instanceof EditText) {
                    setEditTex((EditText) view, text);
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 设置TextView文本
     *
     * @param textView
     * @param text
     */
    public void setTextViewText(TextView textView, String text) {
        try {
            if (textView != null)
                textView.setText(text);
        } catch (Exception e) {

        }
    }

    /**
     * 设置EditText文本
     *
     * @param textView
     * @param text
     */
    public void setEditTex(EditText editText, String text) {
        try {
            if (editText != null)
                editText.setText(text);
        } catch (Exception e) {

        }
    }

    /**
     * 获取TextView或EditText 文本
     *
     * @param view TextView 或 EditText
     * @return
     */
    public String getViewText(View view) {
        try {
            if (view != null) {
                if (view instanceof TextView) {
                    return ((TextView) view).getText().toString().trim();
                }
                if (view instanceof EditText) {
                    return ((EditText) view).getText().toString().trim();
                }
            }
        } catch (Exception e) {

        }

        return "";
    }

    /**
     * 显示图片
     *
     * @param imageView
     * @param drawid
     */
    public void displayImage(ImageView imageView, int drawid) {
        if (imageLoader != null && imageView != null && drawid != 0) {

            if (imageUtil != null) {
                Bitmap bitmap = imageUtil.getBitmap(drawid);
                if (bitmap != null)
                    imageView.setImageBitmap(bitmap);
            }
        }
    }

//    setImageViewResource

    /**
     * 显示图片
     *
     * @param imageView
     * @param drawid
     */
    public void displayImage(ImageView imageView, String url) {
        if (imageLoader != null && imageView != null && url != null)
            imageLoader.displayImage(imageView, url);
    }

    /**
     * 设置背景图片
     *
     * @param viewId
     * @param drawId
     */
    public void setBackground(int viewId, int drawId) {
        try {
            if (mContentView != null) {
                View view = mContentView.findViewById(viewId);

                if (view != null && imageUtil != null) {
                    Drawable backDrawable = imageUtil.getDrawable(drawId);
                    view.setBackground(backDrawable);
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 设置背景图片
     *
     * @param viewId
     * @param drawId
     */
    public void setBackground(View view, int drawId) {
        try {
            if (view != null && imageUtil != null) {
                Drawable backDrawable = imageUtil.getDrawable(drawId);
                view.setBackground(backDrawable);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 设置背景图片
     *
     * @param viewId
     * @param drawId
     */
    public void setBackground(ImageView imageView, int drawId) {
        try {
            if (imageView != null) {
                Drawable backDrawable = imageUtil.getDrawable(drawId);
                imageView.setBackground(backDrawable);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 无数据时显示视图
     *
     * @return
     */
    public static View nodataView(Context context, int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);

        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        return view;
    }

    /**
     * 回收ImageView资源
     *
     * @param viewId
     */
    public void destoryImage(int viewId) {
        try {
            if (mContentView != null) {
                ImageView imageView = (ImageView) mContentView.findViewById(viewId);

                destoryImage(imageView);

            }
        } catch (Exception e) {

        }
    }

    /**
     * 回收ImageView资源
     *
     * @param imageView
     */
    public void destoryImage(ImageView imageView) {
        try {
            if (imageView != null && imageUtil != null)
                imageUtil.destoryImageView(imageView);

        } catch (Exception e) {

        }
    }

    /**
     * 回收ImageView资源
     *
     * @param imageViewList
     */
    public void destoryImage(List<ImageView> imageViewList) {
        try {
            if (imageViewList != null && imageViewList.size() > 0) {
                for (ImageView imageView : imageViewList) {
                    destoryImage(imageView);
                }
            }
        } catch (Exception e) {

        }

    }

    // --------------后台服务管理------------------------------

    public void startService() {

    }

    public void stopService() {

    }

    // ------------改变View大小----------------------

    public void changeViewSize(View view, int width, int height) {
        if (view == null || mContext == null)
            return;

        int screen_width = 0;
        int screen_height = 0;

        screenInfo = CommFunAndroid.GetInstance(mContext).getScreenInfo();

        LayoutParams layoutParams = view.getLayoutParams();
        if (screenInfo != null) {
            screen_width = screenInfo.getWidth();
            screen_height = screenInfo.getHeight();
        }

        layoutParams.width = width;
        layoutParams.height = height;

    }
}