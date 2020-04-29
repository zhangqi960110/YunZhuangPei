package com.pcassem.yunzhuangpei.training.model;

import com.pcassem.yunzhuangpei.entity.CourseDetailsEntity;
import com.pcassem.yunzhuangpei.entity.CourseEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.http.RetrofitHelper;
import com.pcassem.yunzhuangpei.http.TrainingAPI;

import retrofit2.http.Query;
import rx.Observable;


public class TrainingData {

    private TrainingAPI mTrainingAPI;

    public TrainingData() {
        this.mTrainingAPI = RetrofitHelper.getInstance().getTrainingAPI();
    }

    //获取知识列表
    public Observable<ResultListEntity<KnowledgeEntity>> getKnowledgeList(String repository, String category, String firstLevel, String secondLevel, String thirdLevel) {
        return mTrainingAPI.getKnowledgeList(repository, category, firstLevel, secondLevel, thirdLevel);
    }

    //获取知识详情（非文件）
    public Observable<ResultListEntity<KnowledgeDetailsNonDocEntity>> getKnowledgeDetailsList(int knowledgeID) {
        return mTrainingAPI.getKnowledgeDetailsList(knowledgeID);
    }

    //获取知识详情（文件）
    public Observable<ResultListEntity<KnowledgeDetailsDocEntity>> getKnowledgeDocDetailsList(int knowledgeID) {
        return mTrainingAPI.getKnowledgeDocDetailsList(knowledgeID);
    }

    //获取培训课程列表
    public Observable<ResultListEntity<CourseEntity>> getCourseList() {
        return mTrainingAPI.getCourseList();
    }

    //获取培训课程详情
    public Observable<ResultListEntity<CourseDetailsEntity>> getCourseDetailsList(int courseID) {
        return mTrainingAPI.getCourseDetailsList(courseID);
    }

    //获取最新项目案例
    public Observable<ResultListEntity<NewsEntity>> getLatestProjectCasesList() {
        return mTrainingAPI.getLatestProjectCasesList();
    }

    //获取全部项目案例
    public Observable<ResultListEntity<NewsEntity>> getAllProjectCasesList(String category) {
        return mTrainingAPI.getAllProjectCasesList(category);
    }

    //获取搜索项目案例
    public Observable<ResultListEntity<NewsEntity>> getSearchProjectCasesList(String category, String keyword) {
        return mTrainingAPI.getSearchProjectCasesList(category, keyword);
    }

    //获取项目案例详情
    public Observable<ResultListEntity<NewsDetailsEntity>> getProjectCaseDetailsList(int projectID) {
        return mTrainingAPI.getProjectCaseDetailsList(projectID);
    }

}
