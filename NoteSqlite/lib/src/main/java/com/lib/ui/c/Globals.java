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


package com.lib.ui.c;


import com.intel.inde.moe.natj.c.CRuntime;
import com.intel.inde.moe.natj.c.ann.CFunction;
import com.intel.inde.moe.natj.c.ann.CVariable;
import com.intel.inde.moe.natj.c.ann.FunctionPtr;
import com.intel.inde.moe.natj.c.ann.Variadic;
import com.intel.inde.moe.natj.c.map.CStringArrayMapper;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.ReferenceInfo;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ann.UncertainArgument;
import com.intel.inde.moe.natj.general.ann.UncertainReturn;
import com.intel.inde.moe.natj.general.ptr.BytePtr;
import com.intel.inde.moe.natj.general.ptr.ConstBytePtr;
import com.intel.inde.moe.natj.general.ptr.ConstVoidPtr;
import com.intel.inde.moe.natj.general.ptr.DoublePtr;
import com.intel.inde.moe.natj.general.ptr.IntPtr;
import com.intel.inde.moe.natj.general.ptr.LongPtr;
import com.intel.inde.moe.natj.general.ptr.Ptr;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.lib.ui.struct.sqlite3_rtree_geometry;
import com.lib.ui.struct.sqlite3_rtree_query_info;

@Generated
@Runtime(CRuntime.class)
public final class Globals {
	static {
		NatJ.register();
	}

	@Generated
	private Globals() {
	}

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_libversion();

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_sourceid();

	@Generated
	@CFunction
	public static native int sqlite3_libversion_number();

	@Generated
	@CFunction
	public static native int sqlite3_compileoption_used(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zOptName);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_compileoption_get(int N);

	@Generated
	@CFunction
	public static native int sqlite3_threadsafe();

