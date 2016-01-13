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

package com.intel.inde.moe.samples.datecell.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.*;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;

import java.util.HashMap;
import java.util.Map;

import ios.foundation.NSArray;
import ios.foundation.NSDate;
import ios.foundation.NSDateFormatter;
import ios.foundation.NSIndexPath;
import ios.foundation.enums.NSDateFormatterStyle;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIDatePicker;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.UIView;
import ios.uikit.enums.UITableViewCellSelectionStyle;
import ios.uikit.enums.UITableViewRowAnimation;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("TableViewController")
@RegisterOnStartup
public class TableViewController extends UITableViewController {

    @Owned
    @Selector("alloc")
    public static native TableViewController alloc();

    @Owned
    @Selector("init")
    public native TableViewController init();

    protected TableViewController(Pointer peer) {
        super(peer);
    }

    // view tag identifiying the date picker view
    private static final long DATE_PICKER_TAG = 99;
    // key for obtaining the data source item's title
    private static final String TITLE_KEY = "title";
    // key for obtaining the data source item's date value
    private static final String DATA_KEY = "date";

    // keep track of which rows have date cells
    private static final int kDateStartRow = 1;
    private static final int kDateEndRow   = 2;

    // the cells with the start or end date
    private static final String DATE_CELL_ID = "dateCell";
    // the cell containing the date picker
    private static final String DATE_PICKER_ID = "datePicker";
    // the remaining cells at the end
    private static final String OTHER_CELL = "otherCell";

    private Map<String, Object>[] dataArray = null;

    private int pickerCellRowHeight;

    // keep track which indexPath points to the cell with UIDatePicker
    private NSIndexPath datePickerIndexPath = null;
	public NSDateFormatter dateFormatter = null;

    @Override
    public void viewDidLoad() {
        // setup our data source
        Map<String, Object> itemOne =  new HashMap<String, Object>();
        itemOne.put(TITLE_KEY, "Tap a cell to change its date:");

        Map<String, Object> itemTwo =  new HashMap<String, Object>();
        itemTwo.put(TITLE_KEY, "Start Date");
        itemTwo.put(DATA_KEY, NSDate.date());

        Map<String, Object> itemThree =  new HashMap<String, Object>();
        itemThree.put(TITLE_KEY, "End Date");
        itemThree.put(DATA_KEY, NSDate.date());

        Map<String, Object> itemFour =  new HashMap<String, Object>();
        itemFour.put(TITLE_KEY, "(other item1)");

        Map<String, Object> itemFive =  new HashMap<String, Object>();
        itemFive.put(TITLE_KEY, "(other item2)");

        dataArray = new Map[] {itemOne, itemTwo, itemThree, itemFour, itemFive};
        // Create and init NSDateFormatter
        dateFormatter = NSDateFormatter.alloc().init();
        // show short-style date format
        dateFormatter.setDateStyle(NSDateFormatterStyle.ShortStyle);
        dateFormatter.setTimeStyle(NSDateFormatterStyle.NoStyle);

        // obtain the picker view cell's height, works because the cell was pre-defined in our storyboard
        UITableViewCell pickerViewCellToCheck = (UITableViewCell) this.tableView().dequeueReusableCellWithIdentifier(DATE_PICKER_ID);
        pickerCellRowHeight = (int)pickerViewCellToCheck.frame().size().height();
    }

    @Selector("doneButton")
    @Property
    public native UIBarButtonItem getDoneButton();

    @Selector("setDoneButton:")
    @Property
    public native void setDoneButton(UIBarButtonItem button);

    @Selector("pickerView")
    @Property
    public native UIDatePicker getPickerView();

    @Selector("setPickerView:")
    @Property
    public native void setPickerView(UIDatePicker picker);

    /*******************************************
     * Utilities
     *******************************************/

    private boolean indexPathHasPicker(NSIndexPath indexPath)
    {
        return (hasInlineDatePicker() && datePickerIndexPath.row() == indexPath.row());
    }

