package com.pcassem.yunzhuangpei.personal.adapter;

/**
 * Created by zhangqi on 2017/11/23.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.MyProjectEntity;

import java.util.ArrayList;
import java.util.List;


public class MyProjectAdapter extends RecyclerView.Adapter<MyProjectAdapter.ViewHolder> implements View.OnClickListener{


    private static final String TAG = "MyProjectAdapter";

    private Context context;
    private List<MyProjectEntity> mData;
    private ButtonInterface buttonInterface;


    public void buttonSetOnClick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }

    public interface ButtonInterface{
        void onItemClick( View view,int position);
    }

    public MyProjectAdapter(Context context, List<MyProjectEntity> data) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_project, parent, false);
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
            Log.d(TAG, "onBindViewHolder: 默认项目");
            holder.right_image.setVisibility(View.VISIBLE);
            holder.is_checked.setBackground(context.getResources().getDrawable(R.drawable.submit_style));
            holder.is_default.setText("默认项目");
            holder.is_default.setTextColor(context.getResources().getColor(R.color.color_13386d));
        }

        holder.is_checked.setOnClickListener(this);
        holder.is_checked.setTag(mData.get(position).getProjectID());

        holder.edit.setOnClickListener(this);
        holder.edit.setTag(position);

        holder.delete.setOnClickListener(this);
        holder.delete.setTag(mData.get(position).getProjectID());
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
        Button edit;
        Button delete;

        LinearLayout is_checked;
        ImageView right_image;
        TextView is_default;

        public ViewHolder(View itemView) {
            super(itemView);
            projectName = (TextView) itemView.findViewById(R.id.project_name);
            projectAddress = (TextView) itemView.findViewById(R.id.project_address);

            edit = (Button) itemView.findViewById(R.id.edit);
            delete = (Button) itemView.findViewById(R.id.delete);

            is_checked = (LinearLayout) itemView.findViewById(R.id.is_checked);
            right_image = (ImageView) itemView.findViewById(R.id.right_image);
            is_default = (TextView) itemView.findViewById(R.id.is_default);
        }
    }
}