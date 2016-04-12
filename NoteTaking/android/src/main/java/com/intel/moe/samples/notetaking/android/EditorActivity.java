package com.intel.moe.samples.notetaking.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditorActivity extends ActionBarActivity {

    private String action;
    private static EditText editor;
    private String noteFilter;
    private String oldText;
    private String DEFAULT_TEXT = "New Note";
    String note = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editor = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();

        note = intent.getStringExtra(DEFAULT_TEXT);
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
        }
        Intent intent = new Intent();
        intent.putExtra(DEFAULT_TEXT,newText);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishEditing();
    }
}
