package com.pcassem.yunzhuangpei.advisory.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.bean.OnlineBean;
import com.pcassem.yunzhuangpei.advisory.presenter.ConsultPresenter;
import com.pcassem.yunzhuangpei.advisory.presenter.ExportDetailsPresenter;
import com.pcassem.yunzhuangpei.advisory.view.ExportDetailsView;
import com.pcassem.yunzhuangpei.advisory.view.OnlineView;
import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.utils.DateUtil;
import com.pcassem.yunzhuangpei.view.CommomDialog;
import com.pcassem.yunzhuangpei.view.FlowLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.rong.callkit.RongCallKit;
import io.rong.callkit.RongCallProxy;
import io.rong.calllib.IRongCallListener;
import io.rong.calllib.RongCallCommon;
import io.rong.calllib.RongCallSession;
import io.rong.photoview.log.LoggerDefault;


public class ExportDetailsActivity extends AppCompatActivity implements ExportDetailsView,OnlineView, View.OnClickListener,IRongCallListener {

    public static final String TAG = "ExportDetailsActivity";

    private ImageView backIv;

    private ScrollView exprotDetailsActivity;

    private SimpleDraweeView mIcon;
    private TextView mName;
    private TextView mCompany;
    private TextView mTitle;
    private TextView mAddress;
    private TextView mDescription;
    private TextView mBusiness;
    private Button mIsOnline;
    private Button mIsBook;
    private FlowLayout mDomains;
    private TextView mAnswerCount;
    private TextView mBookCount;
    private TextView mOnlineCount;
    private TextView mArticleCount;

    private int isOnline;
    private int isBook;

    private String phoneNumber;

    private LayoutInflater mInflater;
    private List<String> mVals;
    private ExportDetailsPresenter mExportDetailsPresenter;
    private ExportDetailsEntity mData;
    private int specialistID;

