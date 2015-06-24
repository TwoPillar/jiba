
package com.twopillar.jiba.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.twopillar.jiba.R;
import com.twopillar.jiba.api.HttpCallBack;
import com.twopillar.jiba.api.JibaServerApi;
import com.twopillar.jiba.util.StringUtils;
import com.twopillar.jiba.util.ToastUtil;

/**
 * @author GuoJian'an
 * @date 2015-6-24
 *
 */
public class RegisterActivity extends BaseActivity implements OnClickListener{
    
    private EditText etAccountPhone;//手机账号
    private EditText etAccountPwd;//登录密码
    private Button btnRegisterConfirm;//完成注册
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    
    private void initView() {
        etAccountPhone = (EditText)findViewById(R.id.et_account_phone);
        etAccountPwd = (EditText)findViewById(R.id.et_account_pwd);
        btnRegisterConfirm = (Button)findViewById(R.id.btn_register_confirm);
        btnRegisterConfirm.setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_register_confirm:
                String phoneNum = etAccountPhone.getText().toString();
                String password = etAccountPwd.getText().toString();
                if(phoneNum.trim().isEmpty() || phoneNum.length() != 11) {
                    ToastUtil.showToast("手机格式错误");
                    return;
                }
                
                if(StringUtils.isEmpty(password)){
                    ToastUtil.showToast("密码不能为空");
                    return;
                }
                
                register(phoneNum, password);
                break;

            default:
                break;
        }
        
    }
    
    private void register(String phone, String password) {
        JibaServerApi.getInstance(this).register(phone, password, new HttpCallBack()
        {
            
            @Override
            public void onSuccess(JSONObject response)
            {   
                try
                {
                    if("001".equals(response.getString("code"))){
                        ToastUtil.showToast("该手机已经被注册！");
                        return;
                    }else {
                        ToastUtil.showToast("注册成功请登录！");
                        JSONObject jsonObject = new JSONObject(response.getString("result"));
                        int userId = jsonObject.getInt("id");
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                
            }
            
            @Override
            public void onFailure(VolleyError arg0)
            {
                System.out.println(arg0.toString());
            }
        });
    }
}
