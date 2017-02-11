package com.citrus.aboutcitrus;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MaintainerAdapter extends BaseAdapter {

    String[] title;
    String[] summary;
    Context context;
    private static LayoutInflater inflater = null;

    public MaintainerAdapter(FragmentActivity Activity, String[] titleList, String[] summaryList) {
        title = titleList;
        summary = summaryList;
        context = Activity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv1;
        TextView tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_item, null);
        holder.tv1 = (TextView) rowView.findViewById(R.id.textView1);
        holder.tv2 = (TextView) rowView.findViewById(R.id.textView2);
        holder.tv1.setText(title[position]);
        holder.tv2.setText(summary[position]);
        return rowView;
    }

}
