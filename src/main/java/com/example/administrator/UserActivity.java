package com.example.administrator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.myclass.MyClassActivity;
import com.example.administrator.utils.StaticParam;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    public Context mContext;
    private RelativeLayout mrlMyClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mContext = this;
        InitID();
    }

    public void InitID(){
        mrlMyClass = findViewById(R.id.rl_myclass);
        mrlMyClass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.rl_myclass:
                startActivityForResult(new Intent(mContext, MyClassActivity.class), StaticParam.request_code_useractivityto_myclassactivity);
                break;
        }
    }

}
