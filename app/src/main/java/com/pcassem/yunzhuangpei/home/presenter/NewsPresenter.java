package com.pcassem.yunzhuangpei.home.presenter;

import android.util.Log;

import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.home.model.NewsData;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class NewsPresenter implements Presenter {

    private NewsData data;
    private NewsView newsView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<NewsEntity> latestNewsListEntity;
    private ResultListEntity<NewsEntity> newsListEntity;
    private ResultListEntity<NewsEntity> searchNewsListEntity;

    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
    }

    public void getLatestNewsList() {
        mCompositeSubscription.add(data.getLatestNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<NewsEntity>>() {
                    @Override
                    public void onCompleted() {
                        newsView.onSuccess(latestNewsListEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        newsView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<NewsEntity> newsEntityNewsListEntity) {
                        latestNewsListEntity = newsEntityNewsListEntity;
                    }
                }));
    }

    public void getNewsList(){
        mCompositeSubscription.add(data.getNewsList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<NewsEntity>>() {
            @Override
            public void onCompleted() {
                newsView.onSuccess(newsListEntity);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResultListEntity<NewsEntity> newListEntity) {
                newsListEntity = newListEntity;
            }
        }));
    }

    public void getSearchNewsList(String keyword){
        mCompositeSubscription.add(data.getSearchNewsList(keyword)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<NewsEntity>>() {
            @Override
            public void onCompleted() {
                newsView.onSuccess(searchNewsListEntity);
            }

            @Override
            public void onError(Throwable e) {
               newsView.onError();
            }

            @Override
            public void onNext(ResultListEntity<NewsEntity> newsListEntity) {
                  searchNewsListEntity = newsListEntity;
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
