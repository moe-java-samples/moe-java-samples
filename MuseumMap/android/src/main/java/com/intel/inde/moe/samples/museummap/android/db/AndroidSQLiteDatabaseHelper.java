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

package com.intel.inde.moe.samples.museummap.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.intel.inde.moe.samples.museummap.common.core.Utils;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteDatabase;
import com.intel.inde.moe.samples.museummap.common.database.ISQLiteDatabaseHelper;
import com.intel.inde.moe.samples.museummap.common.model.entities.MuseumEntity;

import java.io.InputStream;

public class AndroidSQLiteDatabaseHelper implements ISQLiteDatabaseHelper {

	AndroidSQLiteDatabase db;
	
	private class DBOpenHelper extends SQLiteOpenHelper {

		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase sqlDb) {
			if (db == null) {
				db = new AndroidSQLiteDatabase(sqlDb);
			}
			Utils.executeSQLStatement(db, Utils
					.createTableSQL(MuseumEntity.TABLE_NAME,
							MuseumEntity.fields));
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqlDb, int oldVersion, int newVersion) {
			if (db == null) {
				db = new AndroidSQLiteDatabase(sqlDb);
			}
			Utils.executeSQLStatement(db, Utils
					.dropTableIfExistsSQL(MuseumEntity.TABLE_NAME));
	        onCreate(sqlDb);
		}
		
	}
	
	DBOpenHelper dbH;
	
	public AndroidSQLiteDatabaseHelper(Context ctx, String dbName) {
		dbH = new DBOpenHelper(ctx, dbName, null, 2);
	}

	@Override
	public void close() {
		dbH.close();
		db = null;
	}

	@Override
	public ISQLiteDatabase getWritableDatabase() {
		if (db == null) {
			db = new AndroidSQLiteDatabase(dbH.getWritableDatabase());
		}
		return db;
	}

	@Override
	public InputStream getDefaultDatabaseContents() {
		// TODO Auto-generated method stub
		return null;
	}
}
