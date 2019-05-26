package com.example.administrator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.utils.StaticParam;
import com.example.administrator.utils.T;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText metName;
    private EditText metPwd;
    public Context mContext;
    public static String userName;
    public static String userPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        InitID();
    }

    public void InitID(){
        metName = findViewById(R.id.et_loginName);
        metPwd = findViewById(R.id.et_loginPass);
        Button mbtRegister = findViewById(R.id.bt_register);
        mbtRegister.setOnClickListener(this);
        Button mbtLogin = findViewById(R.id.bt_login);
        mbtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register://注册
                startActivityForResult(new Intent(mContext, RegisterActivity.class), StaticParam.request_code_loginto_registeactivity);
                 break;
            case R.id.bt_login://登录
                startActivityForResult(new Intent(mContext, UserActivity.class), StaticParam.request_code_loginto_useractivity);

//                userName = T.isInputEmpty(metName);
//                userPwd = T.isInputEmpty(metPwd);
//                T.show(mContext,userName +"--"+ userPwd);
//                if(TextUtils.isEmpty(userName)){
//                    metName.setError("请输入用户名");
//                    return;
//                }else if(TextUtils.isEmpty(userPwd)){
//                    metPwd.setError("请输入密码");
//                    return;
//                }else{
//                    startActivityForResult(new Intent(mContext, UserActivity.class), StaticParam.request_code_loginto_useractivity);
//                    startActivityForResult(new Intent(mContext, UserActivity.class), StaticParam.request_code_loginto_useractivity);
//                }
                break;
        }
    }
}
