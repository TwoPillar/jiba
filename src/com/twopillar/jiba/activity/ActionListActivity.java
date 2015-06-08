package com.twopillar.jiba.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.VolleyError;
import com.twopillar.jiba.R;
import com.twopillar.jiba.api.HttpCallBack;
import com.twopillar.jiba.api.JibaServerApi;
import com.twopillar.jiba.model.Action;
import com.twopillar.jiba.util.ImageUtil;

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
	
	private void initData() {
	    JibaServerApi.getInstance(ActionListActivity.this).getActionByType(actionType, new HttpCallBack() {

            @Override
            public void onSuccess(JSONObject response)
            {
              Log.d("ActionListActivity", response.toString());
                
            }

            @Override
            public void onFailure(VolleyError arg0)
            {
                Log.d("ActionListActivity", arg0.toString());
                
            }

        });
		actions = DataSupport.where("bigType = ?",actionType).find(Action.class);
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
				intent.putExtra("imgPath", action.getImgPath());
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
	
	private class ActionAdatper extends ArrayAdapter<Action> {
		
		private int resourceId;
		
		public ActionAdatper(Context context, int resource, List<Action> objects) {
			super(context, resource, objects);
			resourceId = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Action action = getItem(position);
			View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			ImageView iv_pic = (ImageView)view.findViewById(R.id.iv_pic);
			Bitmap picBitmap = ImageUtil.getImageFromAssetsFile(action.getImgPath(),getResources());//根据路径读取资源文件
			iv_pic.setImageBitmap(picBitmap);
			TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
			tv_name.setText(action.getActionName());
			return view;
		}
	}
}
