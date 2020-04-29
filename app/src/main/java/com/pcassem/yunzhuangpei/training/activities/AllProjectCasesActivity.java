package com.pcassem.yunzhuangpei.training.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.training.presenter.ProjectCasesPresenter;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;
import java.util.List;

public class AllProjectCasesActivity extends AppCompatActivity implements TextWatcher, LatestNewsAdapter.OnItemClickListener, NewsView, View.OnClickListener {

    public static final String TAG = "AllProjectCasesActivity";

    private EditText searchProjectCase;
    //伸缩框实现
    private LinearLayout select;
    private LinearLayout selectFirstContent;
    private LinearLayout selectFirstBuinding;
    private LinearLayout selectFirstTunnel;
    private ImageView selectImageView;
    private LinearLayout selectBuilding;
    private LinearLayout selectTunnel;

    private String selectState = "all";


    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NewsEntity> allProjectCasesList;
    private ProjectCasesPresenter projectCasesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_cases);

        initView();
        initTouchEvent();
        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        projectCasesPresenter = new ProjectCasesPresenter(this);
        projectCasesPresenter.onCreate();
        projectCasesPresenter.getAllProjectCasesList("all");
    }

    private void initView() {
        searchProjectCase = (EditText) findViewById(R.id.search_project_case);
        mRecyclerView = (RecyclerView) findViewById(R.id.all_news_recycler_view);
        select = (LinearLayout) findViewById(R.id.navigation_select_btn);
        selectFirstContent = (LinearLayout) findViewById(R.id.select_first_content);
        selectFirstBuinding = (LinearLayout) findViewById(R.id.select_first_buinding);
        selectFirstTunnel = (LinearLayout) findViewById(R.id.select_first_tunnel);
        selectImageView = (ImageView) findViewById(R.id.select_image_view);
        selectBuilding = (LinearLayout) findViewById(R.id.select_buinding);
        selectTunnel = (LinearLayout) findViewById(R.id.select_tunnel);
    }

    private void initTouchEvent(){
        select.setOnClickListener(this);
        selectFirstBuinding.setOnClickListener(this);
        selectFirstTunnel.setOnClickListener(this);
        searchProjectCase.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigation_select_btn:
                displaySelectIcon(selectImageView);
                projectCasesPresenter.getAllProjectCasesList("all");
                selectState = "all";
                break;
            case R.id.select_first_buinding:
                displaySelectIcon(selectBuilding);
                projectCasesPresenter.getAllProjectCasesList("C1");
                selectState = "C1";
                break;
            case R.id.select_first_tunnel:
                displaySelectIcon(selectTunnel);
                projectCasesPresenter.getAllProjectCasesList("C2");
                selectState = "C2";
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(AllProjectCasesActivity.this, ProjectCaseDetailsActivity.class);
        Bundle  bundle = new Bundle();
        bundle.putInt("projectCaseID", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSuccess(ResultListEntity<NewsEntity> newsListEntity) {
        allProjectCasesList = newsListEntity.getResult();
        Log.d(TAG, "onSuccess: " + allProjectCasesList.size());
        if (allProjectCasesList == null) {
            Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mLatestNewsAdapter == null) {
            mLatestNewsAdapter = new LatestNewsAdapter(allProjectCasesList);
            mRecyclerView.setAdapter(mLatestNewsAdapter);
            mLatestNewsAdapter.setmOnItemClickListener(this);
        }
        mLatestNewsAdapter.setmData(allProjectCasesList);
    }

    @Override
    public void onError() {
        Toast.makeText(AllProjectCasesActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
    }

    //输入文本之前的状态
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //输入文字中的状态
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    //输入文字后的状态
    @Override
    public void afterTextChanged(Editable s) {
        projectCasesPresenter.getSearchProjectCasesList(selectState,s.toString());
    }

    private void displaySelectIcon(View view) {
        selectImageView.setVisibility(View.GONE);
        selectBuilding.setVisibility(View.GONE);
        selectTunnel.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

}
