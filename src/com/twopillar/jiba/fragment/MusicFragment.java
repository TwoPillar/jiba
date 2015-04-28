package com.twopillar.jiba.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twopillar.jiba.R;

public class MusicFragment extends Fragment {

	private View layoutView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		layoutView = inflater.inflate(R.layout.fragment_music, null);
		
		return layoutView;
	}
}
