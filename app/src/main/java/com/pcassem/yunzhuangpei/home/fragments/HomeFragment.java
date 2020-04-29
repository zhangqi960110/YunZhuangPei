package com.pcassem.yunzhuangpei.home.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.activities.ExportAnswerActivity;
import com.pcassem.yunzhuangpei.advisory.activities.BookActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SpeedAskActivity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.VoiceBean;
import com.pcassem.yunzhuangpei.home.activities.CameraActivity;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.home.activities.AllNewsActivity;
import com.pcassem.yunzhuangpei.home.activities.HomeSearchActivity;
import com.pcassem.yunzhuangpei.home.activities.NewsDetailsActivity;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.home.presenter.NewsPresenter;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeActivity;
import com.pcassem.yunzhuangpei.training.activities.TrainingCoursesActivity;
import com.pcassem.yunzhuangpei.view.CommomDialog;
import com.pcassem.yunzhuangpei.view.MyLayoutManager;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements View.OnClickListener, NewsView, LatestNewsAdapter.OnItemClickListener {


    public static final String TAG = "HomeFragment";
    public static final int TAKE_PHOTO = 1;
    private Uri imageUri;

    private TextView mHomeSearchBtn;
    private TextView mAllNewsJumpBtn;

    private Button button1;
    private Button button2;

    private LinearLayout button3;
    private LinearLayout button4;
    private LinearLayout button5;
    private LinearLayout button6;

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private MyLayoutManager mLayoutManager;
    private List<NewsEntity> mNewsData;
    private NewsPresenter newsPresenter;
    private StringBuffer mBuffer;


    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initTouchEvent();

        ImageView homeImage = (ImageView) view.findViewById(R.id.home_image);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float screenWidth = dm.widthPixels;
        int height = (int) ((687 / 1098.0) * screenWidth);

        ViewGroup.LayoutParams para = homeImage.getLayoutParams();
        para.height = height;
        homeImage.setLayoutParams(para);

        mBuffer = new StringBuffer();
        mNewsData = new ArrayList<>();
        // 设置布局管理器
        mLayoutManager = new MyLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置adapter
        newsPresenter = new NewsPresenter(this);
        newsPresenter.onCreate();
        newsPresenter.getLatestNewsList();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        }else{
            newsPresenter.getLatestNewsList();
        }
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mHomeSearchBtn = (TextView) view.findViewById(R.id.home_search_btn);
        mAllNewsJumpBtn = (TextView) view.findViewById(R.id.jump_all_news_activity);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);

        button3 = (LinearLayout) view.findViewById(R.id.button3);
        button4 = (LinearLayout) view.findViewById(R.id.button4);
        button5 = (LinearLayout) view.findViewById(R.id.button5);
        button6 = (LinearLayout) view.findViewById(R.id.button6);
    }

    private void initTouchEvent() {

        mHomeSearchBtn.setOnClickListener(this);
        mAllNewsJumpBtn.setOnClickListener(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_btn:
                Intent searchIntent = new Intent(getActivity(), HomeSearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("searchID", 0);
                searchIntent.putExtras(bundle);
                startActivity(searchIntent);
                break;
            case R.id.jump_all_news_activity:
                Intent jumpAllNewsIntent = new Intent(getActivity(), AllNewsActivity.class);
                startActivity(jumpAllNewsIntent);
                break;
            case R.id.button1:
                if (isLogin() == -1) {
                    myDialog();
                } else {
                    Intent intent = new Intent(getActivity(), SpeedAskActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.button2:
                Intent intent1 = new Intent(getActivity(), TrainingCoursesActivity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(getActivity(), KnowledgeActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("selectId", "0");
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(getActivity(), KnowledgeActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("selectId", "1");
                intent3.putExtras(bundle1);
                startActivity(intent3);
                break;
            case R.id.button5:
                if (isLogin() == -1){
                    myDialog();
                }else {
                    Intent intent4 = new Intent(getActivity(), ExportAnswerActivity.class);
                    startActivity(intent4);
                }
                break;
            case R.id.button6:
                if (isLogin() == -1){
                    myDialog();
                }else {
                    Intent intent5 = new Intent(getActivity(), BookActivity.class);
                    startActivity(intent5);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent(getActivity(), CameraActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("imageUri", imageUri.toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    //解析数据
    private String parseDatd(String json) {
        //创建Gson对象
        Gson gson = new Gson();
        VoiceBean voiceBean = gson.fromJson(json, VoiceBean.class);

        StringBuffer sb = new StringBuffer();

        List<VoiceBean.WsBean> ws = voiceBean.getWs();

        for (VoiceBean.WsBean wsBean : ws) {
            String word = wsBean.getCw().get(0).getW();
            sb.append(word);
        }
        return sb.toString();
    }

    //点击最新资讯进入单个资讯
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("newsID", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSuccess(ResultListEntity<NewsEntity> newsListEntity) {
        mNewsData = newsListEntity.getResult();
        if (mNewsData == null) {
            Toast.makeText(getContext(), "无数据", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), "网络出错", Toast.LENGTH_SHORT).show();
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
