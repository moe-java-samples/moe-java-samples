package com.example.database;

import com.example.Note;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;

import java.util.Collections;
import java.util.PriorityQueue;

public class SQLiteDatabase implements ISQLiteDatabase {

	private final VoidPtr dbHandle;
	private final String TABLE = "notes";

	public SQLiteDatabase(VoidPtr dbHandle) {
		if (dbHandle == null) {
			throw new NullPointerException("dbHandle can't be null");
		}
		this.dbHandle = dbHandle;
	}


	@Override
	public Note getNoteById(int id) {
		StringBuilder query = new StringBuilder("SELECT");
		query.append("* FROM ");
		query.append(TABLE);
		query.append(" WHERE id = ");
		query.append(getStringFormat(id));
		ISQLiteCursor cursor = rawQuery(query.toString(),null);
		if (cursor == null) {
			return null;
		}

		cursor.moveToFirst();
		Note note = new Note();
		if (!cursor.isAfterLast()) {
			note.setId(cursor.getInt(0));
			note.setNote(cursor.getString(1));
		}
		cursor.close();
		return note;
	}

	public PriorityQueue<Note> getAllNotes(){
		StringBuilder query = new StringBuilder("SELECT ");
		query.append("* ");
		query.append("FROM ");
		query.append(TABLE);

		ISQLiteCursor cursor = rawQuery(query.toString(), null);//PlateEntity.selectFromDB(db, null);

		if (cursor == null) {
			return null;
		}

		PriorityQueue<Note> notes = new PriorityQueue<Note>(1, Collections.reverseOrder());
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Note note = cursorToObject(cursor);
			notes.offer(note);
			cursor.moveToNext();
		}
		cursor.close();
		return notes;
	}

	@Override
	public ISQLiteCursor rawQuery(String sql, String[] selectionArgs) {
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
	public void delete(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		sql.append(TABLE);
		sql.append(" WHERE id = ");
		sql.append(id);
		execSQL(sql.toString());
	}

	@Override
	public void insert(String note) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT");
		sql.append(" INTO ");
		sql.append(TABLE);
		sql.append(" (note) values (");
		sql.append("\"");
		sql.append(note);
		sql.append("\")");
		//sql.append("\"");
		execSQL(sql.toString());
	}

	@Override
	public void update(Note note) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE);
		query.append(" SET note = ");
		query.append(getStringFormat(note.getNote()));
		query.append(" WHERE id = ");
		query.append(getStringFormat(note.getId()));
		execSQL(query.toString());
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

	public String getStringFormat(Object o){
		return ("\"" + o.toString() + "\"");
	}

	public Note cursorToObject(ISQLiteCursor cursor){
		Note note = new Note();
		note.setId(cursor.getInt(0));
		note.setNote(cursor.getString(1));
		return note;
	}
}
