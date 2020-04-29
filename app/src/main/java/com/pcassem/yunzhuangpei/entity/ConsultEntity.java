package com.pcassem.yunzhuangpei.entity;

import java.util.List;

/**
 * Created by zhangqi on 2018/1/23.
 */

public class ConsultEntity {


    /**
     * id : 1
     * createDate : 2018年01月22日 00:49
     * projectName : 用户测试项目一
     * projectAddress : 天津市市辖区河西区
     * classify : 高层
     * part : 管道
     * description : 图文咨询测试一
     * images : ["http://p0mi0whax.bkt.clouddn.com/demo/icon/user.png","http://p0mi0whax.bkt.clouddn.com/demo/icon/user.png"]
     * state : 1
     * replyDate : null
     * replyContent : null
     */

    private int id;
    private String createDate;
    private String projectName;
    private String projectAddress;
    private String classify;
    private String part;
    private String description;
    private int state;
    private String replyDate;
    private String replyContent;
    private List<String> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
