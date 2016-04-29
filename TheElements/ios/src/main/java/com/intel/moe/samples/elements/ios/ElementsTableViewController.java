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

package com.intel.moe.samples.elements.ios;

import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.elements.common.AtomicElement;

import ios.NSObject;
import ios.foundation.NSIndexPath;
import ios.uikit.UIStoryboardSegue;
import ios.uikit.UITableViewController;
import ios.uikit.protocol.UITableViewDataSource;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("ElementsTableViewController")
@RegisterOnStartup
public class ElementsTableViewController extends UITableViewController {

    private UITableViewDataSource dataSource;

    @Selector("alloc")
    public static native ElementsTableViewController alloc();

    @Selector("init")
    public native ElementsTableViewController init();

    protected ElementsTableViewController(Pointer peer) {
        super(peer);
    }

    public void setDataSource(UITableViewDataSource dataSource) {
        // Retain the data source
        this.dataSource = dataSource;

        // Set the title, and tab bar images from the dataSource object.
        // These are part of the ElementsDataSource interface
        ElementsDataSource elementsDataSource = (ElementsDataSource) dataSource;
        this.setTitle(elementsDataSource.getName());
        this.tabBarItem().setImage(elementsDataSource.getTabBarImage());

        // Set the long name shown in the navigation bar
        this.navigationItem().setTitle(elementsDataSource.getNavigationBarName());
    }

    @Override
    public void viewDidLoad() {
        tableView().setSectionIndexMinimumDisplayRowCount(10);

        tableView().setDelegate(this);
        tableView().setDataSource(dataSource);

        // Create a custom navigation bar button and set it to always say "back"
        //UIBarButtonItem temporaryBarButtonItem = UIBarButtonItem.alloc().init();

    }

    @Override
    public void viewWillAppear(boolean animated) {
        // Force the tableview to load
        this.tableView().reloadData();
    }

    @Override
    public void prepareForSegueSender(UIStoryboardSegue segue, Object sender) {
        if (segue.identifier().equals("showDetail")) {
            NSIndexPath selectedIndexPath = tableView().indexPathForSelectedRow();

            // Find the right view controller
            AtomicElement element = ((ElementsDataSource) this.dataSource).getAtomicElementForIndexPath(selectedIndexPath);
            AtomicElementViewController viewController = (AtomicElementViewController) segue.destinationViewController();

            // Hide the bottom tabbar when we push this view controller
            viewController.setHidesBottomBarWhenPushed(true);

            // Pass the element to this detail view controller
            viewController.setElement(element);
        }
    }
}
