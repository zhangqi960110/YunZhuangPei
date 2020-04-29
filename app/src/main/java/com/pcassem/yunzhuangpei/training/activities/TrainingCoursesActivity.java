package com.pcassem.yunzhuangpei.training.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.CourseEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.training.adapter.TrainingListAdapter;
import com.pcassem.yunzhuangpei.training.presenter.CoursePresenter;
import com.pcassem.yunzhuangpei.training.view.CourseView;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class TrainingCoursesActivity extends AppCompatActivity implements TrainingListAdapter.OnItemClickListener, CourseView {


    private static final String TAG = "TrainingCourseActivity";


    private RecyclerView mRecyclerView;
    private TrainingListAdapter mTrainingListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<CourseEntity> courseList;
    private CoursePresenter mCoursePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_courses);

        initView();

        courseList = new ArrayList<CourseEntity>();

        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mCoursePresenter = new CoursePresenter(this);
        mCoursePresenter.onCreate();
        mCoursePresenter.getCourseList();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.all_news_recycler_view);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(TrainingCoursesActivity.this, CourseDetailsActivity.class);
        Bundle  bundle = new Bundle();
        bundle.putInt("courseID", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSuccess(ResultListEntity<CourseEntity> courseListEntity) {
        courseList = courseListEntity.getResult();
        if (courseList == null) {
            Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mTrainingListAdapter == null) {
            mTrainingListAdapter = new TrainingListAdapter(courseList);
            mRecyclerView.setAdapter(mTrainingListAdapter);
            mTrainingListAdapter.setmOnItemClickListener(this);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(TrainingCoursesActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
    }


}
