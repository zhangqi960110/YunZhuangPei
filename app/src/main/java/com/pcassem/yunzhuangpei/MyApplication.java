package com.pcassem.yunzhuangpei;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pcassem.yunzhuangpei.utils.CityListLoader;

import io.rong.imkit.RongIM;

public class MyApplication extends Application {

    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=58690f64");
        RongIM.init(this);

        CityListLoader.getInstance().loadProData(this);

        mSharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove("type");
        editor.remove("userID");
        editor.remove("username");
        editor.commit();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
