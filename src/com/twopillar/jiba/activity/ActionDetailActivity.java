package com.twopillar.jiba.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.twopillar.jiba.R;

public class ActionDetailActivity extends BaseActivity {
	
	public TextView actionDescription;
	
	public VideoView vvAction;
	
	private ImageButton ibt_back;//返回
	
	private TextView tv_title;//标题
	
	private String title;//标题名
	
	private Intent intent;
	
	private int video;
	
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
	    vvAction = (VideoView)findViewById(R.id.vv_action);
	   

		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText(title);
	}
	
	private void initData() {
		video = intent.getIntExtra("video", 0);
		String description = intent.getStringExtra("description");
		vvAction.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +video)); 
		vvAction.start();  
		vvAction.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {  
  
            @Override  
            public void onPrepared(MediaPlayer mp) {  
                mp.start();  
                mp.setLooping(true);  
  
            }  
        });  
  
		vvAction.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {  
  
                    @Override  
                    public void onCompletion(MediaPlayer mp) {  
                        vvAction.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +video));  
                        vvAction.start();  
  
                    }  
                });  
  
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
