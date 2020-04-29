package com.pcassem.yunzhuangpei.personal.model;

import com.pcassem.yunzhuangpei.entity.ConsultBookEntity;
import com.pcassem.yunzhuangpei.entity.ConsultEntity;
import com.pcassem.yunzhuangpei.entity.ConsultOnlineEntity;
import com.pcassem.yunzhuangpei.entity.MyAdvisoryEntity;
import com.pcassem.yunzhuangpei.personal.bean.LoginBean;
import com.pcassem.yunzhuangpei.entity.MyProjectEntity;
import com.pcassem.yunzhuangpei.personal.bean.UserProjectBean;
import com.pcassem.yunzhuangpei.personal.bean.ProjectIDBean;
import com.pcassem.yunzhuangpei.personal.bean.ProjectBean;
import com.pcassem.yunzhuangpei.personal.bean.RegisterBean;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.UserEntity;
import com.pcassem.yunzhuangpei.http.PersonalAPI;
import com.pcassem.yunzhuangpei.http.RetrofitHelper;

import rx.Observable;

public class PersonalData {
    private PersonalAPI mPersonalAPI;

    public PersonalData() {
        this.mPersonalAPI = RetrofitHelper.getInstance().getPersonalAPI();
    }

    //登录
    public Observable<ResultListEntity<UserEntity>> login(LoginBean user) {
        return mPersonalAPI.login(user);
    }

    //注册
    public Observable<ResultListEntity<String>> userRegister(RegisterBean user) {
        return mPersonalAPI.userRegister(user);
    }

    //我的项目
    public Observable<ResultListEntity<MyProjectEntity>> getMyProjectList(int userID){
        return mPersonalAPI.getMyProjectList(userID);
    }

    //新增项目
    public Observable<ResultListEntity<String>> addProject(UserProjectBean project){
        return mPersonalAPI.addProject(project);
    }

    //个人中心设置默认项目
    public Observable<ResultListEntity<String>> setDefaultProject(ProjectIDBean projectID){
        return mPersonalAPI.setDefaultProject(projectID);
    }

    //个人中心删除项目
    public Observable<ResultListEntity<String>> deleteProject(ProjectIDBean projectID){
        return mPersonalAPI.deleteProject(projectID);
    }

    //个人中心修改项目
    public Observable<ResultListEntity<String>> editProject(ProjectBean project){
        return mPersonalAPI.editProject(project);
    }

    //个人中心我的咨询列表
    public Observable<ResultListEntity<MyAdvisoryEntity>> getMyAdvisoryList(int userID){
        return mPersonalAPI.myAdvisoryList(userID);
    }

    //个人中心我的咨询列表
    public Observable<ResultListEntity<ConsultEntity>> commonConsult(int consultID){
        return mPersonalAPI.commonConsult(consultID);
    }

    //个人中心极速咨询详情
    public Observable<ResultListEntity<ConsultEntity>> quickConsult(int consultID){
        return mPersonalAPI.quickConsult(consultID);
    }

    //个人中心实时咨询详情
    public Observable<ResultListEntity<ConsultOnlineEntity>> onlineConsult(int consultID){
        return mPersonalAPI.onlineConsult(consultID);
    }

    //个人中心现场支持详情
    public Observable<ResultListEntity<ConsultBookEntity>> bookConsult(int consultID){
        return mPersonalAPI.bookConsult(consultID);
    }

}
