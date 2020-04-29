package com.pcassem.yunzhuangpei.home.presenter;

import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.SearchListEntity;
import com.pcassem.yunzhuangpei.home.model.NewsData;
import com.pcassem.yunzhuangpei.home.view.HomeSearchView;
import com.pcassem.yunzhuangpei.training.view.KnowledgeView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2017/12/25.
 */

public class HomeSearchPresenter implements Presenter {
    private NewsData data;
    private HomeSearchView homeSearchView;
    private CompositeSubscription mCompositeSubscription;
    private SearchListEntity searchList;

    public HomeSearchPresenter(HomeSearchView homeSearchView) {
        this.homeSearchView = homeSearchView;
    }

    public void getHomeSearchList(String repository, String keyword){
        mCompositeSubscription.add(data.getHomeSearchList(repository, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchListEntity>() {
                    @Override
                    public void onCompleted() {
                        homeSearchView.onSuccess(searchList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeSearchView.onError();
                    }

                    @Override
                    public void onNext(SearchListEntity knowledgeEntityNewsListEntity) {
                        searchList = knowledgeEntityNewsListEntity;
                    }
                }));
    }

    @Override
    public void onCreate() {
        data = new NewsData();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
