package com.pcassem.yunzhuangpei.personal.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.ConsultBookEntity;
import com.pcassem.yunzhuangpei.entity.ConsultEntity;

import java.util.ArrayList;
import java.util.List;


public class ConsultBookAdapter extends BaseExpandableListAdapter {

    private List<String> groups;
    private List<List<ConsultBookEntity>> childs;

    private Context mContext;

    public ConsultBookAdapter(Context context, List<String> groups, List<List<ConsultBookEntity>> childs) {
        mContext = context;
        this.groups = groups;
        this.childs = childs;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_my_advisory_parent, null);

        TextView tvGroupTitle = (TextView) v.findViewById(R.id.tv_group_title);
        ImageView icon = (ImageView) v.findViewById(R.id.icon_my_project);

        tvGroupTitle.setText(groups.get(groupPosition));
        if (isExpanded) {
            icon.setBackgroundResource(R.drawable.icon_my_project_up);
        } else {
            icon.setBackgroundResource(R.drawable.icon_my_project_down);
        }
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_my_advisory_details_book_child, null);

        ConsultBookEntity consultBook = childs.get(0).get(0);

        LinearLayout linearLayout1 = (LinearLayout) v.findViewById(R.id.details_problem_info);
        LinearLayout linearLayout2 = (LinearLayout) v.findViewById(R.id.details_problem_answer);

        TextView create_time = (TextView) v.findViewById(R.id.create_time);
        TextView project_name = (TextView) v.findViewById(R.id.project_name);
        TextView project_address = (TextView) v.findViewById(R.id.project_address);
        TextView contact_name = (TextView) v.findViewById(R.id.contact_name);
        TextView contact_phone = (TextView) v.findViewById(R.id.contact_phone);
        TextView start_end_date = (TextView) v.findViewById(R.id.start_end_date);
        TextView description = (TextView) v.findViewById(R.id.description);
        TextView specialist_name = (TextView) v.findViewById(R.id.specialist_name);
        TextView reply_date = (TextView) v.findViewById(R.id.reply_date);
        TextView reply_content = (TextView) v.findViewById(R.id.reply_content);

        create_time.setText(consultBook.getCreateDate());
        project_name.setText(consultBook.getProjectName());
        project_address.setText(consultBook.getProjectAddress());
        contact_name.setText(consultBook.getContactName());
        contact_phone.setText(consultBook.getContactPhone());
        start_end_date.setText(consultBook.getStartDate().split(" ")[0] + " 至 " + consultBook.getEndDate().split(" ")[0]);
        description.setText(consultBook.getDescription());

        specialist_name.setText(consultBook.getSpecialistName());
        if (consultBook.getState() == 4){
            reply_date.setText(consultBook.getReplyDate());
            reply_content.setText(consultBook.getReplyContent());
        }else if (consultBook.getState() == 5){
            reply_date.setText("已逾期");
            reply_content.setText("");
        } else {
            reply_date.setText("暂未答复");
            reply_content.setText("");
        }

        if (groupPosition == 1) {
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.VISIBLE);
        } else {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
        }

        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



}
