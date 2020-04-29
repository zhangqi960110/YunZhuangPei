package com.pcassem.yunzhuangpei.advisory.bean;

/**
 * Created by zhangqi on 2018/1/23.
 */

public class OnlineBean {

    private int userID;
    private int projectID;
    private String createDate;
    private int onlineDate;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(int onlineDate) {
        this.onlineDate = onlineDate;
    }
}
