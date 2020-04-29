package com.pcassem.yunzhuangpei.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class UserEntity implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("icon")
    private String icon;
    @SerializedName("type")
    private int type;
    @SerializedName("tokenRongCloud")
    private String tokenRongCloud;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTokenRongCloud() {
        return tokenRongCloud;
    }

    public void setTokenRongCloud(String tokenRongCloud) {
        this.tokenRongCloud = tokenRongCloud;
    }
}
