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


import android.util.Log;

import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.Mapped;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Property;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.natj.objc.map.ObjCObjectMapper;
import com.intel.moe.sqlite.DBManager;
import com.intel.ios.IOSDBManagerFactory;

import java.util.List;

import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.protocol.UITextFieldDelegate;

@Generated
@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("EditInfoViewController")
@RegisterOnStartup
public class EditInfoViewController extends UIViewController implements UITextFieldDelegate {

	public static final String TAG = "EditInfoViewController";

	private DBManager manager;

	private int recordIDToEdit = -1;

    private TableViewController delegate;

	static {
		NatJ.register();
	}

	@Generated
	protected EditInfoViewController(Pointer peer) {
		super(peer);
	}

	@Selector("viewDidLoad")
	public void viewDidLoad() {
		manager = new IOSDBManagerFactory().getDbManager();

		// Check if should load specific record for editing.
		if (recordIDToEdit != -1) {
			// Load the record with the specific ID from the database.
			loadInfoToEdit();
		}
	}

	@Generated
	@Selector("ageField")
	@Property
	public native UITextField getAgeField();

	@Generated
	@Owned
	@Selector("alloc")
	public static native EditInfoViewController alloc();

	@Selector("doSave:")
	public void doSave(@Mapped(ObjCObjectMapper.class) Object sender) {
		String firstName = getFirstNameField().text();
		String lastName = getLastNameField().text();
		String age = getAgeField().text();

		String query;

		try {
			if (recordIDToEdit != -1) {
				query = manager.createUpdateQuery(firstName, lastName, Integer.valueOf(age), recordIDToEdit);
			} else {
				query = manager.createNewRecordQuery(firstName, lastName, Integer.valueOf(age));
			}
		}  catch(NumberFormatException ex){
			Log.d(TAG, "Age is not number!");
			return;
		}

            // Execute the query.
            manager.executeQuery(query);

            // If the query was successfully executed then pop the view controller.
            if (manager.affectedRows != 0) {
                Log.d(TAG, "Query was executed successfully. Affected rows = " + manager.affectedRows);

                // Inform the delegate that the editing was finished.
                delegate.editingInfoWasFinished();

                // Pop the view controller.
                navigationController().popViewControllerAnimated(true);
            }
            else{
                Log.d(TAG, "Could not execute the query.");
            }

	}

	@Generated
	@Selector("firstNameField")
	@Property
	public native UITextField getFirstNameField();

	@Generated
	@Selector("init")
	public native EditInfoViewController init();

	@Generated
	@Selector("initWithCoder:")
	public native EditInfoViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native EditInfoViewController initWithNibNameBundle(String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("lastNameField")
	@Property
	public native UITextField getLastNameField();

	@Generated
	@Selector("setAgeField:")
	public native void setAgeField_unsafe(UITextField value);

	@Generated
	public void setAgeField(UITextField value) {
		com.intel.moe.natj.objc.ObjCObject __old = (com.intel.moe.natj.objc.ObjCObject) getAgeField();
		if (value != null) {
			com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setAgeField_unsafe(value);
		if (__old != null) {
			com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setFirstNameField:")
	public native void setFirstNameField_unsafe(UITextField value);

	@Generated
	public void setFirstNameField(UITextField value) {
		com.intel.moe.natj.objc.ObjCObject __old = (com.intel.moe.natj.objc.ObjCObject) getFirstNameField();
		if (value != null) {
			com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setFirstNameField_unsafe(value);
		if (__old != null) {
			com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setLastNameField:")
	public native void setLastNameField_unsafe(UITextField value);

	@Generated
	public void setLastNameField(UITextField value) {
		com.intel.moe.natj.objc.ObjCObject __old = (com.intel.moe.natj.objc.ObjCObject) getLastNameField();
		if (value != null) {
			com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setLastNameField_unsafe(value);
		if (__old != null) {
			com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old);
		}
	}

	@Override
	public boolean textFieldShouldReturn(UITextField textField) {
		textField.resignFirstResponder();
		return true;
	}

	public void setRecordIDToEdit(int recordIDToEdit) {
		this.recordIDToEdit = recordIDToEdit;
	}

	private void loadInfoToEdit() {

		if (recordIDToEdit != -1) {
			// Create the query.
			String query = manager.createSelectRecordWithId(recordIDToEdit);

			// Load the relevant data.
			List<List<String>> results = manager.loadDataFromDB(query);

			// Set the loaded data to the textfields.
			getFirstNameField().setText(results.get(0).get(manager.getFirstnameIdx()));
			getLastNameField().setText(results.get(0).get(manager.getLastNameIdx()));
			getAgeField().setText(results.get(0).get(manager.getAgeIdx()));
		}
	}

    public void setDelegate(TableViewController tableViewController) {
        this.delegate = tableViewController;
    }
}