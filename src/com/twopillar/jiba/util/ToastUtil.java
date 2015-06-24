package com.twopillar.jiba.util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.twopillar.jiba.common.JBAppliction;

public class ToastUtil extends Toast {
	public ToastUtil(Context context) {
		super(context);
	}

	/**
	 * toast 提示消息
	 * 
	 * @param context
	 * @param msg
	 * @return
	 * @return
	 */
	public static void showToast(String msg) {
		final Toast toast = Toast.makeText(JBAppliction.getContext(), msg,
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setText(msg);
		toast.show();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				toast.cancel();
			}
		}, 500);
		
	}
	/**
	 * 
	 * @param msg
	 *
	 */
	public static void showToastLong(String msg) {
		final Toast toast = Toast.makeText(JBAppliction.getContext(), msg,
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setText(msg);
		toast.show();
	}
	/**
	 * toast 提示消息
	 * 
	 * @param context
	 * @param msg
	 * @return
	 * @return
	 */
	public static void showNotNetToast() {
		final Toast toast = Toast.makeText(JBAppliction.getContext(), "网络连接错误，请检查网络",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(100);
		toast.setText("网络连接错误，请检查网络");
		toast.show();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				toast.cancel();
			}
		}, 700);
	}
	public static void showCtrlSuccessful() {
		Toast.makeText(JBAppliction.getContext(), "操作成功",
				Toast.LENGTH_SHORT).show();
	}

	public static void showCtrlFailed() {
		Toast.makeText(JBAppliction.getContext(), "操作失败",
				Toast.LENGTH_SHORT).show();
	}

}
