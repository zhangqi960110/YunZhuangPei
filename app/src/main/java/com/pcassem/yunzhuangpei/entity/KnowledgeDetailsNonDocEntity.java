package com.pcassem.yunzhuangpei.entity;

import com.google.gson.annotations.SerializedName;

public class KnowledgeDetailsNonDocEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("date")
    private long date;
    @SerializedName("readCount")
    private int readCount;
    @SerializedName("content")
    private String content;
    @SerializedName("likeCount")
    private int likeCount;
    @SerializedName("commentCount")
    private int commentCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

}
