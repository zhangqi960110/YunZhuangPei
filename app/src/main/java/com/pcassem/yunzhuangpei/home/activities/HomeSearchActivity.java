package com.pcassem.yunzhuangpei.home.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.SearchListEntity;
import com.pcassem.yunzhuangpei.home.adapter.KeywordListAdapter;
import com.pcassem.yunzhuangpei.home.adapter.SearchHistoryAdapter;
import com.pcassem.yunzhuangpei.home.presenter.HomeSearchPresenter;
import com.pcassem.yunzhuangpei.home.view.HomeSearchView;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeDetailsActivity;
import com.pcassem.yunzhuangpei.training.adapter.KnowledgeItemAdapter;
import com.pcassem.yunzhuangpei.view.HorizontalListView;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class HomeSearchActivity extends AppCompatActivity implements HomeSearchView, TextWatcher, KnowledgeItemAdapter.OnItemClickListener, View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String TAG = "HomeSearchActivity";
    public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";

    private EditText mSearchEdt;
    private TextView mCancelTv;
    private TextView mEmptiedTv;
    private ListView mSearchHistoryRecyclerView;
    private LinearLayout mSearchHistory;
    private LinearLayout mSearchAnswer;


    private SearchHistoryAdapter mSearchHistoryAdapter;
    private List<Map<String, Object>> mHistoryData = null;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

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
    private KeywordListAdapter keywordListAdapter;
    private List<String> searchTags;
    private HorizontalListView searchTagsListView;
    private TextView noSearch;
    private HomeSearchPresenter homeSearchPresenter;
    private int searchTagStatus = 1;

    private String repository = "all";
    private String keyword = "";
    private String voiceStr = "";
    private String[] strArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = getSharedPreferences("HomeSearchHistory", Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mHistoryData = new ArrayList<Map<String, Object>>();
        setContentView(R.layout.activity_home_search);

        initView();
        initTouchEvent();
        initSearchHistory();
        mLayoutManager = new LinearLayoutManager(HomeSearchActivity.this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(HomeSearchActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        String voice = getIntent().getStringExtra("voice");
        Pattern pattern = Pattern.compile("[。|？| ?]");
        if (voice != null) {
            strArray = pattern.split(voice);
            voiceStr = strArray[strArray.length - 1];
            mSearchEdt.setText(voiceStr);
        }

    }

    private void initView() {

        mSearchEdt = (EditText) findViewById(R.id.home_search_edt);
        mCancelTv = (TextView) findViewById(R.id.home_search_cancel);
        mEmptiedTv = (TextView) findViewById(R.id.home_search_emptied);
        mSearchHistory = (LinearLayout) findViewById(R.id.home_search_history);
        mSearchAnswer = (LinearLayout) findViewById(R.id.search_answer);
        mSearchHistoryRecyclerView = (ListView) findViewById(R.id.home_searchhistory_recyclerview);
        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);
        tv_item_three = (TextView) findViewById(R.id.tv_item_three);
        tv_item_four = (TextView) findViewById(R.id.tv_item_four);
        tv_one_underline = (TextView) findViewById(R.id.tv_one_underline);
        tv_two_underline = (TextView) findViewById(R.id.tv_two_underline);
        tv_three_underline = (TextView) findViewById(R.id.tv_three_underline);
        tv_four_underline = (TextView) findViewById(R.id.tv_four_underline);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        searchTagsListView = (HorizontalListView) findViewById(R.id.keywords);
        noSearch = (TextView) findViewById(R.id.no_search);
    }

    private void initTouchEvent() {

        mSearchEdt.addTextChangedListener(this);

        mCancelTv.setOnClickListener(this);
        mEmptiedTv.setOnClickListener(this);

        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        tv_item_three.setOnClickListener(this);
        tv_item_four.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_cancel:
                onBackPressed();
                break;
            case R.id.home_search_emptied:
                cleanHistory();
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
        }
    }


    @Override
    public void onSuccess(SearchListEntity newsListEntity) {

        if (searchTagStatus == 1) {
            searchTags = newsListEntity.getKeywords();
            if (searchTags.size() != 0) {
                noSearch.setVisibility(View.GONE);
                searchTagsListView.setVisibility(View.VISIBLE);
                if (keywordListAdapter == null) {
                    keywordListAdapter = new KeywordListAdapter(HomeSearchActivity.this, searchTags);
                    searchTagsListView.setAdapter(keywordListAdapter);
                    searchTagsListView.setOnItemClickListener(this);
                } else {
                    keywordListAdapter.setData(searchTags);
                }
            } else {
                searchTagsListView.setVisibility(View.GONE);
                noSearch.setVisibility(View.VISIBLE);
            }
        }

        mTrainingData = newsListEntity.getResult();
        if (mTrainingData == null) {
            Toast.makeText(HomeSearchActivity.this, "无数据", Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mSearchEdt.setText(searchTags.get(position));
        keywordListAdapter.setSelectedPosition(position);
        searchTagStatus = 0;
    }

    @Override
    public void onError() {}

    @Override
    public void onItemClick(View view, String position) {
        int pos = Integer.parseInt(position.substring(1, position.length()));

        Intent intent = new Intent(HomeSearchActivity.this, KnowledgeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("knowledgeID", pos);
        bundle.putInt("module", 3);
        intent.putExtras(bundle);
        startActivity(intent);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        searchTagStatus = 1;
        save();
        keyword = mSearchEdt.getText().toString();
        mSearchAnswer.setVisibility(View.VISIBLE);
        if (homeSearchPresenter == null) {
            checkedStatus(tv_item_one, tv_one_underline);
            homeSearchPresenter = new HomeSearchPresenter(this);
            homeSearchPresenter.onCreate();
        }
        homeSearchPresenter.getHomeSearchList(repository, keyword);
    }


    public void initSearchHistory() {
        String history = mSharedPreferences.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(history)) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            for (Object o : history.split(",")) {
                map = new HashMap<String, Object>();
                map.put("searchHistoryItem", (String) o);
                list.add(map);
            }
            mHistoryData = list;
        }
        if (mHistoryData.size() == 0) {
            mSearchHistory.setVisibility(View.GONE);
        }
        mSearchHistoryAdapter = new SearchHistoryAdapter(this, mHistoryData);
        mSearchHistoryRecyclerView.setAdapter(mSearchHistoryAdapter);
        mSearchHistoryRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSearchEdt.setText((String) mHistoryData.get(position).get("searchHistoryItem"));
                keyword = mSearchEdt.getText().toString();
                mSearchHistory.setVisibility(View.GONE);
                mSearchAnswer.setVisibility(View.VISIBLE);
                if (homeSearchPresenter == null) {
                    checkedStatus(tv_item_one, tv_one_underline);
                    homeSearchPresenter = new HomeSearchPresenter(HomeSearchActivity.this);
                    homeSearchPresenter.onCreate();
                }
                homeSearchPresenter.getHomeSearchList(repository, keyword);
            }
        });
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    public void cleanHistory() {
        mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, "");
        mEditor.commit();
        mHistoryData.clear();
        mSearchHistoryAdapter.notifyDataSetChanged();
        mSearchHistory.setVisibility(View.GONE);
        Toast.makeText(HomeSearchActivity.this, "清除搜索历史记录成功", Toast.LENGTH_SHORT).show();
    }

    public void save() {
        String text = mSearchEdt.getText().toString();
        String oldText = mSharedPreferences.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(text) && !oldText.contains(text)) {
            if (mHistoryData.size() > 4) {
                String[] historyList = oldText.split(",");
                oldText = "";
                for (int i = 0; i < 4; i++) {
                    oldText += historyList[i] + ",";
                }
            }
            mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, text + "," + oldText);
            mEditor.commit();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String keyword = intent.getStringExtra(EXTRA_KEY_KEYWORD);
        if (!TextUtils.isEmpty(keyword)) {
            mSearchEdt.setText(keyword);
        }
    }
}
