package com.example.administrator.utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/5/13 0013.
 */

public class T {

    // toast
    public static void show(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    // 判断是否是整数
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    // 随机数
    public static int randomNum(){
        Random rand = new Random();
        int num = rand.nextInt(50)+1;
        return num;
    }

    // 判断输入是否为空
    public static String isInputEmpty(EditText et){
        String str = et.getText().toString().trim();
        if (str.equals("")){
            et.setError("请输入内容");
            return "";
        }
        return str;
    }
    // 获取radionButton的内容
    public static String radionButtonText(RadioGroup mrgRadioGp){
        String role = "";
        for(int i = 0 ;i < mrgRadioGp.getChildCount();i++){
            RadioButton rb = (RadioButton)mrgRadioGp.getChildAt(i);
            if(rb.isChecked()){
                role = rb.getText().toString().trim();
                break;
            }
        }
        return role;
    }
}
