package com.pcassem.yunzhuangpei.training.presenter;

import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.training.model.TrainingData;
import com.pcassem.yunzhuangpei.training.view.KnowledgeDetailsNonDocView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class KnowledgeDetailsPresenter implements Presenter {
    private TrainingData data;
    private KnowledgeDetailsNonDocView newsDetailsView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<KnowledgeDetailsNonDocEntity> newsDetailsListEntity;

    public KnowledgeDetailsPresenter(KnowledgeDetailsNonDocView newsDetailsView) {
        this.newsDetailsView = newsDetailsView;
    }

    public void getNewsDetailsList(int newsID){
        mCompositeSubscription.add(data.getKnowledgeDetailsList(newsID)
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
        data = new TrainingData();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