	@Generated
	@CFunction
	public static native int sqlite3_close(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_close_v2(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_exec(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String sql,
			@FunctionPtr(name = "call_sqlite3_exec") Function_sqlite3_exec callback,
			VoidPtr arg4, Ptr<BytePtr> errmsg);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_exec {
		@Generated
		public int call_sqlite3_exec(VoidPtr arg0, int arg1,
				@ReferenceInfo(type = Byte.class, depth = 2) Ptr<BytePtr> arg2,
				@ReferenceInfo(type = Byte.class, depth = 2) Ptr<BytePtr> arg3);
	}

	@Generated
	@CFunction
	public static native int sqlite3_initialize();

	@Generated
	@CFunction
	public static native int sqlite3_shutdown();

	@Generated
	@CFunction
	public static native int sqlite3_os_init();

	@Generated
	@CFunction
	public static native int sqlite3_os_end();

	@Generated
	@Variadic()
	@CFunction
	public static native int sqlite3_config(int arg1, Object... varargs);

	@Generated
	@Variadic()
	@CFunction
	public static native int sqlite3_db_config(VoidPtr arg1, int op,
			Object... varargs);

	@Generated
	@CFunction
	public static native int sqlite3_extended_result_codes(VoidPtr arg1,
			int onoff);

	@Generated
	@CFunction
	public static native long sqlite3_last_insert_rowid(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_changes(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_total_changes(VoidPtr arg1);

	@Generated
	@CFunction
	public static native void sqlite3_interrupt(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_complete(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String sql);

	@Generated
	@CFunction
	public static native int sqlite3_complete16(ConstVoidPtr sql);

	@Generated
	@CFunction
	public static native int sqlite3_busy_handler(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_busy_handler") Function_sqlite3_busy_handler arg2,
			VoidPtr arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_busy_handler {
		@Generated
		public int call_sqlite3_busy_handler(VoidPtr arg0, int arg1);
	}

	@Generated
	@CFunction
	public static native int sqlite3_busy_timeout(VoidPtr arg1, int ms);

	@Generated
	@CFunction
	public static native int sqlite3_get_table(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zSql,
			Ptr<Ptr<BytePtr>> pazResult, IntPtr pnRow, IntPtr pnColumn,
			Ptr<BytePtr> pzErrmsg);

	@Generated
	@CFunction
	public static native void sqlite3_free_table(Ptr<BytePtr> result);

	@Generated
	@Variadic()
	@CFunction
	public static native BytePtr sqlite3_mprintf(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg1,
			Object... varargs);

	@Generated
	@CFunction
	public static native BytePtr sqlite3_vmprintf(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg1,
			BytePtr arg2);

	@Generated
	@Variadic()
	@CFunction
	public static native BytePtr sqlite3_snprintf(
			int arg1,
			BytePtr arg2,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg3,
			Object... varargs);

	@Generated
	@CFunction
	public static native BytePtr sqlite3_vsnprintf(
			int arg1,
			BytePtr arg2,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg3,
			BytePtr arg4);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_malloc(int arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_malloc64(long arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_realloc(VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_realloc64(VoidPtr arg1, long arg2);

	@Generated
	@CFunction
	public static native void sqlite3_free(VoidPtr arg1);

	@Generated
	@CFunction
	public static native long sqlite3_msize(VoidPtr arg1);

	@Generated
	@CFunction
	public static native long sqlite3_memory_used();

	@Generated
	@CFunction
	public static native long sqlite3_memory_highwater(int resetFlag);

	@Generated
	@CFunction
	public static native void sqlite3_randomness(int N, VoidPtr P);

	@Generated
	@CFunction
	public static native int sqlite3_set_authorizer(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_set_authorizer") Function_sqlite3_set_authorizer xAuth,
			VoidPtr pUserData);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_set_authorizer {
		@Generated
		public int call_sqlite3_set_authorizer(
				VoidPtr arg0,
				int arg1,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg3,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg4,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg5);
	}

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_trace(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_trace") Function_sqlite3_trace xTrace,
			VoidPtr arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_trace {
		@Generated
		public void call_sqlite3_trace(
				VoidPtr arg0,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg1);
	}

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_profile(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_profile") Function_sqlite3_profile xProfile,
			VoidPtr arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_profile {
		@Generated
		public void call_sqlite3_profile(
				VoidPtr arg0,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg1,
				long arg2);
	}

	@Generated
	@CFunction
	public static native void sqlite3_progress_handler(
			VoidPtr arg1,
			int arg2,
			@FunctionPtr(name = "call_sqlite3_progress_handler") Function_sqlite3_progress_handler arg3,
			VoidPtr arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_progress_handler {
		@Generated
		public int call_sqlite3_progress_handler(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_open(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String filename,
			Ptr<VoidPtr> ppDb);

	@Generated
	@CFunction
	public static native int sqlite3_open16(ConstVoidPtr filename,
			Ptr<VoidPtr> ppDb);

	@Generated
	@CFunction
	public static native int sqlite3_open_v2(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String filename,
			Ptr<VoidPtr> ppDb,
			int flags,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zVfs);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_uri_parameter(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zFilename,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zParam);

	@Generated
	@CFunction
	public static native int sqlite3_uri_boolean(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zFile,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zParam,
			int bDefault);

	@Generated
	@CFunction
	public static native long sqlite3_uri_int64(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
			long arg3);

	@Generated
	@CFunction
	public static native int sqlite3_errcode(VoidPtr db);

	@Generated
	@CFunction
	public static native int sqlite3_extended_errcode(VoidPtr db);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_errmsg(VoidPtr arg1);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_errmsg16(VoidPtr arg1);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_errstr(int arg1);

	@Generated
	@CFunction
	public static native int sqlite3_limit(VoidPtr arg1, int id, int newVal);

	@Generated
	@CFunction
	public static native int sqlite3_prepare(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zSql,
			int nByte,
			Ptr<VoidPtr> ppStmt,
			@UncertainArgument("Options: java.string.array, c.const-byte-ptr-ptr Fallback: java.string.array") @Mapped(CStringArrayMapper.class) String[] pzTail);

	@Generated
	@CFunction
	public static native int sqlite3_prepare_v2(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zSql,
			int nByte,
			Ptr<VoidPtr> ppStmt,
			@UncertainArgument("Options: java.string.array, c.const-byte-ptr-ptr Fallback: java.string.array") @Mapped(CStringArrayMapper.class) String[] pzTail);

	@Generated
	@CFunction
	public static native int sqlite3_prepare16(VoidPtr db, ConstVoidPtr zSql,
			int nByte, Ptr<VoidPtr> ppStmt, Ptr<ConstVoidPtr> pzTail);

	@Generated
	@CFunction
	public static native int sqlite3_prepare16_v2(VoidPtr db,
			ConstVoidPtr zSql, int nByte, Ptr<VoidPtr> ppStmt,
			Ptr<ConstVoidPtr> pzTail);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_sql(VoidPtr pStmt);

	@Generated
	@CFunction
	public static native int sqlite3_stmt_readonly(VoidPtr pStmt);

	@Generated
	@CFunction
	public static native int sqlite3_stmt_busy(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_bind_blob(
			VoidPtr arg1,
			int arg2,
			ConstVoidPtr arg3,
			int n,
			@FunctionPtr(name = "call_sqlite3_bind_blob") Function_sqlite3_bind_blob arg5);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_bind_blob {
		@Generated
		public void call_sqlite3_bind_blob(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_bind_blob64(
			VoidPtr arg1,
			int arg2,
			ConstVoidPtr arg3,
			long arg4,
			@FunctionPtr(name = "call_sqlite3_bind_blob64") Function_sqlite3_bind_blob64 arg5);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_bind_blob64 {
		@Generated
		public void call_sqlite3_bind_blob64(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_bind_double(VoidPtr arg1, int arg2,
			double arg3);

	@Generated
	@CFunction
	public static native int sqlite3_bind_int(VoidPtr arg1, int arg2, int arg3);

	@Generated
	@CFunction
	public static native int sqlite3_bind_int64(VoidPtr arg1, int arg2,
			long arg3);

	@Generated
	@CFunction
	public static native int sqlite3_bind_null(VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	public static native int sqlite3_bind_text(
			VoidPtr arg1,
			int arg2,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg3,
			int arg4,
			@FunctionPtr(name = "call_sqlite3_bind_text") Function_sqlite3_bind_text arg5);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_bind_text {
		@Generated
		public void call_sqlite3_bind_text(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_bind_text16(
			VoidPtr arg1,
			int arg2,
			ConstVoidPtr arg3,
			int arg4,
			@FunctionPtr(name = "call_sqlite3_bind_text16") Function_sqlite3_bind_text16 arg5);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_bind_text16 {
		@Generated
		public void call_sqlite3_bind_text16(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_bind_text64(
			VoidPtr arg1,
			int arg2,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg3,
			long arg4,
			@FunctionPtr(name = "call_sqlite3_bind_text64") Function_sqlite3_bind_text64 arg5,
			byte encoding);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_bind_text64 {
		@Generated
		public void call_sqlite3_bind_text64(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_bind_value(VoidPtr arg1, int arg2,
			VoidPtr arg3);

	@Generated
	@CFunction
	public static native int sqlite3_bind_zeroblob(VoidPtr arg1, int arg2, int n);

	@Generated
	@CFunction
	public static native int sqlite3_bind_zeroblob64(VoidPtr arg1, int arg2,
			long arg3);

	@Generated
	@CFunction
	public static native int sqlite3_bind_parameter_count(VoidPtr arg1);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_bind_parameter_name(VoidPtr arg1,
			int arg2);

	@Generated
	@CFunction
	public static native int sqlite3_bind_parameter_index(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zName);

	@Generated
	@CFunction
	public static native int sqlite3_clear_bindings(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_column_count(VoidPtr pStmt);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_column_name(VoidPtr arg1, int N);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_column_name16(VoidPtr arg1, int N);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_column_database_name(VoidPtr arg1,
			int arg2);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_column_database_name16(
			VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_column_table_name(VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_column_table_name16(VoidPtr arg1,
			int arg2);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_column_origin_name(VoidPtr arg1,
			int arg2);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_column_origin_name16(
			VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_column_decltype(VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_column_decltype16(VoidPtr arg1,
			int arg2);

	@Generated
	@CFunction
	public static native int sqlite3_step(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_data_count(VoidPtr pStmt);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_column_blob(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native int sqlite3_column_bytes(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native int sqlite3_column_bytes16(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native double sqlite3_column_double(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native int sqlite3_column_int(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native long sqlite3_column_int64(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_column_text(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_column_text16(VoidPtr arg1,
			int iCol);

	@Generated
	@CFunction
	public static native int sqlite3_column_type(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_column_value(VoidPtr arg1, int iCol);

	@Generated
	@CFunction
	public static native int sqlite3_finalize(VoidPtr pStmt);

	@Generated
	@CFunction
	public static native int sqlite3_reset(VoidPtr pStmt);

	@Generated
	@CFunction
	public static native int sqlite3_create_function(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zFunctionName,
			int nArg,
			int eTextRep,
			VoidPtr pApp,
			@FunctionPtr(name = "call_sqlite3_create_function_5") Function_sqlite3_create_function_5 xFunc,
			@FunctionPtr(name = "call_sqlite3_create_function_6") Function_sqlite3_create_function_6 xStep,
			@FunctionPtr(name = "call_sqlite3_create_function_7") Function_sqlite3_create_function_7 xFinal);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function_5 {
		@Generated
		public void call_sqlite3_create_function_5(VoidPtr arg0, int arg1,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg2);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function_6 {
		@Generated
		public void call_sqlite3_create_function_6(VoidPtr arg0, int arg1,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg2);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function_7 {
		@Generated
		public void call_sqlite3_create_function_7(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_create_function16(
			VoidPtr db,
			ConstVoidPtr zFunctionName,
			int nArg,
			int eTextRep,
			VoidPtr pApp,
			@FunctionPtr(name = "call_sqlite3_create_function16_5") Function_sqlite3_create_function16_5 xFunc,
			@FunctionPtr(name = "call_sqlite3_create_function16_6") Function_sqlite3_create_function16_6 xStep,
			@FunctionPtr(name = "call_sqlite3_create_function16_7") Function_sqlite3_create_function16_7 xFinal);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function16_5 {
		@Generated
		public void call_sqlite3_create_function16_5(VoidPtr arg0, int arg1,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg2);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function16_6 {
		@Generated
		public void call_sqlite3_create_function16_6(VoidPtr arg0, int arg1,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg2);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function16_7 {
		@Generated
		public void call_sqlite3_create_function16_7(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_create_function_v2(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zFunctionName,
			int nArg,
			int eTextRep,
			VoidPtr pApp,
			@FunctionPtr(name = "call_sqlite3_create_function_v2_5") Function_sqlite3_create_function_v2_5 xFunc,
			@FunctionPtr(name = "call_sqlite3_create_function_v2_6") Function_sqlite3_create_function_v2_6 xStep,
			@FunctionPtr(name = "call_sqlite3_create_function_v2_7") Function_sqlite3_create_function_v2_7 xFinal,
			@FunctionPtr(name = "call_sqlite3_create_function_v2_8") Function_sqlite3_create_function_v2_8 xDestroy);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function_v2_5 {
		@Generated
		public void call_sqlite3_create_function_v2_5(VoidPtr arg0, int arg1,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg2);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function_v2_6 {
		@Generated
		public void call_sqlite3_create_function_v2_6(VoidPtr arg0, int arg1,
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg2);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function_v2_7 {
		@Generated
		public void call_sqlite3_create_function_v2_7(VoidPtr arg0);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_function_v2_8 {
		@Generated
		public void call_sqlite3_create_function_v2_8(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_aggregate_count(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_expired(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_transfer_bindings(VoidPtr arg1,
			VoidPtr arg2);

	@Generated
	@CFunction
	public static native int sqlite3_global_recover();

	@Generated
	@CFunction
	public static native void sqlite3_thread_cleanup();

	@Generated
	@CFunction
	public static native int sqlite3_memory_alarm(
			@FunctionPtr(name = "call_sqlite3_memory_alarm") Function_sqlite3_memory_alarm arg1,
			VoidPtr arg2, long arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_memory_alarm {
		@Generated
		public void call_sqlite3_memory_alarm(VoidPtr arg0, long arg1, int arg2);
	}

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_value_blob(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_value_bytes(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_value_bytes16(VoidPtr arg1);

	@Generated
	@CFunction
	public static native double sqlite3_value_double(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_value_int(VoidPtr arg1);

	@Generated
	@CFunction
	public static native long sqlite3_value_int64(VoidPtr arg1);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_value_text(VoidPtr arg1);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_value_text16(VoidPtr arg1);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_value_text16le(VoidPtr arg1);

	@Generated
	@CFunction
	public static native ConstVoidPtr sqlite3_value_text16be(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_value_type(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_value_numeric_type(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_value_subtype(VoidPtr arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_value_dup(VoidPtr arg1);

	@Generated
	@CFunction
	public static native void sqlite3_value_free(VoidPtr arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_aggregate_context(VoidPtr arg1,
			int nBytes);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_user_data(VoidPtr arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_context_db_handle(VoidPtr arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_get_auxdata(VoidPtr arg1, int N);

	@Generated
	@CFunction
	public static native void sqlite3_set_auxdata(
			VoidPtr arg1,
			int N,
			VoidPtr arg3,
			@FunctionPtr(name = "call_sqlite3_set_auxdata") Function_sqlite3_set_auxdata arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_set_auxdata {
		@Generated
		public void call_sqlite3_set_auxdata(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_blob(
			VoidPtr arg1,
			ConstVoidPtr arg2,
			int arg3,
			@FunctionPtr(name = "call_sqlite3_result_blob") Function_sqlite3_result_blob arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_result_blob {
		@Generated
		public void call_sqlite3_result_blob(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_blob64(
			VoidPtr arg1,
			ConstVoidPtr arg2,
			long arg3,
			@FunctionPtr(name = "call_sqlite3_result_blob64") Function_sqlite3_result_blob64 arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_result_blob64 {
		@Generated
		public void call_sqlite3_result_blob64(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_double(VoidPtr arg1, double arg2);

	@Generated
	@CFunction
	public static native void sqlite3_result_error(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
			int arg3);

	@Generated
	@CFunction
	public static native void sqlite3_result_error16(VoidPtr arg1,
			ConstVoidPtr arg2, int arg3);

	@Generated
	@CFunction
	public static native void sqlite3_result_error_toobig(VoidPtr arg1);

	@Generated
	@CFunction
	public static native void sqlite3_result_error_nomem(VoidPtr arg1);

	@Generated
	@CFunction
	public static native void sqlite3_result_error_code(VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	public static native void sqlite3_result_int(VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	public static native void sqlite3_result_int64(VoidPtr arg1, long arg2);

	@Generated
	@CFunction
	public static native void sqlite3_result_null(VoidPtr arg1);

	@Generated
	@CFunction
	public static native void sqlite3_result_text(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
			int arg3,
			@FunctionPtr(name = "call_sqlite3_result_text") Function_sqlite3_result_text arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_result_text {
		@Generated
		public void call_sqlite3_result_text(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_text64(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
			long arg3,
			@FunctionPtr(name = "call_sqlite3_result_text64") Function_sqlite3_result_text64 arg4,
			byte encoding);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_result_text64 {
		@Generated
		public void call_sqlite3_result_text64(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_text16(
			VoidPtr arg1,
			ConstVoidPtr arg2,
			int arg3,
			@FunctionPtr(name = "call_sqlite3_result_text16") Function_sqlite3_result_text16 arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_result_text16 {
		@Generated
		public void call_sqlite3_result_text16(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_text16le(
			VoidPtr arg1,
			ConstVoidPtr arg2,
			int arg3,
			@FunctionPtr(name = "call_sqlite3_result_text16le") Function_sqlite3_result_text16le arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_result_text16le {
		@Generated
		public void call_sqlite3_result_text16le(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_text16be(
			VoidPtr arg1,
			ConstVoidPtr arg2,
			int arg3,
			@FunctionPtr(name = "call_sqlite3_result_text16be") Function_sqlite3_result_text16be arg4);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_result_text16be {
		@Generated
		public void call_sqlite3_result_text16be(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native void sqlite3_result_value(VoidPtr arg1, VoidPtr arg2);

	@Generated
	@CFunction
	public static native void sqlite3_result_zeroblob(VoidPtr arg1, int n);

	@Generated
	@CFunction
	public static native int sqlite3_result_zeroblob64(VoidPtr arg1, long n);

	@Generated
	@CFunction
	public static native void sqlite3_result_subtype(VoidPtr arg1, int arg2);

	@Generated
	@CFunction
	public static native int sqlite3_create_collation(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zName,
			int eTextRep,
			VoidPtr pArg,
			@FunctionPtr(name = "call_sqlite3_create_collation") Function_sqlite3_create_collation xCompare);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_collation {
		@Generated
		public int call_sqlite3_create_collation(VoidPtr arg0, int arg1,
				ConstVoidPtr arg2, int arg3, ConstVoidPtr arg4);
	}

	@Generated
	@CFunction
	public static native int sqlite3_create_collation_v2(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zName,
			int eTextRep,
			VoidPtr pArg,
			@FunctionPtr(name = "call_sqlite3_create_collation_v2_4") Function_sqlite3_create_collation_v2_4 xCompare,
			@FunctionPtr(name = "call_sqlite3_create_collation_v2_5") Function_sqlite3_create_collation_v2_5 xDestroy);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_collation_v2_4 {
		@Generated
		public int call_sqlite3_create_collation_v2_4(VoidPtr arg0, int arg1,
				ConstVoidPtr arg2, int arg3, ConstVoidPtr arg4);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_collation_v2_5 {
		@Generated
		public void call_sqlite3_create_collation_v2_5(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_create_collation16(
			VoidPtr arg1,
			ConstVoidPtr zName,
			int eTextRep,
			VoidPtr pArg,
			@FunctionPtr(name = "call_sqlite3_create_collation16") Function_sqlite3_create_collation16 xCompare);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_collation16 {
		@Generated
		public int call_sqlite3_create_collation16(VoidPtr arg0, int arg1,
				ConstVoidPtr arg2, int arg3, ConstVoidPtr arg4);
	}

	@Generated
	@CFunction
	public static native int sqlite3_collation_needed(
			VoidPtr arg1,
			VoidPtr arg2,
			@FunctionPtr(name = "call_sqlite3_collation_needed") Function_sqlite3_collation_needed arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_collation_needed {
		@Generated
		public void call_sqlite3_collation_needed(
				VoidPtr arg0,
				VoidPtr arg1,
				int arg2,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg3);
	}

	@Generated
	@CFunction
	public static native int sqlite3_collation_needed16(
			VoidPtr arg1,
			VoidPtr arg2,
			@FunctionPtr(name = "call_sqlite3_collation_needed16") Function_sqlite3_collation_needed16 arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_collation_needed16 {
		@Generated
		public void call_sqlite3_collation_needed16(VoidPtr arg0, VoidPtr arg1,
				int arg2, ConstVoidPtr arg3);
	}

	@Generated
	@CFunction
	public static native int sqlite3_sleep(int arg1);

	@Generated
	@CFunction
	public static native int sqlite3_get_autocommit(VoidPtr arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_db_handle(VoidPtr arg1);

	@Generated
	@CFunction
	@UncertainReturn("Options: java.string, c.const-byte-ptr Fallback: java.string")
	public static native String sqlite3_db_filename(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDbName);

	@Generated
	@CFunction
	public static native int sqlite3_db_readonly(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDbName);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_next_stmt(VoidPtr pDb, VoidPtr pStmt);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_commit_hook(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_commit_hook") Function_sqlite3_commit_hook arg2,
			VoidPtr arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_commit_hook {
		@Generated
		public int call_sqlite3_commit_hook(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_rollback_hook(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_rollback_hook") Function_sqlite3_rollback_hook arg2,
			VoidPtr arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_rollback_hook {
		@Generated
		public void call_sqlite3_rollback_hook(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_update_hook(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_update_hook") Function_sqlite3_update_hook arg2,
			VoidPtr arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_update_hook {
		@Generated
		public void call_sqlite3_update_hook(
				VoidPtr arg0,
				int arg1,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg3,
				long arg4);
	}

	@Generated
	@CFunction
	public static native int sqlite3_enable_shared_cache(int arg1);

	@Generated
	@CFunction
	public static native int sqlite3_release_memory(int arg1);

	@Generated
	@CFunction
	public static native int sqlite3_db_release_memory(VoidPtr arg1);

	@Generated
	@CFunction
	public static native long sqlite3_soft_heap_limit64(long N);

	@Generated
	@CFunction
	public static native void sqlite3_soft_heap_limit(int N);

	@Generated
	@CFunction
	public static native int sqlite3_table_column_metadata(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDbName,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zTableName,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zColumnName,
			@UncertainArgument("Options: java.string.array, c.const-byte-ptr-ptr Fallback: java.string.array") @Mapped(CStringArrayMapper.class) String[] pzDataType,
			@UncertainArgument("Options: java.string.array, c.const-byte-ptr-ptr Fallback: java.string.array") @Mapped(CStringArrayMapper.class) String[] pzCollSeq,
			IntPtr pNotNull, IntPtr pPrimaryKey, IntPtr pAutoinc);

	@Generated
	@CFunction
	public static native int sqlite3_load_extension(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zFile,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zProc,
			Ptr<BytePtr> pzErrMsg);

	@Generated
	@CFunction
	public static native int sqlite3_enable_load_extension(VoidPtr db, int onoff);

	@Generated
	@CFunction
	public static native int sqlite3_auto_extension(
			@FunctionPtr(name = "call_sqlite3_auto_extension") Function_sqlite3_auto_extension xEntryPoint);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_auto_extension {
		@Generated
		public void call_sqlite3_auto_extension();
	}

	@Generated
	@CFunction
	public static native int sqlite3_cancel_auto_extension(
			@FunctionPtr(name = "call_sqlite3_cancel_auto_extension") Function_sqlite3_cancel_auto_extension xEntryPoint);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_cancel_auto_extension {
		@Generated
		public void call_sqlite3_cancel_auto_extension();
	}

	@Generated
	@CFunction
	public static native void sqlite3_reset_auto_extension();

	@Generated
	@CFunction
	public static native int sqlite3_create_module(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zName,
			VoidPtr p, VoidPtr pClientData);

	@Generated
	@CFunction
	public static native int sqlite3_create_module_v2(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zName,
			VoidPtr p,
			VoidPtr pClientData,
			@FunctionPtr(name = "call_sqlite3_create_module_v2") Function_sqlite3_create_module_v2 xDestroy);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_create_module_v2 {
		@Generated
		public void call_sqlite3_create_module_v2(VoidPtr arg0);
	}

	@Generated
	@CFunction
	public static native int sqlite3_declare_vtab(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zSQL);

	@Generated
	@CFunction
	public static native int sqlite3_overload_function(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zFuncName,
			int nArg);

	@Generated
	@CFunction
	public static native int sqlite3_blob_open(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDb,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zTable,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zColumn,
			long iRow, int flags, Ptr<VoidPtr> ppBlob);

	@Generated
	@CFunction
	public static native int sqlite3_blob_reopen(VoidPtr arg1, long arg2);

	@Generated
	@CFunction
	public static native int sqlite3_blob_close(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_blob_bytes(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_blob_read(VoidPtr arg1, VoidPtr Z, int N,
			int iOffset);

	@Generated
	@CFunction
	public static native int sqlite3_blob_write(VoidPtr arg1, ConstVoidPtr z,
			int n, int iOffset);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_vfs_find(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zVfsName);

	@Generated
	@CFunction
	public static native int sqlite3_vfs_register(VoidPtr arg1, int makeDflt);

	@Generated
	@CFunction
	public static native int sqlite3_vfs_unregister(VoidPtr arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_mutex_alloc(int arg1);

	@Generated
	@CFunction
	public static native void sqlite3_mutex_free(VoidPtr arg1);

	@Generated
	@CFunction
	public static native void sqlite3_mutex_enter(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_mutex_try(VoidPtr arg1);

	@Generated
	@CFunction
	public static native void sqlite3_mutex_leave(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_mutex_held(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_mutex_notheld(VoidPtr arg1);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_db_mutex(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_file_control(
			VoidPtr arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDbName,
			int op, VoidPtr arg4);

	@Generated
	@Variadic()
	@CFunction
	public static native int sqlite3_test_control(int op, Object... varargs);

	@Generated
	@CFunction
	public static native int sqlite3_status(int op, IntPtr pCurrent,
			IntPtr pHighwater, int resetFlag);

	@Generated
	@CFunction
	public static native int sqlite3_status64(int op, LongPtr pCurrent,
			LongPtr pHighwater, int resetFlag);

	@Generated
	@CFunction
	public static native int sqlite3_db_status(VoidPtr arg1, int op,
			IntPtr pCur, IntPtr pHiwtr, int resetFlg);

	@Generated
	@CFunction
	public static native int sqlite3_stmt_status(VoidPtr arg1, int op,
			int resetFlg);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_backup_init(
			VoidPtr pDest,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDestName,
			VoidPtr pSource,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zSourceName);

	@Generated
	@CFunction
	public static native int sqlite3_backup_step(VoidPtr p, int nPage);

	@Generated
	@CFunction
	public static native int sqlite3_backup_finish(VoidPtr p);

	@Generated
	@CFunction
	public static native int sqlite3_backup_remaining(VoidPtr p);

	@Generated
	@CFunction
	public static native int sqlite3_backup_pagecount(VoidPtr p);

	@Generated
	@CFunction
	public static native int sqlite3_unlock_notify(
			VoidPtr pBlocked,
			@FunctionPtr(name = "call_sqlite3_unlock_notify") Function_sqlite3_unlock_notify xNotify,
			VoidPtr pNotifyArg);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_unlock_notify {
		@Generated
		public void call_sqlite3_unlock_notify(
				@ReferenceInfo(type = Void.class, depth = 2) Ptr<VoidPtr> arg0,
				int arg1);
	}

	@Generated
	@CFunction
	public static native int sqlite3_stricmp(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2);

	@Generated
	@CFunction
	public static native int sqlite3_strnicmp(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg1,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
			int arg3);

	@Generated
	@CFunction
	public static native int sqlite3_strglob(
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zGlob,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zStr);

	@Generated
	@Variadic()
	@CFunction
	public static native void sqlite3_log(
			int iErrCode,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zFormat,
			Object... varargs);

	@Generated
	@CFunction
	public static native VoidPtr sqlite3_wal_hook(
			VoidPtr arg1,
			@FunctionPtr(name = "call_sqlite3_wal_hook") Function_sqlite3_wal_hook arg2,
			VoidPtr arg3);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_wal_hook {
		@Generated
		public int call_sqlite3_wal_hook(
				VoidPtr arg0,
				VoidPtr arg1,
				@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String arg2,
				int arg3);
	}

	@Generated
	@CFunction
	public static native int sqlite3_wal_autocheckpoint(VoidPtr db, int N);

	@Generated
	@CFunction
	public static native int sqlite3_wal_checkpoint(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDb);

	@Generated
	@CFunction
	public static native int sqlite3_wal_checkpoint_v2(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zDb,
			int eMode, IntPtr pnLog, IntPtr pnCkpt);

	@Generated
	@Variadic()
	@CFunction
	public static native int sqlite3_vtab_config(VoidPtr arg1, int op,
			Object... varargs);

	@Generated
	@CFunction
	public static native int sqlite3_vtab_on_conflict(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_stmt_scanstatus(VoidPtr pStmt, int idx,
			int iScanStatusOp, VoidPtr pOut);

	@Generated
	@CFunction
	public static native void sqlite3_stmt_scanstatus_reset(VoidPtr arg1);

	@Generated
	@CFunction
	public static native int sqlite3_rtree_geometry_callback(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zGeom,
			@FunctionPtr(name = "call_sqlite3_rtree_geometry_callback") Function_sqlite3_rtree_geometry_callback xGeom,
			VoidPtr pContext);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_rtree_geometry_callback {
		@Generated
		public int call_sqlite3_rtree_geometry_callback(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_rtree_geometry arg0,
				int arg1, DoublePtr arg2, IntPtr arg3);
	}

	@Generated
	@CFunction
	public static native int sqlite3_rtree_query_callback(
			VoidPtr db,
			@UncertainArgument("Options: java.string, c.const-byte-ptr Fallback: java.string") String zQueryFunc,
			@FunctionPtr(name = "call_sqlite3_rtree_query_callback_2") Function_sqlite3_rtree_query_callback_2 xQueryFunc,
			VoidPtr pContext,
			@FunctionPtr(name = "call_sqlite3_rtree_query_callback_4") Function_sqlite3_rtree_query_callback_4 xDestructor);

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_rtree_query_callback_2 {
		@Generated
		public int call_sqlite3_rtree_query_callback_2(
				@UncertainArgument("Options: reference, array Fallback: reference") sqlite3_rtree_query_info arg0);
	}

	@Runtime(CRuntime.class)
	@Generated
	static public interface Function_sqlite3_rtree_query_callback_4 {
		@Generated
		public void call_sqlite3_rtree_query_callback_4(VoidPtr arg0);
	}

	@Generated
	@CVariable()
	public static native ConstBytePtr sqlite3_version();

	@Generated
	@CVariable()
	public static native BytePtr sqlite3_temp_directory();

	@Generated
	@CVariable()
	public static native BytePtr sqlite3_data_directory();
}