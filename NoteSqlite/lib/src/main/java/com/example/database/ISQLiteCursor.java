package com.example.database;

public interface ISQLiteCursor {

	void moveToFirst();

	void close();

	boolean isAfterLast();

	String getString(int i);

	int getInt(int i);

	long getLong(int i);

	void moveToNext();

	double getDouble(int i);

}
