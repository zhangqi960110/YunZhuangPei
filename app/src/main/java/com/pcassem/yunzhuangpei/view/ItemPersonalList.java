package com.pcassem.yunzhuangpei.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

/**
 * Created by zhangqi on 2017/11/20.
 */

public class ItemPersonalList extends RelativeLayout {

    private ImageView item_customize_list_imageview;
    private TextView item_customize_list_textview;

    private int mImageView;
    private String mTextView;


    public ItemPersonalList(Context context) {
        super(context);
        initView(context);
    }

    public ItemPersonalList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypedArray(context, attrs);
        initView(context);
    }

    public ItemPersonalList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypedArray(context, attrs);
        initView(context);
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.PersonalItem);
        mImageView = mTypedArray.getResourceId(R.styleable.PersonalItem_image_view,R.drawable.personal_project);
        mTextView = mTypedArray.getString(R.styleable.PersonalItem_text_view);
        //获取资源后要及时回收
        mTypedArray.recycle();
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_customize_personal_list,this,true);
        item_customize_list_imageview = (ImageView) findViewById(R.id.item_customize_list_imageview);
        item_customize_list_textview = (TextView) findViewById(R.id.item_customize_list_textview);

        item_customize_list_imageview.setImageResource(mImageView);
        item_customize_list_textview.setText(mTextView);
    }

}
