package com.pcassem.yunzhuangpei.personal.presenter;

import com.pcassem.yunzhuangpei.entity.MyAdvisoryEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.personal.model.PersonalData;
import com.pcassem.yunzhuangpei.personal.view.AdvisoryView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class AdvisoryPresenter implements Presenter {

    private PersonalData data;
    private AdvisoryView advisoryView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<MyAdvisoryEntity> userInfoList;

    public AdvisoryPresenter(AdvisoryView advisoryView) {
        this.advisoryView = advisoryView;
    }

    public void getMyAdvisoryList(int userID) {
        mCompositeSubscription.add(data.getMyAdvisoryList(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<MyAdvisoryEntity>>() {
                    @Override
                    public void onCompleted() {
                        advisoryView.onSuccess(userInfoList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        advisoryView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<MyAdvisoryEntity> exportEntityResultListEntity) {
                        userInfoList = exportEntityResultListEntity;
                    }
                }));
    }



    @Override
    public void onCreate() {
        data = new PersonalData();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
