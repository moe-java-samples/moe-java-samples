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

package com.intel.moe.samples.museummap.ios.db;

import com.intel.moe.natj.general.ptr.Ptr;
import com.intel.moe.natj.general.ptr.VoidPtr;
import com.intel.moe.natj.general.ptr.impl.PtrFactory;
import com.intel.moe.samples.museummap.common.core.Utils;
import com.intel.moe.samples.museummap.common.database.ISQLiteDatabase;
import com.intel.moe.samples.museummap.common.database.ISQLiteDatabaseHelper;
import com.intel.moe.samples.museummap.common.model.entities.MuseumEntity;

import org.sqlite.c.Globals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ios.foundation.NSArray;
import ios.foundation.c.Foundation;
import ios.foundation.enums.NSSearchPathDirectory;
import ios.foundation.enums.NSSearchPathDomainMask;

public class SQLiteDatabaseHelper implements ISQLiteDatabaseHelper {

	private final String databaseFile;

	private VoidPtr connectionHandle = null;

	public SQLiteDatabaseHelper(String databaseFile) {
		this.databaseFile = databaseFile;
		try {
			init();
		} catch (Exception e) {
			connectionHandle = null;
		}
	}

	private void init() throws IOException {
		// Get path to database
		String docPath = getDocumentsPath();
		if (docPath == null) {
			System.err.println("Failed to load app's document path");
			return;
		}
		File file = new File(docPath, databaseFile);

		// Check existence
		boolean isNew = !file.exists();

		// Open database
		@SuppressWarnings("unchecked")
		Ptr<VoidPtr> dbHandleRef = (Ptr<VoidPtr>) PtrFactory.newPointerPtr(Void.class, 2, 1, true, false);
		if (Globals.sqlite3_open(file.getCanonicalPath(), dbHandleRef) != 0) {
			throw new IOException("Failed to open/create database file");
		}
		connectionHandle = dbHandleRef.get();
		
		// Initialize
		if (isNew) {
			onCreate(getWritableDatabase());
		} else {
			// onUpdate(getWritableDatabase());
		}
	}

	private void onCreate(ISQLiteDatabase sqLiteDatabase) {
		Utils.executeSQLStatement(sqLiteDatabase, Utils
				.createTableSQL(MuseumEntity.TABLE_NAME,
						MuseumEntity.fields));
	}

	@SuppressWarnings("unused")
	private void onUpdate(ISQLiteDatabase sqLiteDatabase) {
		Utils.executeSQLStatement(sqLiteDatabase, Utils
				.dropTableIfExistsSQL(MuseumEntity.TABLE_NAME));
		onCreate(sqLiteDatabase);
	}

	private String getDocumentsPath() {
		NSArray paths = Foundation.NSSearchPathForDirectoriesInDomains(
				NSSearchPathDirectory.DocumentDirectory,
				NSSearchPathDomainMask.UserDomainMask, true);
		return (String) paths.firstObject();
	}

	@Override
	public ISQLiteDatabase getWritableDatabase() {
		return new SQLiteDatabase(connectionHandle);
	}

	@Override
	public void close() {
		if (connectionHandle != null) {
			Globals.sqlite3_close(connectionHandle);
			connectionHandle = null;
		}
	}

	@Override
	public InputStream getDefaultDatabaseContents() {
		return null;
	}

}
