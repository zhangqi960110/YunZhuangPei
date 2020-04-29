package com.pcassem.yunzhuangpei.entity;

import java.util.List;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class ExportEntity {


    /**
     * id : 1
     * name : 测试专家一
     * icon : http://p0mi0whax.bkt.clouddn.com/demo/icon/specialist.jpg
     * company : 远大建工
     * title : 高级工程师
     * address : 湖南长沙
     * isOnline : 1
     * isBook : 1
     * domains : ["防水","构件安装","开裂"]
     */

    private int id;
    private String name;
    private String icon;
    private String company;
    private String title;
    private String address;
    private int isOnline;
    private int isBook;
    private List<String> domains;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }
}
