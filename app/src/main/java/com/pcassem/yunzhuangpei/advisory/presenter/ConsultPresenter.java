package com.pcassem.yunzhuangpei.advisory.presenter;

import android.util.Log;

import com.pcassem.yunzhuangpei.advisory.bean.BookBean;
import com.pcassem.yunzhuangpei.advisory.bean.CommonBean;
import com.pcassem.yunzhuangpei.advisory.bean.OnlineBean;
import com.pcassem.yunzhuangpei.advisory.model.ExportData;
import com.pcassem.yunzhuangpei.advisory.view.BookView;
import com.pcassem.yunzhuangpei.advisory.view.ConsultView;
import com.pcassem.yunzhuangpei.advisory.view.ExportView;
import com.pcassem.yunzhuangpei.advisory.view.OnlineView;
import com.pcassem.yunzhuangpei.entity.ConsultInitEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2018/1/24.
 */

public class ConsultPresenter implements Presenter {

    private static final String TAG = "ConsultPresenter";

    private ExportData data;
    private ConsultView consultView;
    private OnlineView onlineView;
    private CompositeSubscription mCompositeSubscription;

    private ResultListEntity<String> response;
    private ResultListEntity<ConsultInitEntity> initConsult;
    private ResultListEntity<String> commonStr;

    public ConsultPresenter(ConsultView consultView){
        this.consultView = consultView;
    }

    public ConsultPresenter(OnlineView onlineView){
        this.onlineView = onlineView;
    }

    public void uploadImage(String path){
        mCompositeSubscription.add(data.uploadImage(path)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                consultView.onUploadImageSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                consultView.onUploadImageError();
            }

            @Override
            public void onNext(ResultListEntity<String> responseBody) {
                response = responseBody;
            }
        }));
    }

    public void getInitConsult(int userID){
        mCompositeSubscription.add(data.getInitConsult(userID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<ConsultInitEntity>>() {
            @Override
            public void onCompleted() {
                consultView.onGetConsultInitSuccess(initConsult);
            }

            @Override
            public void onError(Throwable e) {
               consultView.onGetConsultInitError();
            }

            @Override
            public void onNext(ResultListEntity<ConsultInitEntity> consultInitEntityResultListEntity) {
                 initConsult = consultInitEntityResultListEntity;
            }
        }));
    }

    public void addCommonConsult(final CommonBean common){
        mCompositeSubscription.add(data.addCommonConsult(common)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                consultView.onAddCommonConsultSuccess(commonStr);
            }

            @Override
            public void onError(Throwable e) {
                consultView.onAddCommonConsultError();
            }

            @Override
            public void onNext(ResultListEntity<String> resultListEntity) {
                commonStr = resultListEntity;
            }
        }));
    }

    public void addQuickConsult(final CommonBean quick){
        mCompositeSubscription.add(data.addQuickConsult(quick)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                consultView.onAddCommonConsultSuccess(commonStr);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
               consultView.onAddCommonConsultError();
            }

            @Override
            public void onNext(ResultListEntity<String> resultListEntity) {
               commonStr =  resultListEntity;
            }
        }));
    }

    public void addBookConsult(final BookBean book){
        mCompositeSubscription.add(data.addBookConsult(book)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                consultView.onAddCommonConsultSuccess(commonStr);
            }

            @Override
            public void onError(Throwable e) {
               consultView.onAddCommonConsultError();
            }

            @Override
            public void onNext(ResultListEntity<String> resultListEntity) {
               commonStr = resultListEntity;
            }
        }));
    }

    public void addOnlineConsult(final OnlineBean online){
        mCompositeSubscription.add(data.addOnlineConsult(online)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                onlineView.onAddOnlineConsultSuccess(commonStr);
            }

            @Override
            public void onError(Throwable e) {
               onlineView.onAddOnlineConsultError();
            }

            @Override
            public void onNext(ResultListEntity<String> resultListEntity) {
              commonStr = resultListEntity;
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
