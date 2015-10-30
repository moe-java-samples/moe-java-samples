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

package com.intel.inde.moe.samples.elements.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.samples.elements.common.AtomicElement;

import ios.NSObject;
import ios.foundation.NSIndexPath;
import ios.uikit.UIImage;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.protocol.UITableViewDataSource;

public class ElementsSortedByStateDataSource extends NSObject implements UITableViewDataSource, ElementsDataSource {

    @Selector("alloc")
    public static native ElementsSortedByStateDataSource alloc();

    @Selector("init")
    public native ElementsSortedByStateDataSource init();

    protected ElementsSortedByStateDataSource(Pointer peer) {
        super(peer);
    }

    // ElementsDataSource
    @Override
    public String getName() {
        return "State";
    }

    @Override
    public String getNavigationBarName() {
        return "Sorted by State";
    }

    @Override
    public UIImage getTabBarImage() {
        return UIImage.imageNamed("state_gray.png");
    }

    @Override
    public AtomicElement getAtomicElementForIndexPath(NSIndexPath indexPath) {
        int modelRow = (int) indexPath.row();
        AtomicElement element = PeriodicElements.elementSortedByState.get(modelRow);
        return element;
    }

    // UITableViewDataSource
    @Override
    public long tableViewNumberOfRowsInSection(UITableView uiTableView, @NInt long var2) {
        return PeriodicElements.elementSortedByState.size();
    }

    @Override
    public UITableViewCell tableViewCellForRowAtIndexPath(UITableView tableView, NSIndexPath indexPath) {
        String cellID = "AtomicElementTableViewCell";
        AtomicElementTableViewCell cell = (AtomicElementTableViewCell) tableView.dequeueReusableCellWithIdentifier(cellID);

        // Set the element for this cell as specified by the datasource. The atomicElementForIndexPath: is declared
        // as part of the ElementsDataSource interface and will return the appropriate element for the index row
        cell.setElement(this.getAtomicElementForIndexPath(indexPath));

        return cell;
    }

    @Override
    public long numberOfSectionsInTableView(UITableView uiTableView) {
        return 1;
    }
}

