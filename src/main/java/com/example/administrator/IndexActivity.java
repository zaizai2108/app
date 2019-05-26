package com.example.administrator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.db.DBConfig;
import com.example.administrator.utils.StaticParam;
import com.example.administrator.utils.T;

import static com.example.administrator.utils.StaticParam.request_code_indexto_loginactivity;


public class IndexActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private Button mbtnGuess, mbtnJump;
    private EditText metJudge;
    private TextView mtvDescribe;
    public String inputNum;
    int num = T.randomNum();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mContext = this;
        if (Build.VERSION.SDK_INT >= 23) {
            //我是在Fragment里写代码的，因此调用getActivity
            //如果不想判断SDK，可以使用ActivityCompat的接口来检查和申请权限
            int hasReadContactsPermission = mContext.checkSelfPermission(
                    android.Manifest.permission.READ_CONTACTS);

            if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
                //这里就会弹出对话框
                requestPermissions(
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }

        initId();
        new DBConfig();
    }

    public void initId(){
        mbtnGuess = findViewById(R.id.bt_guess);
        mbtnGuess.setOnClickListener(this);
        mbtnJump = findViewById(R.id.bt_jump);
        metJudge = findViewById(R.id.et_judgeNumber);
        mtvDescribe = findViewById(R.id.tv_describe);
        mbtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(mContext, LoginActivity.class), StaticParam.request_code_indexto_loginactivity);
            }
        });
    }
    @Override
    public void onClick(View view) {
        if (metJudge.getText().toString().trim().equals("") ){
            T.show(mContext,"请输入数字");
            metJudge.setText("");
        }else{
            inputNum = metJudge.getText().toString().trim();
            if (T.isInteger(inputNum)){
                switchCase(view,  Integer.parseInt(inputNum), metJudge);
            }else{
                T.show(mContext, "请输入数字");
                metJudge.setText("");
            }
        }
    }

    public void switchCase(View view, int inputNum, EditText et){
        switch (view.getId()) {
            case R.id.bt_guess://
                if(inputNum > num){
                    T.show(mContext, "大了");
                    et.setText("");
                }else if(inputNum < num){
                    T.show(mContext, "小了");
                    et.setText("");
                }else{
                    T.show(mContext, "恭喜你对了 再来一次");
                    num = T.randomNum();
                    // 跳转到登录界面
                    //startActivityForResult(new Intent(mContext, LoginActivity.class), StaticParam.request_code_indexto_loginactivity);
                }
                break;
        }
    }
}
