package com.example.administrator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.utils.StaticParam;
import com.example.administrator.utils.T;


public class RegisterActivity extends AppCompatActivity {
    private Context mContext;
    private Button mbtRegisterCommit;
    private EditText metName;
    private EditText metPwd;
    private EditText metPwd2;
    private RadioGroup mrgRadioGp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        InitID();
        mbtRegisterCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    public void InitID(){
        metName = findViewById(R.id.et_registerName);
        metPwd = findViewById(R.id.et_registerPwd);
        metPwd2 = findViewById(R.id.et_registerPwd2);
        mrgRadioGp = findViewById(R.id.rg_registerRole);
        mbtRegisterCommit = findViewById(R.id.bt_registerCommit);
    }

    public void checkLogin(){
        String userName = T.isInputEmpty(metName);
        String userPwd = T.isInputEmpty(metPwd);
        String userPwd2 = T.isInputEmpty(metPwd2);
        String role = T.radionButtonText(mrgRadioGp);
         if(TextUtils.isEmpty(userName)){
            metName.setError("用户名不能为空");
            return;
        }else if(TextUtils.isEmpty(userPwd)){
            metPwd.setError("密码不能为空");
            return;
        }else if(TextUtils.isEmpty(userPwd2)){
            metPwd2.setError("密码不能为空");
            return;
        }else if(!userPwd.equals(userPwd2)) {
            metPwd2.setError("俩次密码不一致");
            return;
         }else {
            startActivityForResult(new Intent(mContext,UserActivity.class), StaticParam.request_code_registerto_userctivity);
        }
        T.show(mContext,userName +"---"+ userPwd +"---"+ userPwd2 +"---"+ role);
    }
}
