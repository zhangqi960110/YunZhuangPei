package com.pcassem.yunzhuangpei.http;

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

import javax.xml.transform.Result;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface PersonalAPI {

    //登录
    @Headers({"Content-type:application/json", "Content-Length:60"})
    @POST("/app/user/login")
    Observable<ResultListEntity<UserEntity>> login(@Body LoginBean user);

    //注册
    @Headers({"Content-type:application/json", "Content-Length:60"})
    @POST("/app/user/register")
    Observable<ResultListEntity<String>> userRegister(@Body RegisterBean user);


    //我的项目
    @GET("/app/user/project/list")
    Observable<ResultListEntity<MyProjectEntity>> getMyProjectList(@Query("userID") int userID);

    //新增项目
    @Headers({"Content-type:application/json"})
    @POST("/app/user/project/add")
    Observable<ResultListEntity<String>> addProject(@Body UserProjectBean project);

    //个人中心设置默认项目
    @Headers({"Content-type:application/json"})
    @POST("/app/user/project/setDefault")
    Observable<ResultListEntity<String>> setDefaultProject(@Body ProjectIDBean projectID);

    //个人中心删除项目
    @Headers({"Content-type:application/json"})
    @POST("/app/user/project/delete")
    Observable<ResultListEntity<String>> deleteProject(@Body ProjectIDBean projectID);

    //个人中心修改项目信息
    @Headers({"Content-type:application/json"})
    @POST("/app/user/project/edit")
    Observable<ResultListEntity<String>> editProject(@Body ProjectBean project);

    //个人中心我的咨询列表
    @GET("/app/user/consult/list")
    Observable<ResultListEntity<MyAdvisoryEntity>> myAdvisoryList(@Query("userID") int userID);

    //个人中心图文咨询详情
    @GET("/app/user/consult/common/info")
    Observable<ResultListEntity<ConsultEntity>> commonConsult(@Query("consultID") int consultID);

    //个人中心极速咨询详情
    @GET("/app/user/consult/quick/info")
    Observable<ResultListEntity<ConsultEntity>> quickConsult(@Query("consultID") int consultID);

    //个人中心实时咨询详情
    @GET("/app/user/consult/online/info")
    Observable<ResultListEntity<ConsultOnlineEntity>> onlineConsult(@Query("consultID") int consultID);

    //个人中心现场支持详情
    @GET("/app/user/consult/book/info")
    Observable<ResultListEntity<ConsultBookEntity>>  bookConsult(@Query("consultID") int consultID);

}
