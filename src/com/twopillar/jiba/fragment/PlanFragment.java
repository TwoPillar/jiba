package com.twopillar.jiba.fragment;

import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.twopillar.jiba.R;
import com.twopillar.jiba.model.Plan;
import com.twopillar.jiba.model.PlanDays;
import com.twopillar.jiba.util.DateUtil;

public class PlanFragment extends Fragment {
	
	private View layoutView;
	
	private Button bt_plan;//制定计划
	
	private Button bt_start;//开始
	
	private TextView tv_rest;//休息
	
	private int planFlag;//1制定计划，2开始训练，3休息
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		layoutView = inflater.inflate(R.layout.fragment_plan, null);
		initView();
		initData();
		setListener();
		return layoutView;
	}
	
	public void initView() {
		bt_plan = (Button)layoutView.findViewById(R.id.bt_plan);
		bt_start = (Button)layoutView.findViewById(R.id.bt_start);
		tv_rest = (TextView)layoutView.findViewById(R.id.tv_rest);
		switch (planFlag) {
		case 1:
			bt_plan.setVisibility(View.VISIBLE);
			bt_start.setVisibility(View.GONE);
			tv_rest.setVisibility(View.GONE);
			break;	
		case 2:
			bt_start.setVisibility(View.VISIBLE);
			bt_plan.setVisibility(View.GONE);
			tv_rest.setVisibility(View.GONE);
			break;
		case 3:	
			tv_rest.setVisibility(View.VISIBLE);
			bt_start.setVisibility(View.GONE);
			bt_plan.setVisibility(View.GONE);
			break;
		}
	}
	
	public void setListener() {
		
		bt_plan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
			}
		});
	}
	
	public void initData() {
		Connector.getDatabase();
		List<Plan> plans = DataSupport.findAll(Plan.class);
		if(plans.isEmpty()) {//
			planFlag = 1;
		}else {
			List<Plan> StartPlan = DataSupport.where("start = ?","1").find(Plan.class);
			int planId = StartPlan.get(0).getId();
			int day = DateUtil.getWeekOfDate(new Date());
			List<PlanDays> planDays = DataSupport.where("day = ? and plan_id = ?",String.valueOf(day),String.valueOf(planId)).find(PlanDays.class);
			if(planDays.get(0).getType() == "ACTION") {
				planFlag = 2;
			}
			else if(planDays.get(0).getType() == "REST") {
				planFlag = 3;
			}
		}
	}
}
