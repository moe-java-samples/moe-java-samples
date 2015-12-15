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

package com.intel.libgdxmissilecommand.ios;

import ios.NSObject;
import ios.foundation.NSDictionary;
import ios.uikit.UIApplication;
import ios.uikit.c.UIKit;
import ios.uikit.protocol.UIApplicationDelegate;

import com.badlogic.gdx.backends.intel.moe.IOSApplication;
import com.badlogic.gdx.backends.intel.moe.IOSApplicationConfiguration;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.libgdxmissilecommand.common.MissileCommand;

@ObjCClassName("Main")
public class Main extends NSObject implements UIApplicationDelegate {

    private IOSApplication app;

    protected Main(Pointer peer) {
        super(peer);
    }

    @Override
    @Selector("application:didFinishLaunchingWithOptions:")
    public boolean applicationDidFinishLaunchingWithOptions(
            UIApplication application, NSDictionary launchOptions) {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        this.app = new IOSApplication(new MissileCommand(), config);
        return app.didFinishLaunching(application, launchOptions);
    }

    @Override
    @IsOptional
    @Selector("applicationDidBecomeActive:")
    public void applicationDidBecomeActive(UIApplication application) {
        app.didBecomeActive(application);
    }

    @Override
    @IsOptional
    @Selector("applicationWillResignActive:")
    public void applicationWillResignActive(UIApplication application) {
        app.willResignActive(application);
    }

    @Override
    @IsOptional
    @Selector("applicationWillTerminate:")
    public void applicationWillTerminate(UIApplication application) {
        app.willTerminate(application);
    }

    @Selector("alloc")
    public static native Main alloc();

    public static void main(String[] args) {
        UIKit.UIApplicationMain(0, null, null, Main.class.getSimpleName());
    }
}