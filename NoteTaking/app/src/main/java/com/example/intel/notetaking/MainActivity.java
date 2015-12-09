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

package com.example.intel.notetaking;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    Data data;
    ArrayList<String> objects;
    private String DEFAULT_TEXT = "New Note";
    private static final int EDITOR_REQUEST_CODE = 1001;
    private ListAdapter listAdapter;
    String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new Data(baseDir);
        objects = new ArrayList<String>();
        fillObjectArray();
        ListView list = (ListView) findViewById(android.R.id.list);

        listAdapter = new ListAdapter(this,objects,data);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data.setCurrentKey(objects.get(position));
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);

                intent.putExtra(DEFAULT_TEXT, data.getAllNotes().get(data.getCurrentKey()));
                startActivityForResult(intent, EDITOR_REQUEST_CODE);
            }
        });
    }

    public void fillObjectArray(){
        objects.clear();
        objects.addAll(data.getAllNotes().keySet());
        Collections.sort(objects, Collections.reverseOrder());
    }

  public void openEditorForNewNote(View view) {
      insertNewObject();
      Intent intent = new Intent(this, EditorActivity.class);
      intent.putExtra(DEFAULT_TEXT, DEFAULT_TEXT);
      startActivityForResult(intent, EDITOR_REQUEST_CODE);
  }

    public void insertNewObject() {
        String date = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss.SSS").format(new Date());
        objects.add(0, date);
        data.setNoteForKey(date, DEFAULT_TEXT);
        data.setCurrentKey(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent note) {
        if (requestCode == EDITOR_REQUEST_CODE && resultCode == RESULT_CANCELED) {

                String stredittext=note.getStringExtra(DEFAULT_TEXT);
                if(stredittext.equals(DEFAULT_TEXT)){
                    data.removeNoteForKey(data.getCurrentKey());
                }else {
                    data.setNoteForCurrentKey(stredittext);
                }
                fillObjectArray();
                listAdapter.notifyDataSetChanged();
                data.saveFile();
        }
    }


}