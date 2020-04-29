package com.pcassem.yunzhuangpei.advisory.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.adapter.SelectProjectListAdapter;
import com.pcassem.yunzhuangpei.entity.MyProjectEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.presenter.ProjectPresenter;
import com.pcassem.yunzhuangpei.personal.view.ProjectListView;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import static com.pcassem.yunzhuangpei.advisory.activities.BookActivity.SELECT_PROJECT_CODE;
import static com.pcassem.yunzhuangpei.advisory.activities.ExportAnswerActivity.SELECT_PROJECT;

public class SelectProjectActivity extends AppCompatActivity implements View.OnClickListener, SelectProjectListAdapter.ButtonInterface, ProjectListView {

    private RecyclerView mRecyclerView;
    private SelectProjectListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<MyProjectEntity> mMyProjectList;
    private ProjectPresenter presenter;

    private ImageView backIv;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_project);
        initView();
        initTouchEvent();
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        presenter = new ProjectPresenter(this);
        presenter.onCreate();
        presenter.getMyProjectList(userID);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        presenter.getMyProjectList(userID);
    }

    private void initView() {
        backIv = (ImageView) findViewById(R.id.back_iv);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mMyProjectList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initTouchEvent() {
        backIv.setOnClickListener(this);
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
    public void onItemClick(View view, final int position) {
        Intent intent = new Intent();
        intent.putExtra("project_id", mMyProjectList.get(position).getProjectID());
        intent.putExtra("project_name", mMyProjectList.get(position).getName());
        intent.putExtra("project_address", mMyProjectList.get(position).getProvinceCode() + "-" + mMyProjectList.get(position).getCityCode() + "-" + mMyProjectList.get(position).getAreaCode());
        SelectProjectActivity.this.setResult(SELECT_PROJECT_CODE, intent);
        SelectProjectActivity.this.finish();
    }

    @Override
    public void onGetProjectSuccess(ResultListEntity<MyProjectEntity> userInfo) {
        mMyProjectList = userInfo.getResult();
        if (mMyProjectList == null) {
            Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mAdapter == null) {
            mAdapter = new SelectProjectListAdapter(this, mMyProjectList);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.buttonSetOnClick(this);
        }
        mAdapter.updateData(mMyProjectList);
    }

    @Override
    public void onGetProjectError() {
        Toast.makeText(this, "请求数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddProjectSuccess(ResultListEntity<String> status) {

    }

    @Override
    public void onAddProjectError() {
    }


    @Override
    public void onDeleteProjectSuccess(ResultListEntity<String> status) {

    }

    @Override
    public void onDeleteProjectError() {
        Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditProjectSuccess(ResultListEntity<String> status) {

    }

    @Override
    public void onEditProjectError() {

    }

}
