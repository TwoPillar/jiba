package com.twopillar.jiba.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.twopillar.jiba.R;
import com.twopillar.jiba.fragment.ActionFragment;
import com.twopillar.jiba.fragment.BBSFragment;
import com.twopillar.jiba.fragment.MeFragment;
import com.twopillar.jiba.fragment.PlanFragment;

public class MainActivity extends BaseActivity{

	private FragmentTabHost mTabHost;
	
	private RadioGroup radioGroup;
	
	private RadioButton plan;//计划
	private RadioButton action;//动作
	private RadioButton bbs;//论坛
	private RadioButton me;//我
	
	private long exitTime = 0;
	String tabs[] = {"Tab1","Tab2","Tab3","Tab4"};
	Class cls[] = {PlanFragment.class,ActionFragment.class,BBSFragment.class,MeFragment.class};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragment_tabs);
		initView();
	}

	private void initView() {
	    plan = (RadioButton)findViewById(R.id.plan);
	    action = (RadioButton)findViewById(R.id.action);
	    bbs = (RadioButton)findViewById(R.id.bbs);
	    me = (RadioButton)findViewById(R.id.me);
	    
		mTabHost = (FragmentTabHost)this.findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.getTabWidget().setVisibility(View.GONE);
		for(int i=0;i<tabs.length;i++){
			mTabHost.addTab(mTabHost.newTabSpec(tabs[i]).setIndicator(tabs[i]),cls[i], null);
		}
		radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.plan:
				    changeSeletedButton(0);
					mTabHost.setCurrentTabByTag(tabs[0]);
					break;
				case R.id.action:
                    changeSeletedButton(1);
                    mTabHost.setCurrentTabByTag(tabs[1]);
					break;
				case R.id.bbs:
				    changeSeletedButton(2);
					mTabHost.setCurrentTabByTag(tabs[2]);
					break;
				case R.id.me:
				    changeSeletedButton(3);
                    mTabHost.setCurrentTabByTag(tabs[3]);
                    break;
				}
			}
		});

		((RadioButton)radioGroup.getChildAt(0)).toggle();
	}
	
	private void changeSeletedButton(int type) {
	    Drawable drawable = null;
	     switch (type) {
            case 0:
                drawable = getResources().getDrawable(R.drawable.icon_plan_selected);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                plan.setCompoundDrawables(null, drawable, null, null);
                plan.setTextColor(Color.parseColor("#FFDA44"));
                
                drawable = getResources().getDrawable(R.drawable.icon_action);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                action.setCompoundDrawables(null, drawable, null, null);
                action.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_bbs);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                bbs.setCompoundDrawables(null, drawable, null, null);
                bbs.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_me);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                me.setCompoundDrawables(null, drawable, null, null);
                me.setTextColor(Color.parseColor("#595959"));
                break;
            case 1:
                drawable = getResources().getDrawable(R.drawable.icon_action_selected);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                action.setCompoundDrawables(null, drawable, null, null);
                action.setTextColor(Color.parseColor("#FFDA44"));
                
                drawable = getResources().getDrawable(R.drawable.icon_plan);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                plan.setCompoundDrawables(null, drawable, null, null);
                plan.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_bbs);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                bbs.setCompoundDrawables(null, drawable, null, null);
                bbs.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_me);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                me.setCompoundDrawables(null, drawable, null, null);
                me.setTextColor(Color.parseColor("#595959"));
                break;
            case 2:
                drawable = getResources().getDrawable(R.drawable.icon_bbs_selected);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                bbs.setCompoundDrawables(null, drawable, null, null);
                bbs.setTextColor(Color.parseColor("#FFDA44"));
                
                drawable = getResources().getDrawable(R.drawable.icon_plan);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                plan.setCompoundDrawables(null, drawable, null, null);
                plan.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_action);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                action.setCompoundDrawables(null, drawable, null, null);
                action.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_me);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                me.setCompoundDrawables(null, drawable, null, null);
                me.setTextColor(Color.parseColor("#595959"));
                break;
            case 3:
                drawable = getResources().getDrawable(R.drawable.icon_me_selected);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                me.setCompoundDrawables(null, drawable, null, null);
                me.setTextColor(Color.parseColor("#FFDA44"));
                
                drawable = getResources().getDrawable(R.drawable.icon_plan);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                plan.setCompoundDrawables(null, drawable, null, null);
                plan.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_action);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                action.setCompoundDrawables(null, drawable, null, null);
                action.setTextColor(Color.parseColor("#595959"));
                
                drawable = getResources().getDrawable(R.drawable.icon_bbs);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                bbs.setCompoundDrawables(null, drawable, null, null);
                bbs.setTextColor(Color.parseColor("#595959"));
                break;

            default:
                break;
        }
        
     }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
				getSupportFragmentManager().popBackStack();
			} else {
				ExitApp();
			}
		}
		return false;
	}

	// 返回键双击退出APP
	public void ExitApp() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}



}
