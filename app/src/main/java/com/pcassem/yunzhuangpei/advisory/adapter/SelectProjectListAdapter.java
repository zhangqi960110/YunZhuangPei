package com.pcassem.yunzhuangpei.advisory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.MyProjectEntity;

import java.util.List;

/**
 * Created by zhangqi on 2018/1/25.
 */

public class SelectProjectListAdapter extends RecyclerView.Adapter<SelectProjectListAdapter.ViewHolder> implements View.OnClickListener{


    private static final String TAG = "SelectProjectListAdapter";

    private Context context;
    private List<MyProjectEntity> mData;
    private ButtonInterface buttonInterface;


    public void buttonSetOnClick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }

    public interface ButtonInterface{
        void onItemClick( View view,int position);
    }

    public SelectProjectListAdapter(Context context, List<MyProjectEntity> data) {
        this.context = context;
        this.mData = data;
    }

    public void updateData(List<MyProjectEntity> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_project, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.projectName.setText(mData.get(position).getName());
        holder.projectAddress.setText(mData.get(position).getProvinceCode() + "-" + mData.get(position).getCityCode() + "-" + mData.get(position).getAreaCode());

        if (mData.get(position).getIsDefault() == 0){
            holder.isDefault.setVisibility(View.VISIBLE);
        }

        holder.selectBtn.setOnClickListener(this);
        holder.selectBtn.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (buttonInterface != null){
            buttonInterface.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView projectName;
        TextView projectAddress;
        Button selectBtn;
        TextView isDefault;


        public ViewHolder(View itemView) {
            super(itemView);
            projectName = (TextView) itemView.findViewById(R.id.project_name);
            projectAddress = (TextView) itemView.findViewById(R.id.project_address);

            selectBtn = (Button) itemView.findViewById(R.id.select_button);
            isDefault = (TextView) itemView.findViewById(R.id.is_default);
        }
    }
}