package com.pcassem.yunzhuangpei.advisory.adapter;

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

public class SelectExportListAdapter extends RecyclerView.Adapter<SelectExportListAdapter.ViewHolder> implements View.OnClickListener{


    private List<ExportEntity> mData;
    private List<String> mVals;
    private LayoutInflater mInflater;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public SelectExportListAdapter(List<ExportEntity> data){
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mInflater = LayoutInflater.from(parent.getContext());

        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_export_recycler_view , parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = mData.get(position).getIcon();
        holder.icon.setImageURI(Uri.parse(url));
        holder.name.setText(mData.get(position).getName());
        holder.company.setText(mData.get(position).getCompany());
        holder.title.setText(mData.get(position).getTitle());
        holder.address.setText(mData.get(position).getAddress());

        holder.domains.removeAllViews();
        mVals = mData.get(position).getDomains();
        for (int i = 0; i < mVals.size(); i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.item_advisory_export_tags, holder.domains, false);
            tv.setText(mVals.get(i));
            holder.domains.addView(tv);
        }

        holder.selectButton.setOnClickListener(this);
        holder.selectButton.setTag(position);
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
        TextView name;
        TextView company;
        TextView title;
        TextView address;
        FlowLayout domains;
        Button selectButton;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (SimpleDraweeView) itemView.findViewById(R.id.export_icon);
            name = (TextView) itemView.findViewById(R.id.export_name);
            company = (TextView) itemView.findViewById(R.id.export_company);
            title = (TextView) itemView.findViewById(R.id.export_title);
            address = (TextView) itemView.findViewById(R.id.export_address);
            domains = (FlowLayout) itemView.findViewById(R.id.export_domains);
            selectButton = (Button) itemView.findViewById(R.id.select_button);
        }
    }

    public void setmData(List<ExportEntity> mData) {
        this.mData = mData;
    }
}
