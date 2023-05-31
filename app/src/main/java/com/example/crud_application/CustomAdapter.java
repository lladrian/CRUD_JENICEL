package com.example.crud_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private List<String> dataList;
    private int itemLayoutResource;
    private int backgroundColor;

    public CustomAdapter(Context context, int resource, List<String> dataList, int backgroundColor) {
        super(context, resource, dataList);
        this.dataList = dataList;
        this.itemLayoutResource = resource;
        this.backgroundColor = backgroundColor;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(itemLayoutResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set the background color for the item
        convertView.setBackgroundColor(backgroundColor);

        // Set the text for the item
        String itemText = dataList.get(position);
        viewHolder.textView.setText(itemText);

        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
    }
}