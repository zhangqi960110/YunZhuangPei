package com.pcassem.yunzhuangpei.view.citythreelist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.activities.AddProjectActivity;
import com.pcassem.yunzhuangpei.personal.activities.EditProjectActivity;
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;
import com.pcassem.yunzhuangpei.view.citythreelist.bean.CityInfoBean;

import java.util.List;

import static com.pcassem.yunzhuangpei.utils.CityListLoader.BUNDATA;

public class AreaActivity extends AppCompatActivity {
    private ImageView mImgBack;

    private RecyclerView mCityRecyclerView;

    private CityInfoBean mProCityInfo = null;
    private int projectID;
    private int type;
    private String mName;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        mProCityInfo = this.getIntent().getParcelableExtra(BUNDATA);
        projectID = this.getIntent().getIntExtra("project_id",-1);
        type = this.getIntent().getIntExtra("type",-1);
        address = this.getIntent().getStringExtra("address02");
        mName = this.getIntent().getStringExtra("project_name");
        initView();

        setData();

    }

    private void setData() {

        if (mProCityInfo != null && mProCityInfo.getCityList().size() > 0) {

            final List<CityInfoBean> cityList = mProCityInfo.getCityList();
            if (cityList == null) {
                return;
            }

            CityAdapter cityAdapter = new CityAdapter(AreaActivity.this, cityList);
            mCityRecyclerView.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
                @Override
                public void onItemSelected(View view, int position) {
                    if (type == 0){
                        Intent intent = new Intent(AreaActivity.this, AddProjectActivity.class);
                        intent.putExtra("address",address +"-"+cityList.get(position).getName());
                        intent.putExtra("project_name",mName);
                        startActivity(intent);
                    }else if (type == 1){
                        Intent intent2 = new Intent(AreaActivity.this, EditProjectActivity.class);
                        intent2.putExtra("project_id",projectID);
                        intent2.putExtra("address",address +"-"+cityList.get(position).getName());
                        intent2.putExtra("project_name",mName);
                        startActivity(intent2);
                    }
                }
            });

        }
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_left);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
