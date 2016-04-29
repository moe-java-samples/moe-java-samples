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

package com.intel.moe.samples.museummap.android.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.intel.moe.samples.museummap.common.database.ISQLiteContentValues;
import com.intel.moe.samples.museummap.common.database.ISQLiteCursor;
import com.intel.moe.samples.museummap.common.database.ISQLiteDatabase;

public class AndroidSQLiteDatabase implements ISQLiteDatabase {

	private SQLiteDatabase db;

	public AndroidSQLiteDatabase(SQLiteDatabase db) {
		if (db == null) {
			throw new IllegalArgumentException("dbHandle can't be null");
		}
		this.db = db;
	}

	@Override
	public ISQLiteContentValues newContentValues() {
		return new AndroidContentValues();
	}

	@Override
	public ISQLiteCursor query(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		if (cursor == null) {
			return null;
		}
		return new AndroidCursor(cursor);
	}

	@Override
	public int delete(String table, String whereClause, String[] whereArgs) {
		return db.delete(table, whereClause, whereArgs);
	}

	@Override
	public long insert(String table, String nullColumnHack,
			ISQLiteContentValues initialValues) {
		ContentValues values = convertFromValues(initialValues);
		return db.insert(table, nullColumnHack, values);
	}

	private ContentValues convertFromValues(ISQLiteContentValues initialValues) {
		return ((AndroidContentValues)initialValues).getContentValues();
	}

	@Override
	public void update(String tableName, ISQLiteContentValues values,
			String whereClause, String[] whereArgs) {
        if (values == null || values.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        
        StringBuilder sql = new StringBuilder(120);
        sql.append("UPDATE ");
        sql.append(tableName);
        sql.append(" SET ");

        // move all bind args to one array
        int setValuesSize = values.size();
        int bindArgsSize = (whereArgs == null) ? setValuesSize : (setValuesSize + whereArgs.length);
        Object[] bindArgs = new Object[bindArgsSize];
        int i = 0;
        for (String colName : values.keySet()) {
            sql.append((i > 0) ? "," : "");
            sql.append(colName);
            bindArgs[i++] = values.get(colName);
            sql.append("=?");
        }
        if (whereArgs != null) {
            for (i = setValuesSize; i < bindArgsSize; i++) {
                bindArgs[i] = whereArgs[i - setValuesSize];
            }
        }
        if (!TextUtils.isEmpty(whereClause)) {
            sql.append(" WHERE ");
            sql.append(whereClause);
        }
        
        SQLiteStatement stmt = db.compileStatement(sql.toString());
        int idx = 0;
        for (Object bind : bindArgs) {
            idx ++;
            if (bind instanceof String) {
            	stmt.bindString(idx, (String) bind);
            } else if (bind instanceof Integer) {
            	stmt.bindLong(idx, (Integer) bind);
	        } else if (bind instanceof Long) {
	        	stmt.bindLong(idx, (Long) bind);
	        }
        }
        stmt.executeUpdateDelete();
	}

	@Override
	public void execSQL(String statement) {
		db.execSQL(statement);
	}

	@Override
	public ISQLiteCursor rawQuery(String sql, String[] selectionArgs) {
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		return cursor == null ? null : new AndroidCursor(cursor);
	}
}
