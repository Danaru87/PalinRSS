package com.palinrss.palinrss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danaru on 07/04/15.
 */
public class ItemRSSAdapter extends ArrayAdapter<ItemRSS>{

    public ItemRSSAdapter (Context context, ArrayList<ItemRSS> itemList)
    {
        super(context, 0, itemList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ItemRSS itemRSS = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_rss_main, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.TitleRss);
        TextView description = (TextView) convertView.findViewById(R.id.DescRSS);

        title.setText(itemRSS.getTitle().substring(0, 40));
        description.setText(itemRSS.getDescription().substring(0,100));

        return convertView;
    }
}
