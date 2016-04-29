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


import com.intel.moe.natj.c.CRuntime;
import com.intel.moe.natj.c.StructObject;
import com.intel.moe.natj.c.ann.FunctionPtr;
import com.intel.moe.natj.c.ann.Structure;
import com.intel.moe.natj.c.ann.StructureField;
import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.Runtime;
import com.intel.moe.natj.general.ptr.VoidPtr;

@Generated
@Structure()
public final class sqlite3_mem_methods extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public sqlite3_mem_methods() {
		super(sqlite3_mem_methods.class);
	}

	@Generated
	protected sqlite3_mem_methods(Pointer peer) {
		super(peer);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	@FunctionPtr(name = "call_xMalloc")
	public native Function_xMalloc xMalloc();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xMalloc {
		@Generated
		public VoidPtr call_xMalloc(int arg0);
	}

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setXMalloc(
			@FunctionPtr(name = "call_xMalloc") Function_xMalloc value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	@FunctionPtr(name = "call_xFree")
	public native Function_xFree xFree();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xFree {
		@Generated
		public void call_xFree(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setXFree(
			@FunctionPtr(name = "call_xFree") Function_xFree value);

	@Generated
	@StructureField(order = 2, isGetter = true)
	@FunctionPtr(name = "call_xRealloc")
	public native Function_xRealloc xRealloc();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xRealloc {
		@Generated
		public VoidPtr call_xRealloc(VoidPtr arg0, int arg1);
	}

	@Generated
	@StructureField(order = 2, isGetter = false)
	public native void setXRealloc(
			@FunctionPtr(name = "call_xRealloc") Function_xRealloc value);

	@Generated
	@StructureField(order = 3, isGetter = true)
	@FunctionPtr(name = "call_xSize")
	public native Function_xSize xSize();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xSize {
		@Generated
		public int call_xSize(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 3, isGetter = false)
	public native void setXSize(
			@FunctionPtr(name = "call_xSize") Function_xSize value);

	@Generated
	@StructureField(order = 4, isGetter = true)
	@FunctionPtr(name = "call_xRoundup")
	public native Function_xRoundup xRoundup();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xRoundup {
		@Generated
		public int call_xRoundup(int arg0);
	}

	@Generated
	@StructureField(order = 4, isGetter = false)
	public native void setXRoundup(
			@FunctionPtr(name = "call_xRoundup") Function_xRoundup value);

	@Generated
	@StructureField(order = 5, isGetter = true)
	@FunctionPtr(name = "call_xInit")
	public native Function_xInit xInit();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xInit {
		@Generated
		public int call_xInit(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 5, isGetter = false)
	public native void setXInit(
			@FunctionPtr(name = "call_xInit") Function_xInit value);

	@Generated
	@StructureField(order = 6, isGetter = true)
	@FunctionPtr(name = "call_xShutdown")
	public native Function_xShutdown xShutdown();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xShutdown {
		@Generated
		public void call_xShutdown(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 6, isGetter = false)
	public native void setXShutdown(
			@FunctionPtr(name = "call_xShutdown") Function_xShutdown value);

	@Generated
	@StructureField(order = 7, isGetter = true)
	public native VoidPtr pAppData();

	@Generated
	@StructureField(order = 7, isGetter = false)
	public native void setPAppData(VoidPtr value);
}