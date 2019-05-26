package com.example.administrator.myclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.R;
import com.example.administrator.db.DBHelper;
import com.example.administrator.utils.MyListAdapter;
import com.example.administrator.utils.StaticParam;
import com.example.administrator.utils.T;

public class MyClassActivity extends AppCompatActivity implements View.OnClickListener{
    public Context mContext;
    private ListView mLv1;
    private Button mbtSeeClass,mbtAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);
        mLv1 = findViewById(R.id.lv_myclass);
        mLv1.setAdapter(new MyListAdapter(MyClassActivity.this));
        mContext = this;
        InitID();
        mbtSeeClass.setOnClickListener(this);
        mbtAddClass.setOnClickListener(this);
    }

    private void InitID() {
        mbtSeeClass = findViewById(R.id.bt_seeclass);
        mbtAddClass = findViewById(R.id.bt_addclass);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.bt_addclass):
//                跳转界面登录（舍弃）
                //startActivityForResult(new Intent(mContext,AddMyClassActivity.class), StaticParam.request_code_myclassto_addmyclassactivity);
                //通过alertdialog弹出对话框，进行添加课程。
                alertAddClass();

                break;
            case(R.id.bt_seeclass):
                startActivityForResult(new Intent(mContext,SeeMyClassActivity.class), StaticParam.request_code_myclassto_seemyclassactivity);
                break;
        }
    }
    public void alertAddClass(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();
        final View dialogView = View.inflate(mContext, R.layout.dialog_add_class, null);
        //设置对话框布局
        dialog.setView(dialogView);
        // 点击对话框外面不会消失，返回键起作用
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("课程添加");
        dialog.show();
        Button btnAddClass = dialogView.findViewById(R.id.bt_addClass);
        Button btnCancelAdd = dialogView.findViewById(R.id.bt_cancelAdd);
        final EditText etDay =  dialogView.findViewById(R.id.et_Day);
        final EditText etName = dialogView.findViewById(R.id.et_Name);
        final EditText etTime = dialogView.findViewById(R.id.et_Time);
        final EditText etAddress = dialogView.findViewById(R.id.et_Address);
        // 添加课程
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classDay = etDay.getText().toString().trim();
                String className = etName.getText().toString().trim();
                String classTime = etTime.getText().toString().trim();
                String classAddress = etAddress.getText().toString().trim();
                if(TextUtils.isEmpty(classAddress)||TextUtils.isEmpty(classDay)||TextUtils.isEmpty(classTime)||TextUtils.isEmpty(className)){
                    T.show(mContext,"请输入完整的课程信息");
                }else{
                    MyClass myClass = new MyClass(classDay,className,classTime,classAddress);
                    Long insertResult = DBHelper.getInstance(mContext).addClass(myClass);
                    etDay.setText("");
                    etName.setText("");
                    etTime.setText("");
                    etAddress.setText("");
                    T.show(mContext,"添加成功");
                }
            }
        });

        btnCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
