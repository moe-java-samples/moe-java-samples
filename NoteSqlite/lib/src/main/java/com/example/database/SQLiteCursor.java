package com.example.database;

import com.lib.ui.c.Globals;

public class SQLiteCursor implements ISQLiteCursor {

	private SQLiteStatement stmt;

	private boolean isAfterLast = false;

	public SQLiteCursor(SQLiteStatement sqLiteStatement) {
		this.stmt = sqLiteStatement;
		moveToNext();
	}

	@Override
	public void moveToFirst() {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		stmt.reset();
		moveToNext();
	}

	@Override
	public void close() {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		stmt.close();
		stmt = null;
	}

	@Override
	public boolean isAfterLast() {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		return isAfterLast;
	}

	@Override
	public String getString(int i) {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		return Globals.sqlite3_column_text(stmt.getStmtHandle(), i);
	}

	@Override
	public int getInt(int i) {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		return Globals.sqlite3_column_int(stmt.getStmtHandle(), i);
	}

	@Override
	public long getLong(int i) {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		return Globals.sqlite3_column_int64(stmt.getStmtHandle(), i);
	}

	@Override
	public double getDouble(int i) {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		return Globals.sqlite3_column_double(stmt.getStmtHandle(), i);
	}

	@Override
	public void moveToNext() {
		if (stmt == null) {
			throw new RuntimeException("statement is closed");
		}
		isAfterLast = !stmt.step();
	}

}
