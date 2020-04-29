package com.pcassem.yunzhuangpei.entity;

import java.util.List;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class ExportDetailsEntity {

    /**
     * id : 1
     * userID : 6
     * tokenRongCloud : 6W5oqXB4lt4MUqSjrbGksKonMbMhbY3i5OwVYteUkHxXy6UYKC3WJZFuqqyz/yYpGpHGn+F4gYNbZ1ro/qgvuPxy9BGYpYj8
     * phoneNumber : 18607310000
     * name : 测试专家一
     * icon : http://p0mi0whax.bkt.clouddn.com/demo/icon/specialist.jpg
     * company : 远大建工
     * title : 高级工程师
     * address : 湖南长沙
     * description : 测试专家一
     * business : 测试专家一
     * isOnline : 1
     * isBook : 1
     * domains : ["防水","构件安装","开裂"]
     * answerCount : 0
     * bookCount : 0
     * onlineCount : 0
     * articleCount : 0
     */

    private int id;
    private int userID;
    private String tokenRongCloud;
    private String phoneNumber;
    private String name;
    private String icon;
    private String company;
    private String title;
    private String address;
    private String description;
    private String business;
    private int isOnline;
    private int isBook;
    private int answerCount;
    private int bookCount;
    private int onlineCount;
    private int articleCount;
    private List<String> domains;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTokenRongCloud() {
        return tokenRongCloud;
    }

    public void setTokenRongCloud(String tokenRongCloud) {
        this.tokenRongCloud = tokenRongCloud;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getIsBook() {
        return isBook;
    }

    public void setIsBook(int isBook) {
        this.isBook = isBook;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }
}
