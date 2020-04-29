package com.pcassem.yunzhuangpei.home.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.PictureAnalysisBean;
import com.pcassem.yunzhuangpei.home.adapter.PictureAnalysisAdapter;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeDetailsActivity;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class PictureAnalysisActivity extends AppCompatActivity implements View.OnClickListener, PictureAnalysisAdapter.OnItemClickListener {


    private ImageView backIv;
    private ImageView picture;
    private RecyclerView mRecyclerView;
    private PictureAnalysisAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<PictureAnalysisBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_analysis);

        initView();
        initTouchEvent();

        Glide.with(this).load(R.drawable.icon00).crossFade().into(picture);

        data = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(PictureAnalysisActivity.this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(PictureAnalysisActivity.this,LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new PictureAnalysisAdapter(getData());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(this);

    }

    private void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        backIv = (ImageView) findViewById(R.id.back_iv);
        picture = (ImageView) findViewById(R.id.picture);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
    }

    private List<PictureAnalysisBean> getData(){
        PictureAnalysisBean p1 = new PictureAnalysisBean();
        p1.setId(386);
        p1.setItemTags("1");
        p1.setTitle("PC构件工程-叠合梁偏位露拼缝");
        data.add(p1);

        PictureAnalysisBean p2 = new PictureAnalysisBean();
        p2.setId(431);
        p2.setItemTags("2-1");
        p2.setTitle("室内装修工程-预制楼板与预制墙梁出现裂缝");
        data.add(p2);

        PictureAnalysisBean p3 = new PictureAnalysisBean();
        p3.setId(430);
        p3.setItemTags("2-2");
        p3.setTitle("室内装修工程-阴角防水涂层开裂");
        data.add(p3);

        PictureAnalysisBean p4 = new PictureAnalysisBean();
        p4.setId(432);
        p4.setItemTags("3");
        p4.setTitle("机电工程-线管穿线困难");
        data.add(p4);

        PictureAnalysisBean p5 = new PictureAnalysisBean();
        p5.setId(383);
        p5.setItemTags("4-1");
        p5.setTitle("PC构件工程-叠合楼板安装板面开裂");
        data.add(p5);

        PictureAnalysisBean p6 = new PictureAnalysisBean();
        p6.setId(433);
        p6.setItemTags("4-2");
        p6.setTitle("PC构件工程-叠合楼板安装后不平直露板缝");
        data.add(p6);

        return data;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(PictureAnalysisActivity.this, KnowledgeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("knowledgeID",position);
        bundle.putInt("module",3);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }
}
