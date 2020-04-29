package com.pcassem.yunzhuangpei.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

import java.util.ArrayList;

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.ViewHolder> implements View.OnClickListener{


private ArrayList<String> mData;

public void setmOnItemClickListener(SearchResultListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        }

private SearchResultListAdapter.OnItemClickListener mOnItemClickListener = null;

public static interface OnItemClickListener {
    void onItemClick(View view, int position);
}

    public SearchResultListAdapter(ArrayList<String> data){
        this.mData = data;
    }

    @Override
    public SearchResultListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_news, parent, false);
        // 实例化viewholder
        SearchResultListAdapter.ViewHolder viewHolder = new SearchResultListAdapter.ViewHolder(v);
        v.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchResultListAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.mTextView.setText(mData.get(position));

        holder.itemView.setTag(position);
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

    TextView mTextView;

    public ViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.item_tv);
    }
}
}
