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

package com.intel.moe.samples.museummap.common.model.entities;

import com.intel.moe.samples.museummap.common.core.Utils;
import com.intel.moe.samples.museummap.common.database.ISQLiteContentValues;
import com.intel.moe.samples.museummap.common.database.ISQLiteCursor;
import com.intel.moe.samples.museummap.common.database.ISQLiteDatabase;
import com.intel.moe.samples.museummap.common.model.Museum;
import com.intel.moe.samples.museummap.common.model.db.DataBaseField;

public class MuseumEntity {

    public static final String TABLE_NAME = "MUSEUMS";

    private static final String SQLITE_TYPE_TEXT = "TEXT";
    private static final String SQLITE_TYPE_DOUBLE = "DOUBLE";
    private static final String SQLITE_TYPE_INTEGER = "INTEGER";

    public static final DataBaseField ID = new DataBaseField("ID", SQLITE_TYPE_INTEGER, true, true);
    public static final DataBaseField NAME = new DataBaseField("NAME", SQLITE_TYPE_TEXT);
    public static final DataBaseField LATITUDE = new DataBaseField("LATITUDE", SQLITE_TYPE_DOUBLE);
    public static final DataBaseField LONGITUDE = new DataBaseField("LONGITUDE", SQLITE_TYPE_DOUBLE);

    public static final DataBaseField[] fields = {ID, NAME, LATITUDE, LONGITUDE};
    public static final String[] fieldNames = {ID.getFieldName(), NAME.getFieldName(), LATITUDE.getFieldName(), LONGITUDE.getFieldName()};

    public static void saveToDB(ISQLiteDatabase db, Museum museum){
        ISQLiteContentValues values = db.newContentValues();
        if (museum.getId() != -1) {
            values.put(MuseumEntity.ID.getFieldName(), museum.getId());
        }
        values.put(MuseumEntity.NAME.getFieldName(), museum.getName());
        values.put(MuseumEntity.LATITUDE.getFieldName(), museum.getLatitude());
        values.put(MuseumEntity.LONGITUDE.getFieldName(), museum.getLongitude());
        museum.setId((int) db.insert(TABLE_NAME, null, values));
    }

    public static void updateToDB(ISQLiteDatabase db, Museum museum){
        ISQLiteContentValues values = db.newContentValues();
        values.put(MuseumEntity.NAME.getFieldName(), museum.getName());
        values.put(MuseumEntity.LATITUDE.getFieldName(), museum.getLatitude());
        values.put(MuseumEntity.LONGITUDE.getFieldName(), museum.getLongitude());
        db.update(TABLE_NAME, values, Utils.createClauseWhereFieldEqualsValue(ID, museum.getId()), null);
    }

    public static void deleteFromDB(ISQLiteDatabase db, int id){
        db.delete(TABLE_NAME, Utils.createClauseWhereFieldEqualsValue(ID, id), null);
    }

    public static ISQLiteCursor selectFromDB(ISQLiteDatabase db, String selection){
        ISQLiteCursor cursor = db.query(TABLE_NAME, fieldNames, selection, null, null, null, null);
        return cursor;
    }

    public static ISQLiteCursor  selectMaxIDFromDB(ISQLiteDatabase db) {
        String column = ID.getFieldName();
        String selection = "SELECT max(" + column + ") FROM " + TABLE_NAME;
        ISQLiteCursor cursor = db.rawQuery(selection, null);
        return cursor;
    }

    public static Museum cursorToObject(ISQLiteCursor cursor){
        Museum museum = new Museum();
        museum.setId(cursor.getInt(0));
        museum.setName(cursor.getString(1));
        museum.setLatitude(cursor.getDouble(2));
        museum.setLongitude(cursor.getDouble(3));
        return museum;
    }

}
