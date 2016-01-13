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

package intel.com.sqlite3demo_android_database_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.intel.inde.moe.sqlite.DBManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static  final String TAG = "MainActivity";

    public  static  final String PEOPLE_INFO_ID_EXTRA = "peopleInfoID";

    private ListView listView ;
    private PeopleAdapter peopleListAdapter ;

    private List<List<String>> arrPeopleInfo;
    private DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find the ListView resource.
        listView = (ListView) findViewById( R.id.listView );

        peopleListAdapter = new PeopleAdapter(this, loadData());

        // Set the ArrayAdapter as the ListView's adapter.
        listView.setAdapter(peopleListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                People p = (People) listView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, EditInfoActivity.class);
                intent.putExtra(PEOPLE_INFO_ID_EXTRA, p.getPeopleInfoID());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "Start activity");

        peopleListAdapter.clear();
        peopleListAdapter.addAll(loadData());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_newRecord) {
            Intent intent = new Intent(this, EditInfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<People> loadData(){

        manager = new AndroidDBManagerFactory(getApplicationContext()).getDbManager();

        ArrayList<People> items = new ArrayList<People>();

        String query = manager.createSelectAllQuery();

        this.arrPeopleInfo = manager.loadDataFromDB(query);

        Iterator<List<String>> peopleInfoIterator = arrPeopleInfo.iterator();

        while (peopleInfoIterator.hasNext()) {
            List<String> info = peopleInfoIterator.next();
            items.add(new People(info.get(manager.getFirstnameIdx()), info.get(manager.getLastNameIdx()), Integer.valueOf(info.get(manager.getAgeIdx())), Integer.valueOf(info.get(manager.getPeopleInfoIDIdx()))));
        }

        return items;
    }
}
