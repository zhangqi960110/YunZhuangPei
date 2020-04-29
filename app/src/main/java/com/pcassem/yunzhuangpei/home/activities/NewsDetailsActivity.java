package com.pcassem.yunzhuangpei.home.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.NewsDetailsPresenter;
import com.pcassem.yunzhuangpei.home.view.NewsDetailsView;
import com.pcassem.yunzhuangpei.utils.DateUtil;
import com.pcassem.yunzhuangpei.utils.HtmlFormatUtil;
import com.pcassem.yunzhuangpei.view.MJavascriptInterface;
import com.pcassem.yunzhuangpei.view.MyWebViewClient;
import com.pcassem.yunzhuangpei.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressLint("SetJavaScriptEnabled")
public class NewsDetailsActivity extends AppCompatActivity implements View.OnClickListener, NewsDetailsView {


    //评论条控件
    private TextView mCommentFrame;
    private EditText mCommentContent;
    private TextView mCommentSend;
    private LinearLayout mJumpCommentActivity;
    private LinearLayout mLike;
    private LinearLayout mCollection;

    private RelativeLayout mLayoutCommentMenu;
    private RelativeLayout mlayoutCommentSay;

    private ScrollView news_details_activity;
    private ImageView backIv;

    private NewsDetailsPresenter mNewsDetailsPresenter;
    private NewsDetailsEntity mNewsDetailsData;
    private TextView mTitle;
    private TextView mDate;
    private TextView mReadCount;
    private WebView mContent;
    private TextView mLikeCount;
    private TextView mCommentCount;
    private int newsID = 0;

    private String[] imageUrls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        initView();
        initTouchEvent();

        mContent.getSettings().setJavaScriptEnabled(true);
        mContent.getSettings().setAppCacheEnabled(true);
        mContent.getSettings().setDatabaseEnabled(true);
        mContent.getSettings().setDomStorageEnabled(true);

        mNewsDetailsPresenter = new NewsDetailsPresenter(this);
        mNewsDetailsPresenter.onCreate();
        newsID = getIntent().getIntExtra("newsID", -1);
        mNewsDetailsPresenter.getNewsDetailsList(newsID);

    }


    private void initView() {

        news_details_activity = (ScrollView) findViewById(R.id.news_details_activity);
        backIv = (ImageView) findViewById(R.id.back_iv);
        mCommentFrame = (TextView) findViewById(R.id.comment_frame);
        mCommentContent = (EditText) findViewById(R.id.comment_content);
        mCommentSend = (TextView) findViewById(R.id.comment_send);
        mJumpCommentActivity = (LinearLayout) findViewById(R.id.jump_comment);
        mLike = (LinearLayout) findViewById(R.id.like);
        mCollection = (LinearLayout) findViewById(R.id.collection);
        mLayoutCommentMenu = (RelativeLayout) findViewById(R.id.layout_comment_menu);
        mlayoutCommentSay = (RelativeLayout) findViewById(R.id.layout_comment_say);


        mTitle = (TextView) findViewById(R.id.title);
        mDate = (TextView) findViewById(R.id.date);
        mReadCount = (TextView) findViewById(R.id.read_count);
        mContent = (WebView) findViewById(R.id.content);
        mLikeCount = (TextView) findViewById(R.id.like_count);
        mCommentCount = (TextView) findViewById(R.id.comment_count);

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
                Toast.makeText(NewsDetailsActivity.this, "正在开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.comment_send:
                sendComment();
                break;
            case R.id.jump_comment:
                Toast.makeText(NewsDetailsActivity.this, "正在开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.like:
                Toast.makeText(NewsDetailsActivity.this, "正在开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.collection:
                Toast.makeText(NewsDetailsActivity.this, "正在开发", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(NewsDetailsActivity.this).clearDiskCache();//清理磁盘缓存需要在子线程中执行
            }
        }).start();
        Glide.get(this).clearMemory();//清理内存缓存可以在UI主线程中进行
        super.onDestroy();
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
            Toast.makeText(NewsDetailsActivity.this, "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(NewsDetailsActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSuccess(ResultListEntity<NewsDetailsEntity> newsListEntity) {
        mNewsDetailsData = newsListEntity.getResult().get(0);
        mTitle.setText(mNewsDetailsData.getTitle() + "");
        mDate.setText(DateUtil.getStandardDate(mNewsDetailsData.getDate()));
        mReadCount.setText("阅读" + mNewsDetailsData.getReadCount() + "次");
        mLikeCount.setText("赞" + mNewsDetailsData.getLikeCount());
        mCommentCount.setText("评论" + mNewsDetailsData.getCommentCount());

        mContent.loadDataWithBaseURL(null, HtmlFormatUtil.getNewContent(mNewsDetailsData.getContent()), "text/html", "utf-8", null);
        imageUrls = StringUtil.returnImageUrlsFromHtml(mNewsDetailsData.getContent());

        mContent.addJavascriptInterface(new MJavascriptInterface(this, imageUrls), "imagelistener");
        mContent.setWebViewClient(new MyWebViewClient());
        news_details_activity.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "网络出错", Toast.LENGTH_SHORT).show();
    }

}