    private boolean indexPathHasDate(NSIndexPath indexPath)
    {
        boolean hasDate = false;

        if ((indexPath.row() == kDateStartRow) ||
                (indexPath.row() == kDateEndRow || (hasInlineDatePicker() && (indexPath.row() == kDateEndRow + 1))))
        {
            hasDate = true;
        }

        return hasDate;
    }

    private boolean hasInlineDatePicker()
    {
        return (datePickerIndexPath != null);
    }

    /*******************************************
     * UITableViewDelegate
     *******************************************/
    @Override
    public double tableViewHeightForRowAtIndexPath(UITableView tableView, NSIndexPath indexPath)
    {
        return (indexPathHasPicker(indexPath) ? pickerCellRowHeight : this.tableView().rowHeight());
    }

    @Override
    public long tableViewNumberOfRowsInSection(UITableView tableView, long section)
    {
        if (hasInlineDatePicker()) {
            // we have a date picker, so allow for it in the number of rows in this section
            long numRows = dataArray.length;
            return ++numRows;
        }

        return dataArray.length;
    }

    @Override
    public UITableViewCell tableViewCellForRowAtIndexPath(UITableView tableView, NSIndexPath indexPath)
    {
        UITableViewCell cell;

        String cellID = OTHER_CELL;
        if (indexPathHasPicker(indexPath)) {
            // The indexPath is the one containing the inline date picker
            cellID = DATE_PICKER_ID;     // the current/opened date picker cell
        } else if (indexPathHasDate(indexPath)) {
            // The indexPath is one that contains the date information
            cellID = DATE_CELL_ID;       // the start/end date cells
        }

        cell = (UITableViewCell)this.tableView().dequeueReusableCellWithIdentifier(cellID);

        if (indexPath.row() == 0) {
            // We decide here that first cell in the table is not selectable (it's just an indicator)
            cell.setSelectionStyle(UITableViewCellSelectionStyle.None);
        }

        // If we have a date picker open whose cell is above the cell we want to update,
        // then we have one more cell than the model allows
        int modelRow = (int) indexPath.row();
        if (datePickerIndexPath != null && datePickerIndexPath.row() <= indexPath.row()) {
            modelRow--;
        }

        Map<String, Object> itemData = dataArray[modelRow];

        // Proceed to configure our cell
        if (cellID.equals(DATE_CELL_ID)) {
            // We have either start or end date cells, populate their date field
            cell.textLabel().setText((String) itemData.get(TITLE_KEY));
            String dateString = dateFormatter.stringFromDate((NSDate) itemData.get(DATA_KEY));
            cell.detailTextLabel().setText(dateString);
        } else if (cellID.equals(OTHER_CELL)) {
            // This cell is a non-date cell, just assign it's text label
            cell.textLabel().setText((String) itemData.get(TITLE_KEY));
        }

        return cell;
    }

