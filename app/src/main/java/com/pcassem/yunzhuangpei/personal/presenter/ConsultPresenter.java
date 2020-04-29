package com.pcassem.yunzhuangpei.personal.presenter;

import com.pcassem.yunzhuangpei.entity.ConsultBookEntity;
import com.pcassem.yunzhuangpei.entity.ConsultEntity;
import com.pcassem.yunzhuangpei.entity.ConsultOnlineEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.personal.model.PersonalData;
import com.pcassem.yunzhuangpei.personal.view.ConsultBookView;
import com.pcassem.yunzhuangpei.personal.view.ConsultOnlineView;
import com.pcassem.yunzhuangpei.personal.view.ConsultView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2018/1/23.
 */

public class ConsultPresenter implements Presenter {

    private PersonalData data;
    private ConsultView consult;
    private ConsultOnlineView consultOnline;
    private ConsultBookView consultBook;
    private CompositeSubscription mCompositeSubscription;

    private ResultListEntity<ConsultEntity> consultList;
    private ResultListEntity<ConsultBookEntity> consultBookList;
    private ResultListEntity<ConsultOnlineEntity> consultOnlineList;

    public ConsultPresenter(ConsultView consult){
        this.consult = consult;
    }

    public ConsultPresenter(ConsultBookView consultBook){
        this.consultBook = consultBook;
    }

    public ConsultPresenter(ConsultOnlineView consultOnline){
        this.consultOnline = consultOnline;
    }

    public void commonConsult(final int consultID){
        mCompositeSubscription.add(data.commonConsult(consultID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<ConsultEntity>>() {
            @Override
            public void onCompleted() {
                consult.onSuccess(consultList);
            }

            @Override
            public void onError(Throwable e) {
               consult.onError();
            }

            @Override
            public void onNext(ResultListEntity<ConsultEntity> consultEntityResultListEntity) {
               consultList = consultEntityResultListEntity;
            }
        }));
    }

    public void quickConsult(final int consultID){
        mCompositeSubscription.add(data.quickConsult(consultID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<ConsultEntity>>() {
            @Override
            public void onCompleted() {
                consult.onSuccess(consultList);
            }

            @Override
            public void onError(Throwable e) {
               consult.onError();
            }

            @Override
            public void onNext(ResultListEntity<ConsultEntity> consultEntityResultListEntity) {
               consultList = consultEntityResultListEntity;
            }
        }));
    }

    public void bookConsult(final int consultID){
        mCompositeSubscription.add(data.bookConsult(consultID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<ConsultBookEntity>>() {
            @Override
            public void onCompleted() {
                consultBook.onSuccess(consultBookList);
            }

            @Override
            public void onError(Throwable e) {
               consultBook.onError();
            }

            @Override
            public void onNext(ResultListEntity<ConsultBookEntity> consultBookEntityResultListEntity) {
              consultBookList = consultBookEntityResultListEntity;
            }
        }));
    }

    public void onlineConsult(int consultID){
        mCompositeSubscription.add(data.onlineConsult(consultID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<ConsultOnlineEntity>>() {
            @Override
            public void onCompleted() {
                consultOnline.onSuccess(consultOnlineList);
            }

            @Override
            public void onError(Throwable e) {
               consultOnline.onError();
            }

            @Override
            public void onNext(ResultListEntity<ConsultOnlineEntity> consultOnlineEntityResultListEntity) {
              consultOnlineList = consultOnlineEntityResultListEntity;
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
