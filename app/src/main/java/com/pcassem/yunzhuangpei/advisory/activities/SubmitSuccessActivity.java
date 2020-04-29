package com.pcassem.yunzhuangpei.advisory.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pcassem.yunzhuangpei.R;

public class SubmitSuccessActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back_iv;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_success);
        initView();
        initTouchEvent();

    }

    private void initView(){
        back_iv = (ImageView) findViewById(R.id.back_iv);
        submit = (Button) findViewById(R.id.submit);
    }

    private void initTouchEvent(){
        back_iv.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.back_iv:
               finish();
               break;
           case R.id.submit:
               finish();
               break;
       }
    }
}
