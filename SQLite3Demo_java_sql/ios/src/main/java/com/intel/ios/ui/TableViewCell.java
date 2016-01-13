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
package com.intel.ios.ui;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.coregraphics.struct.CGRect;
import ios.foundation.NSCoder;
import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;

@Generated
@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("TableViewCell")
@RegisterOnStartup
public class TableViewCell extends UITableViewCell {
	static {
		NatJ.register();
	}

	@Generated
	protected TableViewCell(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native TableViewCell alloc();

	@Generated
	@Selector("init")
	public native TableViewCell init();

	@Generated
	@Selector("initWithCoder:")
	public native TableViewCell initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithFrame:")
	public native TableViewCell initWithFrame(@ByValue CGRect frame);

	@Generated
	@Deprecated
	@Selector("initWithFrame:reuseIdentifier:")
	public native TableViewCell initWithFrameReuseIdentifier(@ByValue CGRect frame, String reuseIdentifier);

	@Generated
	@Selector("initWithStyle:reuseIdentifier:")
	public native TableViewCell initWithStyleReuseIdentifier(@NInt long style, String reuseIdentifier);

	@Generated
	@Selector("setSubTitleLabel:")
	public native void setSubTitleLabel_unsafe(UILabel value);

	@Generated
	public void setSubTitleLabel(UILabel value) {
		com.intel.inde.moe.natj.objc.ObjCObject __old = (com.intel.inde.moe.natj.objc.ObjCObject) subTitleLabel();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setSubTitleLabel_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setTitleLabel:")
	public native void setTitleLabel_unsafe(UILabel value);

	@Generated
	public void setTitleLabel(UILabel value) {
		com.intel.inde.moe.natj.objc.ObjCObject __old = (com.intel.inde.moe.natj.objc.ObjCObject) titleLabel();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setTitleLabel_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("subTitleLabel")
	public native UILabel subTitleLabel();

	@Generated
	@Selector("titleLabel")
	public native UILabel titleLabel();
}