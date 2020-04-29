package com.pcassem.yunzhuangpei.training.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.CourseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingListAdapter extends RecyclerView.Adapter<TrainingListAdapter.ViewHolder> implements View.OnClickListener{


    private List<CourseEntity> mData;

    public void setmOnItemClickListener(TrainingListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private TrainingListAdapter.OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public TrainingListAdapter(List<CourseEntity> data){
        this.mData = data;
    }

    @Override
    public TrainingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training_recycler_view, parent, false);
        // 实例化viewholder
        TrainingListAdapter.ViewHolder viewHolder = new TrainingListAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrainingListAdapter.ViewHolder holder, int position) {
        // 绑定数据
        String url = mData.get(position).getIcon();
        holder.icon.setImageURI(Uri.parse(url));
        holder.title.setText(mData.get(position).getTitle());
        holder.date.setText(formatDate(mData.get(position).getDate()));
        holder.address.setText(mData.get(position).getAddress());
        holder.signCount.setText("已报名 " + mData.get(position).getSignCount() + "人");
        holder.itemView.setTag(mData.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView icon;
        TextView title;
        TextView date;
        TextView address;
        TextView signCount;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (SimpleDraweeView) itemView.findViewById(R.id.course_icon);
            title = (TextView) itemView.findViewById(R.id.course_title);
            date = (TextView) itemView.findViewById(R.id.course_date);
            address = (TextView) itemView.findViewById(R.id.course_address);
            signCount = (TextView) itemView.findViewById(R.id.course_sign_count);
        }
    }

    //时间戳转换
    public String formatDate(long timeStamp) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        return sd;
    }
}
