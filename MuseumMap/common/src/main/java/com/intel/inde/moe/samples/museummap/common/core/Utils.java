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

package com.intel.inde.moe.samples.museummap.common.core;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteContentValues;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteCursor;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteDatabase;
import com.intel.inde.moe.samples.museummap.common.model.Museum;
import com.intel.inde.moe.samples.museummap.common.model.db.DataBaseField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Utils {

    public static String getDateStringInDateFormat(Date date){
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MM/dd/yy kk:mm");
        return format.format(date);
    }

    public static String getDateStringInTimeFormat(Date date){
        SimpleDateFormat format = new SimpleDateFormat("kk:mm");
        return format.format(date);
    }

    public static void executeSQLStatement(ISQLiteDatabase db, String statement) {
        db.execSQL(statement);
    }

    public static String createTableSQL(String tableName, DataBaseField[] fields){
        String statement = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
        for(int i=0; i<fields.length; i++){
            statement += fields[i].getFieldName() + " " + fields[i].getFieldType();
            if(fields[i].isPrimaryKey()){
                statement += " PRIMARY KEY";
            }
            if(fields[i].isAutoIncrement()){
                statement += " AUTOINCREMENT";
            }
            if(fields[i].isNotNull()){
                statement += " NOT NULL";
            }
            if(i != fields.length-1){
                statement += ", ";
            }
        }
        statement += ");";
        return statement;
    }

    public static String createClauseWhereFieldEqualsValue(DataBaseField field, Object value){
        String clause = field.getFieldName() + "=" + value;
        return clause;
    }

    public static String dropTableIfExistsSQL(String tableName){
        String statement = "DROP TABLE IF EXISTS " + tableName + ";";
        return statement;
    }

    public static void insertObject(ISQLiteDatabase db, String tableName, ISQLiteContentValues values){
        db.insert(tableName, null, values);
    }

    public static void deleteObject(ISQLiteDatabase db, String tableName, String whereClause){
        db.delete(tableName, whereClause, null);
    }

    public static void clearObjects(ISQLiteDatabase db, String tableName){
        db.delete(tableName, null, null);
    }

    public static ISQLiteCursor getAllObjects(ISQLiteDatabase db,String tableName, DataBaseField[] fields){
        return getAllObjectsOrderedByParam(db, tableName, fields, null);
    }

    public static ISQLiteCursor getAllObjectsOrderedByParam(ISQLiteDatabase db, String tableName, DataBaseField[] fields, String orderedBy){
        String[] fieldNames = new String[fields.length];
        for(int i=0; i<fields.length; i++){
            fieldNames[i] = fields[i].getFieldName();
        }
        return db.query(tableName, fieldNames, null, null, null, null, orderedBy);
    }
    
    public static List<Museum> get(InputStream inputStream) {
        final Gson gson = new Gson();
		ArrayList<Museum> museums = new ArrayList<>();

        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Map<String, Object> result = (Map<String, Object>) gson.fromJson(
                reader, Map.class);
        ArrayList listMuseum = (ArrayList) result.get("results");
        if (listMuseum != null) {
            for (Object point : listMuseum) {
                LinkedTreeMap node = (LinkedTreeMap) point;
                if (node == null) break;
                String name = (String) node.get("name");
                if (name == null)
                    name = "Unknown";
                node = (LinkedTreeMap) node.get("geometry");
                if (node == null) break;
                node = (LinkedTreeMap) node.get("location");
                if (node == null) break;
                double lat = (double) node.get("lat");
                double lng = (double) node.get("lng");
                museums.add(new com.intel.inde.moe.samples.museummap.common.model.Museum(name, lat, lng));
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return museums;
	}
}
