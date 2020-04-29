package com.pcassem.yunzhuangpei.home.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.SearchListEntity;
import com.pcassem.yunzhuangpei.home.presenter.HomeSearchPresenter;
import com.pcassem.yunzhuangpei.home.view.HomeSearchView;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeDetailsActivity;
import com.pcassem.yunzhuangpei.training.adapter.KnowledgeItemAdapter;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;
import com.pcassem.yunzhuangpei.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CameraActivity extends AppCompatActivity implements HomeSearchView, TextWatcher, KnowledgeItemAdapter.OnItemClickListener, View.OnClickListener {

    public static final String TAG = "HomeSearchActivity";

    private EditText mSearchEdt;
    private TextView mCancelTv;
    private ScrollView mCameraView;
    private LinearLayout mSearchAnswer;

    //tab选择栏实现
    private TextView tv_item_one;
    private TextView tv_item_two;
    private TextView tv_item_three;
    private TextView tv_item_four;
    private TextView tv_one_underline;
    private TextView tv_two_underline;
    private TextView tv_three_underline;
    private TextView tv_four_underline;

    private RecyclerView mRecyclerView;
    private KnowledgeItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<KnowledgeEntity> mTrainingData;
    private HomeSearchPresenter homeSearchPresenter;

    private TextView itemOne;
    private TextView itemTwo;

    private String repository = "all";
    private String keyword = "";

    private Button analysis;
    private ImageView picture;

    private MyGridView imageGridView;
    private List<Map<String, Object>> imageList;
    private SimpleAdapter simpleAdapter;
    private int[] icon = {R.drawable.icon01, R.drawable.icon03, R.drawable.icon07, R.drawable.icon09, R.drawable.icon10,R.drawable.icon12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
        initTouchEvent();
        imageList = new ArrayList<Map<String, Object>>();

        mLayoutManager = new LinearLayoutManager(CameraActivity.this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(CameraActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        String a = getIntent().getStringExtra("imageUri");
        Glide.with(this).load(a).crossFade().into(picture);

        getData();
        String[] from = {"image"};
        int[] to = {R.id.image};
        simpleAdapter = new SimpleAdapter(this, imageList, R.layout.abc_item,from,to);
        imageGridView.setAdapter(simpleAdapter);
    }

    private void initView() {
        picture = (ImageView) findViewById(R.id.picture);
        imageGridView = (MyGridView) findViewById(R.id.grid_view);

        mSearchEdt = (EditText) findViewById(R.id.home_search_edt);
        mCancelTv = (TextView) findViewById(R.id.home_search_cancel);
        mCameraView = (ScrollView) findViewById(R.id.camera_view);
        mSearchAnswer = (LinearLayout) findViewById(R.id.search_answer);
        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);
        tv_item_three = (TextView) findViewById(R.id.tv_item_three);
        tv_item_four = (TextView) findViewById(R.id.tv_item_four);
        tv_one_underline = (TextView) findViewById(R.id.tv_one_underline);
        tv_two_underline = (TextView) findViewById(R.id.tv_two_underline);
        tv_three_underline = (TextView) findViewById(R.id.tv_three_underline);
        tv_four_underline = (TextView) findViewById(R.id.tv_four_underline);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        itemOne = (TextView) findViewById(R.id.item_one);
        itemTwo = (TextView) findViewById(R.id.item_two);
        analysis = (Button) findViewById(R.id.analysis);

    }

    private void initTouchEvent(){
        mSearchEdt.addTextChangedListener(this);

        mCancelTv.setOnClickListener(this);

        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        tv_item_three.setOnClickListener(this);
        tv_item_four.setOnClickListener(this);

        itemOne.setOnClickListener(this);
        itemTwo.setOnClickListener(this);
        analysis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_cancel:
                onBackPressed();
                break;
            case R.id.analysis:
                Intent intent = new Intent(CameraActivity.this,PictureAnalysisActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_item_one:
                checkedStatus(tv_item_one, tv_one_underline);
                repository = "all";
                homeSearchPresenter.getHomeSearchList(repository, keyword);
                break;
            case R.id.tv_item_two:
                checkedStatus(tv_item_two, tv_two_underline);
                repository = "A2";
                homeSearchPresenter.getHomeSearchList(repository, keyword);
                break;
            case R.id.tv_item_three:
                checkedStatus(tv_item_three, tv_three_underline);
                repository = "B1";
                homeSearchPresenter.getHomeSearchList(repository, keyword);
                break;
            case R.id.tv_item_four:
                checkedStatus(tv_item_four, tv_four_underline);
                repository = "B2";
                homeSearchPresenter.getHomeSearchList(repository, keyword);
                break;
            case R.id.item_one:
                itemSelect(itemOne);
                mSearchEdt.setText("叠合梁");
                break;
            case R.id.item_two:
                itemSelect(itemTwo);
                mSearchEdt.setText("叠合楼板");
                break;
        }
    }

    public List<Map<String,Object>> getData(){
        for (int i = 0; i < icon.length; i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("image",icon[i]);
            imageList.add(map);
        }
        return imageList;
    }

    @Override
    public void onSuccess(SearchListEntity newsListEntity) {


        mTrainingData = newsListEntity.getResult();
        if (mTrainingData == null) {
            Toast.makeText(CameraActivity.this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mAdapter == null) {
            mAdapter = new KnowledgeItemAdapter(mTrainingData);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setmOnItemClickListener(this);
        } else {
            mAdapter.setmData(mTrainingData);
        }
    }


    @Override
    public void onError() {
//        Toast.makeText(CameraActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, String position) {
        int pos = Integer.parseInt(position.substring(1, position.length()));

        Intent intent = new Intent(CameraActivity.this, KnowledgeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("knowledgeID", pos);
        bundle.putInt("module", 3);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        keyword = mSearchEdt.getText().toString();
        mCameraView.setVisibility(View.GONE);
        mSearchAnswer.setVisibility(View.VISIBLE);
        if (homeSearchPresenter == null) {
            checkedStatus(tv_item_one, tv_one_underline);
            homeSearchPresenter = new HomeSearchPresenter(this);
            homeSearchPresenter.onCreate();
        }
        Log.d(TAG, "afterTextChanged: " + keyword);
        homeSearchPresenter.getHomeSearchList(repository, keyword);
    }

    private void checkedStatus(TextView tv, TextView underline) {
        emptyStatus();
        tv.setTextSize(16);
        tv.setTextColor(Color.rgb(19, 56, 109));

        underline.setVisibility(View.VISIBLE);
    }

    private void emptyStatus() {
        tv_item_one.setTextSize(14);
        tv_item_one.setTextColor(Color.rgb(153, 153, 153));
        tv_one_underline.setVisibility(View.GONE);

        tv_item_two.setTextSize(14);
        tv_item_two.setTextColor(Color.rgb(153, 153, 153));
        tv_two_underline.setVisibility(View.GONE);

        tv_item_three.setTextSize(14);
        tv_item_three.setTextColor(Color.rgb(153, 153, 153));
        tv_three_underline.setVisibility(View.GONE);

        tv_item_four.setTextSize(14);
        tv_item_four.setTextColor(Color.rgb(153, 153, 153));
        tv_four_underline.setVisibility(View.GONE);

    }

    private void itemSelect(TextView textView){
        itemOne.setTextColor(getResources().getColor(R.color.color_999999));
        itemOne.setBackground(getResources().getDrawable(R.drawable.select_tags));

        itemTwo.setTextColor(getResources().getColor(R.color.color_999999));
        itemTwo.setBackground(getResources().getDrawable(R.drawable.select_tags));

        textView.setTextColor(getResources().getColor(R.color.color_13386d));
        textView.setBackground(getResources().getDrawable(R.drawable.select_tags_checked));
    }

}
