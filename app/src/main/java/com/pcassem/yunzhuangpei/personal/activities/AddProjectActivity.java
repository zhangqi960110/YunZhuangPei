package com.pcassem.yunzhuangpei.personal.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.activities.SubmitSuccessActivity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.bean.UserProjectBean;
import com.pcassem.yunzhuangpei.personal.presenter.ProjectPresenter;
import com.pcassem.yunzhuangpei.personal.view.AddProjectView;
import com.pcassem.yunzhuangpei.view.citythreelist.activity.ProvinceActivity;

public class AddProjectActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener,AddProjectView {

    private ImageView back_iv;

    private RelativeLayout projectAddress;
    private EditText name;
    private TextView address;
    private Button submitProject;

    private String mName;
    private String mAddress;

    private boolean isSubmit = false;
    private ProjectPresenter presenter;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        initView();
        initTouchEvent();
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);

        presenter = new ProjectPresenter(this);
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mName = this.getIntent().getStringExtra("project_name");
        name.setText(mName);
        mAddress = this.getIntent().getStringExtra("address");
        address.setText(mAddress);

        if (mName == null || mAddress == null || mName.equals("") || mAddress.equals("")){
            submitProject.setTextColor(getResources().getColor(R.color.color_666666));
            submitProject.setBackground(getResources().getDrawable(R.drawable.btn_style_white_box_corners));
            isSubmit = false;
        }else {
            submitProject.setTextColor(getResources().getColor(R.color.color_ffffff));
            submitProject.setBackground(getResources().getDrawable(R.drawable.submit_style));
            isSubmit = true;
        }
    }

    private void initView(){
        back_iv = (ImageView) findViewById(R.id.back_iv);
        projectAddress = (RelativeLayout) findViewById(R.id.project_address);
        name = (EditText) findViewById(R.id.project_name);
        address = (TextView) findViewById(R.id.address);
        submitProject = (Button) findViewById(R.id.submit_project);
    }

    private void initTouchEvent(){
        back_iv.setOnClickListener(this);
        name.addTextChangedListener(this);
        projectAddress.setOnClickListener(this);
        submitProject.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.project_address:
                Intent intent = new Intent(AddProjectActivity.this,ProvinceActivity.class);
                intent.putExtra("type",0);
                intent.putExtra("project_name",name.getText().toString());
                startActivity(intent);
                break;
            case R.id.submit_project:
                if (isSubmit){
                    String[] adt = mAddress.split("-");
                    UserProjectBean project = new UserProjectBean();
                    project.setUserID(userID);
                    project.setName(mName);
                    project.setProvinceCode(adt[0]);
                    project.setCityCode(adt[1]);
                    project.setAreaCode(adt[2]);
                    presenter.addProject(project);
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() != 0 && mAddress != null){
            submitProject.setTextColor(getResources().getColor(R.color.color_ffffff));
            submitProject.setBackground(getResources().getDrawable(R.drawable.submit_style));

            isSubmit = true;
        }else{
            submitProject.setTextColor(getResources().getColor(R.color.color_666666));
            submitProject.setBackground(getResources().getDrawable(R.drawable.btn_style_white_box_corners));

            isSubmit = false;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<String> status) {
          if (status.getCode() == 0){
              Intent intent = new Intent(AddProjectActivity.this, MyProjectActivity.class);
              startActivity(intent);
              AddProjectActivity.this.finish();
          }
          if (status.getCode() == 1){
              Toast.makeText(this, "项目已经存在", Toast.LENGTH_SHORT).show();
          }
          if (status.getCode() == 2){
              Toast.makeText(this, "新增失败", Toast.LENGTH_SHORT).show();
          }
    }

    @Override
    public void onError() {
        Toast.makeText(AddProjectActivity.this, "新增失败", Toast.LENGTH_SHORT).show();
    }
}
