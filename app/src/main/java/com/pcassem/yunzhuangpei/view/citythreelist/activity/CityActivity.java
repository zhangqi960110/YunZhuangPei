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
import com.pcassem.yunzhuangpei.view.RecycleViewDivider;
import com.pcassem.yunzhuangpei.view.citythreelist.bean.CityInfoBean;

import java.util.List;

import static com.pcassem.yunzhuangpei.utils.CityListLoader.BUNDATA;

public class CityActivity extends AppCompatActivity {

    private ImageView mImgBack;
    private RecyclerView mCityRecyclerView;


    private CityInfoBean mProInfo = null;
    private int projectID;
    private int type;
    private String mName;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        projectID = this.getIntent().getIntExtra("project_id",-1);
        type = this.getIntent().getIntExtra("type",-1);
        mProInfo = this.getIntent().getParcelableExtra(BUNDATA);
        mName = this.getIntent().getStringExtra("project_name");
        address = this.getIntent().getStringExtra("address01");
        initView();

        setData(mProInfo);

    }

    private void setData(CityInfoBean mProInfo) {

        if (mProInfo != null && mProInfo.getCityList().size() > 0) {

            final List<CityInfoBean> cityList = mProInfo.getCityList();

            if (cityList == null) {
                return;
            }

            CityAdapter cityAdapter = new CityAdapter(CityActivity.this, cityList);
            mCityRecyclerView.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
                @Override
                public void onItemSelected(View view, int position) {
                    Intent intent = new Intent(CityActivity.this, AreaActivity.class);
                    intent.putExtra(BUNDATA, cityList.get(position));
                    intent.putExtra("project_id",projectID);
                    intent.putExtra("type",type);
                    intent.putExtra("address02",address+ "-" +cityList.get(position).getName());
                    intent.putExtra("project_name",mName);
                    startActivity(intent);
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
