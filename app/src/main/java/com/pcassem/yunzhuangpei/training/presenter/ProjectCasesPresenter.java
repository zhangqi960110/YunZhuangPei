package com.pcassem.yunzhuangpei.training.presenter;

import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.model.NewsData;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.training.model.TrainingData;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class ProjectCasesPresenter implements Presenter {

    private TrainingData data;
    private NewsView newsView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<NewsEntity> latestProjectCasesList;
    private ResultListEntity<NewsEntity> allProjectCasesList;
    private ResultListEntity<NewsEntity> searchProjectCasesList;

    public ProjectCasesPresenter(NewsView newsView) {
        this.newsView = newsView;
    }

    public void getLatestProjectCasesList() {
        mCompositeSubscription.add(data.getLatestProjectCasesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<NewsEntity>>() {
                    @Override
                    public void onCompleted() {
                        newsView.onSuccess(latestProjectCasesList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        newsView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<NewsEntity> latestProjectCasesListEntity) {
                        latestProjectCasesList = latestProjectCasesListEntity;
                    }
                }));
    }

    public void getAllProjectCasesList(String category) {
        mCompositeSubscription.add(data.getAllProjectCasesList(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<NewsEntity>>() {
                    @Override
                    public void onCompleted() {
                        newsView.onSuccess(allProjectCasesList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        newsView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<NewsEntity> newsEntityResultListEntity) {
                        allProjectCasesList = newsEntityResultListEntity;
                    }
                }));
    }

    public void getSearchProjectCasesList(String category, String keyword) {
        mCompositeSubscription.add(data.getSearchProjectCasesList(category, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<NewsEntity>>() {
                    @Override
                    public void onCompleted() {
                        newsView.onSuccess(searchProjectCasesList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        newsView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<NewsEntity> newsEntityResultListEntity) {
                        searchProjectCasesList = newsEntityResultListEntity;
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
