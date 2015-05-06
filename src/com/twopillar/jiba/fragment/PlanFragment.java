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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twopillar.jiba.R;
import com.twopillar.jiba.activity.MakePlanActivity;
import com.twopillar.jiba.model.Plan;
import com.twopillar.jiba.model.PlanDays;
import com.twopillar.jiba.util.DateUtil;

public class PlanFragment extends Fragment {
	
	private View layoutView;
	
	private TextView tv_plan_type;//计划类型
	
	private int planFlag;//1制定计划，2开始训练，3休息
	
	private RelativeLayout rl_plan;
	
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
		rl_plan = (RelativeLayout)layoutView.findViewById(R.id.rl_plan);
		tv_plan_type = (TextView)layoutView.findViewById(R.id.tv_plan_type);
		switch (planFlag) {
		case 1://制定计划
			tv_plan_type.setText("制定计划");
			break;	
		case 2://开始训练
			tv_plan_type.setText("开始训练");
			break;
		case 3://休息
			tv_plan_type.setText("休息");
			break;
		}
	}
	
	public void setListener() {
		
		rl_plan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(planFlag == 1) {
					Intent intent = new Intent(getActivity(), MakePlanActivity.class);
					startActivity(intent);
				}
			}
		});
	}
	
	public void initData() {
		Connector.getDatabase();

		List<Plan> StartPlan = DataSupport.where("start = ?","1").find(Plan.class);//查询启动的计划
		if(StartPlan.isEmpty()) {
			planFlag = 1;
		}else {
			int planId = StartPlan.get(0).getId();
			int day = DateUtil.getWeekOfDate(new Date());
			List<PlanDays> planDays = DataSupport.where("day = ? and plan_id = ?",String.valueOf(day),String.valueOf(planId))
											  	 .find(PlanDays.class);//查询当前日期所对应的计划明细
			if(planDays.get(0).getType() == "ACTION") {
				planFlag = 2;
			}
			else if(planDays.get(0).getType() == "REST") {
				planFlag = 3;
			}
		}	
	}
}
