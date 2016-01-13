package com.example.database;


import com.example.Note;

import java.util.PriorityQueue;

public interface ISQLiteDatabase {

	Note getNoteById(int id);

	PriorityQueue<Note> getAllNotes();

	void delete(int note);

	void insert(String note);

	void execSQL(String statement);

	void update(Note note);

	ISQLiteCursor rawQuery(String sql, String[] selectionArgs);

}
