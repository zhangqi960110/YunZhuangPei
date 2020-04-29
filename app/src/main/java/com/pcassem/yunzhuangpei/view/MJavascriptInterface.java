package com.pcassem.yunzhuangpei.view;

import android.content.Context;
import android.content.Intent;

import com.pcassem.yunzhuangpei.home.activities.PhotoBrowserActivity;



public class MJavascriptInterface {
    private Context context;
    private String[] imageUrls;

    public MJavascriptInterface(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        Intent intent = new Intent();
        intent.putExtra("imageUrls", imageUrls);
        intent.putExtra("curImageUrl", img);
        intent.setClass(context, PhotoBrowserActivity.class);
        context.startActivity(intent);
    }
}
