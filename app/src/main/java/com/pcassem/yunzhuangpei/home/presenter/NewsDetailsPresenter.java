package com.pcassem.yunzhuangpei.home.presenter;

import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.model.NewsData;
import com.pcassem.yunzhuangpei.home.view.NewsDetailsView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2017/12/10.
 */

public class NewsDetailsPresenter implements Presenter {

    private NewsData data;
    private NewsDetailsView newsDetailsView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<NewsDetailsEntity> newsDetailsListEntity;

    public NewsDetailsPresenter(NewsDetailsView newsDetailsView) {
        this.newsDetailsView = newsDetailsView;
    }

    public void getNewsDetailsList(int newsID){
        mCompositeSubscription.add(data.getNewsDetailsList(newsID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<NewsDetailsEntity>>() {
                    @Override
                    public void onCompleted() {
                        newsDetailsView.onSuccess(newsDetailsListEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultListEntity<NewsDetailsEntity> newsDetailsEntityNewsListEntity) {
                        newsDetailsListEntity = newsDetailsEntityNewsListEntity;
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
