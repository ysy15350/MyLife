package common;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

public class CommFunMessage {

	public static Context mContext;

	private static long lastClickTime;

	private static long lastShowTime;

	private static long lastFreshTime;

	/**
	 * 防止多次点击
	 * 
	 * @return
	 */
	public synchronized static boolean isFastClick() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 1000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 防止频繁提示相同消息
	 * 
	 * @return
	 */
	public synchronized static boolean isFastShow() {
		long time = System.currentTimeMillis();
		if (time - lastShowTime < 3000) {
			return true;
		}
		lastShowTime = time;
		return false;
	}

	/**
	 * 防止频繁刷新
	 * 
	 * @return
	 */
	public synchronized static boolean isFastFresh() {
		long time = System.currentTimeMillis();
		if (time - lastFreshTime < 10000) {
			return true;
		}
		lastFreshTime = time;
		return false;
	}

	private static String m_message = "";

	/**
	 * 系统提示
	 * 
	 * @param c
	 *            当前Activity
	 * @param message
	 *            提示信息
	 */
	public static void ShowMsgBox(Context context, String message) {
		if (context != null)
			mContext = context;
		if (mContext != null) {
			if (isFastShow() && m_message.equals(message)) {
				return;
			}

			m_message = message;

			Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 200);
			toast.show();
		}
	}

	/**
	 * 消息提示
	 * 
	 * @param message
	 */
	public static void ShowMsgBox(String message) {
		if (mContext != null && !CommFunAndroid.isNullOrEmpty(message)) {
			Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 200);
			toast.show();
		}
	}

	/**
	 * 系统提示
	 * 
	 * @param c
	 *            当前Activity
	 * @param message
	 *            提示信息
	 */
	public static void ShowImgBox(Context context, int pic_id) {

		try {
			if (context != null)
				mContext = context;
			if (mContext != null) {
				// 创建新Toast对象
				Toast showImageToast = new Toast(mContext);

				// 创建新ImageView对象
				ImageView imageView = new ImageView(mContext);

				// 从资源中获取图片
				imageView.setImageResource(pic_id);

				// 设置Toast上的View(imageView)
				showImageToast.setView(imageView);

				// 设置Toast显示时间
				showImageToast.setDuration(Toast.LENGTH_SHORT);

				// 显示Toast
				showImageToast.show();
			}
		} catch (Exception e) {

		}

	}

}