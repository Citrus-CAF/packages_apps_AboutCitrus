package com.citrus.aboutcitrus;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class TCAdapter extends BaseAdapter{
    String [] name;
    String [] desc;
    Context context;
    int [] bgimage;
    int [] image;
    private static LayoutInflater inflater=null;
    public TCAdapter(FragmentActivity Activity, String[] devname, String[] devdesc, int[] devbgImage, int[] devimage) {
        name=devname;
        desc=devdesc;
        bgimage=devbgImage;
        image=devimage;

        context=Activity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.length;
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

    public class Holder
    {
        TextView tv1;
        TextView tv2;
        ImageView img1;
        ImageView img2;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.tccard_item, null);
        holder.tv1=(TextView) rowView.findViewById(R.id.cardtitle);
        holder.tv2=(TextView) rowView.findViewById(R.id.carddescription);
        holder.img1=(ImageView) rowView.findViewById(R.id.bgimage);
        holder.img2=(ImageView) rowView.findViewById(R.id.cardimage);
        holder.tv1.setText(name[position]);
        holder.tv2.setText(desc[position]);
        holder.img1.setImageResource(bgimage[position]);
        holder.img2.setImageResource(image[position]);
        return rowView;
    }

}
