package com.pcassem.yunzhuangpei.home.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.home.presenter.NewsPresenter;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.List;

public class AllNewsActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, LatestNewsAdapter.OnItemClickListener, NewsView {


    private ImageView mBackBtn;
    private EditText mSearchNews;

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NewsEntity> mNewsData;
    private NewsPresenter newsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);

        initView();
        initTouchEvent();

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        newsPresenter = new NewsPresenter(this);
        newsPresenter.onCreate();
        newsPresenter.getNewsList();
    }

    private void initView() {
        mBackBtn = (ImageView) findViewById(R.id.back_iv);
        mRecyclerView = (RecyclerView) findViewById(R.id.all_news_recycler_view);
        mSearchNews = (EditText) findViewById(R.id.search_news);
    }

    private void initTouchEvent() {
        mBackBtn.setOnClickListener(this);
        mSearchNews.addTextChangedListener(this);
    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(AllNewsActivity.this, NewsDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("newsID", position);
        intent.putExtras(bundle);
        startActivity(intent);
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
    public void onSuccess(ResultListEntity<NewsEntity> newsListEntity) {
        mNewsData = newsListEntity.getResult();
        if (mNewsData == null) {
            Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mLatestNewsAdapter == null) {
            mLatestNewsAdapter = new LatestNewsAdapter(mNewsData);
            mRecyclerView.setAdapter(mLatestNewsAdapter);
            mLatestNewsAdapter.setmOnItemClickListener(this);
        }
        mLatestNewsAdapter.setmData(mNewsData);
    }

    @Override
    public void onError() {
        Toast.makeText(AllNewsActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
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
        newsPresenter.getSearchNewsList(s.toString());
    }
}
