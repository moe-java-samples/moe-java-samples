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
package com.intel.inapppurchase.ui;

import com.intel.inapppurchase.common.ProductDetails;
import com.intel.inapppurchase.common.ProductsStore;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.NSObject;
import ios.coregraphics.c.CoreGraphics;
import ios.foundation.NSIndexPath;
import ios.foundation.NSMutableArray;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIButton;
import ios.uikit.UIRefreshControl;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIControlState;
import ios.uikit.enums.UITableViewCellAccessoryType;
import ios.uikit.enums.UITableViewRowAnimation;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("MasterViewController")
@RegisterOnStartup
public class MasterViewController extends UITableViewController {
    ProductsStore productsStore = null;

    protected MasterViewController(Pointer peer) {
        super(peer);
    }

    @Owned
    @Selector("alloc")
    public static native MasterViewController alloc();

    @Selector("init")
    public native MasterViewController init();

    @Override
    public long numberOfSectionsInTableView(UITableView uiTableView) {
        return 1;
    }

    @Override
    public UITableViewCell tableViewCellForRowAtIndexPath(UITableView uiTableView, NSIndexPath nsIndexPath) {
        UITableViewCell cell = uiTableView.dequeueReusableCellWithIdentifierForIndexPath("Cell", nsIndexPath);

        ProductDetails product = productsStore.getProductAt((int) nsIndexPath.row());

        cell.textLabel().setText(product.getTitle());

        cell.detailTextLabel().setText(product.getPrice());

        if (productsStore != null && productsStore.isProductPurchased(product.getProductID())) {
            cell.setAccessoryType(UITableViewCellAccessoryType.Checkmark);
            cell.setAccessoryView(null);
            cell.detailTextLabel().setText("");
        } else {
            UIButton button = UIButton.alloc().initWithFrame(CoreGraphics.CGRectMake(0, 0, 72, 37));
            button.setTitleColorForState(view().tintColor(), UIControlState.Normal);
            button.setTitleForState("Buy", UIControlState.Normal);
            button.setTag(nsIndexPath.row());
            button.addTargetActionForControlEvents(this, new SEL("buyButtonTapped:"), UIControlEvents.TouchUpInside);
            cell.setAccessoryType(UITableViewCellAccessoryType.None);
            cell.setAccessoryView(button);
        }

        return cell;
    }

    @Override
    public long tableViewNumberOfRowsInSection(UITableView uiTableView, @NInt long l) {
        return productsStore.getNumberOfProducts();
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        productsStore = new ProductsStore("IAPHelperProductPurchasedNotification");
        setTitle("In App Rage");

        UIRefreshControl refreshControl = UIRefreshControl.alloc().init();
        refreshControl.addTargetActionForControlEvents(this, new SEL("reload"), UIControlEvents.ValueChanged);

        setRefreshControl(refreshControl);

        reload();

        refreshControl().beginRefreshing();

        UIBarButtonItem restoreButton = UIBarButtonItem.alloc().initWithTitleStyleTargetAction("Restore", UIBarButtonItemStyle.Plain, this, new SEL("restoreTapped:"));
        navigationItem().setRightBarButtonItem(restoreButton);

        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this, new SEL("productPurchased:"), productsStore.getIAPHelperPublicKey(), null);
    }

    @Selector("reload")
    private void reload() {
        tableView().reloadData();
        productsStore.requestProductsWithCompletionHandler(new ProductsStore.RequestProductsCompletionHandler() {
            @Override
            public void callback(boolean status) {
                if (status) {
                    tableView().reloadData();
                }
                refreshControl().endRefreshing();
            }
        });
    }

    @Selector("restoreTapped:")
    void restoreTapped(NSObject sender) {
        if (productsStore != null) {
            productsStore.restoreCompletedTransactions();
        }
    }

    @Selector("buyButtonTapped:")
    void buyButtonTapped(UIButton sender) {
        ProductDetails product = productsStore.getProductAt((int) sender.tag());
        final UIButton buyButton = sender;
        if (productsStore != null) {
            productsStore.purchaseProduct(product, new ProductsStore.RequestPurchaseProductsHandler() {
                @Override
                public void callback(boolean status) {
                    buyButton.setEnabled(!status);
                }
            });
        }
    }

    @Selector("productPurchased:")
    void productPurchased(NSNotification sender) {
        String productIdentifier = (String) sender.object();
        for (int i = 0; i < productsStore.getNumberOfProducts(); i++) {
            if (productsStore.getProductAt(i).getProductID().equals(productIdentifier)) {
                NSMutableArray array = NSMutableArray.array();
                array.add(NSIndexPath.indexPathForRowInSection(i, 0));
                tableView().reloadRowsAtIndexPathsWithRowAnimation(array, UITableViewRowAnimation.Fade);
                break;
            }
        }
    }
}
