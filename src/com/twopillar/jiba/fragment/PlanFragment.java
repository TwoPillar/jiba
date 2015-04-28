package com.twopillar.jiba.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.twopillar.jiba.R;

public class PlanFragment extends Fragment {
	
	private View layoutView;
	
	private Button bt_plan;
	
	private int planFlag;//1制定计划，2开始训练，3休息
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		layoutView = inflater.inflate(R.layout.fragment_plan, null);
		initView();
		setListener();
		return layoutView;
	}
	
	public void initView() {
		bt_plan = (Button)layoutView.findViewById(R.id.bt_plan);
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
		
	}
}
