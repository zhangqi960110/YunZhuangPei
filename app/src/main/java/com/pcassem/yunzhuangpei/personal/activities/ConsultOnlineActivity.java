package com.pcassem.yunzhuangpei.personal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.ConsultOnlineEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.presenter.ConsultPresenter;
import com.pcassem.yunzhuangpei.personal.view.ConsultOnlineView;

import java.util.List;

public class ConsultOnlineActivity extends AppCompatActivity implements ConsultOnlineView,View.OnClickListener {

    private ImageView back_iv;

    private TextView specialist_name;
    private TextView create_date;
    private TextView online_time;

    private ConsultPresenter presenter;
    private List<ConsultOnlineEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_consult_online);
        initView();
        initTouchEvent();

        presenter = new ConsultPresenter(this);
        presenter.onCreate();
        presenter.onlineConsult(this.getIntent().getIntExtra("child_consult",-1));
    }

    private void initView(){
        back_iv = (ImageView) findViewById(R.id.back_iv);

        specialist_name = (TextView) findViewById(R.id.specialist_name);
        create_date = (TextView) findViewById(R.id.create_date);
        online_time = (TextView) findViewById(R.id.online_time);
    }

    private void initTouchEvent(){
        back_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<ConsultOnlineEntity> status) {
        if (status.getCode() == 0){
            data = status.getResult();
            specialist_name.setText(data.get(0).getSpecialistName());
            create_date.setText(data.get(0).getCreateDate());
            online_time.setText(data.get(0).getOnlineTime() / 60 + "分" + data.get(0).getOnlineTime() % 60 + "秒");
        }else {
            Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
    }

}
