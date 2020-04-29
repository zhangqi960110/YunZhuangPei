package com.pcassem.yunzhuangpei.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultListEntity<T> {

    @SerializedName("code")
    private int code;
    @SerializedName("result")
    private List<T> result;

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
