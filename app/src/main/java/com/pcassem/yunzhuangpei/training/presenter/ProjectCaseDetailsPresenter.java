package com.pcassem.yunzhuangpei.training.presenter;

import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.home.view.NewsDetailsView;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.training.model.TrainingData;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2017/12/16.
 */

public class ProjectCaseDetailsPresenter implements Presenter {

    private TrainingData data;
    private NewsDetailsView projectcaseDetailsView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<NewsDetailsEntity> projectCaseDetailsList;

    public ProjectCaseDetailsPresenter(NewsDetailsView projectcaseDetails) {
        this.projectcaseDetailsView = projectcaseDetails;
    }

    public void getProjectCaseDetailsList(int knowledgeID) {
        mCompositeSubscription.add(data.getProjectCaseDetailsList(knowledgeID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<NewsDetailsEntity>>() {
                    @Override
                    public void onCompleted() {
                        projectcaseDetailsView.onSuccess(projectCaseDetailsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        projectcaseDetailsView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<NewsDetailsEntity> latestProjectCasesListEntity) {
                        projectCaseDetailsList = latestProjectCasesListEntity;
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
