package com.pcassem.yunzhuangpei.training.presenter;

import com.pcassem.yunzhuangpei.entity.CourseDetailsEntity;
import com.pcassem.yunzhuangpei.entity.CourseEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.training.model.TrainingData;
import com.pcassem.yunzhuangpei.training.view.CourseDetailsView;
import com.pcassem.yunzhuangpei.training.view.CourseView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CourseDetailsPresenter implements Presenter {

    private TrainingData data;
    private CourseDetailsView courseDetailsView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<CourseDetailsEntity> courseDetailsList;

    public CourseDetailsPresenter(CourseDetailsView courseDetailsView) {
        this.courseDetailsView = courseDetailsView;
    }


    public void getCourseDetailsList(int courseID){
        mCompositeSubscription.add(data.getCourseDetailsList(courseID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<CourseDetailsEntity>>() {
                    @Override
                    public void onCompleted() {
                        courseDetailsView.onSuccess(courseDetailsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        courseDetailsView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<CourseDetailsEntity> courseListEntity) {
                        courseDetailsList = courseListEntity;
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
