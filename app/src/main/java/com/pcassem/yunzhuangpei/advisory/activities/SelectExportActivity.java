package com.pcassem.yunzhuangpei.advisory.activities;

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
import com.pcassem.yunzhuangpei.advisory.adapter.SelectExportListAdapter;
import com.pcassem.yunzhuangpei.advisory.presenter.ExportPresenter;
import com.pcassem.yunzhuangpei.advisory.view.ExportView;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.List;

import static com.pcassem.yunzhuangpei.advisory.activities.BookActivity.SELECT_EXPORT_CODE;

public class SelectExportActivity extends AppCompatActivity implements ExportView, View.OnClickListener, SelectExportListAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SelectExportListAdapter mSelectExportListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView backIv;

    private ExportPresenter mExportPresenter;
    private List<ExportEntity> mExportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_export);
        initView();
        initTouchEvent();

        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mExportPresenter = new ExportPresenter(this);
        mExportPresenter.onCreate();
        mExportPresenter.getAllExportList();
    }

    private void initView(){
        backIv = (ImageView) findViewById(R.id.back_iv);
        mRecyclerView = (RecyclerView) findViewById(R.id.select_export_recycler_view);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("export_id",mExportList.get(position).getId());
        intent.putExtra("export_name", mExportList.get(position).getName());
        SelectExportActivity.this.setResult(SELECT_EXPORT_CODE, intent);
        SelectExportActivity.this.finish();
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
    public void onSuccess(ResultListEntity<ExportEntity> exportList) {
        mExportList = exportList.getResult();
        if (mExportList == null){
            Toast.makeText(SelectExportActivity.this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mSelectExportListAdapter == null){
            mSelectExportListAdapter = new SelectExportListAdapter(mExportList);
            mRecyclerView.setAdapter(mSelectExportListAdapter);
            mSelectExportListAdapter.setmOnItemClickListener(this);
        }
        mSelectExportListAdapter.setmData(mExportList);
    }

    @Override
    public void onError() {
        Toast.makeText(SelectExportActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
    }
}
