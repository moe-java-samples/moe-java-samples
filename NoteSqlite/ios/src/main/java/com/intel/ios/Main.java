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


package com.intel.ios;

import ios.NSObject;
import ios.foundation.NSDictionary;
import ios.uikit.UIApplication;
import ios.uikit.UINavigationController;
import ios.uikit.UISplitViewController;
import ios.uikit.UIViewController;
import ios.uikit.UIWindow;
import ios.uikit.c.UIKit;
import ios.uikit.protocol.UIApplicationDelegate;
import ios.uikit.protocol.UISplitViewControllerDelegate;

import com.intel.ios.ui.DetailViewController;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.Class;
import com.intel.inde.moe.natj.objc.ann.Selector;

@RegisterOnStartup
public class Main extends NSObject implements UIApplicationDelegate, UISplitViewControllerDelegate {

    static
    {
        NatJ.register();
    }

    public static void main(String[] args) {
        UIKit.UIApplicationMain(0, null, null, Main.class.getName());
    }

    @Generated("NatJ")
    @Selector("alloc")
    public static native Main alloc();

    @Generated("NatJ")
    protected Main(Pointer peer) {
        super(peer);
    }

    private UIWindow window;

    @Override
    @Selector("application:didFinishLaunchingWithOptions:")
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        // Override point for customization after application launch.
        UISplitViewController splitViewController = (UISplitViewController) window.rootViewController();
        UINavigationController navigationController = (UINavigationController) splitViewController.viewControllers().lastObject();

        navigationController.topViewController().navigationItem().setLeftBarButtonItem(splitViewController.displayModeButtonItem());

        splitViewController.setDelegate(this);
        return true;
    }

    @Override
    @Selector("setWindow:")
    public void setWindow(UIWindow value) {
        window = value;
    }

    @Override
    @Selector("window")
    public UIWindow window() {
        return window;
    }

    @Override
    @Generated
    public boolean splitViewControllerCollapseSecondaryViewControllerOntoPrimaryViewController(UISplitViewController splitViewController, UIViewController secondaryViewController, UIViewController primaryViewController) {
        if (secondaryViewController.isKindOfClass(com.intel.inde.moe.natj.objc.Class.fromJavaClass(UINavigationController.class)) &&
                ((UINavigationController)secondaryViewController).topViewController().isKindOfClass(Class.fromJavaClass(DetailViewController.class)) &&
                ((DetailViewController)((UINavigationController) secondaryViewController).topViewController()).getDetailItem() == "") {
            // Return YES to indicate that we have handled the collapse by doing nothing; the secondary controller will be discarded.
            return true;
        }
        return false;
    }
}
