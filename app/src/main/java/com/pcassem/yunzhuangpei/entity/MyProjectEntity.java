package com.pcassem.yunzhuangpei.entity;


import com.google.gson.annotations.SerializedName;


public class MyProjectEntity {


    /**
     * projectID : 4
     * userID : 34
     * name : 用户测试项目一
     * provinceCode : 430000
     * cityCode : 430100
     * areaCode : 430104
     * isDefault : 1
     */

    @SerializedName("projectID")
    private int projectID;
    @SerializedName("userID")
    private int userID;
    @SerializedName("name")
    private String name;
    @SerializedName("provinceCode")
    private String provinceCode;
    @SerializedName("cityCode")
    private String cityCode;
    @SerializedName("areaCode")
    private String areaCode;
    @SerializedName("isDefault")
    private int isDefault;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
