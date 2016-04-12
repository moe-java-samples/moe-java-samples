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


package com.intel.moe.samples.noteqslite.ios;


import com.example.Note;
import com.example.database.ISQLiteDatabase;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.uikit.UIViewController;
import ios.foundation.NSBundle;
import ios.uikit.UITextView;
import java.lang.String;

import ios.foundation.NSCoder;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("DetailViewController")
@RegisterOnStartup
public class DetailViewController extends UIViewController {
    static {
        NatJ.register();
    }


    protected DetailViewController(Pointer peer) {
        super(peer);
    }

    private String detailItem = "";

    public ISQLiteDatabase db;

    private Note detailNote;
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
        // Do any additional setup after loading the view, typically from a nib.
        configureView();
    }

    public void configureView() {
        // Update the user interface for the detail item.
        String currentNote = detailItem;//data.getAllNotes().get(detailItem);
        if (!currentNote.equals("New Note")) {
            // if (dataText != null)
            dataText.setText(currentNote);
        }else{
            dataText.setText("");
        }
        noteView().becomeFirstResponder();

    }




    public void setDetailItem(Note newDetailItem, ISQLiteDatabase db) {
        detailNote = newDetailItem;
        this.db = db;
        if (!detailItem.equals(newDetailItem.getNote())) {
            detailItem = newDetailItem.getNote();

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


	@Selector("doSaveNote:")
	public void doSaveNote( Object sender){
        if (detailNote == null || db == null) {
            return;
        }
        if(!dataText.text().equals("")){
            detailNote.setNote(dataText.text());
            db.update(detailNote);
        }else{
            db.delete(detailNote.getId());
        }

    }

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
}
