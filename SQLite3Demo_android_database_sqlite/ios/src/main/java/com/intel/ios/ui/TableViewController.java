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
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.NFloat;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import com.intel.inde.moe.sqlite.DBManager;
import com.intel.ios.IOSDBManagerFactory;

import java.util.List;

import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.foundation.NSIndexPath;
import ios.uikit.UIStoryboardSegue;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.enums.UITableViewCellEditingStyle;
import ios.uikit.protocol.UITableViewDataSource;
import ios.uikit.protocol.UITableViewDelegate;

@Generated
@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("TableViewController")
@RegisterOnStartup
public class TableViewController extends UITableViewController implements UITableViewDelegate, UITableViewDataSource {

	public static final String TAG = "DetailViewController";
	private static final String CELL_ID = "ItemCell";
	private List<List<String>> arrPeopleInfo;


	private DBManager manager;
	private int recordIDToEdit = -1;

	static {
		NatJ.register();
	}

	@Generated
	protected TableViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native TableViewController alloc();

	@Generated
	@Selector("init")
	public native TableViewController init();

	@Selector("viewDidLoad")
	public void viewDidLoad() {

		manager = new IOSDBManagerFactory().getDbManager();

		loadData();

	}

	@Generated
	@Selector("initWithCoder:")
	public native TableViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native TableViewController initWithNibNameBundle(String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("initWithStyle:")
	public native TableViewController initWithStyle(@NInt long style);

	@NInt
	@Selector("numberOfSectionsInTableView:")
	@Override
	public long numberOfSectionsInTableView(UITableView tableView) {
		return 1;
	}

	@NInt
	@Selector("tableView:numberOfRowsInSection:")
	@Override
	public long tableViewNumberOfRowsInSection(UITableView tableView,
											   @NInt long section) {
		return arrPeopleInfo.size();
	}

	public void loadData() {
		// Form the query.
		String query = manager.createSelectAllQuery();

		// Get the results.
		if (this.arrPeopleInfo != null) {
			arrPeopleInfo.clear();
		}
		this.arrPeopleInfo = manager.loadDataFromDB(query);


		// Reload the table view.
		tableView().reloadData();
	}

	@Selector("tableView:cellForRowAtIndexPath:")
	@Override
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		TableViewCell cell = (TableViewCell) tableView.dequeueReusableCellWithIdentifierForIndexPath(CELL_ID, indexPath);

		List<String> arrDataRow = arrPeopleInfo.get((int) indexPath.row());
		String firstName = arrDataRow.get(manager.getFirstnameIdx());
		String lastName = arrDataRow.get(manager.getLastNameIdx());

		cell.titleLabel().setText(firstName + " " + lastName);
		cell.subTitleLabel().setText(arrDataRow.get(manager.getAgeIdx()));

		return cell;
	}

	@Selector("prepareForSegue:sender:")
	@Override
	public void prepareForSegueSender(UIStoryboardSegue segue, Object sender) {
		if (segue.identifier().equals("idSegueEditInfo")) {

			EditInfoViewController dest = (EditInfoViewController) segue.destinationViewController();
			dest.setDelegate(this);
			dest.setRecordIDToEdit(recordIDToEdit);

		}
	}

	@Selector("tableView:heightForRowAtIndexPath:")
	@NFloat
	public double tableViewHeightForRowAtIndexPath(UITableView var1, NSIndexPath var2) {
		return 60.0;
	}


	public void editingInfoWasFinished() {
		// Reload the data.
		loadData();
	}

	@Selector("tableView:commitEditingStyle:forRowAtIndexPath:")
	public void tableViewCommitEditingStyleForRowAtIndexPath(UITableView uiTableView, @NInt long editingStyle, NSIndexPath indexPath) {

		if (editingStyle == UITableViewCellEditingStyle.Delete) {
			// Delete the selected record.
			// Find the record ID.
			int recordIDToDelete = Integer.valueOf(arrPeopleInfo.get((int) indexPath.row()).get(0));

			// Prepare the query.
			String query = manager.createDeleteQuery(recordIDToDelete);

			// Execute the query.
			manager.executeQuery(query);

			// Reload the table view.
			loadData();
		}
	}

	@Selector("addNewRecord:")
	public void addNewRecord(@Mapped(ObjCObjectMapper.class) Object sender) {
		this.recordIDToEdit = -1;
		performSegueWithIdentifierSender("idSegueEditInfo", this);
	}

	@IsOptional
	@Selector("tableView:accessoryButtonTappedForRowWithIndexPath:")
	public void tableViewAccessoryButtonTappedForRowWithIndexPath(UITableView var1, NSIndexPath indexPath) {
		this.recordIDToEdit = Integer.valueOf(arrPeopleInfo.get((int) indexPath.row()).get(0));
		performSegueWithIdentifierSender("idSegueEditInfo", this);
	}
}