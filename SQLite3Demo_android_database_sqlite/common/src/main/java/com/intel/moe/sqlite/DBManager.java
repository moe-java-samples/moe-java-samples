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
package com.intel.moe.sqlite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.*;
import  android.database.Cursor;

public class DBManager {

    public static  final String TAG = "DBManager";

    public static final String DB_NAME = "sampledb.sql";

    public List<String> arrColumnNames;

    public List<List<String>> arrResults;

    public int affectedRows;

    public long lastInsertedRowID;

    private File dbFile;

    private AbstractDBManagerFactory factory;

    private DBManager() {}

    public DBManager(AbstractDBManagerFactory factory) {

        this.factory = factory;

        dbFile = factory.copyDatabaseIntoDocumentsDirectory();
    }

    private void runQuery(String query, boolean queryExecutable) {

        // Initialize the results array.
        if (arrResults != null) {
            arrResults.clear();
        } else {
            this.arrResults = new ArrayList<List<String>>();
        }

        // Initialize the column names array.
        if (arrColumnNames != null) {
            arrColumnNames.clear();
        } else {
            this.arrColumnNames = new ArrayList<String>();
        }

        String dbpathwithname = dbFile.getPath();

        SQLiteDatabase sd = SQLiteDatabase.openDatabase(dbpathwithname, null, SQLiteDatabase.OPEN_READWRITE);
        if(!queryExecutable){
            Cursor c = sd.rawQuery(query, null);
            List<String> arrDataRow;
            if(c.moveToFirst()) {
                do {
                    arrDataRow = new ArrayList<String>();
                    int totalColumns = c.getColumnCount();
                    for(int i=0; i<totalColumns;i++){
                        String dbDataAsChars = c.getString(i);
                        if (dbDataAsChars != null) {
                            arrDataRow.add(dbDataAsChars);
                        }

                        // Keep the current column name.
                        if (this.arrColumnNames.size() != totalColumns) {
                            dbDataAsChars = c.getColumnName(i);
                            arrColumnNames.add(dbDataAsChars);
                        }
                    }
                    if (arrDataRow.size() > 0) {
                        arrResults.add(arrDataRow);
                    }
                } while (c.moveToNext());
            }
        } else {
            // This is the case of an executable query (insert, update, ...).

            sd.execSQL(query);
            
            this.affectedRows = 1;
            this.lastInsertedRowID = 1;
        }
    }

    public List loadDataFromDB(String query) {
        runQuery(query, false);
        return arrResults;
    }

    public void executeQuery(String query) {
        runQuery(query, true);
    }

    public String createUpdateQuery(String firstName, String lastName, int age, int peopleInfoID) {

        StringBuffer sb = new StringBuffer();
        sb.append("update peopleInfo set ");
        sb.append("firstname=");
        sb.append("'");
        sb.append(firstName);
        sb.append("'");
        sb.append(",");
        sb.append("lastname=");
        sb.append("'");
        sb.append(lastName);
        sb.append("'");
        sb.append(",");
        sb.append("age=");
        sb.append(age);
        sb.append(" where peopleInfoID=");
        sb.append(peopleInfoID);

        return sb.toString();
    }

    public String createNewRecordQuery(String firstName, String lastName, int age) {

        StringBuffer sb = new StringBuffer();
        sb.append("insert into peopleInfo values(");
        sb.append("null");
        sb.append(",");
        sb.append("'");
        sb.append(firstName);
        sb.append("'");
        sb.append(",");
        sb.append("'");
        sb.append(lastName);
        sb.append("'");
        sb.append(",");
        sb.append(age);
        sb.append(")");

        return sb.toString();
    }

    public String createDeleteQuery(int id) {
        //[NSString stringWithFormat:@"delete from peopleInfo where peopleInfoID=%d", recordIDToDelete];
        StringBuffer sb = new StringBuffer();
        sb.append("delete from peopleInfo where peopleInfoID=");
        sb.append(id);

        return sb.toString();
    }

    public String createSelectAllQuery() {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from peopleInfo");

        return sb.toString();
    }

    public String createSelectRecordWithId(int id) {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from peopleInfo where peopleInfoID=");
        sb.append(id);

        return sb.toString();
    }

    public int getPeopleInfoIDIdx() {
        return arrColumnNames.indexOf("peopleInfoID");
    }

    public int getFirstnameIdx() {
        return  arrColumnNames.indexOf("firstname");
    }

    public int getLastNameIdx() {
        return arrColumnNames.indexOf("lastname");
    }

    public int getAgeIdx() {
        return  arrColumnNames.indexOf("age");
    }
}
