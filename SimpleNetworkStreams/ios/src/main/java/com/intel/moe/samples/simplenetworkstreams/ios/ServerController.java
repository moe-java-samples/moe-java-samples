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

package com.intel.moe.samples.simplenetworkstreams.ios;

import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.simplenetworkstreams.common.Networking;
import com.intel.moe.samples.simplenetworkstreams.common.SocketServerThreadBase;

import ios.NSObject;
import ios.c.Globals;
import ios.uikit.UIColor;
import ios.uikit.UILabel;
import ios.uikit.UITextView;
import ios.uikit.UIViewController;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("ServerController")
@RegisterOnStartup
public class ServerController extends UIViewController {

    static {
        NatJ.register();
    }

    @Selector("alloc")
    public static native ServerController alloc();

    @Selector("init")
    public native ServerController init();

    protected ServerController(Pointer peer) {
        super(peer);
    }

    // stuff for IB

    @Selector("addressLabel")
    @Property
    public native UILabel getAddressLabel();

    @Selector("portLabel")
    @Property
    public native UILabel getPortLabel();

    @Selector("logTextView")
    @Property
    public native UITextView getLogTextView();

    private SocketServerThread socketServerThread;

    @Selector("viewDidLoad")
    @Override
    public void viewDidLoad() {
        getLogTextView().layer().setBorderWidth(0.5);
        UIColor borderColor = UIColor.lightGrayColor();
        getLogTextView().layer().setBorderColor(borderColor.CGColor());
        getLogTextView().layer().setCornerRadius(5.0);

        String ipAddress = Networking.getIpAddress();
        getAddressLabel().setText(ipAddress);

        socketServerThread = new SocketServerThread();
        socketServerThread.start();
    }

    private class SocketServerThread extends SocketServerThreadBase {

        @Override
        public void onInfo(final String message) {
            Globals.dispatch_async(Globals.dispatch_get_main_queue(), new Globals.Block_dispatch_async() {
                @Override
                public void call_dispatch_async() {
                    getPortLabel().setText(message);
                }
            });
        }

        @Override
        public void onLog(final String message) {
            Globals.dispatch_async(Globals.dispatch_get_main_queue(), new Globals.Block_dispatch_async() {
                @Override
                public void call_dispatch_async() {
                    getLogTextView().setText(getLogTextView().text() + message + "\n");
                }
            });
        }
    }


    @Selector("clearAction:")
    public void clearAction(NSObject sender) {
        getLogTextView().setText("");
    }
}
