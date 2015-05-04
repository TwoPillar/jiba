package com.twopillar.jiba.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.twopillar.jiba.R;

public class ActionDetailActivity extends Activity {
	
	public TextView action_description;
	
	public WebView wv_action_img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_detail);
		initView();
		initData();
	}
	
	private void initView() {
		action_description = (TextView)findViewById(R.id.action_description);
		wv_action_img = (WebView)findViewById(R.id.wv_action_img);
	}
	
	private void initData() {
		Intent intent = getIntent();
		String imgPath = intent.getStringExtra("imgPath");
		wv_action_img.loadUrl("file:///android_asset/"+imgPath);
	  
		String description = intent.getStringExtra("description");
		action_description.setText(description);
	}
	
}
