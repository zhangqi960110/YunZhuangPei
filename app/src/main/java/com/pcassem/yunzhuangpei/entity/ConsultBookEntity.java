package com.pcassem.yunzhuangpei.entity;


public class ConsultBookEntity {


    /**
     * id : 1
     * createDate : 2018年01月16日 00:55
     * projectName : 用户测试项目一
     * projectAddress : 天津市市辖区河西区
     * contactName : 张三
     * contactPhone : 13807310000
     * startDate : 2018年01月18日 00:00
     * endDate : 2018年01月21日 00:00
     * description : 现场支持测试一
     * specialistName : 测试专家一
     * state : 1
     * replyDate : null
     * replyContent : null
     */

    private int id;
    private String createDate;
    private String projectName;
    private String projectAddress;
    private String contactName;
    private String contactPhone;
    private String startDate;
    private String endDate;
    private String description;
    private String specialistName;
    private int state;
    private String replyDate;
    private String replyContent;

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
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
}
