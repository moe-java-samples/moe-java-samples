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

package org.sqlite.struct;


import com.intel.moe.natj.c.StructObject;
import com.intel.moe.natj.c.ann.Structure;
import com.intel.moe.natj.c.ann.StructureField;
import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;

@Generated
@Structure()
public final class sqlite3_index_constraint extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public sqlite3_index_constraint() {
		super(sqlite3_index_constraint.class);
	}

	@Generated
	protected sqlite3_index_constraint(Pointer peer) {
		super(peer);
	}

	@Generated
	public sqlite3_index_constraint(int iColumn, byte op, byte usable,
			int iTermOffset) {
		super(sqlite3_index_constraint.class);
		setIColumn(iColumn);
		setOp(op);
		setUsable(usable);
		setITermOffset(iTermOffset);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	public native int iColumn();

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setIColumn(int value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	public native byte op();

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setOp(byte value);

	@Generated
	@StructureField(order = 2, isGetter = true)
	public native byte usable();

	@Generated
	@StructureField(order = 2, isGetter = false)
	public native void setUsable(byte value);

	@Generated
	@StructureField(order = 3, isGetter = true)
	public native int iTermOffset();

	@Generated
	@StructureField(order = 3, isGetter = false)
	public native void setITermOffset(int value);
}