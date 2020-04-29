package com.pcassem.yunzhuangpei.personal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.ConsultBookEntity;
import com.pcassem.yunzhuangpei.entity.ConsultEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.adapter.ConsultBookAdapter;
import com.pcassem.yunzhuangpei.personal.adapter.ConsultCommonAdapter;
import com.pcassem.yunzhuangpei.personal.presenter.ConsultPresenter;
import com.pcassem.yunzhuangpei.personal.view.ConsultBookView;
import com.pcassem.yunzhuangpei.personal.view.ConsultView;

import java.util.ArrayList;
import java.util.List;

public class ConsultBookActivity extends AppCompatActivity implements View.OnClickListener, ConsultBookView {

    ExpandableListView expandableListView;
    ConsultBookAdapter adapter;

    private List<String> groups;
    private List<List<ConsultBookEntity>> childs;

    private ImageView backIv;

    private ConsultPresenter presenter;
    private List<ConsultBookEntity> consultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_consult_book);

        initView();
        initTouchEvent();
        initData();

        presenter = new ConsultPresenter(this);
        presenter.onCreate();
        presenter.bookConsult(this.getIntent().getIntExtra("child_consult",-1));

    }

    private void initView() {
        backIv = (ImageView) findViewById(R.id.back_iv);
        expandableListView = (ExpandableListView) findViewById(R.id.my_advisory_details_expandable_view);
    }

    private void initTouchEvent() {
        backIv.setOnClickListener(this);
    }

    private void initData() {
        groups = new ArrayList<String>();
        childs = new ArrayList<List<ConsultBookEntity>>();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<ConsultBookEntity> status) {
        if (status.getCode() == 0){
            consultList = status.getResult();

            groups.add("问题信息");
            groups.add("专家答复");

            for (int i = 0; i < 2; i++) {
                childs.add(consultList);
            }

            adapter = new ConsultBookAdapter(this, groups, childs);
            expandableListView.setAdapter(adapter);
        }else {
            Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError() {

    }
}
