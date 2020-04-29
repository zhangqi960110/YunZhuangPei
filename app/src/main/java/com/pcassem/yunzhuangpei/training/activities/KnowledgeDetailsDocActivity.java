package com.pcassem.yunzhuangpei.training.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.activities.CommentActivity;
import com.pcassem.yunzhuangpei.home.presenter.SearchKnowledgeDetailsPresenter;
import com.pcassem.yunzhuangpei.training.presenter.KnowledgeDetailsDocPresenter;
import com.pcassem.yunzhuangpei.training.presenter.KnowledgeDetailsPresenter;
import com.pcassem.yunzhuangpei.training.view.KnowledgeDetailsDocView;
import com.pcassem.yunzhuangpei.training.view.KnowledgeDetailsNonDocView;
import com.pcassem.yunzhuangpei.utils.DateUtil;
import com.pcassem.yunzhuangpei.utils.HtmlFormatUtil;
import com.pcassem.yunzhuangpei.view.MJavascriptInterface;
import com.pcassem.yunzhuangpei.view.MyWebViewClient;
import com.pcassem.yunzhuangpei.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KnowledgeDetailsDocActivity extends AppCompatActivity implements View.OnClickListener, KnowledgeDetailsDocView {


    public static final String TAG = "KnowledgeDetailsActivity";
    private ScrollView knowledge_details_doc_activity;
    private ImageView backIv;
    //评论条控件
    private TextView mCommentFrame;
    private EditText mCommentContent;
    private TextView mCommentSend;
    private LinearLayout mJumpCommentActivity;
    private LinearLayout mLike;
    private LinearLayout mCollection;

    private RelativeLayout mLayoutCommentMenu;
    private RelativeLayout mlayoutCommentSay;

    private KnowledgeDetailsDocEntity mNewsDetailsData;
    private KnowledgeDetailsDocPresenter mKnowledgeDetailsPresenter;
    private TextView mTitle;
    private TextView mDate;
    private TextView mReadCount;
    private ImageView mIcon;
    private TextView mFileName;
    private TextView mLikeCount;
    private TextView mCommentCount;

    private int knowledgeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_doc_details);

        initView();
        initTouchEvent();

        knowledgeID = getIntent().getIntExtra("knowledgeID", -1);
        mKnowledgeDetailsPresenter = new KnowledgeDetailsDocPresenter(this);
        mKnowledgeDetailsPresenter.onCreate();
        mKnowledgeDetailsPresenter.getKnowledgeDocDetailsList(knowledgeID);
    }


    private void initView() {

        knowledge_details_doc_activity = (ScrollView) findViewById(R.id.knowledge_details_doc_activity);
        backIv = (ImageView) findViewById(R.id.back_iv);
        mCommentFrame = (TextView) findViewById(R.id.comment_frame);
        mCommentContent = (EditText) findViewById(R.id.comment_content);
        mCommentSend = (TextView) findViewById(R.id.comment_send);
        mJumpCommentActivity = (LinearLayout) findViewById(R.id.jump_comment);
        mLayoutCommentMenu = (RelativeLayout) findViewById(R.id.layout_comment_menu);
        mlayoutCommentSay = (RelativeLayout) findViewById(R.id.layout_comment_say);


        mTitle = (TextView) findViewById(R.id.title);
        mDate = (TextView) findViewById(R.id.date);
        mReadCount = (TextView) findViewById(R.id.read_count);
        mFileName = (TextView) findViewById(R.id.file_name);
        mIcon = (ImageView) findViewById(R.id.icon);
        mLikeCount = (TextView) findViewById(R.id.like_count);
        mCommentCount = (TextView) findViewById(R.id.comment_count);
        mLike = (LinearLayout) findViewById(R.id.like);
        mCollection = (LinearLayout) findViewById(R.id.collection);

    }

    private void initTouchEvent() {
        backIv.setOnClickListener(this);
        mCommentFrame.setOnClickListener(this);
        mCommentSend.setOnClickListener(this);
        mJumpCommentActivity.setOnClickListener(this);
        mLike.setOnClickListener(this);
        mCollection.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.comment_frame:
//                mLayoutCommentMenu.setVisibility(View.GONE);
//                mlayoutCommentSay.setVisibility(View.VISIBLE);
                Toast.makeText(this, "正在开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.comment_send:
                sendComment();
                break;
            case R.id.jump_comment:
                Toast.makeText(this, "正在开发", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(KnowledgeDetailsDocActivity.this, CommentActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("newsID", knowledgeID + "");
//                intent.putExtras(bundle);
//                startActivity(intent);
                break;
            case R.id.like:
                Toast.makeText(this, "正在开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.collection:
                Toast.makeText(this, "正在开发", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mlayoutCommentSay.getVisibility() == View.VISIBLE) {
            mlayoutCommentSay.setVisibility(View.GONE);
            mLayoutCommentMenu.setVisibility(View.VISIBLE);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void sendComment() {
        if (mCommentContent.getText().toString().equals("")) {
            Toast.makeText(KnowledgeDetailsDocActivity.this, "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(KnowledgeDetailsDocActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSuccess(ResultListEntity<KnowledgeDetailsDocEntity> newsListEntity) {
        mNewsDetailsData = newsListEntity.getResult().get(0);
        mTitle.setText(mNewsDetailsData.getTitle() + "");
        mDate.setText(DateUtil.getStandardDate(mNewsDetailsData.getDate()));
        mReadCount.setText("阅读" + mNewsDetailsData.getReadCount() + "次");
        if (mNewsDetailsData.getFileType() == 1){
            mIcon.setBackgroundResource(R.drawable.doc);
        }else {
            mIcon.setBackgroundResource(R.drawable.pdf);
        }
        mFileName.setText(mNewsDetailsData.getFileName());
        mLikeCount.setText("赞" + mNewsDetailsData.getLikeCount());
        mCommentCount.setText("评论" + mNewsDetailsData.getCommentCount());

        knowledge_details_doc_activity.setVisibility(View.GONE);

    }

    @Override
    public void onError() {
        Toast.makeText(this, "网络出错", Toast.LENGTH_SHORT).show();
    }

}
