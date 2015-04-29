package com.twopillar.jiba.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.twopillar.jiba.R;
import com.twopillar.jiba.fragment.ActionFragment;
import com.twopillar.jiba.fragment.MusicFragment;
import com.twopillar.jiba.fragment.PlanFragment;

public class MainActivity extends FragmentActivity{

	private FragmentTabHost mTabHost;
	private RadioGroup m_radioGroup;
	String tabs[] = {"Tab1","Tab2","Tab3"};
	Class cls[] = {PlanFragment.class,ActionFragment.class,MusicFragment.class};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragment_tabs);
		Log.v("jiba", "11111");
		initView();
	}

	private void initView()
	{
		mTabHost = (FragmentTabHost)this.findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.getTabWidget().setVisibility(View.GONE);
		for(int i=0;i<tabs.length;i++){
			mTabHost.addTab(mTabHost.newTabSpec(tabs[i]).setIndicator(tabs[i]),cls[i], null);
		}
		m_radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		m_radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.plan:
					mTabHost.setCurrentTabByTag(tabs[0]);
					break;
				case R.id.action:
					mTabHost.setCurrentTabByTag(tabs[1]);
					break;
				case R.id.music:
					mTabHost.setCurrentTabByTag(tabs[2]);
					break;
				}
			}
		});

		((RadioButton) m_radioGroup.getChildAt(0)).toggle();
	}


}
