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

package com.intel.moe.samples.taxi.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.taxi.common.TaxiRequest;
import com.intel.moe.samples.taxi.common.TaxiService;

import ios.NSObject;
import ios.corelocation.CLLocation;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.uikit.UIAlertAction;
import ios.uikit.UIAlertController;
import ios.uikit.UIAlertView;
import ios.uikit.UILabel;
import ios.uikit.UIStoryboardSegue;
import ios.uikit.UITableViewController;
import ios.uikit.enums.UIAlertActionStyle;
import ios.uikit.enums.UIAlertControllerStyle;
import ios.uikit.protocol.UITableViewDataSource;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("TableViewController")
@RegisterOnStartup
public class TableViewController extends UITableViewController implements UITableViewDataSource {

    @Selector("alloc")
    public static native TableViewController alloc();

    @Selector("init")
    public native TableViewController init();

    protected TableViewController(Pointer peer) {
        super(peer);
    }

    @Selector("departureLabel")
    @Property
    public native UILabel getDepartureLabel();

    @Selector("destinationLabel")
    @Property
    public native UILabel getDestinationLabel();

    @Selector("scheduleLabel")
    @Property
    public native UILabel getScheduleLabel();

    @Selector("phoneNumberLabel")
    @Property
    public native UILabel getPhoneNumberLabel();

    @Selector("commentLabel")
    @Property
    public native UILabel getCommentLabel();



    @Override
    public void viewDidLoad() {
        if (!LocationManager.getSharedManager().isLocationServicesAvailable()) {
            UIAlertController alertController = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Taxi",
                    "Please enable geolocation", UIAlertControllerStyle.Alert);
            UIAlertAction ok = UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Default, null);
            alertController.addAction(ok);
            this.presentViewControllerAnimatedCompletion(alertController, true, null);
        }
        LocationManager.getSharedManager().startUpdatingLocation();
    }

    CLLocationCoordinate2D departureCoordinate = new CLLocationCoordinate2D(0.0, 0.0);
    private String departureAddress = "";
    boolean departureObtained = false;

    CLLocationCoordinate2D destinationCoordinate = new CLLocationCoordinate2D(0.0, 0.0);
    private String destinationAddress = "";
    boolean destinationObtained = false;

    @Override
    public void prepareForSegueSender(UIStoryboardSegue segue, Object sender) {

        MapsViewController destinationViewController = (MapsViewController) segue.destinationViewController();

        if (segue.identifier().equals("departureSegue")) {
            if (!departureObtained) {
                CLLocation location = LocationManager.getSharedManager().currentLocation();
                if (location != null) {
                    departureCoordinate = location.coordinate();
                } else {
                    System.out.println(LocationManager.LOCATION_WARNING);
                }
            }
            destinationViewController.pickLocation(departureCoordinate, (coordinate, address) -> {
                departureCoordinate = coordinate;
                departureAddress = address;
                departureObtained = true;
                getDepartureLabel().setText(address);
                tableView().reloadData();
            });
            destinationViewController.navigationItem().setTitle("Set pickup location");
        }

        if (segue.identifier().equals("destinationSegue")) {
            if (!destinationObtained) {
                CLLocation location = LocationManager.getSharedManager().currentLocation();
                if (location != null) {
                    destinationCoordinate = location.coordinate();
                } else {
                    System.out.println(LocationManager.LOCATION_WARNING);
                }
            }
            destinationViewController.pickLocation(destinationCoordinate, (coordinate, address) -> {
                destinationCoordinate = coordinate;
                destinationAddress = address;
                destinationObtained = true;
                getDestinationLabel().setText(address);
                tableView().reloadData();
            });
            destinationViewController.navigationItem().setTitle("Set destination");
        }
    }

    @Selector("sendButtonAction:")
    public void sendButtonAction(NSObject sender) {
        TaxiRequest request = new TaxiRequest(
                getDepartureLabel().text(),
                getDestinationLabel().text(),
                getScheduleLabel().text(),
                getPhoneNumberLabel().text(),
                getCommentLabel().text()
        );
        if (TaxiService.instance().sendRequest(request)) {
            UIAlertView alert = UIAlertView.alloc().initWithTitleMessageDelegateCancelButtonTitleOtherButtonTitles(
                    "Taxi", "You car is coming to you!", this, "OK", null
            );
            alert.show();
        }
    }
}
