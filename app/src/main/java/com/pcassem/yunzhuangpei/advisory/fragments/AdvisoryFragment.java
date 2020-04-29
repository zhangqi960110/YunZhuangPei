package com.pcassem.yunzhuangpei.advisory.fragments;


import android.app.Dialog;
import android.content.Context;
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
import com.pcassem.yunzhuangpei.advisory.activities.ExportAnswerActivity;
import com.pcassem.yunzhuangpei.advisory.activities.ExportDetailsActivity;
import com.pcassem.yunzhuangpei.advisory.activities.FindExportActivity;
import com.pcassem.yunzhuangpei.advisory.activities.BookActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SpeedAskActivity;
import com.pcassem.yunzhuangpei.advisory.adapter.ExportListAdapter;
import com.pcassem.yunzhuangpei.advisory.presenter.ExportPresenter;
import com.pcassem.yunzhuangpei.advisory.view.ExportView;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.view.CommomDialog;
import com.pcassem.yunzhuangpei.view.MyLayoutManager;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class AdvisoryFragment extends Fragment implements ExportView, View.OnClickListener, ExportListAdapter.OnItemClickListener {

    private LinearLayout mExportAnswer;
    private LinearLayout mSpeedAsk;
    private LinearLayout mSiteGudie;
    private LinearLayout mFindExport;
    private TextView mAllExports;

    private RecyclerView mRecyclerView;
    private ExportListAdapter mExportListAdapter;
    private MyLayoutManager mLayoutManager;

    private ExportPresenter mExportPresenter;
    private List<ExportEntity> mExportList;


    public static AdvisoryFragment newInstance() {
        AdvisoryFragment advisoryFragment = new AdvisoryFragment();
        return advisoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advisory, container, false);
        initView(view);
        initTouchEvent();

        mExportList = new ArrayList<>();

        // 设置布局管理器
        mLayoutManager = new MyLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mExportPresenter = new ExportPresenter(this);
        mExportPresenter.onCreate();
        mExportPresenter.getCommendExportList();
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mExportPresenter.getCommendExportList();

    }

    private void initView(View view) {
        mExportAnswer = (LinearLayout) view.findViewById(R.id.advisory_export_answer);
        mSpeedAsk = (LinearLayout) view.findViewById(R.id.advisory_speed_ask);
        mSiteGudie = (LinearLayout) view.findViewById(R.id.advisory_site_guide);
        mFindExport = (LinearLayout) view.findViewById(R.id.advisory_find_export);

        mAllExports = (TextView) view.findViewById(R.id.all_exports);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.export_recycler_view);
    }

    private void initTouchEvent() {
        mExportAnswer.setOnClickListener(this);
        mSpeedAsk.setOnClickListener(this);
        mSiteGudie.setOnClickListener(this);
        mFindExport.setOnClickListener(this);
        mAllExports.setOnClickListener(this);

    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), ExportDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("specialistID", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.advisory_export_answer:
                if (isLogin() == -1) {
                    myDialog();
                } else {
                    Intent intent1 = new Intent(getActivity(), ExportAnswerActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.advisory_speed_ask:
                if (isLogin() == -1) {
                    myDialog();
                } else {
                    Intent intent2 = new Intent(getActivity(), SpeedAskActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.advisory_site_guide:
                if (isLogin() == -1) {
                    myDialog();
                } else {
                    Intent intent3 = new Intent(getActivity(), BookActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.advisory_find_export:

                Intent intent4 = new Intent(getActivity(), FindExportActivity.class);
                startActivity(intent4);

                break;
            case R.id.all_exports:
                Intent intent5 = new Intent(getActivity(), FindExportActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<ExportEntity> exportList) {
        mExportList = exportList.getResult();
        if (mExportList == null) {
            Toast.makeText(getActivity(), "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mExportListAdapter == null) {
            mExportListAdapter = new ExportListAdapter(getActivity(), mExportList);
            mRecyclerView.setAdapter(mExportListAdapter);
            mExportListAdapter.setmOnItemClickListener(this);
        }
        mExportListAdapter.setmData(mExportList);
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "网络出错", Toast.LENGTH_SHORT).show();
    }

    private int isLogin() {
        String username = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString("username", "");
        if (username == "") {
            return -1;
        } else {
            return getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        }

    }

    private void myDialog() {
        new CommomDialog(getActivity(), R.style.commom_dialog, "尚未登录，是否先登录？", new CommomDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                }

            }
        }).setTitle("提示信息").show();
    }
}
