package com.pcassem.yunzhuangpei.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.renderscript.Sampler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.training.activities.TrainingCoursesActivity;

/**
 * Created by zhangqi on 2017/12/4.
 */

public class AnimationUtil {


    private static AnimationUtil sInstance;

    private Context context;
    private int height;

    private AnimationUtil(Context context) {
        this.context = context;
    }

    public static AnimationUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AnimationUtil(context);
        }
        return sInstance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMeHeight() {
        int mHiddenViewMeasuredHeight = dpToPx(getHeight());
        return mHiddenViewMeasuredHeight;
    }

    public int dpToPx(int length) {
        return (int) (getDensity() * length + 0.5);
    }

    public float getDensity() {
        return context.getResources().getDisplayMetrics().density;
    }

    public void animate(View v, int start, int end) {
        ValueAnimator animator = createDropAnimator(v, start, end);
        animator.start();
    }

    public void modifyHight(View view, int position, String[][] list) {

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = dpToPx(raws(position, list) * 28 + 42);
        view.setLayoutParams(lp);
    }

    public int raws(int position, String[][] list) {

        String[] standardLabelLength = context.getResources().getStringArray(R.array.tags_width);
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int allsTagsLength = 0;
        for (int i = 0; i < list[position].length; i++) {
            allsTagsLength += dpToPx(Integer.parseInt(standardLabelLength[list[position][i].length() - 1]));
        }
        int width = dpToPx(14) + allsTagsLength;
        if (width > screenWidth) {
            return 2;
        } else {
            return 1;
        }
    }


    public void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, getMeHeight());
        animator.start();
    }

    public void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }

}
