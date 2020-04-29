package com.pcassem.yunzhuangpei.advisory.presenter;

import com.pcassem.yunzhuangpei.advisory.model.ExportData;
import com.pcassem.yunzhuangpei.advisory.view.ExportView;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class ExportPresenter implements Presenter {

    private ExportData data;
    private ExportView exportView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<ExportEntity> commendExportList;
    private ResultListEntity<ExportEntity> allExportList;

    public ExportPresenter(ExportView exportView) {
        this.exportView = exportView;
    }

    public void getCommendExportList() {
        mCompositeSubscription.add(data.getCommendExportList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<ExportEntity>>() {
                    @Override
                    public void onCompleted() {
                        exportView.onSuccess(commendExportList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        exportView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<ExportEntity> exportEntityResultListEntity) {
                        commendExportList = exportEntityResultListEntity;
                    }
                }));
    }

    public void getAllExportList(){
        mCompositeSubscription.add(data.getAllExportList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<ExportEntity>>() {
            @Override
            public void onCompleted() {
                exportView.onSuccess(allExportList);
            }

            @Override
            public void onError(Throwable e) {
               exportView.onError();
            }

            @Override
            public void onNext(ResultListEntity<ExportEntity> exportEntityResultListEntity) {
               allExportList = exportEntityResultListEntity;
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