    private ConsultPresenter presenter;
    private int type;
    private int userID;
    private long createDate;
    private long startDate;
    private long endDate;
    private boolean isCallKit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_details);
        initView();
        initTouchEvent();

        type = getSharedPreferences("type",Context.MODE_PRIVATE).getInt("type",1);
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        mVals = new ArrayList<>();

        mExportDetailsPresenter = new ExportDetailsPresenter(this);
        mExportDetailsPresenter.onCreate();
        specialistID = getIntent().getIntExtra("specialistID", -1);
        mExportDetailsPresenter.getExportDetails(specialistID);

        presenter = new ConsultPresenter(this);
        presenter.onCreate();
    }

    private void initView(){
        mInflater = LayoutInflater.from(this);
        exprotDetailsActivity = (ScrollView) findViewById(R.id.export_details_activity);
        backIv = (ImageView) findViewById(R.id.back_iv);
        mIcon = (SimpleDraweeView) findViewById(R.id.icon);
        mName = (TextView) findViewById(R.id.name);
        mCompany = (TextView) findViewById(R.id.company);
        mTitle = (TextView) findViewById(R.id.title);
        mAddress = (TextView) findViewById(R.id.address);
        mDescription = (TextView) findViewById(R.id.description);
        mBusiness = (TextView) findViewById(R.id.business);
        mIsOnline = (Button) findViewById(R.id.is_online);
        mIsBook = (Button) findViewById(R.id.is_book);
        mDomains = (FlowLayout) findViewById(R.id.domains);
        mAnswerCount = (TextView) findViewById(R.id.answer_count);
        mBookCount = (TextView) findViewById(R.id.book_count);
        mOnlineCount = (TextView) findViewById(R.id.online_count);
        mArticleCount = (TextView) findViewById(R.id.article_count);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
        mIsOnline.setOnClickListener(this);
        mIsBook.setOnClickListener(this);
        RongCallProxy.getInstance().setAppCallListener(this);
    }

    private void popularTagsData(){
        for (int i = 0; i < mVals.size(); i++) {
            TextView tv = (TextView) mInflater.inflate(
                    R.layout.item_advisory_export_tags, mDomains, false);
            tv.setText(mVals.get(i));
            //添加到父View
            mDomains.addView(tv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.is_online:
                if (isLogin() == -1){
                    myDialog();
                }else {
                    RongCallKit.startSingleCall(ExportDetailsActivity.this, phoneNumber , RongCallKit.CallMediaType.CALL_MEDIA_TYPE_VIDEO);
                }
                break;
            case R.id.is_book:
                if (isLogin() == -1){
                    myDialog();
                }else if (type == 1){
                    Toast.makeText(this, "非付费会员不能实时连线", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(ExportDetailsActivity.this, BookActivity.class);
                    startActivity(intent);
                    ExportDetailsActivity.this.finish();
                }
                break;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<ExportDetailsEntity> exportDetails) {
        mData = exportDetails.getResult().get(0);

        phoneNumber = mData.getPhoneNumber();

        String url = mData.getIcon();
        mIcon.setImageURI(Uri.parse(url));
        mName.setText(mData.getName());
        mCompany.setText(mData.getCompany());
        mTitle.setText(mData.getTitle());
        mAddress.setText("工作地点："+mData.getAddress());
        mDescription.setText(mData.getDescription());
        mBusiness.setText(mData.getBusiness());
        mVals = mData.getDomains();
        popularTagsData();

        isOnline = mData.getIsOnline();
        isBook = mData.getIsBook();

        if (isOnline == 1){
            mIsOnline.setBackgroundResource(R.drawable.submit_style);
        }else {
            mIsOnline.setBackgroundResource(R.drawable.submit_non_style);
            mIsOnline.setEnabled(false);
        }

        if (isBook == 1){
            mIsBook.setBackgroundResource(R.drawable.submit_style);
        }else {
            mIsBook.setBackgroundResource(R.drawable.submit_non_style);
            mIsBook.setEnabled(false);
        }

        mAnswerCount.setText(mData.getAnswerCount() + "次");
        mBookCount.setText(mData.getBookCount() + "次");
        mOnlineCount.setText(mData.getOnlineCount() + "次");
        mArticleCount.setText(mData.getArticleCount() + "篇");
        exprotDetailsActivity.setVisibility(View.VISIBLE);

    }

    @Override
    public void onError() {

    }

    @Override
    public void onAddOnlineConsultSuccess(ResultListEntity<String> result) {
        if (result.getCode() == 0){
            Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "提交有误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAddOnlineConsultError() {
        Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onAddOnlineConsultError: ");
    }

    private int isLogin() {
        String username = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString("username", "");
        if (username == "") {
            return -1;
        } else {
            return getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        }

    }

    private void myDialog() {
        new CommomDialog(ExportDetailsActivity.this, R.style.commom_dialog, "尚未登录，是否先登录？", new CommomDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Intent intent = new Intent(ExportDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                }

            }
        }).setTitle("提示信息").show();
    }


    @Override
    public void onCallOutgoing(RongCallSession rongCallSession, SurfaceView surfaceView) {
        Log.d(TAG, "onCallOutgoing: ");
        createDate = System.currentTimeMillis();
    }

    @Override
    public void onCallConnected(RongCallSession rongCallSession, SurfaceView surfaceView) {
        Log.d(TAG, "onCallConnected: ");
    }

    @Override
    public void onCallDisconnected(RongCallSession rongCallSession, RongCallCommon.CallDisconnectedReason callDisconnectedReason) {
        Log.d(TAG, "onCallDisconnected: ");
        endDate = System.currentTimeMillis();
        if (isCallKit){
            OnlineBean online = new OnlineBean();
            online.setUserID(userID);
            online.setProjectID(0);
            online.setCreateDate(DateUtil.stampToDate(createDate));
            online.setOnlineDate((int) ((endDate - startDate) / 1000));
            presenter.addOnlineConsult(online);
        }
    }

    @Override
    public void onRemoteUserRinging(String s) {
        Log.d(TAG, "onRemoteUserRinging: ");
    }

    @Override
    public void onRemoteUserJoined(String s, RongCallCommon.CallMediaType callMediaType, SurfaceView surfaceView) {
        Log.d(TAG, "onRemoteUserJoined: ");
        startDate = System.currentTimeMillis();
        isCallKit = true;
    }

    @Override
    public void onRemoteUserInvited(String s, RongCallCommon.CallMediaType callMediaType) {
        Log.d(TAG, "onRemoteUserInvited: ");
    }

    @Override
    public void onRemoteUserLeft(String s, RongCallCommon.CallDisconnectedReason callDisconnectedReason) {
    }

    @Override
    public void onMediaTypeChanged(String s, RongCallCommon.CallMediaType callMediaType, SurfaceView surfaceView) {
    }

    @Override
    public void onError(RongCallCommon.CallErrorCode callErrorCode) {
    }

    @Override
    public void onRemoteCameraDisabled(String s, boolean b) {
    }
}
