package com.twopillar.jiba.activity;

import java.util.Calendar;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.twopillar.jiba.R;
import com.twopillar.jiba.model.Plan;
import com.twopillar.jiba.util.ActivityCollector;

public class SetStartDateActivity extends BaseActivity{
	
	private DatePicker date_picker;
	
	private TextView tv_title;
	
	private ImageButton ibt_back;
	
	private int year;
	
	private int month;
	
	private int day;
	
	private Button bt_ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_start_date);
		initView();
		initData();
		setListener();
	}
	
	private void initView() {
		date_picker = (DatePicker)findViewById(R.id.date_picker);
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText("设置启动时间");
		
		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
		bt_ok = (Button)findViewById(R.id.bt_ok);
	}
	
	private void initData() {
	  
	}
	
	private void setListener() {
		ibt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		bt_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			     comparingDate(Calendar.getInstance());
			}

			private void comparingDate(Calendar now) {
				year = date_picker.getYear();
				month = date_picker.getMonth();
				day = date_picker.getDayOfMonth();
		     
				int nowYear = now.get(Calendar.YEAR);
				int nowMonth = now.get(Calendar.MONTH);
				int nowDay = now.get(Calendar.DAY_OF_MONTH);
				if(nowYear > year) {
					Toast.makeText(SetStartDateActivity.this, "必须大于当前年份", Toast.LENGTH_SHORT).show();
				}else {
					if(nowMonth > month) {
						Toast.makeText(SetStartDateActivity.this, "必须大于当前月份", Toast.LENGTH_SHORT).show();
					}else {
						if(nowDay > day) {
							Toast.makeText(SetStartDateActivity.this, "必须大于当前日期", Toast.LENGTH_SHORT).show();
						}else {
							List<Plan> oldStartPlan = DataSupport.where("start = ?","1").find(Plan.class);//查询启动的计划
							ContentValues values = new ContentValues();
							if(!oldStartPlan.isEmpty()) {
								values.put("start", false);
								values.put("currentDay", 0);
								values.put("startdate", 0);
								DataSupport.update(Plan.class, values, oldStartPlan.get(0).getId());//把旧的启动计划关闭
							}
							int planId = getIntent().getIntExtra("planId", 0);
							values.clear();
							values.put("start", true);
							values.put("currentDay", 1);
							values.put("startdate", String.valueOf(year)+String.valueOf(month)+String.valueOf(day));
							DataSupport.update(Plan.class, values, planId);//启动新的计划
							ActivityCollector.finshAll();
							Intent intent = new Intent(SetStartDateActivity.this,MainActivity.class);
							startActivity(intent);
						} 
					}
				}
			}
		});
	}
}
