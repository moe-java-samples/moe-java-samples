package com.example.database;

public class TextUtils {

	public static boolean isEmpty(String whereClause) {
		if(whereClause == null) {
			return true;
		}
		return whereClause.trim().length() == 0;
	}

}
