package com.example.madrasaregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<Record> records;
    public RecordAdapter(Context context, ArrayList<Record> records) {
        this.context = context;
        this.records = records;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.student_record_item, parent, false);
        }
       Record currentItem = (Record) getItem(position);
        TextView date = (TextView)
                convertView.findViewById(R.id.txt_record_date);
        TextView sabak = (TextView)
                convertView.findViewById(R.id.txt_record_sabak);
        TextView sabki = (TextView)
                convertView.findViewById(R.id.txt_record_sabki);
        TextView manzil = (TextView)
                convertView.findViewById(R.id.txt_record_manzil);
        date.setText(currentItem.getDate());
        sabak.setText(currentItem.getSabak().toString());
        sabki.setText(currentItem.getSabki().toString());
        manzil.setText(currentItem.getManzil().toString());
        return convertView;
    }
}

