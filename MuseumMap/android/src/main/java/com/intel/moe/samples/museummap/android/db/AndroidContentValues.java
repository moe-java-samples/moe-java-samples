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

import android.content.ContentValues;

import com.intel.moe.samples.museummap.common.database.ISQLiteContentValues;

import java.util.Set;

public class AndroidContentValues implements ISQLiteContentValues {
	
	private ContentValues mValues;
	
	public AndroidContentValues() {
		mValues = new ContentValues();
	}

	@Override
	public Object get(String arg0) {
		return mValues.get(arg0);
	}

	@Override
	public Set<String> keySet() {
		return mValues.keySet();
	}

	@Override
	public void put(String arg0, String arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public void put(String arg0, Byte arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public void put(String arg0, Short arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public void put(String arg0, Integer arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public void put(String arg0, Long arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public void put(String arg0, Float arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public void put(String arg0, Double arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public void put(String arg0, Boolean arg1) {
		mValues.put(arg0, arg1);
	}

	@Override
	public int size() {
		return mValues.size();
	}

	public ContentValues getContentValues() {
		return mValues;
	}

}
