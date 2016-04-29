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

package com.intel.moe.samples.museummap.android.db;

import android.database.Cursor;

import com.intel.moe.samples.museummap.common.database.ISQLiteCursor;

public class AndroidCursor implements ISQLiteCursor {
	
	private Cursor cursor;
	
	public AndroidCursor(Cursor cursor) {
		if (cursor == null) throw new IllegalArgumentException("cursor must not be null");
		this.cursor = cursor;
	}

	@Override
	public void close() {
		cursor.close();
	}

	@Override
	public int getInt(int arg0) {
		return cursor.getInt(arg0);
	}

	@Override
	public long getLong(int arg0) {
		return cursor.getLong(arg0);
	}

	@Override
	public String getString(int arg0) {
		return cursor.getString(arg0);
	}

	@Override
	public boolean isAfterLast() {
		return cursor.isAfterLast();
	}

	@Override
	public void moveToFirst() {
		cursor.moveToFirst();
	}

	@Override
	public void moveToNext() {
		cursor.moveToNext();
	}

	@Override
	public double getDouble(int i) {
		return cursor.getDouble(i);
	}

}
