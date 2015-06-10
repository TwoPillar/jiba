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
	
	public TextView actionDescription;
	
	public WebView vvAction;
	
	private ImageButton ibt_back;//返回
	
	private TextView tv_title;//标题
	
	private String title;//标题名
	
	private Intent intent;
	
	private String video;
	
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
	    actionDescription = (TextView)findViewById(R.id.action_description);
	    vvAction = (WebView)findViewById(R.id.wv_action);
	   

		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText(title);
	}
	
	private void initData() {
		video = intent.getStringExtra("video");
		vvAction.loadUrl("http://192.168.1.69:8080/jiba-server/action/shoulder/mp4/2212.gif");
		String description = intent.getStringExtra("description");
		actionDescription.setText(description);
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
