package com.pcassem.yunzhuangpei.advisory.presenter;

import com.pcassem.yunzhuangpei.advisory.model.ExportData;
import com.pcassem.yunzhuangpei.advisory.view.ExportDetailsView;
import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class ExportDetailsPresenter implements Presenter {
    private ExportData data;
    private ExportDetailsView exportView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<ExportDetailsEntity> commendExportList;

    public ExportDetailsPresenter(ExportDetailsView exportView) {
        this.exportView = exportView;
    }

    public void getExportDetails(int specialistID) {
        mCompositeSubscription.add(data.getExportDetails(specialistID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<ExportDetailsEntity>>() {
                    @Override
                    public void onCompleted() {
                        exportView.onSuccess(commendExportList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        exportView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<ExportDetailsEntity> exportEntityResultListEntity) {
                        commendExportList = exportEntityResultListEntity;
                    }
                }));
    }

    @Override
    public void onCreate() {
        data = new ExportData();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
