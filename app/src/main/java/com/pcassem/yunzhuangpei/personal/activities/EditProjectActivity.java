package com.pcassem.yunzhuangpei.personal.activities;

import android.app.Dialog;
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
import com.pcassem.yunzhuangpei.entity.MyProjectEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.bean.ProjectIDBean;
import com.pcassem.yunzhuangpei.personal.bean.ProjectBean;
import com.pcassem.yunzhuangpei.personal.presenter.ProjectPresenter;
import com.pcassem.yunzhuangpei.personal.view.ProjectListView;
import com.pcassem.yunzhuangpei.view.CommomDialog;
import com.pcassem.yunzhuangpei.view.citythreelist.activity.ProvinceActivity;

public class EditProjectActivity extends AppCompatActivity implements View.OnClickListener, ProjectListView,TextWatcher {


    private ImageView back_iv;

    private RelativeLayout address;
    private EditText project_name;
    private TextView project_address;

    private Button delete;
    private Button save;

    private int projectID;
    private String nameStr;
    private String addressStr;

    private boolean isSave = false;

    private ProjectPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        initView();
        initTouchEvent();
        initData();
    }

    private void initView() {
        back_iv = (ImageView) findViewById(R.id.back_iv);
        address = (RelativeLayout) findViewById(R.id.address);
        project_name = (EditText) findViewById(R.id.project_name);
        project_address = (TextView) findViewById(R.id.project_address);
        delete = (Button) findViewById(R.id.delete);
        save = (Button) findViewById(R.id.save);
    }

    private void initTouchEvent() {
        back_iv.setOnClickListener(this);
        address.setOnClickListener(this);
        delete.setOnClickListener(this);
        save.setOnClickListener(this);
        project_name.addTextChangedListener(this);
    }

    private void initData() {
        presenter = new ProjectPresenter(this);
        presenter.onCreate();

        projectID = this.getIntent().getIntExtra("my_project_id", -1);
        nameStr = this.getIntent().getStringExtra("my_project_name");
        addressStr = this.getIntent().getStringExtra("my_project_address");

        project_name.setText(nameStr);
        project_address.setText(addressStr);
    }

    @Override
    protected void onResume() {
        super.onResume();

        projectID = this.getIntent().getIntExtra("project_id",-1);
        if (projectID == -1){
            projectID = this.getIntent().getIntExtra("my_project_id", -1);
        }

        nameStr = this.getIntent().getStringExtra("project_name");
        if (nameStr == null){
            nameStr = this.getIntent().getStringExtra("my_project_name");
        }
        project_name.setText(nameStr);

        addressStr = this.getIntent().getStringExtra("address");
        if (addressStr != null){
            save.setTextColor(getResources().getColor(R.color.color_ffffff));
            save.setBackground(getResources().getDrawable(R.drawable.submit_style));
            isSave  = true;
        }else {
            addressStr = this.getIntent().getStringExtra("my_project_address");
        }
        project_address.setText(addressStr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.address:
                Intent intent = new Intent(EditProjectActivity.this,ProvinceActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("project_id",projectID);
                intent.putExtra("project_name",project_name.getText().toString());
                startActivity(intent);
                break;
            case R.id.delete:
                new CommomDialog(this, R.style.commom_dialog, "是否删除？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            ProjectIDBean project = new ProjectIDBean();
                            project.setProjectID(projectID);
                            presenter.deleteProject(project);
                        }
                    }
                }).setTitle("提示信息").show();
                break;
            case R.id.save:
                if (isSave){
                    String[] a = addressStr.split("-");
                    ProjectBean project = new ProjectBean();
                    project.setProjectID(projectID);
                    project.setName(project_name.getText().toString());
                    project.setProvinceCode(a[0]);
                    project.setCityCode(a[1]);
                    project.setAreaCode(a[2]);
                    presenter.editProject(project);
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onEditProjectSuccess(ResultListEntity<String> status) {
       if (status.getCode() == 0){
           Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
           Intent intent = new Intent(EditProjectActivity.this, MyProjectActivity.class);
           startActivity(intent);
           EditProjectActivity.this.finish();
       }
       if (status.getCode() == 1){
           Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onEditProjectError() {
        Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteProjectSuccess(ResultListEntity<String> status) {
        if (status.getCode() == 0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (status.getCode() == 1) {
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeleteProjectError() {
        Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetProjectSuccess(ResultListEntity<MyProjectEntity> userInfo) {

    }

    @Override
    public void onGetProjectError() {

    }

    @Override
    public void onAddProjectSuccess(ResultListEntity<String> status) {

    }

    @Override
    public void onAddProjectError() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!s.toString().trim().equals(nameStr)){
            save.setTextColor(getResources().getColor(R.color.color_ffffff));
            save.setBackground(getResources().getDrawable(R.drawable.submit_style));
            isSave = true;
        }
    }
}
