package com.twopillar.jiba.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.twopillar.jiba.R;
import com.twopillar.jiba.model.Plan;

public class MyPlanListActivity extends Activity{
	
	private ListView ll_plan_list;//单选列表
	
	private MyPlanAadapter myPlanAadapter;
	
	private TextView tv_title;
	
	private ImageButton ibt_back;
	
	private Button bt_start;//设为当前健身计划
	
	private Plan currentPlan = null;
	
	private List<Plan> plans;
	
	private Button bt_plan_detail;//查看计划明细 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_plan_list);
		initView();
		initData();
		setListener();
	}
	
	private void initView() {	
		ll_plan_list = (ListView)findViewById(R.id.ll_plan_list);
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText("我的健身计划");
		
		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
		
		bt_start = (Button)findViewById(R.id.bt_start);
		bt_plan_detail = (Button)findViewById(R.id.bt_plan_detail);
	}
	
	private void initData() {
		plans = Plan.where("ismyplan = ?","1").find(Plan.class);
		myPlanAadapter = new MyPlanAadapter(MyPlanListActivity.this, R.layout.item_my_plan, plans);
		ll_plan_list.setAdapter(myPlanAadapter);
	}
	
	private void setListener() {
		ll_plan_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				myPlanAadapter.setSelectItem(position);
				myPlanAadapter.notifyDataSetInvalidated();  
			}
		});
		
		ibt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		bt_plan_detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(currentPlan == null){
					Toast.makeText(MyPlanListActivity.this, "请选择计划", Toast.LENGTH_SHORT).show();
				}else {
					Intent intent = new Intent(MyPlanListActivity.this, RecommendPlanActivity.class);
					intent.putExtra("planName", currentPlan.getPlanName());
					intent.putExtra("planId", currentPlan.getId());
					intent.putExtra("myPlanFlag", 1);
					startActivity(intent);
				}
			}
		});
		
		
		bt_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(currentPlan == null){
					Toast.makeText(MyPlanListActivity.this, "请选择计划", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(MyPlanListActivity.this, String.valueOf(currentPlan.getId()), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(MyPlanListActivity.this,SetStartDateActivity.class);
					intent.putExtra("planId", currentPlan.getId());
					startActivity(intent);
				}
				
			}
		});
	}
	
	class MyPlanAadapter extends ArrayAdapter<Plan> {
		
		private int resourceId;
		
		private int  selectItem = -1; 
		
		private boolean intoFlag = true;
		
		public MyPlanAadapter(Context context, int resourceId,
				List<Plan> objects) {
			super(context, resourceId, objects);
			this.resourceId = resourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Plan plan = getItem(position);
			ViewHolder holder;
			View view;
			if(convertView == null) {
				holder = new ViewHolder();
				view = LayoutInflater.from(getContext()).inflate(resourceId, null);
				holder.planName = (TextView)view.findViewById(R.id.tv_plan_name);
				holder.iv_selected = (ImageView)view.findViewById(R.id.iv_selected);
				holder.iv_selected_frame = (ImageView)view.findViewById(R.id.iv_selected_frame);
				view.setTag(holder);
			}else {
				view = convertView;
				holder = (ViewHolder)view.getTag();
			}
			holder.planName.setText(plan.getPlanName());
			if(plan.isStart() && intoFlag) {
				selectItem = position;
				intoFlag = false;
			}
			if (position == selectItem) {
				currentPlan = plan;
				holder.iv_selected.setVisibility(View.VISIBLE);
				holder.planName.setTextColor(Color.parseColor("#FFFFFF"));
				view.setBackgroundColor(Color.parseColor("#000000"));
				holder.iv_selected_frame.setImageResource(R.drawable.selected_frame);
            }   
            else {  
            	holder.iv_selected.setVisibility(View.GONE);
            	holder.planName.setTextColor(Color.parseColor("#000000"));
            	view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            	holder.iv_selected_frame.setImageResource(R.drawable.unselected_frame);
            }    
			return view;
		}
		
		public  void setSelectItem(int selectItem) {  
            this.selectItem = selectItem;  
        }
		
		class ViewHolder {
			TextView planName;
			
			ImageView iv_selected;
			
			ImageView iv_selected_frame;
		}
	}
}
