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

package com.intel.wordsearch;

import ios.NSObject;
import ios.coregraphics.struct.CGRect;
import ios.foundation.NSDictionary;
import ios.uikit.UIApplication;
import ios.uikit.UIColor;
import ios.uikit.UIImage;
import ios.uikit.UINavigationController;
import ios.uikit.UIScreen;
import ios.uikit.UIViewController;
import ios.uikit.UIWindow;
import ios.uikit.c.UIKit;
import ios.uikit.enums.UIBarStyle;
import ios.uikit.protocol.UIApplicationDelegate;

import com.intel.wordsearch.ui.WordSearchController;
import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ann.Selector;

@RegisterOnStartup
public class Main extends NSObject implements UIApplicationDelegate {

    static
    {
        NatJ.register();
    }

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
    @Selector("application:didFinishLaunchingWithOptions:")
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        UIColor intelBlue = UIColor.alloc().initWithRedGreenBlueAlpha(0.0, 113/255.f, 197/255.f, 1.0);

        UIViewController vc = WordSearchController.alloc().init();

        UINavigationController navigationController = UINavigationController.alloc().init();
        navigationController.navigationBar().setBarStyle(UIBarStyle.Black);
        navigationController.navigationBar().setBarTintColor(intelBlue);
        navigationController.navigationBar().setShadowImage(UIImage.alloc().init());
        navigationController.navigationBar().setTranslucent(false);
        navigationController.navigationBar().setTintColor(UIColor.whiteColor());

        navigationController.initWithRootViewController(vc);

        UIScreen screen = UIScreen.mainScreen();

        CGRect bounds = screen.bounds();
        window = UIWindow.alloc().initWithFrame(bounds);

        window.setRootViewController(navigationController);

        window.makeKeyAndVisible();

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
}
