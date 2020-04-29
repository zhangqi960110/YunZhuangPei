package com.pcassem.yunzhuangpei.entity;


import com.google.gson.annotations.SerializedName;

public class NewsEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("date")
    private long date;
    @SerializedName("icon")
    private String icon;
    @SerializedName("readCount")
    private int readCount;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }
}
