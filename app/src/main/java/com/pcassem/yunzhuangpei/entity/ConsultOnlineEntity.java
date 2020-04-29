package com.pcassem.yunzhuangpei.entity;

/**
 * Created by zhangqi on 2018/1/23.
 */

public class ConsultOnlineEntity {

    /**
     * id : 1
     * createDate : 2018年01月21日 00:58
     * specialistName : 测试专家一
     * onlineTime : 40
     */

    private int id;
    private String createDate;
    private String specialistName;
    private int onlineTime;

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

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public int getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(int onlineTime) {
        this.onlineTime = onlineTime;
    }
}
