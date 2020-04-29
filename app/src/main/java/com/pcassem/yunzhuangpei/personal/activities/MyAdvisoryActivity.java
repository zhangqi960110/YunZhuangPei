package com.pcassem.yunzhuangpei.personal.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.MyAdvisoryEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.adapter.MyAdvisoryAdapter;
import com.pcassem.yunzhuangpei.personal.presenter.AdvisoryPresenter;
import com.pcassem.yunzhuangpei.personal.view.AdvisoryView;

import java.util.ArrayList;
import java.util.List;

public class MyAdvisoryActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener, View.OnClickListener, AdvisoryView {

    ExpandableListView expandableListView;
    MyAdvisoryAdapter adapter;

    private int type;

    private List<String> groups;
    private List<List<MyAdvisoryEntity>> childs;

    private List<MyAdvisoryEntity> childOne;
    private List<MyAdvisoryEntity> childTwo;
    private List<MyAdvisoryEntity> childThree;
    private List<MyAdvisoryEntity> childFour;

    private List<MyAdvisoryEntity> consultList;

    private ImageView backIv;

    private AdvisoryPresenter presenter;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_advisory);

        initView();
        initTouchEvent();
        initData();

        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);

        presenter = new AdvisoryPresenter(this);
        presenter.onCreate();
        presenter.getMyAdvisoryList(userID);
    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.my_advisory_expandable_view);
        backIv = (ImageView) findViewById(R.id.back_iv);
    }

    private void initTouchEvent() {
        expandableListView.setOnChildClickListener(this);
        backIv.setOnClickListener(this);
    }

    private void initData() {
        groups = new ArrayList<String>();
        childs = new ArrayList<List<MyAdvisoryEntity>>();

        childOne = new ArrayList<>();
        childTwo = new ArrayList<>();
        childThree = new ArrayList<>();
        childFour = new ArrayList<>();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        type = childs.get(groupPosition).get(childPosition).getType();

        if (type == 1) {
            Intent intent = new Intent(this, ConsultCommonActivity.class);
            intent.putExtra("child_consult", childs.get(groupPosition).get(childPosition).getConsultID());
            startActivity(intent);
        }
        if (type == 2) {
            Intent intent = new Intent(this, ConsultQuickActivity.class);
            intent.putExtra("child_consult", childs.get(groupPosition).get(childPosition).getConsultID());
            startActivity(intent);
        }

        if (type == 3) {
            Intent intent = new Intent(this, ConsultOnlineActivity.class);
            intent.putExtra("child_consult", childs.get(groupPosition).get(childPosition).getConsultID());
            startActivity(intent);
        }

        if (type == 4) {
            Intent intent = new Intent(this, ConsultBookActivity.class);
            intent.putExtra("child_consult", childs.get(groupPosition).get(childPosition).getConsultID());
            startActivity(intent);
        }

        return true;
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
    public void onSuccess(ResultListEntity<MyAdvisoryEntity> status) {
        consultList = status.getResult();
        for (int i = 0; i < consultList.size(); i++) {
            if (consultList.get(i).getType() == 1) {
                childOne.add(consultList.get(i));
            }
            if (consultList.get(i).getType() == 2) {
                childTwo.add(consultList.get(i));
            }
            if (consultList.get(i).getType() == 3) {
                childThree.add(consultList.get(i));
            }
            if (consultList.get(i).getType() == 4) {
                childFour.add(consultList.get(i));
            }
        }
        childs.add(childOne);
        childs.add(childTwo);
        childs.add(childFour);
        childs.add(childThree);

        groups.add("专家解答");
        groups.add("急速咨询");
        groups.add("现场指导");
        groups.add("在线支持");

        adapter = new MyAdvisoryAdapter(this, groups, childs);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
    }
}
