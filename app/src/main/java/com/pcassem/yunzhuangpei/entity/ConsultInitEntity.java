package com.pcassem.yunzhuangpei.entity;

import java.util.List;

/**
 * Created by zhangqi on 2018/1/23.
 */

public class ConsultInitEntity {

    /**
     * userProjectID : 4
     * userProjectName : 用户测试项目一
     * userProjectAddress : 天津市市辖区河西区
     * classifyList : ["高层","管廊"]
     * partList : ["梁","柱","管道"]
     */

    private int userProjectID;
    private String userProjectName;
    private String userProjectAddress;
    private List<String> classifyList;
    private List<String> partList;

    public int getUserProjectID() {
        return userProjectID;
    }

    public void setUserProjectID(int userProjectID) {
        this.userProjectID = userProjectID;
    }

    public String getUserProjectName() {
        return userProjectName;
    }

    public void setUserProjectName(String userProjectName) {
        this.userProjectName = userProjectName;
    }

    public String getUserProjectAddress() {
        return userProjectAddress;
    }

    public void setUserProjectAddress(String userProjectAddress) {
        this.userProjectAddress = userProjectAddress;
    }

    public List<String> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<String> classifyList) {
        this.classifyList = classifyList;
    }

    public List<String> getPartList() {
        return partList;
    }

    public void setPartList(List<String> partList) {
        this.partList = partList;
    }
}
