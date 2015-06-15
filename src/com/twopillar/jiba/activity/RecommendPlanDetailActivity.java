package com.twopillar.jiba.activity;

import java.util.List;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.twopillar.jiba.R;
import com.twopillar.jiba.model.Action;
import com.twopillar.jiba.model.PlanAction;
import com.twopillar.jiba.model.PlanDays;
import com.twopillar.jiba.util.ImageUtil;

public class RecommendPlanDetailActivity extends BaseActivity{
	
	private ListView lv_actions;
	
	private TextView tv_title;
	
	private ImageButton ibt_back;
	
	private PlanDays planDays;
	
	private ActionAdapter actionAdapter;
	
	private List<PlanAction> planActions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend_plan_detail);
		initView();
		initData();
		setlistener();
	}
	
	private void initView() {
		lv_actions = (ListView)findViewById(R.id.lv_actions);
		tv_title = (TextView)findViewById(R.id.tv_title);
		ibt_back = (ImageButton)findViewById(R.id.ibt_back);
	}
	
	private void initData() {
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		tv_title.setText(title);
		
		int planDaysId = intent.getIntExtra("planDaysId", 0);
		planDays = PlanDays.find(PlanDays.class, planDaysId);
		planActions = planDays.getPlanActions();
		actionAdapter = new ActionAdapter(RecommendPlanDetailActivity.this, R.layout.item_plan_action, planActions);
		lv_actions.setAdapter(actionAdapter);
	}
	
	private void setlistener() {
		ibt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		lv_actions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				PlanAction planAction = planActions.get(position);
				Action action = Action.find(Action.class, planAction.getActionId());
				Intent intent = new Intent(RecommendPlanDetailActivity.this,ActionDetailActivity.class);
				/*intent.putExtra("imgPath", action.getImgPath());*/
				intent.putExtra("title", action.getActionName());
				intent.putExtra("description", action.getDescription());
				startActivity(intent);
				
			}
		});
	}
	
	class ActionAdapter extends ArrayAdapter<PlanAction> {
		
		private int resourceId;
		
		public ActionAdapter(Context context, int resourceId,
				List<PlanAction> objects) {
			super(context, resourceId, objects);
			this.resourceId = resourceId;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			PlanAction planAction = getItem(position);
			Holder holder;
			View view;
			if(convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(resourceId, null);
				holder = new Holder();
				holder.tv_seq = (TextView)view.findViewById(R.id.tv_seq);
				holder.tv_action_name = (TextView)view.findViewById(R.id.tv_action_name);
				holder.tv_muscle = (TextView)view.findViewById(R.id.tv_muscle);
				holder.iv_action_pic = (ImageView)view.findViewById(R.id.iv_action_pic);
				holder.tv_reps_sets = (TextView)view.findViewById(R.id.tv_reps_sets);
				view.setTag(holder);
			}else {
				view = convertView;
				holder = (Holder)view.getTag();
			}
			holder.tv_seq.setText(String.valueOf(planAction.getSequence()+1));
			Action action = Action.find(Action.class, planAction.getActionId());
			holder.tv_action_name.setText(action.getActionName());
			holder.tv_muscle.setText(action.getBigType());
	/*		Bitmap bm = ImageUtil.getImageFromAssetsFile(action.getImgPath(), getResources());*/
			/*holder.iv_action_pic.setImageBitmap(bm);*/
			holder.tv_reps_sets.setText(planAction.getSets()+"x"+planAction.getReps());
			return view;
		}
		
		class Holder {
			TextView tv_seq;
			ImageView iv_action_pic;
			TextView tv_action_name;
			TextView tv_muscle;
			TextView tv_reps_sets;
		}
		
	}
}
