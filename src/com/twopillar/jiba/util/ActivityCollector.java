package com.twopillar.jiba.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * @author GuoJian'an
 * @date 2015-7-7
 * activity收集
 */
public class ActivityCollector {
	
	public static List<Activity> activities = new ArrayList<Activity>();
	
	/**
     * 添加activity
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	/**
     * 移除activity
	 * @param activity
	 */
	public static void removeActivity(Activity activity) {
		activities.remove(activity);
	}
	
	/**
     * 结束所有开启的activity
	 * @author Administrator
	 */
	public static void finshAll() {
		for(Activity activity : activities) {
			if(!activity.isFinishing()) {
				activity.finish();
			}
		}
	}
}
