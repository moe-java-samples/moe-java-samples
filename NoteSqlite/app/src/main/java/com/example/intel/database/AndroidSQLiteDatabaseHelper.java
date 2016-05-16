package com.example.intel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;


import com.example.database.ISQLiteDatabase;
import com.example.database.ISQLiteDatabaseHelper;
import com.example.database.SQLiteDatabaseHelper;

import java.io.InputStream;

public class AndroidSQLiteDatabaseHelper extends SQLiteDatabaseHelper {

	public static final String DB_NAME = "notes.db";


	public AndroidSQLiteDatabaseHelper(String databaseFile) {
		super(databaseFile);
	}

	@Override
	protected String getDocumentsPath() {
		return Environment.getExternalStorageDirectory().getPath();
	}
}
