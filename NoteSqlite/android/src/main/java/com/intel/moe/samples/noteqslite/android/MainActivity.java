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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.Note;
import com.example.database.ISQLiteDatabase;

import com.example.database.SQLiteDatabaseHelper;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ISQLiteDatabase db;
    ArrayList<Note> objects;
    private String DEFAULT_TEXT = "New Note";
    private static final int EDITOR_REQUEST_CODE = 1001;
    private ListAdapter listAdapter;
    String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabaseHelper dbHelper = new AndroidSQLiteDatabaseHelper(AndroidSQLiteDatabaseHelper.DB_NAME);
        db = dbHelper.getWritableDatabase();
        objects = new ArrayList<Note>();
        makeObjects();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        ListView list = (ListView) findViewById(android.R.id.list);
        listAdapter = new ListAdapter(this,objects);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(this);

        FloatingActionButton sendButton = (FloatingActionButton) findViewById(R.id.add_button);
        sendButton.setOnClickListener(addNoteOnClickListener);
    }

    View.OnClickListener addNoteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openEditorForNewNote(view);
        }
    };

    public void makeObjects(){
        objects.clear();
        PriorityQueue<Note> temp = db.getAllNotes();
        while(!temp.isEmpty()){
            objects.add(temp.poll());
        }
    }

  public void openEditorForNewNote(View view) {
      insertNewObject();
      Intent intent = new Intent(this, EditorActivity.class);
      intent.putExtra(DEFAULT_TEXT, objects.get(0).getId());
      startActivityForResult(intent, EDITOR_REQUEST_CODE);
  }

    public void insertNewObject() {

        db.insert(DEFAULT_TEXT);
        makeObjects();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent note) {
        makeObjects();
        listAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        intent.putExtra(DEFAULT_TEXT, objects.get(position).getId());
        startActivityForResult(intent, EDITOR_REQUEST_CODE);
    }
}