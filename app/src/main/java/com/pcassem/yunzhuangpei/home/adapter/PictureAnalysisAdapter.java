package com.pcassem.yunzhuangpei.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.PictureAnalysisBean;

import java.util.List;


public class PictureAnalysisAdapter extends RecyclerView.Adapter<PictureAnalysisAdapter.ViewHolder> implements View.OnClickListener {

    private List<PictureAnalysisBean> mData;

    public void setmOnItemClickListener(PictureAnalysisAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private PictureAnalysisAdapter.OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public PictureAnalysisAdapter(List<PictureAnalysisBean> data) {
        this.mData = data;
    }

    @Override
    public PictureAnalysisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_analysis, parent, false);
        // 实例化viewholder
        PictureAnalysisAdapter.ViewHolder viewHolder = new PictureAnalysisAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PictureAnalysisAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.tags.setText(mData.get(position).getItemTags());
        holder.title.setText(mData.get(position).getTitle());
        holder.itemView.setTag(mData.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tags;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            tags = (TextView) itemView.findViewById(R.id.tags);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public void setmData(List<PictureAnalysisBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}

