package com.example.database;

//import com.intel.carhunt.common.Logger;
//import com.intel.carhunt.common.Utils;
//import com.intel.carhunt.model.entities.PlateEntity;

import com.intel.moe.natj.general.ptr.Ptr;
import com.intel.moe.natj.general.ptr.VoidPtr;
import com.intel.moe.natj.general.ptr.impl.PtrFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.lib.ui.c.Globals;

public abstract class SQLiteDatabaseHelper implements ISQLiteDatabaseHelper {

	//private static final Logger LOG = Logger.get(SQLiteDatabaseHelper.class, false);

	private final String databaseFile;

	private VoidPtr connectionHandle;

	public SQLiteDatabaseHelper(String databaseFile) {
		this.databaseFile = databaseFile;
		try {
			init();
		} catch (Exception e) {
			System.out.println("Failed to init database" + e);
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
		System.out.println("Opened db at " + file.getCanonicalPath());
	//	LOG.debug("Opened db at " + file.getCanonicalPath());
		connectionHandle = dbHandleRef.get();

		if (isNew) {
			onCreate(getWritableDatabase());
		} else {
			// onUpdate(getWritableDatabase());
		}
	}

	private void onCreate(ISQLiteDatabase sqLiteDatabase) {
		String query = "Create table if not exists notes(id integer primary key autoincrement, note text)";
		sqLiteDatabase.execSQL(query);
	}

	protected abstract String getDocumentsPath();

	@Override
	public ISQLiteDatabase getWritableDatabase() {
		return new SQLiteDatabase(connectionHandle);
	}

	@Override
	public synchronized void close() {
		if (connectionHandle != null) {
			Globals.sqlite3_close(connectionHandle);
			connectionHandle = null;
		}
	}


}
