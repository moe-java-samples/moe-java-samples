package com.example.database;



public interface ISQLiteDatabaseHelper {

	ISQLiteDatabase getWritableDatabase();

	void close();

}
