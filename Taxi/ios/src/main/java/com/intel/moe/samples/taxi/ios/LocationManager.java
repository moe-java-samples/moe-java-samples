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

import ios.corelocation.CLLocation;
import ios.corelocation.CLLocationManager;
import ios.corelocation.c.CoreLocation;
import ios.corelocation.enums.CLAuthorizationStatus;
import ios.corelocation.protocol.CLLocationManagerDelegate;
import ios.foundation.NSArray;
import ios.foundation.NSError;

public class LocationManager implements CLLocationManagerDelegate {

    public enum State {
        Stopped,
        Running,
        Unavailable,
        Unauthorized
    }

    public static final String LOCATION_WARNING = "You should enable Location Service in your Simulator: Debug > Location.";

    private static LocationManager sharedManager = null;

    private LocationManagerDelegate delegate = null;
    private float trackingDistance;
    private boolean tracking = false;
    private CLLocation currentLocation;
    private LocationManager.State state = State.Stopped;
    private CLLocationManager locationManager;

    static {
        sharedManager = new LocationManager();

        sharedManager.init();

        try {
            Class.forName(CLLocation.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        locationManager = CLLocationManager.alloc().init();
        locationManager.setDelegate(this);
        locationManager.setDesiredAccuracy(CoreLocation.kCLLocationAccuracyBestForNavigation());
        locationManager.setPausesLocationUpdatesAutomatically(true);

        tracking = false;
        trackingDistance = 30.0f;
    }

    // CLLocationManagerDelegate

    @Override
    public void locationManagerDidFailWithError(CLLocationManager manager, NSError error) {
        System.out.println("--- Location error: " + error);
    }

    @Override
    public void locationManagerDidUpdateLocations(CLLocationManager manager, NSArray<? extends CLLocation> locations) {
        System.out.println("--- locationManagerDidUpdateLocations()");
        CLLocation updatedLocation = locations.lastObject();

        if (CoreLocation.CLLocationCoordinate2DIsValid(updatedLocation.coordinate())) {
            if (delegate != null) {
                delegate.didUpdateLocation(this, updatedLocation);
            }
        }
    }

    public static LocationManager getSharedManager() {
        return sharedManager;
    }

    public void setDelegate(LocationManagerDelegate delegate) {
        this.delegate = delegate;
    }

    public LocationManager.State getState() {
        return state;
    }

    public boolean isTracking() {
        return tracking;
    }

    public void setTracking(boolean tracking) {
        this.tracking = tracking;
        if (tracking) {
            System.out.println("--- Location updated");
            if (delegate != null) {
                delegate.didUpdateTrackingLocation(this, locationManager.location());
            }
        }
    }

    public boolean isLocationServicesAvailable() {
        if (CLLocationManager.authorizationStatus() == CLAuthorizationStatus.Denied) {
            System.out.println("--- Denied");
            return false;
        } else if (CLLocationManager.authorizationStatus() == CLAuthorizationStatus.Restricted) {
            System.out.println("--- Restricted");
            return false;
        }
        return true;
    }

    public CLLocation currentLocation() {
        return locationManager.location();
    }

    void startUpdatingLocation() {
        if (state == State.Running) {
            return;
        }

        locationManager.requestWhenInUseAuthorization();

        locationManager.startUpdatingLocation();
        System.out.println("--- startUpdatingLocation");
        state = State.Running;
    }

    void stopUpdatingLocation() {
        locationManager.stopUpdatingLocation();
        state = State.Stopped;
    }
}
