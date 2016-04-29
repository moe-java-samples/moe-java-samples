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

package com.intel.moe.samples.lazytableloader.ios;

import com.intel.moe.samples.lazytableloader.common.Bookmarks;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.lazytableloader.common.TopAppParser;

import ios.c.Globals;
import ios.foundation.NSDictionary;
import ios.uikit.UIAlertView;
import ios.uikit.UIApplication;
import ios.uikit.UINavigationController;
import ios.uikit.UIResponder;
import ios.uikit.UIWindow;
import ios.uikit.protocol.UIApplicationDelegate;

@ObjCClassName("LazyTableAppDelegate")
public class LazyTableAppDelegate extends UIResponder implements UIApplicationDelegate {

    @Owned
    @Selector("alloc")
    public static native LazyTableAppDelegate alloc();

    @Selector("init")
    public native LazyTableAppDelegate init();

    protected LazyTableAppDelegate(Pointer peer) {
        super(peer);
    }

    private UIWindow window;

    @Selector("window")
    public UIWindow getWindow()
    {
        return window;
    }

    @Selector("setWindow:")
    public void setWindow(UIWindow window)
    {
         this.window = window;
    }

    private Thread loaderThread;

    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        if (loaderThread == null) {
            loaderThread = new Thread(new Runnable() {

                @Override
                public void run() {

                    final TopAppParser topAppParser = new TopAppParser(Bookmarks.TopPaidAppsFeed);
                    if (!topAppParser.getLastErrorMessage().isEmpty()) {
                        UIApplication.sharedApplication().setNetworkActivityIndicatorVisible(false);
                        handleError(topAppParser.getLastErrorMessage());
                        return;
                    }

                    Globals.dispatch_sync(Globals.dispatch_get_main_queue(), new Globals.Block_dispatch_sync() {

                        @Override
                        public void call_dispatch_sync() {
                            UIApplication.sharedApplication().setNetworkActivityIndicatorVisible(false);


                            RootViewController rootViewController = (RootViewController)((UINavigationController)window.rootViewController()).topViewController();

                            rootViewController.setEntries(topAppParser.getItems());

                            // tell our table view to reload its data, now that parsing has completed
                            rootViewController.tableView().reloadData();
                        }
                    });
                }
            });
            loaderThread.start();
            UIApplication.sharedApplication().setNetworkActivityIndicatorVisible(true);
        }

        return true;
    }

    private void handleError(String errorMessage) {
        UIAlertView alertView =
                UIAlertView.alloc().init();
        alertView.setMessage("Cannot Show Top Paid Apps: " + errorMessage);
        alertView.show();
    }
}
