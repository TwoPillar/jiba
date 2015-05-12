package com.twopillar.jiba.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.twopillar.jiba.R;

public class ActionDetailActivity extends BaseActivity {
	
	public TextView action_description;
	
	public WebView wv_action_img;
	
	private ImageButton ibt_back;//返回
	
	private TextView tv_title;//标题
	
	private String title;//标题名
	
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_detail);
		intent = getIntent();
		title = intent.getStringExtra("title");
		initView();
		initData();
		setListener();
	}
	
	private void initView() {
		action_description = (TextView)findViewById(R.id.action_description);
		wv_action_img = (WebView)findViewById(R.id.wv_action_img);
		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText(title);
	}
	
	private void initData() {
		String imgPath = intent.getStringExtra("imgPath");
		wv_action_img.loadUrl("file:///android_asset/"+imgPath);
	  
		String description = intent.getStringExtra("description");
		action_description.setText(description);
	}
	
	private void setListener() {
		ibt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
}
