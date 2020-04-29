package com.pcassem.yunzhuangpei.training.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.CourseDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.training.fragments.SignUpDialogFragment;
import com.pcassem.yunzhuangpei.training.presenter.CourseDetailsPresenter;
import com.pcassem.yunzhuangpei.training.view.CourseDetailsView;
import com.pcassem.yunzhuangpei.utils.HtmlFormatUtil;
import com.pcassem.yunzhuangpei.utils.DateUtil;

public class CourseDetailsActivity extends AppCompatActivity implements View.OnClickListener, CourseDetailsView {

    private ScrollView course_details_activity;
    private ImageView backIv;

    private TextView courseTitle;
    private SimpleDraweeView courseIcon;
    private TextView courseDate;
    private TextView courseAddress;
    private TextView courseSponsor;
    private TextView courseFee;
    private TextView courseTotalCount;
    private WebView courseContent;
    private Button sourseSignStatus;
    private int signStatus = 1;

    private CourseDetailsEntity courseDetails;
    private CourseDetailsPresenter courseDetailsPresenter;
    private int courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        initView();
        initTouceEvent();

        courseID = getIntent().getIntExtra("courseID", -1);
        courseDetailsPresenter = new CourseDetailsPresenter(this);
        courseDetailsPresenter.onCreate();
        courseDetailsPresenter.getCourseDetailsList(courseID);
    }

    private void initView() {
        course_details_activity = (ScrollView) findViewById(R.id.course_details_activity);
        backIv = (ImageView) findViewById(R.id.back_iv);
        courseTitle = (TextView) findViewById(R.id.course_title);
        courseIcon = (SimpleDraweeView) findViewById(R.id.course_icon);
        courseDate = (TextView) findViewById(R.id.course_date);
        courseAddress = (TextView) findViewById(R.id.course_address);
        courseSponsor = (TextView) findViewById(R.id.course_sponsor);
        courseFee = (TextView) findViewById(R.id.course_fee);
        courseTotalCount = (TextView) findViewById(R.id.course_total_count);
        courseContent = (WebView) findViewById(R.id.course_content);
        sourseSignStatus = (Button) findViewById(R.id.course_sign_status);
    }

    private void initTouceEvent() {
        sourseSignStatus.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_sign_status:
                if (signStatus == 1){
                    SignUpDialogFragment.newInstance().show(getSupportFragmentManager(), "dialog");
                }
                break;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<CourseDetailsEntity> courseDetailsListEntity) {

        courseDetails = courseDetailsListEntity.getResult().get(0);
        String iconUrl = courseDetails.getIcon();
        courseTitle.setText(courseDetails.getTitle());
        courseIcon.setImageURI(Uri.parse(iconUrl));
        courseDate.setText("培训时间：" + DateUtil.formatDate(courseDetails.getDate()));
        courseAddress.setText("培训地点：" + courseDetails.getAddress());
        courseSponsor.setText("举办方：" + courseDetails.getSponsor());
        courseFee.setText("培训费：" + courseDetails.getFee() + "元/每人");
        String countStr = "限<font color='#ff0000'>" + courseDetails.getTotalCount() + "</font>人，" + "<font color='#ff0000'>" + courseDetails.getSignCount()+ "</font>人已报名";
        courseTotalCount.setText(Html.fromHtml(countStr));
        courseContent.loadDataWithBaseURL(null, HtmlFormatUtil.getNewContent(courseDetails.getContent()), "text/html", "utf-8", null);

        signStatus = courseDetails.getSignStatus();
        if (signStatus == 2){
            sourseSignStatus.setBackground(getResources().getDrawable(R.drawable.submit_non_style));
            sourseSignStatus.setText("已报名");
        }else if (signStatus == 3){
            sourseSignStatus.setBackground(getResources().getDrawable(R.drawable.submit_non_style));
            sourseSignStatus.setText("无法报名");
        }
        course_details_activity.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "网络出错", Toast.LENGTH_SHORT).show();
    }

}
