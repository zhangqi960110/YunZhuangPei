package com.pcassem.yunzhuangpei.training.presenter;


import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.training.model.TrainingData;
import com.pcassem.yunzhuangpei.training.view.KnowledgeView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class KnowledgePresenter implements Presenter {

    private TrainingData data;
    private KnowledgeView trainingView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<KnowledgeEntity> newsListEntity;

    public KnowledgePresenter(KnowledgeView trainingView) {
        this.trainingView = trainingView;
    }


    public void getKnowledgeList(String repository, String category, String firstLevel, String secondLevel, String thirdLevel){
        mCompositeSubscription.add(data.getKnowledgeList(repository, category, firstLevel, secondLevel, thirdLevel)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<KnowledgeEntity>>() {
            @Override
            public void onCompleted() {
                trainingView.onSuccess(newsListEntity);
            }

            @Override
            public void onError(Throwable e) {
               trainingView.onError();
            }

            @Override
            public void onNext(ResultListEntity<KnowledgeEntity> knowledgeEntityNewsListEntity) {
                 newsListEntity = knowledgeEntityNewsListEntity;
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
