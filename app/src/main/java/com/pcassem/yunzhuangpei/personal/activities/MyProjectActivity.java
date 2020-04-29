package com.pcassem.yunzhuangpei.personal.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.MyProjectEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.adapter.MyProjectAdapter;
import com.pcassem.yunzhuangpei.personal.bean.ProjectIDBean;
import com.pcassem.yunzhuangpei.personal.presenter.ProjectPresenter;
import com.pcassem.yunzhuangpei.personal.view.ProjectListView;
import com.pcassem.yunzhuangpei.view.CommomDialog;

import java.util.ArrayList;
import java.util.List;

public class MyProjectActivity extends AppCompatActivity implements View.OnClickListener,MyProjectAdapter.ButtonInterface,ProjectListView {

    private static final String TAG = "MyProjectActivity";

    private RecyclerView mRecyclerView;
    private MyProjectAdapter mMyProjectAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<MyProjectEntity> mMyProjectList;
    private ProjectPresenter presenter;

    private ImageView backIv;
    private RelativeLayout addProject;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        initView();
        initTouchEvent();
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        presenter.getMyProjectList(userID);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        presenter.getMyProjectList(userID);
    }

    private void initView(){
        backIv = (ImageView) findViewById(R.id.back_iv);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        addProject = (RelativeLayout) findViewById(R.id.add_project);

        mMyProjectList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        presenter = new ProjectPresenter(this);
        presenter.onCreate();
        presenter.getMyProjectList(userID);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
        addProject.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.add_project:
                Intent intent = new Intent(MyProjectActivity.this, AddProjectActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(View view, final int position) {
        switch (view.getId()){
            case R.id.edit:
                Intent intent = new Intent(MyProjectActivity.this,EditProjectActivity.class);
                intent.putExtra("my_project_id",mMyProjectList.get(position).getProjectID());
                intent.putExtra("my_project_name",mMyProjectList.get(position).getName());
                intent.putExtra("my_project_address",mMyProjectList.get(position).getProvinceCode()+"-"+mMyProjectList.get(position).getCityCode()+"-"+mMyProjectList.get(position).getAreaCode());
                startActivity(intent);
                break;
            case R.id.delete:
                new CommomDialog(this, R.style.commom_dialog, "是否删除？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            ProjectIDBean project = new ProjectIDBean();
                            project.setProjectID(position);
                            presenter.deleteProject(project);
                            dialog.dismiss();
                        }

                    }
                }).setTitle("提示信息").show();
                break;
            case R.id.is_checked:
                ProjectIDBean projectID = new ProjectIDBean();
                projectID.setProjectID(position);
                presenter.setDefaultProject(projectID);
                break;
        }
    }

    @Override
    public void onGetProjectSuccess(ResultListEntity<MyProjectEntity> userInfo) {
        mMyProjectList = userInfo.getResult();
        if (mMyProjectList == null) {
            Toast.makeText(MyProjectActivity.this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mMyProjectAdapter == null) {
            mMyProjectAdapter = new MyProjectAdapter(MyProjectActivity.this, mMyProjectList);
            mRecyclerView.setAdapter(mMyProjectAdapter);
            mMyProjectAdapter.buttonSetOnClick(this);
        }
        mMyProjectAdapter.updateData(mMyProjectList);
    }

    @Override
    public void onGetProjectError() {
        Toast.makeText(MyProjectActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddProjectSuccess(ResultListEntity<String> status) {
        if (status.getCode() == 0){
            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
            presenter.getMyProjectList(userID);
        }
        if (status.getCode() == 1){
            Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAddProjectError() {
        Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDeleteProjectSuccess(ResultListEntity<String> status) {
        if (status.getCode() == 0){
            Toast.makeText(MyProjectActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            presenter.getMyProjectList(userID);
        }
        if (status.getCode() == 1){
            Toast.makeText(this, "正在咨询该项目，无法删除", Toast.LENGTH_SHORT).show();
        }
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
