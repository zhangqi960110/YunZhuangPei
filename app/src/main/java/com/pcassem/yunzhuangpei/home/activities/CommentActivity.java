package com.pcassem.yunzhuangpei.home.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.CommentEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.adapter.CommentListAdapter;
import com.pcassem.yunzhuangpei.home.presenter.CommentPresenter;
import com.pcassem.yunzhuangpei.home.view.CommentView;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener, CommentView {

    private EditText mCommentContent;
    private TextView mCommentSend;

    private ListView mCommentList;
    private CommentListAdapter mCommentListAdapter;
    private List<CommentEntity> data;
    private CommentPresenter mCommentPresenter;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        initTouchEvent();

        data = new ArrayList<>();

        mCommentPresenter = new CommentPresenter(this);
        mCommentPresenter.onCreate();
        mCommentPresenter.getCommentList(Integer.parseInt(getIntent().getStringExtra("newsID")) + 1);
    }

    private void initView() {
        backIv = (ImageView) findViewById(R.id.back_iv);

        mCommentContent = (EditText) findViewById(R.id.comment_content);
        mCommentSend = (TextView) findViewById(R.id.comment_send);
        mCommentList = (ListView) findViewById(R.id.comment_list);

    }

    private void initTouchEvent() {
        mCommentSend.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.comment_send:
                sendComment();
                break;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<CommentEntity> commentListEntity) {
        data = commentListEntity.getResult();
        if (data == null) {
            Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mCommentListAdapter == null) {
            mCommentListAdapter = new CommentListAdapter(this, data);
            mCommentList.setAdapter(mCommentListAdapter);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(CommentActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
    }

    public void sendComment() {
        Toast.makeText(this, "暂未开发", Toast.LENGTH_SHORT).show();
//        if(mCommentContent.getText().toString().equals("")){
//            Toast.makeText(CommentActivity.this, "评论不能为空！", Toast.LENGTH_SHORT).show();
//        }else{
//            CommentEntity comment = new CommentEntity();
//            comment.setUserName("评论者"+(data.size()+1));
//            comment.setContent(mCommentContent.getText().toString());
//            mCommentListAdapter.addComment(comment);
//            // 发送完，清空输入框
//            mCommentContent.setText("");
//
//            Toast.makeText(CommentActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
//        }
    }

}
