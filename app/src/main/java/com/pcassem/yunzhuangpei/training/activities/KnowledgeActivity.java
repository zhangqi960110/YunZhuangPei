package com.pcassem.yunzhuangpei.training.activities;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.training.adapter.KnowledgePagerAdapter;
import com.pcassem.yunzhuangpei.training.adapter.SelectFirstListAdapter;
import com.pcassem.yunzhuangpei.training.adapter.SelectSecondListAdapter;
import com.pcassem.yunzhuangpei.utils.AnimationUtil;
import com.pcassem.yunzhuangpei.utils.JsonUtil;
import com.pcassem.yunzhuangpei.view.FlowLayout;
import com.pcassem.yunzhuangpei.view.HorizontalListView;

import org.json.JSONObject;

public class KnowledgeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "KnowledgeActivity";

    //tab选择栏实现
    private TextView tv_item_one;
    private TextView tv_item_two;
    private TextView tv_one_underline;
    private TextView tv_two_underline;
    private ViewPager myViewPager;
    private KnowledgePagerAdapter adapter;

    //伸缩框实现
    private LinearLayout select;
    private LinearLayout selectView;
    private LinearLayout selectFirstContent;
    private LinearLayout selectSecondContent;
    private LinearLayout selectFirstBuinding;
    private LinearLayout selectFirstTunnel;
    private ImageView selectImageView;
    private LinearLayout selectBuilding;
    private LinearLayout selectTunnel;
    private TextView mTitle;

    //分类填充数据
    private JSONObject selectedObject;
    private JSONObject leftKnowledgeObject;
    private JSONObject rightKnowledgeObject;

    String titleList[] = new String[]{"工艺流程","问题解答","规范政策"};
    String knowledgeList[][] = new String[][]{new String[]{"A2", "A1"}, new String[]{"B1", "B2"}, new String[]{"C1", "C2"}};
    String knowledgeTitle[][] = new String[][]{new String[]{"施工指南", "模版资料"}, new String[]{"常见问题", "施工问题"}, new String[]{"标准规范", "政策文件"}};
    Boolean isThreeLayers[][] = new Boolean[][]{new Boolean[]{true, true}, new Boolean[]{false, true}, new Boolean[]{false, false}};
    String leftFirstTitleList[];
    String leftSecondTitleList[][];
    String leftThirdTitleList[][][];

    String rightFirstTitleList[];
    String rightSecondTitleList[][];
    String rightThirdTitleList[][][];

    String leftFirstIdList[];
    String leftSecondIdList[][];
    String leftThirdIdList[][][];

    String rightFirstIdList[];
    String rightSecondIdList[][];
    String rightThirdIdList[][][];


    //网络请求字段保存
    int selectedId;
    Boolean isLeft = true;
    String[] request;
    int[][] saveSate = new int[][]{new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}};

    //选择框内部选择列表实现
    private HorizontalListView selectFirstList;
    private HorizontalListView selectSecondList;


    private SelectFirstListAdapter selectFirstListAdapter;
    private SelectSecondListAdapter selectSecondListAdapter;
    private LayoutInflater mInflater;
    private FlowLayout mFlowLayout;

    private AnimationUtil animationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("selectId");
        selectedId = Integer.parseInt(data);

        initView();
        initTouchEvent();
        initData(selectedId);
        mTitle.setText(titleList[selectedId]);
        mInflater = LayoutInflater.from(this);
        animationUtil = AnimationUtil.getInstance(this);
        animationUtil.setHeight(81);

        selectFirstListAdapter = new SelectFirstListAdapter(KnowledgeActivity.this, leftFirstTitleList);
        selectFirstList.setAdapter(selectFirstListAdapter);

        selectSecondListAdapter = new SelectSecondListAdapter(KnowledgeActivity.this, new String[]{});
        selectSecondList.setAdapter(selectSecondListAdapter);

        tv_item_one.setText(knowledgeTitle[selectedId][0]);
        tv_item_two.setText(knowledgeTitle[selectedId][1]);

        request = new String[]{knowledgeList[selectedId][0], "all", "all", "all", "all"};
        if (isThreeLayers[selectedId][0]) {
            selectSecondList.setVisibility(View.VISIBLE);
        } else {
            selectSecondList.setVisibility(View.GONE);
        }
        adapter = new KnowledgePagerAdapter(getSupportFragmentManager(), request);
        myViewPager.setAdapter(adapter);
        checkedStatus(tv_item_one, tv_one_underline);
    }

    private void initData(int position) {
        selectedObject = JsonUtil.selectObject(JsonUtil.firstStandardString(this), position);

        leftKnowledgeObject = JsonUtil.selectObject(JsonUtil.selectObject(selectedObject, 0), 0);
        rightKnowledgeObject = JsonUtil.selectObject(JsonUtil.selectObject(selectedObject, 1), 0);
        //选择标签title
        leftFirstTitleList = JsonUtil.selectOneList(leftKnowledgeObject, "title");
        leftSecondTitleList = JsonUtil.selectTwoList(leftKnowledgeObject, "title");
        leftThirdTitleList = JsonUtil.selectThreeList(leftKnowledgeObject, "title");

        rightFirstTitleList = JsonUtil.selectOneList(rightKnowledgeObject, "title");
        rightSecondTitleList = JsonUtil.selectTwoList(rightKnowledgeObject, "title");
        rightThirdTitleList = JsonUtil.selectThreeList(rightKnowledgeObject, "title");

        leftFirstIdList = JsonUtil.selectOneList(leftKnowledgeObject, "id");
        leftSecondIdList = JsonUtil.selectTwoList(leftKnowledgeObject, "id");
        leftThirdIdList = JsonUtil.selectThreeList(leftKnowledgeObject, "id");

        rightFirstIdList = JsonUtil.selectOneList(rightKnowledgeObject, "id");
        rightSecondIdList = JsonUtil.selectTwoList(rightKnowledgeObject, "id");
        rightThirdIdList = JsonUtil.selectThreeList(rightKnowledgeObject, "id");
    }

    //初始化控件
    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);
        tv_one_underline = (TextView) findViewById(R.id.tv_one_underline);
        tv_two_underline = (TextView) findViewById(R.id.tv_two_underline);
        select = (LinearLayout) findViewById(R.id.navigation_select_btn);
        selectView = (LinearLayout) findViewById(R.id.navigation_select);
        selectFirstContent = (LinearLayout) findViewById(R.id.select_first_content);
        selectSecondContent = (LinearLayout) findViewById(R.id.select_second_content);
        selectFirstBuinding = (LinearLayout) findViewById(R.id.select_first_buinding);
        selectFirstTunnel = (LinearLayout) findViewById(R.id.select_first_tunnel);
        selectImageView = (ImageView) findViewById(R.id.select_image_view);
        selectBuilding = (LinearLayout) findViewById(R.id.select_buinding);
        selectTunnel = (LinearLayout) findViewById(R.id.select_tunnel);

        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        selectFirstList = (HorizontalListView) findViewById(R.id.select_first_list);
        selectSecondList = (HorizontalListView) findViewById(R.id.select_second_list);
        mFlowLayout = (FlowLayout) findViewById(R.id.flow_layout);
    }

    //监听事件
    private void initTouchEvent() {
        myViewPager.setOnPageChangeListener(new MyPagerChangeListener());
        selectFirstList.setOnItemClickListener(this);
        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        selectFirstBuinding.setOnClickListener(this);
        selectFirstTunnel.setOnClickListener(this);
        select.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigation_select_btn:
                if (selectView.getVisibility() == View.GONE)
                    animationUtil.animateOpen(selectView);
                selectFirstContent.setVisibility(View.VISIBLE);
                selectSecondContent.setVisibility(View.GONE);
                if (isLeft) {
                    saveSate[0][0] = -1;
                    saveSate[0][1] = -1;
                    saveSate[0][2] = -1;
                    saveSate[0][3] = -1;

                } else {
                    saveSate[1][0] = -1;
                    saveSate[1][1] = -1;
                    saveSate[1][2] = -1;
                    saveSate[1][3] = -1;
                }
                selectKnowledge();
                request[1] = "all";
                request[2] = "all";
                request[3] = "all";
                request[4] = "all";
                adapter.setData(request);
                break;
            case R.id.select_first_buinding:
                if (isLeft) {
                    selectFirstListAdapter.setData(leftFirstTitleList);
                    saveSate[0][0] = 0;
                    saveSate[0][1] = -1;
                    saveSate[0][2] = -1;
                    saveSate[0][3] = -1;
                    adapter.setData(request);
                } else {
                    selectFirstListAdapter.setData(rightFirstTitleList);
                    saveSate[1][0] = 0;
                    saveSate[1][1] = -1;
                    saveSate[1][2] = -1;
                    saveSate[1][3] = -1;
                }
                request[1] = "C1";
                request[2] = "all";
                request[3] = "all";
                request[4] = "all";
                adapter.setData(request);
                selectFirstPosition = -1;
                displaySelectIcon(selectBuilding);
                selectFirstContent.setVisibility(View.GONE);
                selectSecondContent.setVisibility(View.VISIBLE);
                break;
            case R.id.select_first_tunnel:
                if (isLeft) {
                    selectFirstListAdapter.setData(leftFirstTitleList);
                    saveSate[0][0] = 1;
                    saveSate[0][1] = -1;
                    saveSate[0][2] = -1;
                    saveSate[0][3] = -1;
                } else {
                    selectFirstListAdapter.setData(rightFirstTitleList);
                    saveSate[1][0] = 1;
                    saveSate[1][1] = -1;
                    saveSate[1][2] = -1;
                    saveSate[1][3] = -1;
                }
                request[1] = "C2";
                request[2] = "all";
                request[3] = "all";
                request[4] = "all";
                adapter.setData(request);
                selectFirstPosition = -1;
                displaySelectIcon(selectTunnel);
                selectFirstContent.setVisibility(View.GONE);
                selectSecondContent.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_item_one:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.tv_item_two:
                myViewPager.setCurrentItem(1);
                break;
        }
    }

    int selectFirstPosition = -1;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (selectFirstPosition != position) {
            selectFirstListAdapter.setSelectedPosition(position);
            if (isLeft) {
                saveSate[0][1] = position;
                saveSate[0][2] = -1;
                saveSate[0][3] = -1;
                if (isThreeLayers[selectedId][0]) {
                    selectSecondListAdapter.setData(leftSecondTitleList[position]);
                    setSubList(position);
                } else {
                    popularTagsData(leftSecondTitleList[position], position, -1);
                }
                request[2] = leftFirstIdList[position];
            } else {
                saveSate[1][1] = position;
                saveSate[1][2] = -1;
                saveSate[1][3] = -1;
                if (isThreeLayers[selectedId][1]) {
                    selectSecondListAdapter.setData(rightSecondTitleList[position]);
                    setSubList(position);
                } else {
                    popularTagsData(rightSecondTitleList[position], position, -1);
                }
                request[2] = rightFirstIdList[position];
            }
            selectFirstPosition = position;
            request[3] = "all";
            request[4] = "all";
        } else {
            if (isLeft) {
                selectFirstListAdapter.setData(leftFirstTitleList);
                saveSate[0][1] = -1;
                saveSate[0][2] = -1;
                saveSate[0][3] = -1;
            } else {
                selectFirstListAdapter.setData(rightFirstTitleList);
                saveSate[0][1] = -1;
                saveSate[0][2] = -1;
                saveSate[0][3] = -1;
            }
            selectSecondListAdapter.setData(new String[]{});
            mFlowLayout.removeAllViews();
            selectFirstPosition = -1;
            request[2] = "all";
            request[3] = "all";
            request[4] = "all";
        }
        adapter.setData(request);
        selectSecondPosition = -1;
    }

    private int selectSecondPosition = -1;
    public void setSubList(final int position) {
        final int localtion = position;
        mFlowLayout.removeAllViews();
        selectThirdPosition = -1;

        selectSecondList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (selectSecondPosition != position) {
                    selectSecondListAdapter.setSelectedPosition(position);
                    if (isLeft) {
                        saveSate[0][2] = position;
                        saveSate[0][3] = -1;
                        popularTagsData(leftThirdTitleList[localtion][position], localtion, position);
                        request[3] = leftSecondIdList[localtion][position];
                    } else {
                        saveSate[1][2] = position;
                        saveSate[1][3] = -1;
                        popularTagsData(leftThirdTitleList[localtion][position], localtion, position);
                        request[3] = rightSecondIdList[localtion][position];
                    }
                    request[4] = "all";
                    selectSecondPosition = position;
                } else {
                    if (isLeft) {
                        saveSate[0][2] = -1;
                        saveSate[0][3] = -1;
                        selectSecondListAdapter.setData(leftSecondTitleList[localtion]);
                    } else {
                        saveSate[0][2] = -1;
                        saveSate[0][3] = -1;
                        selectSecondListAdapter.setData(rightSecondTitleList[localtion]);
                    }
                    request[3] = "all";
                    request[4] = "all";
                    mFlowLayout.removeAllViews();
                    selectSecondPosition = -1;
                }
                adapter.setData(request);
            }
        });
    }


    private int selectThirdPosition = -1;
    private void popularTagsData(final String[] data, final int first, final int second) {
        mFlowLayout.removeAllViews();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null)
                continue;

            final TextView textView = (TextView) mInflater.inflate(R.layout.item_select_tags, mFlowLayout, false);
            textView.setText(data[i]);
            textView.setTag(i);
            //添加到父View
            mFlowLayout.addView(textView);
            mFlowLayout.setOnItemClickClick(new FlowLayout.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    if (selectThirdPosition != position) {
                        if (isLeft) {
                            saveSate[0][3] = position;
                            if (isThreeLayers[selectedId][0]){
                                request[4] = leftThirdIdList[first][second][position];
                            }else{
                                request[3] = leftSecondIdList[first][position];
                                request[4] = "all";
                            }
                        } else {
                            saveSate[1][3] = position;
                            if (isThreeLayers[selectedId][1]){
                                request[4] = rightThirdIdList[first][second][position];
                            }else{
                                request[3] = rightSecondIdList[first][position];
                                request[4] = "all";
                            }
                        }
                        selectThirdPosition = position;
                    } else {
                        if (isLeft) {
                            saveSate[0][3] = -1;
                            if (isThreeLayers[selectedId][0]){
                                request[4] = "all";
                            }else{
                                request[3] = "all";
                                request[4] = "all";
                            }
                        } else {
                            saveSate[1][3] = -1;
                            if (isThreeLayers[selectedId][1]){
                                request[4] = "all";
                            }else{
                                request[3] = "all";
                                request[4] = "all";
                            }
                        }
                        TextView tv = (TextView) mFlowLayout.getChildAt(position);
                        tv.setBackgroundResource(R.drawable.select_tags);
                        tv.setTextColor(getResources().getColor(R.color.color_333333));
                        selectThirdPosition = -1;
                    }
                    adapter.setData(request);

                }
            });
        }
    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    isLeft = true;
                    checkedStatus(tv_item_one, tv_one_underline);
                    saveSelectState(0);
                    break;
                case 1:
                    isLeft = false;
                    checkedStatus(tv_item_two, tv_two_underline);
                    saveSelectState(1);
                    break;
            }
        }
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

    }

    private void displaySelectIcon(View view) {
        selectImageView.setVisibility(View.GONE);
        selectBuilding.setVisibility(View.GONE);
        selectTunnel.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    private void selectKnowledge() {
        if (isLeft){
            request[0] = knowledgeList[selectedId][0];
        }else{
            request[0] = knowledgeList[selectedId][1];
        }
        request[1] = "all";
        request[2] = "all";
        request[3] = "all";
        request[4] = "all";
        displaySelectIcon(selectImageView);
        selectFirstContent.setVisibility(View.VISIBLE);
        selectSecondContent.setVisibility(View.GONE);

        selectFirstListAdapter.setData(new String[]{});
        selectSecondListAdapter.setData(new String[]{});
        mFlowLayout.removeAllViews();
    }

    private void saveSelectState(int left) {

        selectSecondList.setVisibility(View.VISIBLE);
        if (!isThreeLayers[selectedId][left])
            selectSecondList.setVisibility(View.GONE);
        selectKnowledge();

        if (saveSate[left][0] == 0)
            displaySelectIcon(selectBuilding);

        if (saveSate[left][0] == 1)
            displaySelectIcon(selectTunnel);

        if (saveSate[left][0] == 0 || saveSate[left][0] == 1) {
            if (isLeft) {
                selectFirstListAdapter.setData(leftFirstTitleList);
                if (saveSate[left][1] != -1) {
                    selectFirstListAdapter.setSelectedPosition(saveSate[left][1]);
                    selectFirstPosition = saveSate[left][1];
                    if (isThreeLayers[selectedId][left]) {
                        selectSecondListAdapter.setData(leftSecondTitleList[saveSate[left][1]]);
                        if (saveSate[left][2] != -1) {
                            selectSecondListAdapter.setSelectedPosition(saveSate[left][2]);
                            selectSecondPosition = saveSate[left][2];
                            popularTagsData(leftThirdTitleList[saveSate[left][1]][saveSate[left][2]], saveSate[left][1], saveSate[left][2]);
                            if (saveSate[left][3] != -1) {
                                TextView tv = (TextView) mFlowLayout.getChildAt(saveSate[left][3]);
                                tv.setBackgroundResource(R.drawable.select_tags_checked);
                                tv.setTextColor(getResources().getColor(R.color.color_13386d));
                                selectThirdPosition = saveSate[left][3];
                            }
                        }
                    } else {
                        popularTagsData(leftSecondTitleList[saveSate[left][1]], saveSate[left][1], -1);
                        if (saveSate[left][3] != -1) {
                            TextView tv = (TextView) mFlowLayout.getChildAt(saveSate[left][3]);
                            tv.setBackgroundResource(R.drawable.select_tags_checked);
                            tv.setTextColor(getResources().getColor(R.color.color_13386d));
                            selectThirdPosition = saveSate[left][3];
                        }
                    }
                }
            } else {
                selectFirstListAdapter.setData(rightFirstTitleList);
                if (saveSate[left][1] != -1) {
                    selectFirstListAdapter.setSelectedPosition(saveSate[left][1]);
                    selectFirstPosition = saveSate[left][1];
                    if (isThreeLayers[selectedId][left]) {
                        selectSecondListAdapter.setData(rightSecondTitleList[saveSate[left][1]]);
                        if (saveSate[left][2] != -1) {
                            selectSecondListAdapter.setSelectedPosition(saveSate[left][2]);
                            popularTagsData(rightThirdTitleList[saveSate[left][1]][saveSate[left][2]], saveSate[left][1], saveSate[left][2]);
                            if (saveSate[left][3] != -1) {
                                TextView tv = (TextView) mFlowLayout.getChildAt(saveSate[left][3]);
                                tv.setBackgroundResource(R.drawable.select_tags_checked);
                                tv.setTextColor(getResources().getColor(R.color.color_13386d));
                                selectThirdPosition = saveSate[left][3];
                            }
                        }
                    } else {
                        popularTagsData(rightSecondTitleList[saveSate[left][1]], saveSate[left][1], -1);
                        if (saveSate[left][3] != -1) {
                            TextView tv = (TextView) mFlowLayout.getChildAt(saveSate[left][3]);
                            tv.setBackgroundResource(R.drawable.select_tags_checked);
                            tv.setTextColor(getResources().getColor(R.color.color_13386d));
                            selectThirdPosition = saveSate[left][3];
                        }
                    }
                }
            }
            selectFirstContent.setVisibility(View.GONE);
            selectSecondContent.setVisibility(View.VISIBLE);
        }

        if (saveSate[left][0] == 0){
            request[1] = "C1";
        }else if (saveSate[left][0] == 1){
            request[1] = "C2";
        }else {
            request[1] = "all";
        }

        if (saveSate[left][1] == -1){
            request[2] = "all";
        }else {
            if (isLeft){
                request[2] = leftFirstIdList[saveSate[left][1]];
            }else{
                request[2] = rightFirstIdList[saveSate[left][1]];
            }
        }

        if (isThreeLayers[selectedId][left]){
            if (saveSate[left][2] == -1){
                request[3] = "all";
            }else {
                if (isLeft){
                    request[3] = leftSecondIdList[saveSate[left][1]][saveSate[left][2]];
                }else{
                    request[3] = rightSecondIdList[saveSate[left][1]][saveSate[left][2]];
                }
            }
        }else{
            if (saveSate[left][2] == -1){
                request[3] = "all";
                request[4] = "all";
                adapter.setData(request);
                return;
            }else {
                if (isLeft){
                    request[3] = leftSecondIdList[saveSate[left][1]][saveSate[left][2]];
                    request[4] = "all";
                    adapter.setData(request);
                    return;
                }else{
                    request[3] = rightSecondIdList[saveSate[left][1]][saveSate[left][2]];
                    request[4] = "all";
                    adapter.setData(request);
                    return;
                }
            }
        }

        if (saveSate[left][3] == -1){
            request[4] = "all";
            adapter.setData(request);
        }else {
            if (isLeft){
                request[4] = leftThirdIdList[saveSate[left][1]][saveSate[left][2]][saveSate[left][3]];
                adapter.setData(request);
            }else {
                request[4] = rightThirdIdList[saveSate[left][1]][saveSate[left][2]][saveSate[left][3]];
                adapter.setData(request);
            }
        }

    }

}
