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

package com.intel.inde.moe.samples.museummap.ios.db;

import com.intel.inde.moe.natj.general.ptr.Ptr;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.intel.inde.moe.natj.general.ptr.impl.PtrFactory;

import org.sqlite.c.Globals;

public class SQLiteStatement {
	
	private final String statement;

	private final Object[] bindArgs;

	private VoidPtr stmtHandle;

	private VoidPtr dbHandle;

	private String lastError;

	private int affectedCount = 0;

	private long lastInsertedID = -1;
	
	VoidPtr getStmtHandle() {
		return stmtHandle;
	}

	VoidPtr getDbHandle() {
		return dbHandle;
	}

	String getLastError() {
		return lastError;
	}

	public SQLiteStatement(String statement, Object[] bindArgs) {
		if (statement == null) {
			throw new NullPointerException();
		}
		this.statement = statement;
		this.bindArgs = bindArgs == null ? new Object[0] : bindArgs;
	}

	public boolean prepare(VoidPtr dbHandle) {
		if (dbHandle == null) {
			throw new NullPointerException();
		}
		this.dbHandle = dbHandle;

		@SuppressWarnings("unchecked")
		Ptr<VoidPtr> stmtRef = (Ptr<VoidPtr>) PtrFactory.newPointerPtr(
				Void.class, 2, 1, true, false);
		int err = Globals.sqlite3_prepare_v2(dbHandle, statement, -1, stmtRef,
				null);
		if (err != 0) {
			lastError = Globals.sqlite3_errmsg(dbHandle);
			return false;
		}
		stmtHandle = stmtRef.get();
		int idx = 0;
		for (Object bind : bindArgs) {
			idx++;
			if (bind instanceof String) {
				err = Globals.sqlite3_bind_text(stmtHandle, idx, (String) bind, -1, new Globals.Function_sqlite3_bind_text() {
					@Override
					public void call_sqlite3_bind_text(VoidPtr arg0) {

					}
				});
			} else if (bind instanceof Integer) {
				err = Globals.sqlite3_bind_int(stmtHandle, idx,  (Integer) bind);
			} else if (bind instanceof Long) {
				err = Globals.sqlite3_bind_int64(stmtHandle, idx,  (Long) bind);
			} else if (bind instanceof Double) {
				err = Globals.sqlite3_bind_double(stmtHandle, idx,  (Double) bind);
			} else if (bind == null) {
				err = Globals.sqlite3_bind_null(stmtHandle, idx);
			} else {
				lastError = "No implemented SQLite3 bind function found for " + bind.getClass().getName();
				return false;
			}
			if (err != 0) {
				lastError = Globals.sqlite3_errmsg(dbHandle);
				return false;
			}
		}
		return true;
	}
	
	public boolean exec() {
		if (stmtHandle == null) {
			throw new RuntimeException("statement handle is closed");
		}
		int err = Globals.sqlite3_step(stmtHandle);
		if (err == 101 /* SQLITE_DONE */) {
			affectedCount = Globals.sqlite3_changes(dbHandle);
			lastInsertedID = Globals.sqlite3_last_insert_rowid(dbHandle);
		}
		close();
		if (err != 101 /* SQLITE_DONE */) {
			lastError = Globals.sqlite3_errmsg(dbHandle);
			return false;
		}
		return true;
	}

	public SQLiteCursor query() {
		return new SQLiteCursor(this);
	}

	private void close() {
		if (stmtHandle != null) {
			Globals.sqlite3_finalize(stmtHandle);
			stmtHandle = null;
		}
	}

	boolean step() {
		if (stmtHandle == null) {
			throw new RuntimeException("statement handle is closed");
		}
		int err = Globals.sqlite3_step(stmtHandle);
		if (err != 100 /* SQLITE_ROW */) {
			lastError = Globals.sqlite3_errmsg(dbHandle);
			return false;
		}
		return true;
	}

	void reset() {
		if (stmtHandle == null) {
			throw new RuntimeException("statement handle is closed");
		}
		Globals.sqlite3_reset(stmtHandle);
	}

	public String getStatement() {
		return statement;
	}

	public int getAffectedCount() {
		return affectedCount;
	}

	public long getLastInsertedID() {
		return lastInsertedID;
	}
}
