package com.pcassem.yunzhuangpei.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangqi on 2017/12/10.
 */

public class CommentEntity {

    @SerializedName("userIcon")
    private String userIcon;
    @SerializedName("userName")
    private String userName;
    @SerializedName("createTime")
    private long createTime;
    @SerializedName("content")
    private String content;

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
