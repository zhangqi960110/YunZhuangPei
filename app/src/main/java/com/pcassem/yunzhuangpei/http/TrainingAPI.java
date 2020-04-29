package com.pcassem.yunzhuangpei.http;

import com.pcassem.yunzhuangpei.entity.CourseDetailsEntity;
import com.pcassem.yunzhuangpei.entity.CourseEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface TrainingAPI {


    //知识列表
    @GET("/app/repository/list")
    Observable<ResultListEntity<KnowledgeEntity>> getKnowledgeList(@Query("repository") String repository, @Query("category") String category, @Query("firstLevel") String firstLevel, @Query("secondLevel") String secondLevel, @Query("thirdLevel") String thirdLevel);

    //知识详情（非文件）
    @GET("/app/repository/info")
    Observable<ResultListEntity<KnowledgeDetailsNonDocEntity>> getKnowledgeDetailsList(@Query("knowledgeID") int knowledgeID);

    //知识详情（文件）
    @GET("/app/repository/file")
    Observable<ResultListEntity<KnowledgeDetailsDocEntity>> getKnowledgeDocDetailsList(@Query("knowledgeID") int knowledgeID);

    //培训课程列表
    @GET("/app/training/list")
    Observable<ResultListEntity<CourseEntity>> getCourseList();

    //培训课程详情
    @GET("/app/training/info")
    Observable<ResultListEntity<CourseDetailsEntity>> getCourseDetailsList(@Query("courseID") int courseID);

    //最新项目案例
    @GET("/app/project/latestList")
    Observable<ResultListEntity<NewsEntity>> getLatestProjectCasesList();

    //全部项目案例
    @GET("/app/project/list")
    Observable<ResultListEntity<NewsEntity>> getAllProjectCasesList(@Query("category") String category);

    //搜索项目案例
    @GET("/app/project/searchList")
    Observable<ResultListEntity<NewsEntity>> getSearchProjectCasesList(@Query("category") String category, @Query("keyword") String keyword);

    //项目案例详情
    @GET("/app/project/info")
    Observable<ResultListEntity<NewsDetailsEntity>> getProjectCaseDetailsList(@Query("projectID") int projectID);
}
