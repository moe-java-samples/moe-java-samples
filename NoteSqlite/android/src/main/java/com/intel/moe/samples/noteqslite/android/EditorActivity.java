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
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Note;
import com.example.database.ISQLiteDatabase;
import com.example.database.SQLiteDatabaseHelper;

public class EditorActivity extends ActionBarActivity {

    private String action;
    private static EditText editor;
    private String noteFilter;
    private String oldText;
    private String DEFAULT_TEXT = "New Note";
    private ISQLiteDatabase db;
    private Integer noteId;
    private Note noteDetail;
    String note = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editor = (EditText) findViewById(R.id.editText);
        SQLiteDatabaseHelper dbHelper = new AndroidSQLiteDatabaseHelper(AndroidSQLiteDatabaseHelper.DB_NAME);
        db = dbHelper.getWritableDatabase();

        Intent intent = getIntent();

        noteId = intent.getIntExtra(DEFAULT_TEXT,0);
        noteDetail = db.getNoteById(noteId);
        note = noteDetail.getNote();
        if (note.equals( "New Note")) {
            setTitle("New Note");
        } else {
            setTitle("Edit Note");
            editor.setText(note);
            editor.requestFocus();
        }
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_editor, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finishEditing();
                break;
            case R.id.action_delete:
                deleteNote();
                break;
        }
        return true;
    }

    private void deleteNote() {
        editor.setText("");
        Toast.makeText(this, getString(R.string.note_deleted),
                Toast.LENGTH_SHORT).show();

        finishEditing();
    }

    private void finishEditing() {
        String newText = editor.getText().toString().trim();
        if(newText.equals("")){
            newText = DEFAULT_TEXT;
            db.delete(noteId);
        }

        else if(!newText.equals(note)){
            noteDetail.setNote(newText);
            db.update(noteDetail);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        finishEditing();
    }
}
