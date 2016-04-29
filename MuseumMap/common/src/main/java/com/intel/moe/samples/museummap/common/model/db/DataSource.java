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

package com.intel.moe.samples.museummap.common.model.db;

import com.intel.moe.samples.museummap.common.core.Utils;
import com.intel.moe.samples.museummap.common.database.ISQLiteCursor;
import com.intel.moe.samples.museummap.common.database.ISQLiteDatabase;
import com.intel.moe.samples.museummap.common.database.ISQLiteDatabaseHelper;
import com.intel.moe.samples.museummap.common.model.Museum;
import com.intel.moe.samples.museummap.common.model.entities.MuseumEntity;

import java.util.ArrayList;

public class DataSource {


    private ISQLiteDatabase db;
    private ISQLiteDatabaseHelper dbHelper;

    public DataSource(ISQLiteDatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
        Utils.executeSQLStatement(db, Utils
                .createTableSQL(MuseumEntity.TABLE_NAME, MuseumEntity.fields));
    }

	public void close(){
        dbHelper.close();
    }

    public void createMuseum(Museum museum){
        MuseumEntity.saveToDB(db, museum);
    }

    public void updateMuseum(Museum museum){
        MuseumEntity.updateToDB(db, museum);
    }

    public void deleteMuseum(int id){
        MuseumEntity.deleteFromDB(db, id);
    }

    public Museum getMuseumById(int id){
    	ISQLiteCursor cursor = MuseumEntity.selectFromDB(db, MuseumEntity.ID.getFieldName() + " = " + id);
    	if (cursor == null) {
    		return null;
    	}

        cursor.moveToFirst();
        Museum museum = null;
        if (!cursor.isAfterLast()) {
            museum = MuseumEntity.cursorToObject(cursor);
        }
        cursor.close();
        return museum;
    }

    public ArrayList<Museum> getAllMuseum(){
        ISQLiteCursor cursor = MuseumEntity.selectFromDB(db, null);
    	if (cursor == null) {
    		return null;
    	}

        ArrayList<Museum> museums = new ArrayList<Museum>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Museum museum = MuseumEntity.cursorToObject(cursor);
            museums.add(museum);
            cursor.moveToNext();
        }
        cursor.close();
        return museums;
    }

    public ArrayList<Museum> getMuseumsByAllParameters(String name, double lat, double lng){
        ArrayList<Museum> museums = new ArrayList<Museum>();
        ISQLiteCursor cursor;
        cursor = MuseumEntity.selectFromDB(db, "UPPER(" + MuseumEntity.NAME.getFieldName() + ") LIKE UPPER(\"%" + name.replace("\"", "") + "%\") and " +
                MuseumEntity.LATITUDE.getFieldName() + "=" + String.valueOf(lat) + " and " +
                MuseumEntity.LONGITUDE.getFieldName() + "=" + String.valueOf(lng));

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Museum museum = MuseumEntity.cursorToObject(cursor);
            museums.add(museum);
            cursor.moveToNext();
        }
        cursor.close();
        return museums;
    }
}
