package com.pcassem.yunzhuangpei.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.CommentEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentListAdapter extends BaseAdapter {

    Context context;
    List<CommentEntity> commentListData;
    CommentEntity commentEntity;

    public CommentListAdapter(Context c, List<CommentEntity> data) {
        this.context = c;
        this.commentListData = data;
    }

    @Override
    public int getCount() {
        return commentListData.size();
    }

    @Override
    public Object getItem(int i) {
        return commentListData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        // 重用convertView
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news_comment_list, null);
            viewHolder.userIcon = (SimpleDraweeView) convertView.findViewById(R.id.user_icon);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.createTime = (TextView) convertView.findViewById(R.id.create_time);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 适配数据
        commentEntity = commentListData.get(i);
        String url = commentEntity.getUserIcon();
        viewHolder.userIcon.setImageURI(Uri.parse(url));
        viewHolder.userName.setText(commentEntity.getUserName());
        viewHolder.createTime.setText(formatDate(commentEntity.getCreateTime()));
        viewHolder.content.setText(commentEntity.getContent());

        return convertView;

    }

    public void addComment(CommentEntity comment) {
        commentListData.add(comment);
        notifyDataSetChanged();
    }


    public static class ViewHolder {
        SimpleDraweeView userIcon;
        TextView userName;
        TextView createTime;
        TextView content;
    }

    //时间戳转换
    public String formatDate(long timeStamp) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        return sd;
    }
}

