package com.example.administrator.myclass;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.R;
import com.example.administrator.utils.allClassListAdapter;

public class SeeMyClassActivity extends AppCompatActivity implements View.OnClickListener {
    public Context mContext;
    private ListView mlv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_class);
        mContext = this;
        InitID();
        mlv = findViewById(R.id.lv_allclass);
        mlv.setAdapter(new allClassListAdapter(SeeMyClassActivity.this));
    }

    private void InitID() {
    }

    @Override
    public void onClick(View v) {
    }

}
