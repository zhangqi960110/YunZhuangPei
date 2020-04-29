package com.pcassem.yunzhuangpei.home.model;

import com.pcassem.yunzhuangpei.entity.CommentEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.SearchListEntity;
import com.pcassem.yunzhuangpei.http.HomeAPI;
import com.pcassem.yunzhuangpei.http.RetrofitHelper;

import rx.Observable;

/**
 * Created by zhangqi on 2017/12/9.
 */

public class NewsData {

    private HomeAPI mHomeAPI;

    public NewsData() {
        this.mHomeAPI = RetrofitHelper.getInstance().getHomeAPI();
    }

    public Observable<ResultListEntity<NewsEntity>> getLatestNewsList() {
        return mHomeAPI.getLatestNewsList();
    }

    public Observable<ResultListEntity<NewsEntity>> getNewsList(){
        return mHomeAPI.getNewsList();
    }

    public Observable<ResultListEntity<NewsEntity>> getSearchNewsList(String keyword){
        return mHomeAPI.getSearchNewsList(keyword);
    }

    public Observable<ResultListEntity<NewsDetailsEntity>> getNewsDetailsList(int newsID){
        return mHomeAPI.getNewsDetailsList(newsID);
    }

    public Observable<ResultListEntity<CommentEntity>> getCommentList(int newsID){
        return mHomeAPI.getCommentList(newsID);
    }

    //获取搜索知识列表
    public Observable<ResultListEntity<KnowledgeEntity>> getSearchKnowledgeList(String category, String keyword){
        return mHomeAPI.getSearchKnowledgeList(category,keyword);
    }

    //获取知识详情
    public Observable<ResultListEntity<KnowledgeDetailsNonDocEntity>> getSearchKnowledgeDetails(int knowledgeID){
        return mHomeAPI.getSearchKnowledgeDetails(knowledgeID);
    }

    //最新获取搜索列表详情
    public Observable<SearchListEntity> getHomeSearchList(String category, String keyword){
        return mHomeAPI.getHomeSearchList(category,keyword);
    }
}
