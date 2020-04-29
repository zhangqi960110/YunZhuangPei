package com.pcassem.yunzhuangpei.entity;

/**
 * Created by zhangqi on 2018/1/22.
 */

public class MyAdvisoryEntity {


    /**
     * consultID : 1
     * type : 1
     * title : 用户测试项目一
     * createDate : 2018年01月22日 00:49
     */

    private int consultID;
    private int type;
    private String title;
    private String createDate;

    public int getConsultID() {
        return consultID;
    }

    public void setConsultID(int consultID) {
        this.consultID = consultID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
