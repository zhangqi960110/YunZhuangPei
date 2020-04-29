package com.pcassem.yunzhuangpei.training.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeActivity;
import com.pcassem.yunzhuangpei.training.activities.AllProjectCasesActivity;
import com.pcassem.yunzhuangpei.training.activities.ProjectCaseDetailsActivity;
import com.pcassem.yunzhuangpei.training.activities.TrainingCoursesActivity;
import com.pcassem.yunzhuangpei.training.presenter.ProjectCasesPresenter;
import com.pcassem.yunzhuangpei.view.MyLayoutManager;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class TrainingFragment extends Fragment implements View.OnClickListener,NewsView, LatestNewsAdapter.OnItemClickListener {

    private LinearLayout mJumpProcessActivity;
    private LinearLayout mJumpStandardActivity;
    private LinearLayout mJumpProblemSolvingActivity;
    private LinearLayout mJumpTrainingCoursesActivity;
    private TextView mJumpMoreProjectCases;

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mProjectCasesListAdapter;
    private MyLayoutManager mLayoutManager;
    private List<NewsEntity> mProjectCasesList;
    private ProjectCasesPresenter mProjectCasesPresenter;


    public static TrainingFragment newInstance() {
        TrainingFragment trainingFragment = new TrainingFragment();
        return trainingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        initView(view);
        initTouchEvent();

        mProjectCasesList = new ArrayList<>();
        // 设置布局管理器
        mLayoutManager = new MyLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置adapter
        mProjectCasesPresenter = new ProjectCasesPresenter(this);
        mProjectCasesPresenter.onCreate();
        mProjectCasesPresenter.getLatestProjectCasesList();
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mProjectCasesPresenter.getLatestProjectCasesList();
    }

    private void initView(View view){

        mJumpProcessActivity = (LinearLayout) view.findViewById(R.id.training_process);
        mJumpStandardActivity = (LinearLayout)view.findViewById(R.id.training_standard);
        mJumpProblemSolvingActivity = (LinearLayout)view.findViewById(R.id.training_problem_solving);
        mJumpTrainingCoursesActivity = (LinearLayout)view.findViewById(R.id.training_training_courses);
        mJumpMoreProjectCases = (TextView) view.findViewById(R.id.training_project_cases_more);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.training_recycler_view);
    }

    private void initTouchEvent(){
        mJumpProcessActivity.setOnClickListener(this);
        mJumpStandardActivity.setOnClickListener(this);
        mJumpProblemSolvingActivity.setOnClickListener(this);
        mJumpTrainingCoursesActivity.setOnClickListener(this);
        mJumpMoreProjectCases.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.training_process:
                Intent processIntent = new Intent(getActivity(), KnowledgeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectId", "0");
                processIntent.putExtras(bundle);
                startActivity(processIntent);
                break;
            case R.id.training_standard:
                Intent standardIntent = new Intent(getActivity(), KnowledgeActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("selectId", "2");
                standardIntent.putExtras(bundle2);
                startActivity(standardIntent);
                break;
            case R.id.training_problem_solving:
                Intent problemSolvingIntent = new Intent(getActivity(), KnowledgeActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("selectId", "1");
                problemSolvingIntent.putExtras(bundle1);
                startActivity(problemSolvingIntent);
                break;
            case R.id.training_training_courses:
                Intent coursesIntent = new Intent(getActivity(), TrainingCoursesActivity.class);
                startActivity(coursesIntent);
                break;
            case R.id.training_project_cases_more:
                Intent projectCasesMoreIntent = new Intent(getActivity(),AllProjectCasesActivity.class);
                startActivity(projectCasesMoreIntent);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), ProjectCaseDetailsActivity.class);
        Bundle  bundle = new Bundle();
        bundle.putInt("projectCaseID", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSuccess(ResultListEntity<NewsEntity> projectCasesList) {
        mProjectCasesList = projectCasesList.getResult();
        if (mProjectCasesList == null){
            Toast.makeText(getContext(), "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mProjectCasesListAdapter == null){
            mProjectCasesListAdapter = new LatestNewsAdapter(mProjectCasesList);
            mRecyclerView.setAdapter(mProjectCasesListAdapter);
            mProjectCasesListAdapter.setmOnItemClickListener(this);
        }
        mProjectCasesListAdapter.setmData(mProjectCasesList);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "网络出错", Toast.LENGTH_SHORT).show();
    }
}
