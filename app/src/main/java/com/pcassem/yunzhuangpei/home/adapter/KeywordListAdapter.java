package com.pcassem.yunzhuangpei.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

import java.util.List;

public class KeywordListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<String> data;
    private int selectedPosition = -1;

    public KeywordListAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KeywordListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_keyword, null);
            holder = new KeywordListAdapter.ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.text_view);
            convertView.setTag(holder);
        } else {
            holder = (KeywordListAdapter.ViewHolder) convertView.getTag();
        }

        if (selectedPosition == position) {
            holder.textView.setTextColor(convertView.getResources().getColor(R.color.color_13386d));
            holder.textView.setBackgroundResource(R.drawable.select_tags_checked);
        } else {
            holder.textView.setTextColor(convertView.getResources().getColor(R.color.color_999999));
            holder.textView.setBackgroundResource(R.drawable.select_tags);
        }
        holder.textView.setText(data.get(position));
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

    public void setData(List<String> data) {
        this.data = data;
        setSelectedPosition(-1);
        notifyDataSetInvalidated();
    }


    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetInvalidated();
    }



}

