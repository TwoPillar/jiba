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
import com.twopillar.jiba.activity.ActionListActivity;

public class ActionFragment extends Fragment implements OnClickListener{

	private View layoutView;
	private Button bt_thorax;//胸部
	private Button bt_shoulder;//肩部

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		layoutView = inflater.inflate(R.layout.fragment_action, null);
		bt_thorax = (Button)layoutView.findViewById(R.id.bt_thorax);
		bt_thorax.setOnClickListener(this);
		bt_shoulder = (Button)layoutView.findViewById(R.id.bt_shoulder);
		bt_shoulder.setOnClickListener(this);
		return layoutView;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bt_thorax:
			intent = new Intent(getActivity(), ActionListActivity.class);
			intent.putExtra("actionType", "thorax");
			intent.putExtra("title", "胸部");
			startActivity(intent);	
			break;
		case R.id.bt_shoulder:
			intent = new Intent(getActivity(), ActionListActivity.class);
			intent.putExtra("actionType", "shoulder");
			intent.putExtra("title", "肩部");
			startActivity(intent);	
			break;
		default:
			break;
		}
		
	}
	
}
