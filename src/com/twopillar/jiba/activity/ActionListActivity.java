package com.twopillar.jiba.activity;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.twopillar.jiba.R;
import com.twopillar.jiba.common.ViewHolder;
import com.twopillar.jiba.model.Action;

public class ActionListActivity extends BaseActivity{
	
	private ListView lv_actionList;//动作列表;
	
	private ActionAdatper actionAdatper;
	
	private List<Action> actions = new ArrayList<Action>();
	
	private String actionType;//动作类型
	
	private ImageButton ibt_back;//返回
	
	private TextView tv_title;//标题
	
	private String title;//标题名
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_list);
		Intent intent = getIntent();
		actionType = intent.getStringExtra("actionType");
		title = intent.getStringExtra("title");
		initView();
		initData();
		setListener();
	}
	
	private void initView() {
		lv_actionList = (ListView)findViewById(R.id.lv_actionList);
		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText(title);
	}
	
	@SuppressLint("NewApi")
    private void initData() {
	    actions = DataSupport.where("bigtype = ?", actionType).find(Action.class);
	    actionAdatper = new ActionAdatper(ActionListActivity.this, R.layout.item_action, actions);
        lv_actionList.setAdapter(actionAdatper);
        actionAdatper.notifyDataSetChanged();
	}
	
	private void setListener() {
		lv_actionList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Action action = actions.get(position);
				Intent intent = new Intent(ActionListActivity.this,ActionDetailActivity.class);
				intent.putExtra("video", action.getDrawableId());
				intent.putExtra("title", action.getActionName());
				intent.putExtra("description", action.getDescription());
				startActivity(intent);
			}
		});
		
		ibt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
	}
	
	@SuppressLint("NewApi")
    private class ActionAdatper extends ArrayAdapter<Action> {
		
		private int resourceId;
		
		private MediaMetadataRetriever retriever;
		
		private Uri uri;
		
		public ActionAdatper(Context context, int resource, List<Action> objects) {
			super(context, resource, objects);
			resourceId = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		    Action action = getItem(position);
		    if(convertView == null) {
		        convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
		    }
    			ImageView iv_action = ViewHolder.get(convertView, R.id.iv_action);
/*    			retriever = new MediaMetadataRetriever();
                uri=Uri.parse("android.resource://" + getPackageName() + "/"+action.getDrawableId());
                retriever.setDataSource(getContext(), uri);//缩略图
    			iv_action.setImageBitmap(retriever.getFrameAtTime());*/
    			iv_action.setImageResource(action.getDrawablePicId());
    			TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
    			tv_name.setText(action.getActionName());
		  
    			return convertView;
		}
	}
}
