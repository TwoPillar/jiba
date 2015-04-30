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
import com.twopillar.jiba.activity.ActionDetailActivity;

public class ActionFragment extends Fragment {

	private View layoutView;
	private Button bt_thorax;//胸部

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		layoutView = inflater.inflate(R.layout.fragment_action, null);
		bt_thorax = (Button)layoutView.findViewById(R.id.bt_thorax);
		bt_thorax.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ActionDetailActivity.class);
				intent.putExtra("bigType", "thorax");
				startActivity(intent);	
			}
		});
		return layoutView;
	}
	
}
