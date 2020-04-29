package com.pcassem.yunzhuangpei.home.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.ViewHolder> implements View.OnClickListener{


    private List<NewsEntity> mData;

    public LatestNewsAdapter(List<NewsEntity> data){
        this.mData = data;
    }

    public void setmData(List<NewsEntity> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_news, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        String url = mData.get(position).getIcon();
        holder.newsIcon.setImageURI(Uri.parse(url));
        holder.newsTitle.setText(mData.get(position).getTitle());
        holder.newsDate.setText(DateUtil.getStandardDate(mData.get(position).getDate()));
        holder.newsReadCount.setText("阅读"+mData.get(position).getReadCount()+"次");
        holder.itemView.setTag(mData.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView newsIcon;
        TextView newsTitle;
        TextView newsDate;
        TextView newsReadCount;

        public ViewHolder(View itemView) {
            super(itemView);
            newsIcon = (SimpleDraweeView) itemView.findViewById(R.id.news_icon);
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsDate = (TextView) itemView.findViewById(R.id.news_date);
            newsReadCount = (TextView) itemView.findViewById(R.id.news_read_count);
        }
    }

}