    /*******************************************
     * UITableViewDelegate
     *******************************************/
    @Override
    public void tableViewDidSelectRowAtIndexPath(UITableView tableView, NSIndexPath indexPath) {
        try {
            UITableViewCell cell = tableView.cellForRowAtIndexPath(indexPath);
            if (cell.reuseIdentifier().equals(DATE_CELL_ID)) {
                displayInlineDatePickerForRowAtIndexPath(indexPath);
            } else {
                tableView.deselectRowAtIndexPathAnimated(indexPath, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*******************************************
     * Animation utilities
     *******************************************/
    protected void displayInlineDatePickerForRowAtIndexPath(NSIndexPath indexPath)
    {
        // Display the date picker inline with the table content
        tableView().beginUpdates();

        boolean before = false;   // indicates if the date picker is below "indexPath", help us determine which row to reveal
        boolean sameCellClicked = false;
        if (hasInlineDatePicker()) {
            before = datePickerIndexPath.row() < indexPath.row();
            sameCellClicked = (datePickerIndexPath.row() - 1 == indexPath.row());
        }

        // Remove any date picker cell if it exists
        if (hasInlineDatePicker()) {
            NSArray indexPaths = NSArray.arrayWithObject(NSIndexPath.indexPathForRowInSection(datePickerIndexPath.row(), 0));

            tableView().deleteRowsAtIndexPathsWithRowAnimation(
                    indexPaths,
                    UITableViewRowAnimation.Fade);

            datePickerIndexPath = null;
        }

        if (!sameCellClicked) {
            // Hide the old date picker and display the new one
            int rowToReveal = (before ? (int)indexPath.row() - 1 : (int)indexPath.row());
            NSIndexPath indexPathToReveal = NSIndexPath.indexPathForRowInSection(rowToReveal, 0);

            toggleDatePickerForSelectedIndexPath(indexPathToReveal);
            datePickerIndexPath = NSIndexPath.indexPathForRowInSection(indexPathToReveal.row() + 1, 0);
        }

        // Always deselect the row containing the start or end date
        this.tableView().deselectRowAtIndexPathAnimated(indexPath, true);

        this.tableView().endUpdates();

        // Inform our date picker of the current date to match the current cell
        updateDatePicker();
    }

    private void toggleDatePickerForSelectedIndexPath(NSIndexPath indexPath)
    {
        this.tableView().beginUpdates();

        NSArray indexPaths = NSArray.arrayWithObject(NSIndexPath.indexPathForRowInSection(indexPath.row() + 1, 0));

        // check if 'indexPath' has an attached date picker below it
        if (hasPickerForIndexPath(indexPath)) {
            // found a picker below it, so remove it
            this.tableView().deleteRowsAtIndexPathsWithRowAnimation(indexPaths,
                    UITableViewRowAnimation.Fade);
        } else {
            // didn't find a picker below it, so we should insert it
            this.tableView().insertRowsAtIndexPathsWithRowAnimation(indexPaths,
                    UITableViewRowAnimation.Fade);
        }

        this.tableView().endUpdates();
    }

    private void updateDatePicker()
    {
        if (datePickerIndexPath != null) {
            UITableViewCell associatedDatePickerCell = this.tableView().cellForRowAtIndexPath(datePickerIndexPath);

            UIDatePicker targetedDatePicker = (UIDatePicker)associatedDatePickerCell.viewWithTag(DATE_PICKER_TAG);
            if (targetedDatePicker != null) {
                // we found a UIDatePicker in this cell, so update it's date value
                Map itemData = dataArray[(int) datePickerIndexPath.row() - 1];
                targetedDatePicker.setDateAnimated((NSDate)itemData.get(DATA_KEY), false);
            }
        }
    }

    private boolean hasPickerForIndexPath(NSIndexPath indexPath)
    {
        boolean hasDatePicker;

        int targetedRow = (int) indexPath.row();
        targetedRow++;

        NSIndexPath index = NSIndexPath.indexPathForRowInSection(targetedRow, 0);
        UITableViewCell checkDatePickerCell = this.tableView().cellForRowAtIndexPath(index);
        UIView checkDatePicker = (checkDatePickerCell.viewWithTag(DATE_PICKER_TAG));

        hasDatePicker = (checkDatePicker != null);
        return hasDatePicker;
    }

    /*******************************************
     * Action handler
     *******************************************/

    @Selector("dateAction:")
    public void dateAction(UIDatePicker sender)
    {
        NSIndexPath targetedCellIndexPath;

        if (hasInlineDatePicker()) {
            // Inline date picker: update the cell's date "above" the date picker cell
            targetedCellIndexPath = NSIndexPath.indexPathForRowInSection(datePickerIndexPath.row() - 1, 0);
        } else {
            // External date picker: update the current "selected" cell's date
            targetedCellIndexPath = this.tableView().indexPathForSelectedRow();
        }

        if (targetedCellIndexPath != null) {
            UITableViewCell cell = this.tableView().cellForRowAtIndexPath(targetedCellIndexPath);
            UIDatePicker targetedDatePicker = (UIDatePicker) sender;

            // Update our data model
            Map<String, Object> itemData = dataArray[(int) targetedCellIndexPath.row()];
            itemData.put(DATA_KEY, targetedDatePicker.date());

            // Update the cell's date string
            cell.detailTextLabel().setText(dateFormatter.stringFromDate(targetedDatePicker.date()));
        }
    }
}
