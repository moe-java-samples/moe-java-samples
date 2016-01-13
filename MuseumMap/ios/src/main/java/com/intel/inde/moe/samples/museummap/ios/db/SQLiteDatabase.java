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

package com.intel.inde.moe.samples.museummap.ios.db;

import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteContentValues;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteCursor;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteDatabase;

public class SQLiteDatabase implements ISQLiteDatabase {

	private final VoidPtr dbHandle;

	public SQLiteDatabase(VoidPtr dbHandle) {
		if (dbHandle == null) {
			throw new NullPointerException("dbHandle can't be null");
		}
		this.dbHandle = dbHandle;
	}

	@Override
	public ISQLiteContentValues newContentValues() {
		return new SQLiteContentValues();
	}

	@Override
	public ISQLiteCursor query(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		if (selectionArgs != null || groupBy != null || having != null || orderBy != null) {
			throw new RuntimeException("Unimplemented");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		if (columns == null || columns.length == 0) {
			sql.append("*");
		} else {
			for (String val : columns) {
				sql.append(val);
				sql.append(",");
			}
			sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(" FROM ");
		sql.append(table);
		if (!TextUtils.isEmpty(selection)) {
			sql.append(" WHERE (");
			sql.append(selection);
			sql.append(")");
		}

		SQLiteStatement stmt = new SQLiteStatement(sql.toString(), null);
		if (stmt.prepare(dbHandle)) {
			return stmt.query();
		} else {
			System.err.println("Error querying - " + stmt.getLastError());
			System.err.println("\tin: " + stmt.getStatement());
		}
		return null;
	}

	@Override
	public ISQLiteCursor rawQuery(String sql, String[] selectionArgs) {
		SQLiteStatement stmt = new SQLiteStatement(sql, null);
		if (stmt.prepare(dbHandle)) {
			return stmt.query();
		} else {
			System.err.println("Error querying - " + stmt.getLastError());
			System.err.println("\tin: " + stmt.getStatement());
		}
		return null;
	}

	@Override
	public int delete(String table, String whereClause, String[] whereArgs) {
		if (whereArgs != null) {
			throw new RuntimeException("Unimplemented");
		}

		SQLiteStatement stmt = new SQLiteStatement("DELETE FROM "
				+ table
				+ (!TextUtils.isEmpty(whereClause) ? " WHERE " + whereClause
						: ""), null);
		int affected = 0;
		if (stmt.prepare(dbHandle)) {
			if (!stmt.exec()) {
				System.err.println("Error deleting - " + stmt.getLastError());
			} else {
				affected = stmt.getAffectedCount();
			}
		} else {
			System.err.println("Error deleting - " + stmt.getLastError());
			System.err.println("\tin: " + stmt.getStatement());
		}
		return affected;
	}

	@Override
	public long insert(String table, String nullColumnHack,
			ISQLiteContentValues initialValues) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT");
		sql.append(" INTO ");
		sql.append(table);
		sql.append('(');

		Object[] bindArgs = null;
		int size = (initialValues != null && initialValues.size() > 0) ? initialValues
				.size() : 0;
		if (size > 0) {
			bindArgs = new Object[size];
			int i = 0;
			for (String colName : initialValues.keySet()) {
				sql.append((i > 0) ? "," : "");
				sql.append(colName);
				bindArgs[i++] = initialValues.get(colName);
			}
			sql.append(')');
			sql.append(" VALUES (");
			for (i = 0; i < size; i++) {
				sql.append((i > 0) ? ",?" : "?");
			}
		} else {
			sql.append(nullColumnHack + ") VALUES (NULL");
		}
		sql.append(')');

		SQLiteStatement stmt = new SQLiteStatement(sql.toString(), bindArgs);
		long lastInsertedID = -1L;
		if (stmt.prepare(dbHandle)) {
			if (!stmt.exec()) {
				System.err.println("Error inserting - " + stmt.getLastError());
			} else {
				lastInsertedID = stmt.getLastInsertedID();
			}
		} else {
			System.err.println("Error inserting - " + stmt.getLastError());
			System.err.println("\tin: " + stmt.getStatement());
		}
		return lastInsertedID;
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
        
        SQLiteStatement stmt = new SQLiteStatement(sql.toString(), bindArgs);
		if (stmt.prepare(dbHandle)) {
			if (!stmt.exec()) {
				System.err.println("Error updating - " + stmt.getLastError());
			}
		} else {
			System.err.println("Error updating - " + stmt.getLastError());
			System.err.println("\tin: " + stmt.getStatement());
		}
	}

	@Override
	public void execSQL(String statement) {
		SQLiteStatement stmt = new SQLiteStatement(statement, null);
		if (stmt.prepare(dbHandle)) {
			if (!stmt.exec()) {
				System.err.println("Error executing - " + stmt.getLastError());
			}
		} else {
			System.err.println("Error executing - " + stmt.getLastError());
			System.err.println("\tin: " + stmt.getStatement());
		}
	}
}
