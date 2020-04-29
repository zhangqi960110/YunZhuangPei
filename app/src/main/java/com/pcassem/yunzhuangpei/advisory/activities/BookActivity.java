package com.pcassem.yunzhuangpei.advisory.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.bean.BookBean;
import com.pcassem.yunzhuangpei.advisory.presenter.ConsultPresenter;
import com.pcassem.yunzhuangpei.advisory.view.ConsultView;
import com.pcassem.yunzhuangpei.entity.ConsultInitEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyProjectActivity;
import com.pcassem.yunzhuangpei.utils.DateUtil;
import com.pcassem.yunzhuangpei.view.CommomDialog;

import java.text.ParseException;
import java.util.Calendar;

public class BookActivity extends AppCompatActivity implements View.OnClickListener, ConsultView {

    public static final String TAG = "BookActivity";
    public static final int SELECT_EXPORT_CODE = 100;
    public static final int SELECT_PROJECT_CODE = 101;

    private RelativeLayout mGuideSelectExport;

    private ImageView backIv;
    private TextView description_style;
    private RelativeLayout selectExportLayout;
    private ImageView selectImage;
    private TextView selectExportName;

    private TextView projectName;
    private ImageView selectProject;
    private TextView projectAddress;
    private EditText contactName;
    private EditText contactPhone;
    private TextView startDate;
    private TextView endDate;
    int year, month, day;
    private EditText content;
    private Button submit;


    private ConsultPresenter presenter;
    private ConsultInitEntity initConsult;

    private int userID;
    private int specialistID;
    private int projectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();
        initTouchEvent();
        initData();
    }

    private void initView() {
        mGuideSelectExport = (RelativeLayout) findViewById(R.id.guide_select_export);
        backIv = (ImageView) findViewById(R.id.back_iv);
        description_style = (TextView) findViewById(R.id.description_style);
        String str1 = "问题描述<font color='#999999'><small>(请详细描述项目现状和问题，越详细越好，便于专家更准确地为您分析解答)</small></font>";
        description_style.setText(Html.fromHtml(str1));

        startDate = (TextView) findViewById(R.id.start_date);
        endDate = (TextView) findViewById(R.id.end_date);

        selectExportLayout = (RelativeLayout) findViewById(R.id.guide_select_export);
        selectImage = (ImageView) findViewById(R.id.select_image);
        selectExportName = (TextView) findViewById(R.id.select_export_name);
        projectName = (TextView) findViewById(R.id.project_name);
        selectProject = (ImageView) findViewById(R.id.select_project);
        projectAddress = (TextView) findViewById(R.id.project_address);
        contactName = (EditText) findViewById(R.id.contact_name);
        contactPhone = (EditText) findViewById(R.id.contact_phone);
        content = (EditText) findViewById(R.id.content);
        submit = (Button) findViewById(R.id.submit);

    }

    private void initTouchEvent() {
        mGuideSelectExport.setOnClickListener(this);
        backIv.setOnClickListener(this);
        selectProject.setOnClickListener(this);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        selectExportLayout.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    private void initData() {
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        final Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH);
        day = ca.get(Calendar.DAY_OF_MONTH);

        presenter = new ConsultPresenter(this);
        presenter.onCreate();
        presenter.getInitConsult(userID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.guide_select_export:
                Intent intent = new Intent(BookActivity.this, SelectExportActivity.class);
                startActivityForResult(intent, SELECT_EXPORT_CODE);
                break;
            case R.id.select_project:
                Intent intent1 = new Intent(BookActivity.this, SelectProjectActivity.class);
                startActivityForResult(intent1, SELECT_PROJECT_CODE);
                break;
            case R.id.start_date:
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        startDate.setText(year + "-" + (++month) + "-" + day);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(this, 0, listener, year, month, day);
                dialog.show();
                break;
            case R.id.end_date:
                DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        endDate.setText(year + "-" + (++month) + "-" + day);
                    }
                };
                DatePickerDialog dialog2 = new DatePickerDialog(this, 0, listener2, year, month, day);
                dialog2.show();
                break;
            case R.id.submit:
                if (isSubmit()) {
                    BookBean book = new BookBean();
                    book.setUserID(userID);
                    book.setSpecialistID(specialistID);
                    book.setProjectID(projectID);
                    book.setContactName(contactName.getText().toString());
                    book.setContactPhone(contactPhone.getText().toString());
                    book.setStartDate(startDate.getText().toString());
                    book.setEndDate(endDate.getText().toString());
                    book.setContent(content.getText().toString());
                    presenter.addBookConsult(book);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SELECT_EXPORT_CODE) {
            specialistID = data.getExtras().getInt("export_id");
            String export_name = data.getExtras().getString("export_name");
            selectExportName.setText(export_name);
            selectImage.setVisibility(View.GONE);
            selectExportName.setVisibility(View.VISIBLE);
        } else if (resultCode == SELECT_PROJECT_CODE) {
            String project_name = data.getExtras().getString("project_name");
            String project_address = data.getExtras().getString("project_address");
            projectID = data.getExtras().getInt("project_id");
            projectName.setText(project_name);
            projectAddress.setText(project_address);
        }
    }

    @Override
    public void onAddCommonConsultSuccess(ResultListEntity<String> result) {
        if (result.getCode() == 0) {
            Intent intent = new Intent(BookActivity.this, SubmitSuccessActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAddCommonConsultError() {
        Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetConsultInitSuccess(ResultListEntity<ConsultInitEntity> result) {
        if (result.getCode() == 0) {
            initConsult = result.getResult().get(0);
            projectName.setText(initConsult.getUserProjectName());
            projectAddress.setText(initConsult.getUserProjectAddress());

            projectID = initConsult.getUserProjectID();
        } else {
            myDialog();
            BookActivity.this.finish();
        }
    }

    @Override
    public void onGetConsultInitError() {
        myDialog();
    }

    @Override
    public void onUploadImageSuccess(ResultListEntity<String> responseBody) {

    }

    @Override
    public void onUploadImageError() {

    }

    private boolean isSubmit() {
        if (TextUtils.isEmpty(selectExportName.getText()) || selectExportName.getVisibility() == View.GONE) {
            Toast.makeText(this, "请选择专家", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(projectName.getText())) {
            Toast.makeText(this, "请选择项目", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(contactName.getText())) {
            Toast.makeText(this, "请填写联系人", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(contactPhone.getText())) {
            Toast.makeText(this, "请填写联系电话", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(startDate.getText())) {
            Toast.makeText(this, "请选择开始日期", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(endDate.getText())) {
            Toast.makeText(this, "请选择结束日期", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            long startDt = DateUtil.dateToStemp(startDate.getText().toString().trim() + " 00:00:00");
            long endDt = DateUtil.dateToStemp(endDate.getText().toString().trim() + " 00:00:00");
            if ((startDt - endDt) >= 0) {
                Toast.makeText(this, "请重新选择日期", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "请重新选择日期", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(content.getText())) {
            Toast.makeText(this, "请添加问题描述", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void myDialog() {
        new CommomDialog(BookActivity.this, R.style.commom_dialog, "没有项目，请新建项目", new CommomDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Intent intent = new Intent(BookActivity.this, MyProjectActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                    BookActivity.this.finish();
                } else {
                    BookActivity.this.finish();
                }

            }
        }).setTitle("提示信息").show();
    }
}
