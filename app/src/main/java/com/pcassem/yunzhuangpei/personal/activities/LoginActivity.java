package com.pcassem.yunzhuangpei.personal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.fragments.LoginFragment;
import com.pcassem.yunzhuangpei.personal.fragments.RegisterFragment;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "LoginActivity";

    private Button title_left_btn , title_right_btn;
    private ImageView backIv;
    private TextView title;

    /**
     * Fragment管理器
     */
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private RegisterFragment mLFragment ;
    private LoginFragment mRFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initTouchEvent();
    }

    private void initView() {

        backIv = (ImageView) findViewById(R.id.back_iv);
        title = (TextView) findViewById(R.id.title);

        title_left_btn = (Button)findViewById(R.id.constact_group);
        title_right_btn = (Button)findViewById(R.id.constact_all);

        title_right_btn.setOnClickListener(this);
        title_left_btn.setOnClickListener(this);
        title_right_btn.performClick();

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mRFragment = new LoginFragment();
        mTransaction.replace(R.id.id_content, mRFragment);
        mTransaction.commit();
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.constact_group:
                title.setText("注册");
                if(title_left_btn.isEnabled()){
                    title_left_btn.setEnabled(false);
                    title_right_btn.setEnabled(true);
                }
                mFragmentManager = getSupportFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                if(mLFragment == null){
                    mLFragment = new RegisterFragment();
                }
                mTransaction.replace(R.id.id_content, mLFragment);
                mTransaction.commit();
                break;

            case R.id.constact_all:
                title.setText("登录");
                if(title_right_btn.isEnabled()){
                    title_left_btn.setEnabled(true);
                    title_right_btn.setEnabled(false);
                }
                mFragmentManager = getSupportFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                if(mRFragment == null){
                    mRFragment = new LoginFragment();
                }
                mTransaction.replace(R.id.id_content, mRFragment);
                mTransaction.commit();
                break;
        }
    }

}

