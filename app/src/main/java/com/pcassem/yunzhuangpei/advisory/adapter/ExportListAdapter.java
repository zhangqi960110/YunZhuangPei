package com.pcassem.yunzhuangpei.advisory.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqi on 2017/11/20.
 */

public class ExportListAdapter extends RecyclerView.Adapter<ExportListAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<ExportEntity> mData;
    private List<String> mVals;
    private int mIsOnline;
    private int mIsBook;
    private LayoutInflater mInflater;

    public void setmOnItemClickListener(ExportListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private ExportListAdapter.OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public ExportListAdapter(Context context, List<ExportEntity> data) {
        mVals = new ArrayList<>();
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public ExportListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mInflater = LayoutInflater.from(parent.getContext());

        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advisory_export_recycler_view, parent, false);
        // 实例化viewholder
        ExportListAdapter.ViewHolder viewHolder = new ExportListAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExportListAdapter.ViewHolder holder, int position) {
        // 绑定数据
        String url = mData.get(position).getIcon();
        holder.icon.setImageURI(Uri.parse(url));
        holder.name.setText(mData.get(position).getName());
        holder.company.setText(mData.get(position).getCompany());
        holder.title.setText(mData.get(position).getTitle());
        holder.address.setText(mData.get(position).getAddress());

        mIsOnline = mData.get(position).getIsOnline();
        mIsBook = mData.get(position).getIsBook();

        if (mIsOnline == 1) {
            holder.isOnline.setBackgroundResource(R.drawable.advisory_export_accept_btn);
            holder.isOnline.setTextColor(mContext.getResources().getColor(R.color.color_13386d));
        } else {
            holder.isOnline.setBackgroundResource(R.drawable.advisory_export_no_export_btn);
            holder.isOnline.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        }

        if (mIsBook == 1) {
            holder.isBook.setBackgroundResource(R.drawable.advisory_export_accept_btn);
            holder.isBook.setTextColor(mContext.getResources().getColor(R.color.color_13386d));
        } else {
            holder.isBook.setBackgroundResource(R.drawable.advisory_export_no_export_btn);
            holder.isBook.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        }

        holder.domains.removeAllViews();
        mVals = mData.get(position).getDomains();
        for (int i = 0; i < mVals.size(); i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.item_advisory_export_tags, holder.domains, false);
            tv.setText(mVals.get(i));
            holder.domains.addView(tv);
        }

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

        SimpleDraweeView icon;
        TextView name;
        TextView company;
        TextView title;
        TextView address;
        TextView isOnline;
        TextView isBook;
        FlowLayout domains;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (SimpleDraweeView) itemView.findViewById(R.id.export_icon);
            name = (TextView) itemView.findViewById(R.id.export_name);
            company = (TextView) itemView.findViewById(R.id.export_company);
            title = (TextView) itemView.findViewById(R.id.export_title);
            address = (TextView) itemView.findViewById(R.id.export_address);
            isOnline = (TextView) itemView.findViewById(R.id.export_is_online);
            isBook = (TextView) itemView.findViewById(R.id.export_is_book);
            domains = (FlowLayout) itemView.findViewById(R.id.export_domains);
        }
    }

    public void setmData(List<ExportEntity> mData) {
        this.mData = mData;
    }
}
