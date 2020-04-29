package com.pcassem.yunzhuangpei.training.presenter;

import com.pcassem.yunzhuangpei.entity.CourseEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.model.NewsData;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.training.model.TrainingData;
import com.pcassem.yunzhuangpei.training.view.CourseView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class CoursePresenter implements Presenter {

    private TrainingData data;
    private CourseView courseView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<CourseEntity> courseList;

    public CoursePresenter(CourseView courseView) {
        this.courseView = courseView;
    }


    public void getCourseList(){
        mCompositeSubscription.add(data.getCourseList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<CourseEntity>>() {
                    @Override
                    public void onCompleted() {
                        courseView.onSuccess(courseList);
                    }

                    @Override
                    public void onError(Throwable e) {
                         courseView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<CourseEntity> courseListEntity) {
                        courseList = courseListEntity;
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
