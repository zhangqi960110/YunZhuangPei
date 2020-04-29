package com.pcassem.yunzhuangpei.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by zhangqi on 2017/12/7.
 */

public class JsonUtil {

    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static JSONObject firstStandardString(Context context) {
        try {
            JSONObject object = new JSONObject(getJson("select.json", context));
            return object;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject selectObject(JSONObject object, int position) {
        try {
            JSONObject object1 = (JSONObject) object.getJSONArray("nexts").get(position);
            return object1;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String[] selectOneList(JSONObject object, String str) {
        String[] strList = null;
        try {
            JSONArray jsonArray = object.getJSONArray("nexts");
            strList = new String[10];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jb = (JSONObject) jsonArray.get(i);
                strList[i] = jb.getString(str);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strList;
    }

    public static String[][] selectTwoList(JSONObject object, String str) {

        String[][] strList = new String[10][10];
        try {
            JSONArray jsonArray = object.getJSONArray("nexts");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object1 = (JSONObject) jsonArray.get(i);

                JSONArray jsonArray1 = object1.getJSONArray("nexts");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject object2 = (JSONObject) jsonArray1.get(j);
                    strList[i][j] = object2.getString(str);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strList;
    }

    public static String[][][] selectThreeList(JSONObject object, String str) {
        String[][][] strList = new String[10][10][10];
        try {
            JSONArray jsonArray = object.getJSONArray("nexts");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object1 = (JSONObject) jsonArray.get(i);
                JSONArray jsonArray1 = object1.getJSONArray("nexts");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject object2 = (JSONObject) jsonArray1.get(j);
                    JSONArray jsonArray2 = object2.getJSONArray("nexts");
                    for (int k = 0; k < jsonArray2.length(); k++) {
                        JSONObject object3 = (JSONObject) jsonArray2.get(k);
                        strList[i][j][k] = object3.getString(str);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strList;
    }

}
