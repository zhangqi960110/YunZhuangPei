package com.pcassem.yunzhuangpei.http;

import com.pcassem.yunzhuangpei.entity.CommentEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.SearchListEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface HomeAPI {

    //最新资讯
    @GET("/app/index/latestNewsList")
    Observable<ResultListEntity<NewsEntity>> getLatestNewsList();

    //全部资讯
    @GET("/app/index/newsList")
    Observable<ResultListEntity<NewsEntity>> getNewsList();

    //搜索资讯
    @GET("/app/index/searchNewsList")
    Observable<ResultListEntity<NewsEntity>> getSearchNewsList(@Query("keyword") String keyword);

    //资讯详情
    @GET("/app/index/newsInfo")
    Observable<ResultListEntity<NewsDetailsEntity>> getNewsDetailsList(@Query("newsID") int newsID);

    //评论给列表
    @GET("/app/index/newsCommentList")
    Observable<ResultListEntity<CommentEntity>> getCommentList(@Query("newsID") int newsID);

    //搜索知识列表
    @GET("/app/index/search/List")
    Observable<ResultListEntity<KnowledgeEntity>> getSearchKnowledgeList(@Query("repository") String repository, @Query("keyword") String keyword);

    //搜索知识列表详情
    @GET("/app/index/search/info")
    Observable<ResultListEntity<KnowledgeDetailsNonDocEntity>> getSearchKnowledgeDetails(@Query("knowledgeID") int knowledgeID);

    //最新知识搜索列表接口
    @GET("/app/index/search/audio")
    Observable<SearchListEntity> getHomeSearchList(@Query("repository") String repository, @Query("keyword") String keyword);

}
