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


import com.intel.inde.moe.natj.c.StructObject;
import com.intel.inde.moe.natj.c.ann.Structure;
import com.intel.inde.moe.natj.c.ann.StructureField;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.UncertainArgument;
import com.intel.inde.moe.natj.general.ann.UncertainReturn;
import com.intel.inde.moe.natj.general.ptr.BytePtr;

@Generated
@Structure()
public final class sqlite3_index_info extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public sqlite3_index_info() {
		super(sqlite3_index_info.class);
	}

	@Generated
	protected sqlite3_index_info(Pointer peer) {
		super(peer);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	public native int nConstraint();

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setNConstraint(int value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	@UncertainReturn("Options: reference, array Fallback: reference")
	public native sqlite3_index_constraint aConstraint();

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setAConstraint(
			@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_index_constraint value);

	@Generated
	@StructureField(order = 2, isGetter = true)
	public native int nOrderBy();

	@Generated
	@StructureField(order = 2, isGetter = false)
	public native void setNOrderBy(int value);

	@Generated
	@StructureField(order = 3, isGetter = true)
	@UncertainReturn("Options: reference, array Fallback: reference")
	public native sqlite3_index_orderby aOrderBy();

	@Generated
	@StructureField(order = 3, isGetter = false)
	public native void setAOrderBy(
			@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_index_orderby value);

	@Generated
	@StructureField(order = 4, isGetter = true)
	@UncertainReturn("Options: reference, array Fallback: reference")
	public native sqlite3_index_constraint_usage aConstraintUsage();

	@Generated
	@StructureField(order = 4, isGetter = false)
	public native void setAConstraintUsage(
			@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_index_constraint_usage value);

	@Generated
	@StructureField(order = 5, isGetter = true)
	public native int idxNum();

	@Generated
	@StructureField(order = 5, isGetter = false)
	public native void setIdxNum(int value);

	@Generated
	@StructureField(order = 6, isGetter = true)
	public native BytePtr idxStr();

	@Generated
	@StructureField(order = 6, isGetter = false)
	public native void setIdxStr(BytePtr value);

	@Generated
	@StructureField(order = 7, isGetter = true)
	public native int needToFreeIdxStr();

	@Generated
	@StructureField(order = 7, isGetter = false)
	public native void setNeedToFreeIdxStr(int value);

	@Generated
	@StructureField(order = 8, isGetter = true)
	public native int orderByConsumed();

	@Generated
	@StructureField(order = 8, isGetter = false)
	public native void setOrderByConsumed(int value);

	@Generated
	@StructureField(order = 9, isGetter = true)
	public native double estimatedCost();

	@Generated
	@StructureField(order = 9, isGetter = false)
	public native void setEstimatedCost(double value);

	@Generated
	@StructureField(order = 10, isGetter = true)
	public native long estimatedRows();

	@Generated
	@StructureField(order = 10, isGetter = false)
	public native void setEstimatedRows(long value);
}