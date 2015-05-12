package com.twopillar.jiba.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.twopillar.jiba.R;
import com.twopillar.jiba.util.StringUtils;

public class MyInfoActivity extends BaseActivity {
	
	private EditText et_height;
	
	private EditText et_weight;
	
	private RadioGroup sexRadioGroup;
	
	private RadioButton sexRadioButton;
	
	private String height;
	
	private String weight;
	
	private String sex;
	
	private Button bt_next;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		initView();
		setListener();
	}
	
	public void initView() {
		et_height = (EditText)findViewById(R.id.et_height);
		et_weight = (EditText)findViewById(R.id.et_weight);
		sexRadioGroup = (RadioGroup)findViewById(R.id.sex);
		sexRadioButton = (RadioButton)findViewById(sexRadioGroup.getCheckedRadioButtonId());
		bt_next = (Button)findViewById(R.id.bt_next);

	}
	
	public void setListener() {
		sexRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				 int radioButtonId = group.getCheckedRadioButtonId();
				 sexRadioButton = (RadioButton)findViewById(radioButtonId);
				 sex = sexRadioButton.getText().toString();
			}
		});
		
		bt_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				height = et_height.getText().toString().trim();
				weight = et_weight.getText().toString().trim();
				sex = sexRadioButton.getText().toString();
				Intent intent = new Intent(MyInfoActivity.this,MyInfoActivity.class);
				if(StringUtils.isBlank(height)) {
					Toast.makeText(MyInfoActivity.this, "请输入身高", Toast.LENGTH_SHORT).show();
					return;
				}
				if(StringUtils.isBlank(weight)) {
					Toast.makeText(MyInfoActivity.this, "请输入体重", Toast.LENGTH_SHORT).show();
					return;
				}
				intent.putExtra("height", height);
				intent.putExtra("weight", weight);
				intent.putExtra("sex", sex);
				startActivity(intent);
			}
		});
	}
}
