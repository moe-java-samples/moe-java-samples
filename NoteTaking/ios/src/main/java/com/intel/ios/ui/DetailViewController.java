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
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.NSObject;
import ios.foundation.*;
import ios.foundation.enums.NSSearchPathDirectory;
import ios.foundation.enums.NSSearchPathDomainMask;
import ios.uikit.*;

import java.lang.String;

import com.example.Data;
import ios.uikit.enums.UIBarButtonSystemItem;
import ios.uikit.protocol.UIApplicationDelegate;
import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.uikit.UIButton;
import ios.uikit.UITextView;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.uikit.protocol.UITextFieldDelegate;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("DetailViewController")
@RegisterOnStartup
public class DetailViewController extends UIViewController implements UITextFieldDelegate {
    static {
        NatJ.register();
    }

    protected DetailViewController(Pointer peer) {
        super(peer);
    }

    private String detailItem = "";
    private int key = 0;
    public Data data;

    @Owned
    @Selector("alloc")
    public static native DetailViewController alloc();

    @Selector("init")
    public native DetailViewController init();

    private UITextView dataText;

    @Override
    @Selector("viewDidLoad")
    public void viewDidLoad() {
        super.viewDidLoad();

        dataText = noteView();
        noteView().setDelegate(this);

        // Do any additional setup after loading the view, typically from a nib.
        configureView();

    }

    public void configureView() {
        // Update the user interface for the detail item.
        if(data!=null) {

            if (data.getNoteForCurrentKey()!=null) {
                dataText.setText(data.getNoteForCurrentKey());
            } else {
                dataText.setText("");
            }
        }
        noteView().becomeFirstResponder();
    }

    public void setDetailItem(String newDetailItem, Data d) {
        if (!detailItem.equals(newDetailItem)) {
            detailItem = newDetailItem;
            data =d;
            data.setCurrentKey(newDetailItem);

        }
    }

    public String getDetailItem() {
        return detailItem;
    }

    @Generated
    @Selector("initWithNibName:bundle:")
    public native DetailViewController initWithNibNameBundle(
            String nibNameOrNil, NSBundle nibBundleOrNil);

    @Generated
	@Selector("initWithCoder:")
	public native DetailViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("noteView")
	public native UITextView noteView();

	@Generated
	@Selector("setNoteView:")
	public native void setNoteView_unsafe(UITextView value);

	@Generated
	public void setNoteView(UITextView value) {
		com.intel.inde.moe.natj.objc.ObjCObject __old = (com.intel.inde.moe.natj.objc.ObjCObject) noteView();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setNoteView_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("saveNote")
	public native UIButton saveNote();

	@Generated
	@Selector("setSaveNote:")
	public native void setSaveNote_unsafe(UIButton value);

	@Generated
	public void setSaveNote(UIButton value) {
		com.intel.inde.moe.natj.objc.ObjCObject __old = (com.intel.inde.moe.natj.objc.ObjCObject) saveNote();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setSaveNote_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("doSaveNote:")
	public void doSaveNote(NSObject sender){
        if(data!=null) {
            if (!dataText.text().isEmpty()) {
                data.setNoteForCurrentKey(dataText.text());
            } else {
                data.removeNoteForKey(data.getCurrentKey());
            }
        }

    }

    @Override
    public void touchesBeganWithEvent(NSSet<? extends UITouch> nsSet, UIEvent uiEvent) {
        super.touchesBeganWithEvent(nsSet, uiEvent);
        this.view().endEditing(true);
    }

    @Override
    public boolean textFieldShouldReturn(UITextField textField) {
        noteView().resignFirstResponder();
        return true;
    }
}
