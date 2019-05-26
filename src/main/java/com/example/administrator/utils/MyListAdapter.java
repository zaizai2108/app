package com.example.administrator.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.R;
import com.example.administrator.myclass.MyClass;
import com.example.administrator.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/16 0016.
 */

public class MyListAdapter extends BaseAdapter {

    private ArrayList<MyClass> classData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public MyListAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        classData= DBHelper.getInstance(mContext).getOneDayClass();
    }

    @Override
    public int getCount() {
        return classData.size();
    }

    @Override
    public Object getItem(int i) {
        return classData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder{
        public ImageView imageView;
        public TextView tvDay,tvTitle,tvTime,tvAddress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item,null);
            holder = new ViewHolder();
            holder.tvDay = convertView.findViewById(R.id.tv_day);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvAddress = convertView.findViewById(R.id.tv_address);
            holder.tvTime = convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //给控件赋值
        holder.tvDay.setText(classData.get(position).day);
        holder.tvTitle.setText(classData.get(position).name);
        holder.tvAddress.setText(classData.get(position).address);
        holder.tvTime.setText(classData.get(position).time);
        // Glide.with().load().into(holder.imageView);   加载百度图片
        return convertView;
    }
}
