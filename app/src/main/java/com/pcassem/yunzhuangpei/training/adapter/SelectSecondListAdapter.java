package com.pcassem.yunzhuangpei.training.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

/**
 * Created by zhangqi on 2017/12/7.
 */

public class SelectSecondListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    String[] data;
    public int selectedPosition = -1;

    public SelectSecondListAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_second_select_list, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
//            viewHolder.underline = (TextView)convertView.findViewById(R.id.underline_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (selectedPosition == position) {
            viewHolder.textView.setTextColor(convertView.getResources().getColor(R.color.color_13386d));
//            viewHolder.underline.setVisibility(View.VISIBLE);
        } else {
            viewHolder.textView.setTextColor(convertView.getResources().getColor(R.color.color_999999));
//            viewHolder.underline.setVisibility(View.GONE);
        }
        viewHolder.textView.setText(data[position]);
//        viewHolder.underline.setText(secondProcessList[position]);
        return convertView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetInvalidated();
    }

    public void setData(String[] data) {
        this.data = data;
        setSelectedPosition(-1);
        notifyDataSetInvalidated();
    }

    public static class ViewHolder {
        public TextView textView;
//        public TextView underline;
    }


}
