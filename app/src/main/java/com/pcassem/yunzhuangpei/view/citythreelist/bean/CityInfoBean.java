package com.pcassem.yunzhuangpei.view.citythreelist.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class CityInfoBean implements Parcelable {

    
    private String id;
    private String code;
    private String name;


    private ArrayList<CityInfoBean> cityList;
    
    public ArrayList<CityInfoBean> getCityList() {
        return cityList;
    }
    
    public void setCityList(ArrayList<CityInfoBean> cityList) {
        this.cityList = cityList;
    }

    public String getId() {
        return id == null ? "" : id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name == null ? "" : name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeTypedList(this.cityList);
    }
    
    protected CityInfoBean(Parcel in) {
        this.id = in.readString();
        this.code = in.readString();
        this.name = in.readString();
        this.cityList = in.createTypedArrayList(CityInfoBean.CREATOR);
    }
    
    public static final Creator<CityInfoBean> CREATOR = new Creator<CityInfoBean>() {
        @Override
        public CityInfoBean createFromParcel(Parcel source) {
            return new CityInfoBean(source);
        }
        
        @Override
        public CityInfoBean[] newArray(int size) {
            return new CityInfoBean[size];
        }
    };

    @Override
    public String toString() {
        return "CityInfoBean{" +
                ", id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
