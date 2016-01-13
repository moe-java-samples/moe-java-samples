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


import com.intel.inde.moe.natj.c.CRuntime;
import com.intel.inde.moe.natj.c.StructObject;
import com.intel.inde.moe.natj.c.ann.FunctionPtr;
import com.intel.inde.moe.natj.c.ann.Structure;
import com.intel.inde.moe.natj.c.ann.StructureField;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.ReferenceInfo;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ann.UncertainArgument;
import com.intel.inde.moe.natj.general.ptr.ConstVoidPtr;
import com.intel.inde.moe.natj.general.ptr.IntPtr;
import com.intel.inde.moe.natj.general.ptr.LongPtr;
import com.intel.inde.moe.natj.general.ptr.Ptr;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;

@Generated
@Structure()
public final class sqlite3_io_methods extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public sqlite3_io_methods() {
		super(sqlite3_io_methods.class);
	}

	@Generated
	protected sqlite3_io_methods(Pointer peer) {
		super(peer);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	public native int iVersion();

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setIVersion(int value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	@FunctionPtr(name = "call_xClose")
	public native Function_xClose xClose();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xClose {
		@Generated
		public int call_xClose(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0);
	}

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setXClose(
			@FunctionPtr(name = "call_xClose") Function_xClose value);

	@Generated
	@StructureField(order = 2, isGetter = true)
	@FunctionPtr(name = "call_xRead")
	public native Function_xRead xRead();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xRead {
		@Generated
		public int call_xRead(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				VoidPtr arg1, int arg2, long arg3);
	}

	@Generated
	@StructureField(order = 2, isGetter = false)
	public native void setXRead(
			@FunctionPtr(name = "call_xRead") Function_xRead value);

	@Generated
	@StructureField(order = 3, isGetter = true)
	@FunctionPtr(name = "call_xWrite")
	public native Function_xWrite xWrite();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xWrite {
		@Generated
		public int call_xWrite(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				ConstVoidPtr arg1, int arg2, long arg3);
	}

	@Generated
	@StructureField(order = 3, isGetter = false)
	public native void setXWrite(
			@FunctionPtr(name = "call_xWrite") Function_xWrite value);

	@Generated
	@StructureField(order = 4, isGetter = true)
	@FunctionPtr(name = "call_xTruncate")
	public native Function_xTruncate xTruncate();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xTruncate {
		@Generated
		public int call_xTruncate(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				long arg1);
	}

	@Generated
	@StructureField(order = 4, isGetter = false)
	public native void setXTruncate(
			@FunctionPtr(name = "call_xTruncate") Function_xTruncate value);

	@Generated
	@StructureField(order = 5, isGetter = true)
	@FunctionPtr(name = "call_xSync")
	public native Function_xSync xSync();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xSync {
		@Generated
		public int call_xSync(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				int arg1);
	}

	@Generated
	@StructureField(order = 5, isGetter = false)
	public native void setXSync(
			@FunctionPtr(name = "call_xSync") Function_xSync value);

	@Generated
	@StructureField(order = 6, isGetter = true)
	@FunctionPtr(name = "call_xFileSize")
	public native Function_xFileSize xFileSize();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xFileSize {
		@Generated
		public int call_xFileSize(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				LongPtr arg1);
	}

	@Generated
	@StructureField(order = 6, isGetter = false)
	public native void setXFileSize(
			@FunctionPtr(name = "call_xFileSize") Function_xFileSize value);

	@Generated
	@StructureField(order = 7, isGetter = true)
	@FunctionPtr(name = "call_xLock")
	public native Function_xLock xLock();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xLock {
		@Generated
		public int call_xLock(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				int arg1);
	}

	@Generated
	@StructureField(order = 7, isGetter = false)
	public native void setXLock(
			@FunctionPtr(name = "call_xLock") Function_xLock value);

	@Generated
	@StructureField(order = 8, isGetter = true)
	@FunctionPtr(name = "call_xUnlock")
	public native Function_xUnlock xUnlock();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xUnlock {
		@Generated
		public int call_xUnlock(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				int arg1);
	}

	@Generated
	@StructureField(order = 8, isGetter = false)
	public native void setXUnlock(
			@FunctionPtr(name = "call_xUnlock") Function_xUnlock value);

	@Generated
	@StructureField(order = 9, isGetter = true)
	@FunctionPtr(name = "call_xCheckReservedLock")
	public native Function_xCheckReservedLock xCheckReservedLock();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xCheckReservedLock {
		@Generated
		public int call_xCheckReservedLock(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				IntPtr arg1);
	}

	@Generated
	@StructureField(order = 9, isGetter = false)
	public native void setXCheckReservedLock(
			@FunctionPtr(name = "call_xCheckReservedLock") Function_xCheckReservedLock value);

	@Generated
	@StructureField(order = 10, isGetter = true)
	@FunctionPtr(name = "call_xFileControl")
	public native Function_xFileControl xFileControl();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xFileControl {
		@Generated
		public int call_xFileControl(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				int arg1, VoidPtr arg2);
	}

	@Generated
	@StructureField(order = 10, isGetter = false)
	public native void setXFileControl(
			@FunctionPtr(name = "call_xFileControl") Function_xFileControl value);

	@Generated
	@StructureField(order = 11, isGetter = true)
	@FunctionPtr(name = "call_xSectorSize")
	public native Function_xSectorSize xSectorSize();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xSectorSize {
		@Generated
		public int call_xSectorSize(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0);
	}

	@Generated
	@StructureField(order = 11, isGetter = false)
	public native void setXSectorSize(
			@FunctionPtr(name = "call_xSectorSize") Function_xSectorSize value);

	@Generated
	@StructureField(order = 12, isGetter = true)
	@FunctionPtr(name = "call_xDeviceCharacteristics")
	public native Function_xDeviceCharacteristics xDeviceCharacteristics();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xDeviceCharacteristics {
		@Generated
		public int call_xDeviceCharacteristics(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0);
	}

	@Generated
	@StructureField(order = 12, isGetter = false)
	public native void setXDeviceCharacteristics(
			@FunctionPtr(name = "call_xDeviceCharacteristics") Function_xDeviceCharacteristics value);

	@Generated
	@StructureField(order = 13, isGetter = true)
	@FunctionPtr(name = "call_xShmMap")
	public native Function_xShmMap xShmMap();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xShmMap {
		@Generated
		public int call_xShmMap(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				int arg1, int arg2, int arg3,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg4);
	}

	@Generated
	@StructureField(order = 13, isGetter = false)
	public native void setXShmMap(
			@FunctionPtr(name = "call_xShmMap") Function_xShmMap value);

	@Generated
	@StructureField(order = 14, isGetter = true)
	@FunctionPtr(name = "call_xShmLock")
	public native Function_xShmLock xShmLock();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xShmLock {
		@Generated
		public int call_xShmLock(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				int arg1, int arg2, int arg3);
	}

	@Generated
	@StructureField(order = 14, isGetter = false)
	public native void setXShmLock(
			@FunctionPtr(name = "call_xShmLock") Function_xShmLock value);

	@Generated
	@StructureField(order = 15, isGetter = true)
	@FunctionPtr(name = "call_xShmBarrier")
	public native Function_xShmBarrier xShmBarrier();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xShmBarrier {
		@Generated
		public void call_xShmBarrier(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0);
	}

	@Generated
	@StructureField(order = 15, isGetter = false)
	public native void setXShmBarrier(
			@FunctionPtr(name = "call_xShmBarrier") Function_xShmBarrier value);

	@Generated
	@StructureField(order = 16, isGetter = true)
	@FunctionPtr(name = "call_xShmUnmap")
	public native Function_xShmUnmap xShmUnmap();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xShmUnmap {
		@Generated
		public int call_xShmUnmap(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				int arg1);
	}

	@Generated
	@StructureField(order = 16, isGetter = false)
	public native void setXShmUnmap(
			@FunctionPtr(name = "call_xShmUnmap") Function_xShmUnmap value);

	@Generated
	@StructureField(order = 17, isGetter = true)
	@FunctionPtr(name = "call_xFetch")
	public native Function_xFetch xFetch();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xFetch {
		@Generated
		public int call_xFetch(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				long arg1, int arg2,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg3);
	}

	@Generated
	@StructureField(order = 17, isGetter = false)
	public native void setXFetch(
			@FunctionPtr(name = "call_xFetch") Function_xFetch value);

	@Generated
	@StructureField(order = 18, isGetter = true)
	@FunctionPtr(name = "call_xUnfetch")
	public native Function_xUnfetch xUnfetch();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xUnfetch {
		@Generated
		public int call_xUnfetch(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_file arg0,
				long arg1, VoidPtr arg2);
	}

	@Generated
	@StructureField(order = 18, isGetter = false)
	public native void setXUnfetch(
			@FunctionPtr(name = "call_xUnfetch") Function_xUnfetch value);
}