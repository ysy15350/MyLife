package common.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImageLoader_Final implements ImageLoader {



	/**
	 * FinalBitmap加载线程数量
	 */
	private int finalBitmapLoadThreadSize = 5;

	/**
	 * 上下文
	 */
	private static Context mContext;

	/**
	 * 图片加载类
	 */
	private static ImageLoader_Final imageLoader;

	/**
	 * 正在加载时显示图片（默认图片）
	 */
	public static int loadingImageResId = 0;
	/**
	 * 加载失败时显示图片
	 */
	public static int loadfailImageResid = 0;


	/**
	 * 获取FinalBitmap加载线程数量
	 * 
	 * @return
	 */
	public int getFinalBitmapLoadThreadSize() {
		return finalBitmapLoadThreadSize;
	}

	/**
	 * 设置FinalBitmap加载线程数量
	 * 
	 * @param finalBitmapLoadThreadSize
	 */
	public void setFinalBitmapLoadThreadSize(int finalBitmapLoadThreadSize) {
		this.finalBitmapLoadThreadSize = finalBitmapLoadThreadSize;
	}

	/**
	 * 设置Context
	 * 
	 * @param mContext
	 */
	public static void setmContext(Context mContext) {
		ImageLoader_Final.mContext = mContext;
	}

	/**
	 * 设置加载时图片(默认图片)资源ID
	 * 
	 * @param loadingImageResId
	 */
	public void setLoadingImageResId(int loadingImageResId) {
		this.loadingImageResId = loadingImageResId;
	}

	/**
	 * 获取加载失败时图片(默认图片)资源ID
	 * 
	 * @return
	 */
	public int getLoadfailImageResid() {
		return loadfailImageResid;
	}

	/**
	 * 设置加载失败时图片(默认图片)资源ID
	 * 
	 * @param loadfailImageResid
	 */
	public void setLoadfailImageResid(int loadfailImageResid) {
		this.loadfailImageResid = loadfailImageResid;
	}

	/**
	 * 私有构造方法，保证不能被实例化
	 */
	private ImageLoader_Final() {

	}

	/**
	 * 获取imageLoader对象,外部使用此实例对象处理图片
	 * 
	 * @param context
	 * @return
	 */
	public static ImageLoader_Final GetInstance(Context context) {
		// 这里可以保证只实例化一次
		// 即在第一次调用时实例化
		// 以后调用便不会再实例化
		if (imageLoader == null) {
			imageLoader = new ImageLoader_Final();
		}

		if (context != null)
			mContext = context;

		return imageLoader;
	}

	/**
	 * 实例化imageLoader对象
	 * 
	 * @param context
	 */
	public static synchronized void newInstance(Context context) {
		if (null == imageLoader) {
			imageLoader = new ImageLoader_Final();

			if (context != null)
				ImageLoader_Final.mContext = context;

		}
	}



	/**
	 * 显示图片
	 * 
	 * @param imageView
	 *            图片控件
	 * @param url
	 *            图片url(web)
	 * @param loadingImageResId
	 *            默认图片（drawId）
	 * @param loadfailImageResid
	 *            加载失败显示图片
	 */
	public void displayImage(ImageView imageView, String url, int loadingImageResId, int loadfailImageResid) {
		try {
			if (imageView != null && mContext != null && url != null) {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void displayImage() {

	}

	/**
	 * 显示图片，加载图片和失败时显示图片默认设置
	 * 
	 * @param imageView
	 * @param url
	 */
	public void displayImage(ImageView imageView, String url) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void displayImage(ImageView imageView, int resId) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}