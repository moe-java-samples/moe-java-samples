// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


package com.intel.moe.samples.noteqslite.android;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Note;

import java.util.ArrayList;



public class ListAdapter extends ArrayAdapter {

    private Activity activity;
    private ArrayList<Note> noteList;

    public ListAdapter(Activity activity, ArrayList<Note> notes) {
        super(activity, 0, notes);
        this.activity = activity;
        noteList = notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // String key = noteList.get(position);//(String) getItem(position);
        String noteText = noteList.get(position).getNote();
        if (convertView == null) {
            convertView =  LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, parent, false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        int tint = ContextCompat.getColor(activity, R.color.colorPrimary);
        icon.setColorFilter(tint);

        TextView tv = (TextView) convertView.findViewById(R.id.tvNote);
        tv.setText(noteText);
        return convertView;
    }

    @Override
    public int getCount(){
        return noteList.size();
    }
}
