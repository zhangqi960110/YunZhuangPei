package com.pcassem.yunzhuangpei.personal.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.ConsultEntity;

import java.util.List;

/**
 * Created by zhangqi on 2018/1/23.
 */

public class ConsultQuickAdapter extends BaseExpandableListAdapter {

    private List<String> groups;
    private List<List<ConsultEntity>> childs;

    private Context mContext;

    public ConsultQuickAdapter(Context context, List<String> groups, List<List<ConsultEntity>> childs) {
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
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_my_advisory_details_child, null);

        LinearLayout linearLayout1 = (LinearLayout) v.findViewById(R.id.details_problem_info);
        LinearLayout linearLayout2 = (LinearLayout) v.findViewById(R.id.details_problem_answer);

        TextView create_time = (TextView) v.findViewById(R.id.create_time);
        TextView project_name = (TextView) v.findViewById(R.id.project_name);
        TextView project_address = (TextView) v.findViewById(R.id.project_address);
        TextView classify = (TextView) v.findViewById(R.id.classify);
        TextView part = (TextView) v.findViewById(R.id.part);
        TextView description = (TextView) v.findViewById(R.id.description);
        TextView reply_date = (TextView) v.findViewById(R.id.reply_date);
        TextView reply_content = (TextView) v.findViewById(R.id.reply_content);

        LinearLayout image_layout_top = (LinearLayout) v.findViewById(R.id.image_layout_top);

        SimpleDraweeView image_one = (SimpleDraweeView) v.findViewById(R.id.image_one);
        SimpleDraweeView image_two = (SimpleDraweeView) v.findViewById(R.id.image_two);
        SimpleDraweeView image_three = (SimpleDraweeView) v.findViewById(R.id.image_three);
        SimpleDraweeView image_four = (SimpleDraweeView) v.findViewById(R.id.image_four);
        SimpleDraweeView image_five = (SimpleDraweeView) v.findViewById(R.id.image_five);

        create_time.setText(childs.get(0).get(0).getCreateDate());
        project_name.setText(childs.get(0).get(0).getProjectName());
        project_address.setText(childs.get(0).get(0).getProjectAddress());
        classify.setText(childs.get(0).get(0).getClassify());
        part.setText(childs.get(0).get(0).getPart());
        description.setText(childs.get(0).get(0).getDescription());

        displayImage(childs.get(0).get(0).getImages(),
                childs.get(0).get(0).getImages().size(),
                image_layout_top,
                image_one, image_two,image_three,image_four,image_five);

        if (childs.get(0).get(0).getState() == 4){
            reply_date.setText(childs.get(0).get(0).getReplyDate());
            reply_content.setText(childs.get(0).get(0).getReplyContent());
        }else if (childs.get(0).get(0).getState() == 5){
            reply_date.setText("已逾期");
            reply_content.setText("");
        } else{
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


    private void displayImage(List<String> list,
                              int position,
                              LinearLayout image_layout_top,
                              SimpleDraweeView image_one, SimpleDraweeView image_two,SimpleDraweeView image_three, SimpleDraweeView image_four,SimpleDraweeView image_five){
        if (position == 1){
            image_one.setImageURI(Uri.parse(list.get(0)));
            image_layout_top.setVisibility(View.VISIBLE);
        }
        if (position == 2){
            image_one.setImageURI(Uri.parse(list.get(0)));
            image_two.setImageURI(Uri.parse(list.get(1)));
            image_layout_top.setVisibility(View.VISIBLE);
        }
        if (position == 3){
            image_one.setImageURI(Uri.parse(list.get(0)));
            image_two.setImageURI(Uri.parse(list.get(1)));
            image_three.setImageURI(Uri.parse(list.get(2)));
            image_layout_top.setVisibility(View.VISIBLE);
        }
        if (position == 4){
            image_one.setImageURI(Uri.parse(list.get(0)));
            image_two.setImageURI(Uri.parse(list.get(1)));
            image_three.setImageURI(Uri.parse(list.get(2)));
            image_four.setImageURI(Uri.parse(list.get(3)));
            image_layout_top.setVisibility(View.VISIBLE);
        }
        if (position == 5){
            image_one.setImageURI(Uri.parse(list.get(0)));
            image_two.setImageURI(Uri.parse(list.get(1)));
            image_three.setImageURI(Uri.parse(list.get(2)));
            image_four.setImageURI(Uri.parse(list.get(3)));
            image_five.setImageURI(Uri.parse(list.get(4)));
            image_layout_top.setVisibility(View.VISIBLE);
        }
    }


}
