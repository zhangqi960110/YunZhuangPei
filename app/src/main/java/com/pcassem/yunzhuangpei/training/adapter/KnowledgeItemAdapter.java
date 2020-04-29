package com.pcassem.yunzhuangpei.training.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;

import java.util.List;


public class KnowledgeItemAdapter extends RecyclerView.Adapter<KnowledgeItemAdapter.ViewHolder> implements View.OnClickListener {

    private List<KnowledgeEntity> mData;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, String position);
    }


    public KnowledgeItemAdapter(List<KnowledgeEntity> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_standard_recycler_view, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.title.setText(mData.get(position).getTitle());

        if (mData.get(position).getIsFile() == 2) {
            holder.next.setVisibility(View.GONE);
            holder.download.setVisibility(View.VISIBLE);
            holder.itemView.setTag("Y"+mData.get(position).getId());
        } else {
            holder.next.setVisibility(View.VISIBLE);
            holder.download.setVisibility(View.GONE);
            holder.itemView.setTag("N" + mData.get(position).getId());
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView next;
        Button download;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            next = (ImageView) itemView.findViewById(R.id.next);
            download = (Button) itemView.findViewById(R.id.download);
        }
    }

    public void setmData(List<KnowledgeEntity> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}