package com.example.administrator.myclass;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.R;
import com.example.administrator.db.DBHelper;
import com.example.administrator.utils.T;

public class AddMyClassActivity extends AppCompatActivity implements View.OnClickListener{
    public Context mContext;
    private MyClass myClass;
    private Button mbtAddClass;
    private EditText metClass,metAddress,metTime, metDay;
    public String day,name,address,time;
    public long insertResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_class);
        mContext = this;
        InitID();
        mbtAddClass.setOnClickListener(this);
    }
    public void InitID(){
        mbtAddClass = findViewById(R.id.bt_commit);
        metClass = findViewById(R.id.et_class);
        metAddress = findViewById(R.id.et_address);
        metTime = findViewById(R.id.et_time);
        metDay = findViewById(R.id.et_day);
    }

    @Override
    public void onClick(View view) {
        day = metDay.getText().toString().trim();
        name = metClass.getText().toString().trim();
        time = metTime.getText().toString().trim();
        address = metAddress.getText().toString().trim();
        //添加数据 todo 完成后要回到今日课程界面
        myClass = new MyClass(day,name,time,address);
        insertResult = DBHelper.getInstance(mContext).addClass(myClass);
        T.show(mContext,insertResult + "存取数据" + insertResult);

        //跳转到今日课程界面
    }

    //写数据 有话文件名称
//    public void writeData(String data){
//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = openFileOutput("userData.txt",MODE_PRIVATE);
//            fileOutputStream.write(data.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if (fileOutputStream != null){
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    // 读取数据
//    public String readData() {
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = openFileInput("userData.txt");
//            byte[] buff = new byte[1024];
//            StringBuilder sb = new StringBuilder("");
//            int len = 0;
//            while ((len = fileInputStream.read(buff)) > 0) {
//                sb.append(new String(buff), 0, len);
//            }
//            return sb.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fileInputStream != null){
//                try {
//                    fileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
}
