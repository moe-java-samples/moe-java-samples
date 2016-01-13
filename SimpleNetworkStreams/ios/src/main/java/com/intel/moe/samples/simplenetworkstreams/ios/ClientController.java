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

import ios.NSObject;
import ios.uikit.UIAlertView;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UILabel;
import ios.uikit.UITextField;
import ios.uikit.UITextView;
import ios.uikit.UIViewController;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("ClientController")
@RegisterOnStartup
public class ClientController extends UIViewController {

    static {
        NatJ.register();
    }

    @Selector("alloc")
    public static native ClientController alloc();

    @Selector("init")
    public native ClientController init();

    protected ClientController(Pointer peer) {
        super(peer);
    }

    // stuff for IB

    @Selector("addressTextField")
    @Property
    public native UITextField getAddressTextField();

    @Selector("portTextField")
    @Property
    public native UITextField getPortTextField();

    @Selector("messageTextView")
    @Property
    public native UITextView getMessageTextView();

    @Selector("connectAndSendButton")
    @Property
    public native UIButton getConnectAndSendButton();

    @Selector("statusLabel")
    @Property
    public native UILabel getStatusLabel();

    @Selector("viewDidLoad")
    @Override
    public void viewDidLoad() {
        getMessageTextView().layer().setBorderWidth(0.5);
        UIColor borderColor = UIColor.lightGrayColor();
        getMessageTextView().layer().setBorderColor(borderColor.CGColor());
        getMessageTextView().layer().setCornerRadius(5.0);
    }

    @Selector("connectAndSendAction:")
    public void connectAndSendAction(NSObject sender) {
        String message = getMessageTextView().text();
        if(message.equals("")) {
            message = null;
            UIAlertView toast = UIAlertView.alloc().initWithTitleMessageDelegateCancelButtonTitleOtherButtonTitles(
                    null, "Please provide message text", null, null, null
            );
            toast.show();
            toast.dismissWithClickedButtonIndexAnimated(0, true);
        }

        ClientTask clientTask = new ClientTask(
                getAddressTextField().text(),
                Integer.parseInt(getPortTextField().text()),
                message
                );
        clientTask.execute();
    }

    private class ClientTask extends AsyncTask {

        String address;
        int port;
        String message;
        String response = "";

        ClientTask(String address, int port, String message) {
            this.address = address;
            this.port = port;
            this.message = message;
        }

        @Override
        protected void doInBackground() {
            response = Networking.Send(address, port, message);
        }

        @Override
        protected void onPostExecute() {
            getStatusLabel().setText(response);
        }
    }
}
