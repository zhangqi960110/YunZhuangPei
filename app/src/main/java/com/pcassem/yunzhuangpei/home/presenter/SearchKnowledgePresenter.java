package com.pcassem.yunzhuangpei.home.presenter;

import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.model.NewsData;
import com.pcassem.yunzhuangpei.training.view.KnowledgeView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class SearchKnowledgePresenter implements Presenter {

    private NewsData data;
    private KnowledgeView trainingView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<KnowledgeEntity> newsListEntity;

    public SearchKnowledgePresenter(KnowledgeView trainingView) {
        this.trainingView = trainingView;
    }

    public void getSearchKnowledgeList(String repository, String keyword){
        mCompositeSubscription.add(data.getSearchKnowledgeList(repository, keyword)
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
