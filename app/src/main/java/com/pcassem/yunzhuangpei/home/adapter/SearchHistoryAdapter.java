package com.pcassem.yunzhuangpei.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.activities.HomeSearchActivity;

import java.util.List;
import java.util.Map;

import static android.R.attr.data;

/**
 * Created by zhangqi on 2017/11/18.
 */

public class SearchHistoryAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private List<Map<String,Object>> data;

    public SearchHistoryAdapter(Context context,List<Map<String,Object>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.item_list_view_search_history, null);
            holder.searchHistoryItem = (TextView) convertView.findViewById(R.id.contentTextView);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.searchHistoryItem.setText((String) data.get(position).get("searchHistoryItem"));

        return convertView;
    }

    static class ViewHolder {
        public TextView searchHistoryItem;
    }

}
