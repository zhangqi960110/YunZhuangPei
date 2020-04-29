package com.pcassem.yunzhuangpei.home.presenter;

import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.model.NewsData;
import com.pcassem.yunzhuangpei.training.view.KnowledgeDetailsNonDocView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class SearchKnowledgeDetailsPresenter implements Presenter {

    private NewsData data;
    private KnowledgeDetailsNonDocView newsDetailsView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<KnowledgeDetailsNonDocEntity> newsDetailsListEntity;

    public SearchKnowledgeDetailsPresenter(KnowledgeDetailsNonDocView newsDetailsView) {
        this.newsDetailsView = newsDetailsView;
    }

    public void getSearchKnowledgeDetails(int newsID){
        mCompositeSubscription.add(data.getSearchKnowledgeDetails(newsID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<KnowledgeDetailsNonDocEntity>>() {
                    @Override
                    public void onCompleted() {
                        newsDetailsView.onSuccess(newsDetailsListEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultListEntity<KnowledgeDetailsNonDocEntity> newsDetailsEntityNewsListEntity) {
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
