package com.pcassem.yunzhuangpei.home.presenter;

import com.pcassem.yunzhuangpei.entity.CommentEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.model.NewsData;
import com.pcassem.yunzhuangpei.home.view.CommentView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class CommentPresenter implements Presenter {

    private NewsData data;
    private CommentView commentView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<CommentEntity> commentListEntity;

    public CommentPresenter(CommentView commentView) {
        this.commentView = commentView;
    }

    public void getCommentList(int newsID){
        mCompositeSubscription.add(data.getCommentList(newsID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<CommentEntity>>() {
            @Override
            public void onCompleted() {
                commentView.onSuccess(commentListEntity);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResultListEntity<CommentEntity> commentEntityNewsListEntity) {
                 commentListEntity = commentEntityNewsListEntity;
            }
        }));
    }


    @Override
    public void onCreate() {
        data = new NewsData();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
