package com.intel.moe.samples.notetaking.android;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.intel.moe.samples.notetaking.common.Data;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {
   // private
    private Data data;
    private ArrayList<String> noteList;
    public ListAdapter(Activity context, ArrayList<String> notes, Data d){
        super(context,0,notes);
        noteList = notes;
        data =d;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String key = noteList.get(position);
        String noteText = data.getAllNotes().get(key);
        if (convertView == null) {
            convertView =  LayoutInflater.from(getContext()).inflate(
                    R.layout.note_list_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.icon);
        iconImageView.setColorFilter(convertView.getResources().getColor(R.color.colorPrimary));

        TextView tv = (TextView) convertView.findViewById(R.id.tvNote);
        tv.setText(noteText);
        return convertView;
    }

    @Override
    public int getCount(){
        return data.getAllNotes().size();
    }
}
