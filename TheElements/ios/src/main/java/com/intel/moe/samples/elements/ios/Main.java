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
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.objc.ann.Selector;

import ios.NSObject;
import ios.foundation.NSDictionary;
import ios.foundation.NSMutableArray;
import ios.uikit.UIApplication;
import ios.uikit.UINavigationController;
import ios.uikit.UIStoryboard;
import ios.uikit.UITabBarController;
import ios.uikit.UIWindow;
import ios.uikit.c.UIKit;
import ios.uikit.protocol.UIApplicationDelegate;
import ios.uikit.protocol.UITableViewDataSource;

public class Main extends NSObject implements UIApplicationDelegate {

    public static void main(String[] args) {
        UIKit.UIApplicationMain(0, null, null, Main.class.getName());
    }

    @Selector("alloc")
    public static native Main alloc();

    protected Main(Pointer peer) {
        super(peer);
    }

    private UIWindow window;

    @Override
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        UITabBarController tabBarController = (UITabBarController) window().rootViewController();

        UITableViewDataSource dataSource;

        UIStoryboard storyboard = UIStoryboard.storyboardWithNameBundle("MainUI", null);

        NSMutableArray viewControllers = NSMutableArray.arrayWithCapacity(4);

        PeriodicElements.setupElementsArray();

        // Create our tabbar view controllers, since we already have one defined in our storyboard
        // we will create 3 more instances of it, and assign each it's own kind data source

        // By name
        String navForTableViewID = "navForTableView";
        UINavigationController navController = (UINavigationController) storyboard.instantiateViewControllerWithIdentifier("navForTableView");
        ElementsTableViewController viewController = (ElementsTableViewController) navController.topViewController();
        dataSource = ElementsSortedByNameDataSource.alloc().init();
        viewController.setDataSource(dataSource);
        viewControllers.addObject(navController);

        // By atomic number
        navController = (UINavigationController) storyboard.instantiateViewControllerWithIdentifier(navForTableViewID);
        viewController = (ElementsTableViewController) navController.topViewController();
        dataSource = ElementsSortedByAtomicNumberDataSource.alloc().init();
        viewController.setDataSource(dataSource);
        viewControllers.addObject(navController);

        // By symbol
        navController = (UINavigationController) storyboard.instantiateViewControllerWithIdentifier(navForTableViewID);
        viewController = (ElementsTableViewController) navController.topViewController();
        dataSource = ElementsSortedBySymbolDataSource.alloc().init();
        viewController.setDataSource(dataSource);
        viewControllers.addObject(navController);

        // By state
        navController = (UINavigationController) storyboard.instantiateViewControllerWithIdentifier(navForTableViewID);
        viewController = (ElementsTableViewController) navController.topViewController();
        dataSource = ElementsSortedByStateDataSource.alloc().init();
        viewController.setDataSource(dataSource);
        viewControllers.addObject(navController);

        tabBarController.setViewControllers(viewControllers);

        return true;
    }

    @Override
    public void setWindow(UIWindow value) {
        window = value;
    }

    @Override
    public UIWindow window() {
        return window;
    }
}
