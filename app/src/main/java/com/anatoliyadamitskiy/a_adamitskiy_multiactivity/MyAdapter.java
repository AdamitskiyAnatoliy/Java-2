package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class MyAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Person> mObjects;
    private static final int ID_CONSTANT = 0x1000000;

    public MyAdapter(Context c, ArrayList<Person> objects) {
        mContext = c;
        mObjects = objects;
    }

    @Override
    public int getCount() {
        if(mObjects != null) {
            return mObjects.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(mObjects != null && position < mObjects.size() && position >= 0) {
            return mObjects.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Person item = (Person) getItem(position);

        holder.textView1.setText(item.getName());
        holder.textView2.setText(item.getNumber());
        holder.textView3.setText(item.getPosition());

        return convertView;
    }


    static class ViewHolder {

        public TextView textView1;
        public TextView textView2;
        public TextView textView3;

        public ViewHolder (View v) {

            textView1 = (TextView)v.findViewById(R.id.nameView);
            textView2 = (TextView)v.findViewById(R.id.numberView);
            textView3 = (TextView)v.findViewById(R.id.positionView);

        }

    }

}
