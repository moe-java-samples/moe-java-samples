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
import com.intel.moe.natj.general.ann.UncertainArgument;
import com.intel.moe.natj.general.ann.UncertainReturn;
import com.intel.moe.natj.general.ptr.VoidPtr;

@Generated
@Structure()
public final class sqlite3_pcache_methods2 extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public sqlite3_pcache_methods2() {
		super(sqlite3_pcache_methods2.class);
	}

	@Generated
	protected sqlite3_pcache_methods2(Pointer peer) {
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
	public native VoidPtr pArg();

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setPArg(VoidPtr value);

	@Generated
	@StructureField(order = 2, isGetter = true)
	@FunctionPtr(name = "call_xInit")
	public native Function_xInit xInit();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xInit {
		@Generated
		public int call_xInit(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 2, isGetter = false)
	public native void setXInit(
			@FunctionPtr(name = "call_xInit") Function_xInit value);

	@Generated
	@StructureField(order = 3, isGetter = true)
	@FunctionPtr(name = "call_xShutdown")
	public native Function_xShutdown xShutdown();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xShutdown {
		@Generated
		public void call_xShutdown(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 3, isGetter = false)
	public native void setXShutdown(
			@FunctionPtr(name = "call_xShutdown") Function_xShutdown value);

	@Generated
	@StructureField(order = 4, isGetter = true)
	@FunctionPtr(name = "call_xCreate")
	public native Function_xCreate xCreate();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xCreate {
		@Generated
		public VoidPtr call_xCreate(int arg0, int arg1, int arg2);
	}

	@Generated
	@StructureField(order = 4, isGetter = false)
	public native void setXCreate(
			@FunctionPtr(name = "call_xCreate") Function_xCreate value);

	@Generated
	@StructureField(order = 5, isGetter = true)
	@FunctionPtr(name = "call_xCachesize")
	public native Function_xCachesize xCachesize();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xCachesize {
		@Generated
		public void call_xCachesize(VoidPtr arg0, int arg1);
	}

	@Generated
	@StructureField(order = 5, isGetter = false)
	public native void setXCachesize(
			@FunctionPtr(name = "call_xCachesize") Function_xCachesize value);

	@Generated
	@StructureField(order = 6, isGetter = true)
	@FunctionPtr(name = "call_xPagecount")
	public native Function_xPagecount xPagecount();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xPagecount {
		@Generated
		public int call_xPagecount(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 6, isGetter = false)
	public native void setXPagecount(
			@FunctionPtr(name = "call_xPagecount") Function_xPagecount value);

	@Generated
	@StructureField(order = 7, isGetter = true)
	@FunctionPtr(name = "call_xFetch")
	public native Function_xFetch xFetch();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xFetch {
		@Generated
		@UncertainReturn("Options: reference, array Fallback: reference")
		public sqlite3_pcache_page call_xFetch(VoidPtr arg0, int arg1, int arg2);
	}

	@Generated
	@StructureField(order = 7, isGetter = false)
	public native void setXFetch(
			@FunctionPtr(name = "call_xFetch") Function_xFetch value);

	@Generated
	@StructureField(order = 8, isGetter = true)
	@FunctionPtr(name = "call_xUnpin")
	public native Function_xUnpin xUnpin();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xUnpin {
		@Generated
		public void call_xUnpin(
				VoidPtr arg0,
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_pcache_page arg1,
				int arg2);
	}

	@Generated
	@StructureField(order = 8, isGetter = false)
	public native void setXUnpin(
			@FunctionPtr(name = "call_xUnpin") Function_xUnpin value);

	@Generated
	@StructureField(order = 9, isGetter = true)
	@FunctionPtr(name = "call_xRekey")
	public native Function_xRekey xRekey();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xRekey {
		@Generated
		public void call_xRekey(
				VoidPtr arg0,
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_pcache_page arg1,
				int arg2, int arg3);
	}

	@Generated
	@StructureField(order = 9, isGetter = false)
	public native void setXRekey(
			@FunctionPtr(name = "call_xRekey") Function_xRekey value);

	@Generated
	@StructureField(order = 10, isGetter = true)
	@FunctionPtr(name = "call_xTruncate")
	public native Function_xTruncate xTruncate();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xTruncate {
		@Generated
		public void call_xTruncate(VoidPtr arg0, int arg1);
	}

	@Generated
	@StructureField(order = 10, isGetter = false)
	public native void setXTruncate(
			@FunctionPtr(name = "call_xTruncate") Function_xTruncate value);

	@Generated
	@StructureField(order = 11, isGetter = true)
	@FunctionPtr(name = "call_xDestroy")
	public native Function_xDestroy xDestroy();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xDestroy {
		@Generated
		public void call_xDestroy(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 11, isGetter = false)
	public native void setXDestroy(
			@FunctionPtr(name = "call_xDestroy") Function_xDestroy value);

	@Generated
	@StructureField(order = 12, isGetter = true)
	@FunctionPtr(name = "call_xShrink")
	public native Function_xShrink xShrink();

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_xShrink {
		@Generated
		public void call_xShrink(VoidPtr arg0);
	}

	@Generated
	@StructureField(order = 12, isGetter = false)
	public native void setXShrink(
			@FunctionPtr(name = "call_xShrink") Function_xShrink value);
}