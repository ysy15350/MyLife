package common;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ysy15350.mylife.chat.LoginActivity;

public class ExitApplication extends Application {

	private List<Activity> activityList = new LinkedList();
	private static ExitApplication instance;

	/**
	 * 单例模式中获取唯一的ExitApplication实例
	 * 
	 * @return 返回单例ExitApplication
	 */
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}

	/**
	 * 添加Activity到容器中
	 * 
	 * @param activity
	 *            当前Activity
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 遍历所有Activity并finish
	 */
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * 遍历所有Activity并finish
	 */
	public void loginout() {
		for (Activity activity : activityList) {
			if (!(activity instanceof LoginActivity))
				activity.finish();
		}
	}

	/**
	 * 关闭指定Acitivty
	 * 
	 * @param c
	 *            Activity
	 */
	public void finishActivity(final Context c) {
		for (Activity activity : activityList) {
			if (c == activity)
				activity.finish();
		}
	}

}