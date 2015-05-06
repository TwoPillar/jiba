package com.twopillar.jiba.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}
	
	private void init() {
		initView();
		initData();
		setListener();
	}
	
	public void initView() {
	
	}

	
	public void initData() {
		
	}
	
	public void setListener() {
		
	}
}
