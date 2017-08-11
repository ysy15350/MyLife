package common.image;

import android.widget.ImageView;

public interface ImageLoader {

	/**
	 * 显示图片
	 * 
	 * @param imageView
	 *            图片控件
	 * @param url
	 *            url地址
	 */
	void displayImage(ImageView imageView, String url);

	/**
	 * 显示图片
	 * 
	 * @param imageView
	 *            图片控件
	 * @param url
	 *            url地址
	 * @param loadingImageResId
	 *            正在加载时的图片资源ID
	 * @param loadfailImageResid
	 *            加载失败时的图片资源ID
	 */
	void displayImage(ImageView imageView, String url, int loadingImageResId, int loadfailImageResid);

}