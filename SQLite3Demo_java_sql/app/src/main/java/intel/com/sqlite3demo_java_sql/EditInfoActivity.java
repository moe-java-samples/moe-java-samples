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
package intel.com.sqlite3demo_java_sql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.intel.inde.moe.sqlite.DBManager;

import java.util.List;

public class EditInfoActivity extends AppCompatActivity {

    private static String TAG = "EditInfoActivity";

    private Button save, delete;
    private EditText firstNameEdit, lastNameEdit, ageEdit;
    private int peopleInfoID;
    private DBManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        manager = new AndroidDBManagerFactory(getApplicationContext()).getDbManager();

        firstNameEdit = (EditText) findViewById(R.id.firstNameText);
        lastNameEdit = (EditText) findViewById(R.id.lastNameText);
        ageEdit = (EditText) findViewById(R.id.ageText);

        save = (Button) findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        delete = (Button) findViewById(R.id.deleteButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecord();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        this.peopleInfoID = intent.getIntExtra(MainActivity.PEOPLE_INFO_ID_EXTRA, -1);

        loadData();

        if (peopleInfoID != -1) {
            delete.setEnabled(true);
        } else {
            delete.setEnabled(false);
        }

    }

    private void saveData() {

        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String age = ageEdit.getText().toString();

        String query;


        try {
            if (peopleInfoID == -1) {
                query = manager.createNewRecordQuery(firstName, lastName, Integer.valueOf(age));
            } else {
                query = manager.createUpdateQuery(firstName, lastName, Integer.valueOf(age), peopleInfoID);
            }
        } catch(NumberFormatException ex){
            Log.d(TAG, "Age is not number!");
            return;
        }

        manager.executeQuery(query);

        // If the query was successfully executed then pop the activity.
        if (manager.affectedRows != 0) {
            Log.d(TAG, "Query was executed successfully. Affected rows = " + manager.affectedRows);

            finish();
        }
        else{
            Log.d(TAG, "Could not execute the query.");
        }
    }

    private void deleteRecord() {

        // Prepare the query.
        String query = manager.createDeleteQuery(peopleInfoID);

        // Execute the query.
        manager.executeQuery(query);

        finish();
    }

    private void loadData() {
        if (peopleInfoID != -1) {
            String query = manager.createSelectRecordWithId(peopleInfoID);

            List<List<String>> result =  manager.loadDataFromDB(query);

            List<String> peopleInfo = result.get(0);

            firstNameEdit.setText(peopleInfo.get(manager.getFirstnameIdx()));
            lastNameEdit.setText(peopleInfo.get(manager.getLastNameIdx()));
            ageEdit.setText(peopleInfo.get(manager.getAgeIdx()));

        } else {
            Log.d(TAG, "New record");
        }
    }
}
