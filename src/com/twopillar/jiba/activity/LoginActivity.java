
package com.twopillar.jiba.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.twopillar.jiba.R;
import com.twopillar.jiba.api.HttpCallBack;
import com.twopillar.jiba.api.JibaServerApi;
import com.twopillar.jiba.util.StringUtils;
import com.twopillar.jiba.util.ToastUtil;

/**
 * @author GuoJian'an
 * @date 2015-6-9
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
    private Button loginBtn;
    
    private EditText accountEt;//账号

    private EditText passwordEt;//密码

    private ImageView showPasswordIv;//显示密码

    private ImageView blankPasswordIv;//隐藏密码
    
    private TextView registerTv;//注册

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView()
    {
        loginBtn = (Button)findViewById(R.id.bt_login);
        loginBtn.setOnClickListener(this);

        accountEt = (EditText)findViewById(R.id.et_account);
        passwordEt = (EditText)findViewById(R.id.et_password);

        showPasswordIv = (ImageView)findViewById(R.id.show_password);
        showPasswordIv.setOnClickListener(this);

        blankPasswordIv = (ImageView)findViewById(R.id.blank_password);
        blankPasswordIv.setOnClickListener(this);
        
        registerTv = (TextView)findViewById(R.id.tv_register);
        registerTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_login:
                String account = accountEt.getText().toString();
                String password = passwordEt.getText().toString();
                if (StringUtils.isBlank(account))
                {
                    ToastUtil.showToast("账号不能为空");
                    return;
                }
                if (StringUtils.isBlank(password))
                {
                    ToastUtil.showToast("密码不能为空");
                    return;
                }
                loginBtn.setEnabled(false);
                verificationPhone(account, password);
                break;
            case R.id.show_password:
                passwordEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                blankPasswordIv.setVisibility(TextView.VISIBLE);
                showPasswordIv.setVisibility(TextView.GONE);
                break;
            case R.id.blank_password:
                passwordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showPasswordIv.setVisibility(TextView.VISIBLE);
                blankPasswordIv.setVisibility(TextView.GONE);
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            default:
                break;
        }

    }

    /**
     * 验证账号密码
     * 
     * @param account
     * @param password
     */
    private void verificationPhone(final String account, final String password)
    {
        
        JibaServerApi.getInstance(this).login(account, password, new HttpCallBack()
        {
            
            @Override
            public void onSuccess(JSONObject response)
            {
                System.out.println(response.toString());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            
            @Override
            public void onFailure(VolleyError arg0)
            {
                System.out.println(arg0.toString());
                loginBtn.setEnabled(true);
            }
        });
    }
}
