package com.pcassem.yunzhuangpei.personal.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.UserEntity;
import com.pcassem.yunzhuangpei.home.activities.HomeActivity;

import io.rong.imkit.RongIM;

public class SystemSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backIv;
    private LinearLayout logoutLlt;
    private RelativeLayout logout;

    private TextView new_password;
    private TextView confirm_new_password;
    private TextView confirm_save;


    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_settings);
        initView();
        initTouchEvent();
        String username = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString("username","");
        if (username == ""){
            logoutLlt.setVisibility(View.GONE);
        }else {
            logoutLlt.setVisibility(View.VISIBLE);
        }
    }

    private void initView(){
        backIv = (ImageView) findViewById(R.id.back_iv);
        logoutLlt = (LinearLayout) findViewById(R.id.logout_linearlayout);
        logout = (RelativeLayout) findViewById(R.id.logout);
        new_password = (TextView) findViewById(R.id.new_password);
        confirm_new_password = (TextView) findViewById(R.id.confirm_new_password);
        confirm_save = (TextView) findViewById(R.id.confirm_save);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
        logout.setOnClickListener(this);
        new_password.setOnClickListener(this);
        confirm_new_password.setOnClickListener(this);
        confirm_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.logout:
                mSharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.remove("type");
                editor.remove("userID");
                editor.remove("username");
                editor.commit();
                RongIM.getInstance().logout();
                Intent intent = new Intent(SystemSettingsActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.new_password:
                Toast.makeText(this, "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.confirm_new_password:
                Toast.makeText(this, "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.confirm_save:
                Toast.makeText(this, "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
