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
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;

@Generated
@Structure()
public final class sqlite3_mutex_methods extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public sqlite3_mutex_methods() {
		super(sqlite3_mutex_methods.class);
	}

	@Generated
	protected sqlite3_mutex_methods(Pointer peer) {
		super(peer);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	@FunctionPtr(name = "call_xMutexInit")
	public native Function_xMutexInit xMutexInit();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexInit {
		@Generated
		public int call_xMutexInit();
	}

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setXMutexInit(
			@FunctionPtr(name = "call_xMutexInit") Function_xMutexInit value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	@FunctionPtr(name = "call_xMutexEnd")
	public native Function_xMutexEnd xMutexEnd();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexEnd {
		@Generated
		public int call_xMutexEnd();
	}

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setXMutexEnd(
			@FunctionPtr(name = "call_xMutexEnd") Function_xMutexEnd value);

	@Generated
	@StructureField(order = 2, isGetter = true)
	@FunctionPtr(name = "call_xMutexAlloc")
	public native Function_xMutexAlloc xMutexAlloc();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexAlloc {
		@Generated
		public VoidPtr call_xMutexAlloc(int arg0);
	}

	@Generated
	@StructureField(order = 2, isGetter = false)
	public native void setXMutexAlloc(
			@FunctionPtr(name = "call_xMutexAlloc") Function_xMutexAlloc value);

	@Generated
	@StructureField(order = 3, isGetter = true)
	@FunctionPtr(name = "call_xMutexFree")
	public native Function_xMutexFree xMutexFree();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexFree {
		@Generated
		public void call_xMutexFree(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 3, isGetter = false)
	public native void setXMutexFree(
			@FunctionPtr(name = "call_xMutexFree") Function_xMutexFree value);

	@Generated
	@StructureField(order = 4, isGetter = true)
	@FunctionPtr(name = "call_xMutexEnter")
	public native Function_xMutexEnter xMutexEnter();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexEnter {
		@Generated
		public void call_xMutexEnter(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 4, isGetter = false)
	public native void setXMutexEnter(
			@FunctionPtr(name = "call_xMutexEnter") Function_xMutexEnter value);

	@Generated
	@StructureField(order = 5, isGetter = true)
	@FunctionPtr(name = "call_xMutexTry")
	public native Function_xMutexTry xMutexTry();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexTry {
		@Generated
		public int call_xMutexTry(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 5, isGetter = false)
	public native void setXMutexTry(
			@FunctionPtr(name = "call_xMutexTry") Function_xMutexTry value);

	@Generated
	@StructureField(order = 6, isGetter = true)
	@FunctionPtr(name = "call_xMutexLeave")
	public native Function_xMutexLeave xMutexLeave();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexLeave {
		@Generated
		public void call_xMutexLeave(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 6, isGetter = false)
	public native void setXMutexLeave(
			@FunctionPtr(name = "call_xMutexLeave") Function_xMutexLeave value);

	@Generated
	@StructureField(order = 7, isGetter = true)
	@FunctionPtr(name = "call_xMutexHeld")
	public native Function_xMutexHeld xMutexHeld();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexHeld {
		@Generated
		public int call_xMutexHeld(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 7, isGetter = false)
	public native void setXMutexHeld(
			@FunctionPtr(name = "call_xMutexHeld") Function_xMutexHeld value);

	@Generated
	@StructureField(order = 8, isGetter = true)
	@FunctionPtr(name = "call_xMutexNotheld")
	public native Function_xMutexNotheld xMutexNotheld();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMutexNotheld {
		@Generated
		public int call_xMutexNotheld(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 8, isGetter = false)
	public native void setXMutexNotheld(
			@FunctionPtr(name = "call_xMutexNotheld") Function_xMutexNotheld value);
}