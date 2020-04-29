package com.pcassem.yunzhuangpei.personal.presenter;

import com.pcassem.yunzhuangpei.entity.MyProjectEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.Presenter;
import com.pcassem.yunzhuangpei.personal.bean.UserProjectBean;
import com.pcassem.yunzhuangpei.personal.bean.ProjectIDBean;
import com.pcassem.yunzhuangpei.personal.bean.ProjectBean;
import com.pcassem.yunzhuangpei.personal.model.PersonalData;
import com.pcassem.yunzhuangpei.personal.view.AddProjectView;
import com.pcassem.yunzhuangpei.personal.view.ProjectListView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2018/1/20.
 */

public class ProjectPresenter implements Presenter {

    private PersonalData data;
    private ProjectListView projectListView;
    private AddProjectView addProjectView;
    private CompositeSubscription mCompositeSubscription;
    private ResultListEntity<MyProjectEntity> myProjectList;
    private ResultListEntity<String> myProjectStr;

    public ProjectPresenter(ProjectListView myPersonalView) {
        this.projectListView = myPersonalView;
    }

    public ProjectPresenter(AddProjectView addProjectView) {
        this.addProjectView = addProjectView;
    }

    public void getMyProjectList(int userID) {
        mCompositeSubscription.add(data.getMyProjectList(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<MyProjectEntity>>() {
                    @Override
                    public void onCompleted() {
                        projectListView.onGetProjectSuccess(myProjectList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        projectListView.onGetProjectError();
                    }

                    @Override
                    public void onNext(ResultListEntity<MyProjectEntity> myProjectEntityResultListEntity) {
                        myProjectList = myProjectEntityResultListEntity;
                    }
                }));
    }

    public void addProject(UserProjectBean project) {
        mCompositeSubscription.add(data.addProject(project)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultListEntity<String>>() {
                    @Override
                    public void onCompleted() {
                        addProjectView.onSuccess(myProjectStr);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addProjectView.onError();
                    }

                    @Override
                    public void onNext(ResultListEntity<String> exportEntityResultListEntity) {
                        myProjectStr = exportEntityResultListEntity;
                    }
                }));
    }


    public void setDefaultProject(ProjectIDBean projectID){
        mCompositeSubscription.add(data.setDefaultProject(projectID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                projectListView.onAddProjectSuccess(myProjectStr);
            }

            @Override
            public void onError(Throwable e) {
                projectListView.onAddProjectError();
            }

            @Override
            public void onNext(ResultListEntity<String> exportEntityResultListEntity) {
                myProjectStr = exportEntityResultListEntity;
            }
        }));
    }

    public void deleteProject(ProjectIDBean projectID){
        mCompositeSubscription.add(data.deleteProject(projectID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                projectListView.onDeleteProjectSuccess(myProjectStr);
            }

            @Override
            public void onError(Throwable e) {
                projectListView.onDeleteProjectError();
            }

            @Override
            public void onNext(ResultListEntity<String> exportEntityResultListEntity) {
                myProjectStr = exportEntityResultListEntity;
            }
        }));
    }

    public void editProject(ProjectBean project){
        mCompositeSubscription.add(data.editProject(project)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResultListEntity<String>>() {
            @Override
            public void onCompleted() {
                projectListView.onEditProjectSuccess(myProjectStr);
            }

            @Override
            public void onError(Throwable e) {
                projectListView.onEditProjectError();
            }

            @Override
            public void onNext(ResultListEntity<String> exportEntityResultListEntity) {
                myProjectStr = exportEntityResultListEntity;
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
