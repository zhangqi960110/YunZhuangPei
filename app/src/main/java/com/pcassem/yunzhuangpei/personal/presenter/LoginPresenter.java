package com.pcassem.yunzhuangpei.personal.presenter;

import com.pcassem.yunzhuangpei.personal.bean.LoginBean;
import com.pcassem.yunzhuangpei.personal.bean.RegisterBean;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.UserEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.personal.model.PersonalData;
import com.pcassem.yunzhuangpei.personal.view.LoginView;
import com.pcassem.yunzhuangpei.personal.view.RegisterView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class LoginPresenter implements Presenter {
    private PersonalData data;
    private LoginView loginView;
    private RegisterView registerView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<UserEntity> userInfoList;
    private ResultListEntity<String> registerStatus;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public LoginPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void login(LoginBean user) {
        mCompositeSubscription.add(data.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<UserEntity>>() {
                    @Override
                    public void onCompleted() {
                        loginView.onSuccess(userInfoList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<UserEntity> exportEntityResultListEntity) {
                        userInfoList = exportEntityResultListEntity;
                    }
                }));
    }

    public void userRegister(RegisterBean user) {
        mCompositeSubscription.add(data.userRegister(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<String>>() {
                    @Override
                    public void onCompleted() {
                        registerView.onSuccess(registerStatus);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<String> exportEntityResultListEntity) {
                        registerStatus = exportEntityResultListEntity;
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
