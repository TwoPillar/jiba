package com.twopillar.jiba.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.twopillar.jiba.R;
import com.twopillar.jiba.model.Action;

public class ActionDetailActivity extends Activity{
	
	private ListView lv_actionList;//动作列表;
	
	private ActionAdatper actionAdatper;
	
	private List<Action> actions = new ArrayList<Action>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_detail);
		initView();
		initData();
	}
	
	private void initView() {
		lv_actionList = (ListView)findViewById(R.id.lv_actionList);
	}
	
	private void initData() {
		Intent intent = getIntent();
		String bigType = intent.getStringExtra("bigType");
		actions = DataSupport.where("bigType = ?",bigType).find(Action.class);
		actionAdatper = new ActionAdatper(ActionDetailActivity.this, R.layout.item_action, actions);
		lv_actionList.setAdapter(actionAdatper);
		actionAdatper.notifyDataSetChanged();
		Log.v("111", actions.toString());
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
			Bitmap picBitmap = getImageFromAssetsFile(action.getImgPath());
			iv_pic.setImageBitmap(picBitmap);
			TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
			tv_name.setText(action.getActionName());
			return view;
		}
	}
	
	/**
     * 从Assets中读取图片
     */
    private Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

    }
}
