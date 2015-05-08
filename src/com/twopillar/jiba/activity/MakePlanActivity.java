package com.twopillar.jiba.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.twopillar.jiba.R;
import com.twopillar.jiba.model.Plan;

public class MakePlanActivity extends Activity{
	
	private ListView iv_recommend;
	
	private PlanAdatper planAdatper;
	
	private TextView tv_title;
	
	private ImageButton ibt_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_plan);
		initView();
		initData();
		setlistener();
	}
	
	private void initView() {
		iv_recommend = (ListView)findViewById(R.id.iv_recommend);
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText("制定计划");
		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
	}
	
	private void initData() {
		List<Plan> plans = Plan.where("recommend = ?","1").find(Plan.class);
		List<String> planNames = new ArrayList<String>();
		for (Plan plan : plans) {
			planNames.add(plan.getPlanName());
		}
		planAdatper = new PlanAdatper(MakePlanActivity.this, R.layout.item_recommend_plan, plans);
		iv_recommend.setAdapter(planAdatper);
		planAdatper.notifyDataSetChanged();
	}
	
	private void setlistener() {
		ibt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		iv_recommend.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Plan recommendPlan = (Plan)planAdatper.getItem(position);
				Intent intent = new Intent(MakePlanActivity.this, RecommendPlanActivity.class);
				intent.putExtra("planName", recommendPlan.getPlanName());
				intent.putExtra("planId", recommendPlan.getId());
				startActivity(intent);
			}
		});
	}
	
	class PlanAdatper extends ArrayAdapter<Plan> {
		
		private int resourceId;
		
		public PlanAdatper(Context context, int resourceId, List<Plan> plans) {
			super(context, resourceId, plans);
			this.resourceId = resourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Plan plan = getItem(position);
			View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			TextView planName = (TextView) view.findViewById(R.id.tv_plan_name);
			planName.setText(plan.getPlanName());
			return view;
		}
	}
}
