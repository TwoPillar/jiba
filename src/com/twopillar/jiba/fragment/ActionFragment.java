package com.twopillar.jiba.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twopillar.jiba.R;
import com.twopillar.jiba.adapter.ActionAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class ActionFragment extends Fragment {
    
    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private ActionAdapter mAdapter ;
    private View layoutView;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		layoutView = inflater.inflate(R.layout.fragment_action, null);
		mViewPager = (ViewPager) layoutView.findViewById(R.id.id_viewpager);
        mTabPageIndicator = (TabPageIndicator) layoutView.findViewById(R.id.id_indicator);
        mAdapter = new ActionAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        
        mTabPageIndicator.setViewPager(mViewPager, 0);
		return layoutView;
	}

	
}
