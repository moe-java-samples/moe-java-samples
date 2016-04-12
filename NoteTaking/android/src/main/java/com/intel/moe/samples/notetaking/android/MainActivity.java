package com.intel.moe.samples.notetaking.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.intel.moe.samples.notetaking.common.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Data data;
    ArrayList<String> objects;
    private String DEFAULT_TEXT = "New Note";
    private static final int EDITOR_REQUEST_CODE = 1001;
    private ListAdapter listAdapter;
    private static String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new Data(baseDir);
        objects = new ArrayList<>();
        fillObjectArray();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        ListView list = (ListView) findViewById(android.R.id.list);
        listAdapter = new ListAdapter(this,objects,data);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        data.setCurrentKey(objects.get(position));
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);

        intent.putExtra(DEFAULT_TEXT, data.getAllNotes().get(data.getCurrentKey()));
        startActivityForResult(intent, EDITOR_REQUEST_CODE);
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
        data.setCurrentKey(date);
        data.setNoteForCurrentKey(DEFAULT_TEXT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent note) {
        if (requestCode == EDITOR_REQUEST_CODE && resultCode == RESULT_OK) {

                String stredittext=note.getStringExtra(DEFAULT_TEXT);
                if(stredittext.equals(DEFAULT_TEXT)){
                    data.removeCurrentNote();
                }else {
                    data.setNoteForCurrentKey(stredittext);
                }
                fillObjectArray();
                listAdapter.notifyDataSetChanged();
                data.saveFile();
        }
    }


}