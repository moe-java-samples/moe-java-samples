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

import com.intel.moe.samples.museummap.common.database.ISQLiteCursor;

import org.sqlite.c.Globals;

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
