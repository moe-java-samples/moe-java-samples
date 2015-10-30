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

package com.intel.inde.moe.samples.lazytableloader.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.intel.inde.moe.samples.lazytableloader.common.AppRecord;

import java.util.List;

public class AppListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<AppRecord> appItems;

    public AppListAdapter(Activity activity, List<AppRecord> appItems) {
        this.activity = activity;
        this.appItems = appItems;
    }

    @Override
    public int getCount() {
        return appItems.size();
    }

    @Override
    public Object getItem(int position) {
        return appItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_layout, null);


        TextView appName = (TextView) convertView.findViewById(R.id.appName);
        TextView artist = (TextView) convertView.findViewById(R.id.artist);
        ImageView appIcon = (ImageView) convertView.findViewById(R.id.appIcon);

        // getting movie data for the row
        AppRecord appRecordItem = appItems.get(position);

        // title
        appName.setText(appRecordItem.getAppName());

        // description
        artist.setText(appRecordItem.getArtist());

        Bitmap bm;
        if (appRecordItem.getAppIcon() != null) {
            bm = BitmapFactory.decodeByteArray(appRecordItem.getAppIcon().getImage(), 0,
                    appRecordItem.getAppIcon().size());
        } else {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.placeholder);
        }

        appIcon.setImageBitmap(bm);

        return convertView;
    }
}
