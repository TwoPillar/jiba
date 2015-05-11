package com.twopillar.jiba.activity;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.twopillar.jiba.R;
import com.twopillar.jiba.model.Action;
import com.twopillar.jiba.model.Plan;
import com.twopillar.jiba.model.PlanAction;
import com.twopillar.jiba.model.PlanDays;
import com.twopillar.jiba.util.ImageUtil;

public class RecommendPlanActivity extends Activity{
	
	private ListView lv_plan_detail;
	
	private TextView tv_title;
	
	private PlanDaysAdapter planDaysAdapter;
	
	private ImageButton ibt_back;
	
	private Button bt_add_my_plan;//加入到我的健身计划
	
	private int planId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend_plan);
		initView();
		initData();
		setlistener();
	}
	
	private void initView() {
		lv_plan_detail = (ListView)findViewById(R.id.lv_plan_detail);
		tv_title = (TextView)findViewById(R.id.tv_title);
		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
		bt_add_my_plan = (Button)findViewById(R.id.bt_add_my_plan);
	}
	
	private void initData() {
		Intent intent = getIntent();
		tv_title.setText(intent.getStringExtra("planName")); 
		planId = intent.getIntExtra("planId", 0);
		int myPlanFlag = intent.getIntExtra("myPlanFlag", 0);//判断是否来自我的计划列表
		if(myPlanFlag == 1){
			bt_add_my_plan.setVisibility(View.GONE);
		}
		List<PlanDays> planDaysList = PlanDays.where("plan_id = ?",String.valueOf(planId)).find(PlanDays.class);
		List<PlanDays> filterPlanDaysList = new ArrayList<PlanDays>();
		for(PlanDays planDays : planDaysList){
			if(!planDays.getPlanActions().isEmpty()){
				filterPlanDaysList.add(planDays);
			}
		}
		planDaysAdapter = new PlanDaysAdapter(RecommendPlanActivity.this, R.layout.item_recommend_plan_detail, filterPlanDaysList);
		lv_plan_detail.setAdapter(planDaysAdapter);
	}
	
	private void setlistener() {
		ibt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		lv_plan_detail.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				PlanDays PlanDays = (PlanDays)planDaysAdapter.getItem(position);
				Intent intent = new Intent(RecommendPlanActivity.this,RecommendPlanDetailActivity.class);
				intent.putExtra("title", PlanDays.getDescription());
				intent.putExtra("planDaysId", PlanDays.getId());
				startActivity(intent);
			}
		});
		
		bt_add_my_plan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put("ismyplan", true);
				DataSupport.update(Plan.class, values, planId);//更新我的计划列表
				Intent intent = new Intent(RecommendPlanActivity.this,MyPlanListActivity.class);
				intent.putExtra("planId", planId);
				startActivity(intent);
			}
		});
	}
	
	class PlanDaysAdapter extends ArrayAdapter<PlanDays> {
		
		private int resourceId;
		
		public PlanDaysAdapter(Context context, int resourceId,
				List<PlanDays> objects) {
			super(context, resourceId, objects);
			this.resourceId = resourceId;
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			PlanDays planDays = getItem(position);
			Holder holder;
			View view;
			if(convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(resourceId, null);
				holder = new Holder();
				holder.tv_description = (TextView)view.findViewById(R.id.tv_description);
				holder.tv_total = (TextView)view.findViewById(R.id.tv_total);
				holder.tv_day = (TextView)view.findViewById(R.id.tv_day);
				holder.iv_pic1 = (ImageView)view.findViewById(R.id.iv_pic1);
				holder.iv_pic2 = (ImageView)view.findViewById(R.id.iv_pic2);
				holder.iv_pic3 = (ImageView)view.findViewById(R.id.iv_pic3);
				view.setTag(holder);
			}else {
				view = convertView;
				holder = (Holder)view.getTag();
			}
			holder.tv_description.setText(planDays.getDescription());
			String day = FomatDay(planDays.getDay());
			holder.tv_day.setText(day);
			List<PlanAction> planActions = planDays.getPlanActions();
			holder.tv_total.setText("总共"+String.valueOf(planActions.size())+"动作");
			Action action1= Action.find(Action.class, planActions.get(0).getActionId());
			Bitmap picBitmap1 = ImageUtil.getImageFromAssetsFile(action1.getImgPath(),getResources());//根据路径读取资源文件
			holder.iv_pic1.setImageBitmap(picBitmap1);
			
			Action action2= Action.find(Action.class, planActions.get(1).getActionId());
			Bitmap picBitmap2 = ImageUtil.getImageFromAssetsFile(action2.getImgPath(),getResources());//根据路径读取资源文件
			holder.iv_pic2.setImageBitmap(picBitmap2);
			
			Action action3= Action.find(Action.class, planActions.get(2).getActionId());
			Bitmap picBitmap3 = ImageUtil.getImageFromAssetsFile(action3.getImgPath(),getResources());//根据路径读取资源文件
			holder.iv_pic3.setImageBitmap(picBitmap3);
			
			return view;
		}


		private String FomatDay(int dayNum) {
			String day = null;
			switch (dayNum) {
			case 0:
				day = "周一";
				break;
			case 1:
				day = "周二";
				break;
			case 2:
				day = "周三";
				break;
			case 3:
				day = "周四";
				break;
			case 4:
				day = "周五";
				break;
			case 5:
				day = "周六";
				break;
			case 6:
				day = "周日";
				break;
			default:
				break;
			}
			return day;
		}
		
		class Holder {
			
			TextView tv_description;
			
			TextView tv_day;
			
			TextView tv_total;
			
			ImageView iv_pic1;
			
			ImageView iv_pic2;
			
			ImageView iv_pic3;
		}
	}
		
}
