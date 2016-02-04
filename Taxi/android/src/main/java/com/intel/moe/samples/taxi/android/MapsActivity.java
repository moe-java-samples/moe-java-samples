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

package com.intel.moe.samples.taxi.android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class MapsActivity extends AppCompatActivity implements ConnectionCallbacks,
        OnConnectionFailedListener,
        TouchableWrapper.UpdateMapAfterUserInteraction,
        LocationListener,
        OnMapReadyCallback {

    private static final String TAG = "MapsActivity";

    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private Marker marker;

    private LatLng startLocation = null;

    private Location currentLocation = null;
    private LatLng selectedCoordinate;
    private Location selectedLocation = null;

    ActionBar actionBar;
    private String actionBarTitle = "Set location";

    @SuppressLint("ParcelCreator")
    class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            String fullAddress = resultData.getString(Constants.RESULT_DATA_KEY);

            if (resultCode == Constants.SUCCESS_RESULT && fullAddress != null) {
                String[] strings = fullAddress.split("\n");
                addressOutput = strings[0];
                actionBar.setTitle(addressOutput);
            } else {
                actionBar.setTitle("Can't get address");
            }

            addressRequested = false;
        }
    }

    private AddressResultReceiver resultReceiver;

    protected boolean addressRequested;

    protected String addressOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBarTitle = bundle.getString("actionBarTitle");

            startLocation = bundle.getParcelable("startLocation");
        }

        setUpMapIfNeeded();

        resultReceiver = new AddressResultReceiver(new Handler());
        addressRequested = false;
        addressOutput = "";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Set a Toolbar to act as the ActionBar for this Activity window
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(actionBarTitle);
        }

        toolbar.setNavigationOnClickListener(buttonBackOnClickListener);

        Button locationButton = (Button) this.findViewById(R.id.locationButton);
        locationButton.setOnClickListener(buttonLocationOnClickListener);

        Button doneButton = (Button) this.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(buttonDoneOnClickListener);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    OnMapClickListener mapClickListener = new OnMapClickListener() {

        @Override
        public void onMapClick(LatLng coordinate) {
            Log.d(TAG, "--- onMapClick");
        }
    };

    private CameraPosition lastCameraPosition = null;

    private void idleAtCameraPosition(CameraPosition cameraPosition) {
        Log.d(TAG, "--- idleAtCameraPosition");
        selectedCoordinate = cameraPosition.target;

        selectedLocation = new Location("Test");
        selectedLocation.setLatitude(selectedCoordinate.latitude);
        selectedLocation.setLongitude(selectedCoordinate.longitude);
        selectedLocation.setTime(new Date().getTime()); //Set time as current Date

        startIntentService();
        addressRequested = true;

        lastCameraPosition = null;
    }

    OnCameraChangeListener cameraChangeListener = new OnCameraChangeListener() {
        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
            lastCameraPosition = cameraPosition;
            if (!isTouched) {
                idleAtCameraPosition(cameraPosition);
            }
        }
    };

    private CameraUpdate getCameraUpdate(LatLng coordinate) {
        CameraPosition camera = new CameraPosition.Builder()
                .target(coordinate)
                .zoom(17)
                .build();
        return CameraUpdateFactory.newCameraPosition(camera);
    }

    View.OnClickListener buttonBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    View.OnClickListener buttonLocationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (currentLocation != null) {
                LatLng coordinate = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                CameraUpdate cameraUpdate = getCameraUpdate(coordinate);
                map.animateCamera(cameraUpdate);
            }
        }
    };

    View.OnClickListener buttonDoneOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("selectedCoordinate", selectedCoordinate);
            bundle.putString("addressOutput", addressOutput);

            Intent result = new Intent();
            result.putExtras(bundle);
            setResult(RESULT_OK, result);
            finish();
        }
    };

        @Override
    protected void onResume() {
        super.onResume();
        if (googleApiClient.isConnected()) {
            startLocationUpdates();
        } else {
            setUpMapIfNeeded();
        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(mapClickListener);
        map.setOnCameraChangeListener(cameraChangeListener);

        marker = createMarker(new Location("Default"));
        marker.setVisible(false);
    }

    @Override
    public void onConnected(Bundle bundle) {
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (currentLocation != null) {
            LatLng currentCoordinate = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

            marker.setPosition(currentCoordinate);
            marker.setVisible(true);

            CameraUpdate cameraUpdate = getCameraUpdate(currentCoordinate);
            map.moveCamera(cameraUpdate);
        }

        if (startLocation != null) {
            Log.d(TAG, "--- startLocation: " + startLocation.latitude + " : " + startLocation.longitude);
            CameraUpdate cameraUpdate = getCameraUpdate(startLocation);
            map.moveCamera(cameraUpdate);
        }

        startLocationUpdates();
    }

    private Marker createMarker(Location location) {
        LatLng coordinate = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(coordinate).title("My location");
        return map.addMarker(markerOptions);
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, createLocationRequest(), this);
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        intent.putExtra(Constants.RECEIVER, resultReceiver);

        intent.putExtra(Constants.LOCATION_DATA_EXTRA, selectedLocation);

        startService(intent);
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
        marker.setPosition(latlng);
        Log.d(TAG, "--- onLocationChanged");
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onUpdateMapAfterUserInteraction() {

    }

    private boolean isTouched = false;

    @Override
    public void onTouchDown() {
        actionBar.setTitle(actionBarTitle);
        isTouched = true;
    }

    @Override
    public void onTouchUp() {
        isTouched = false;
        if (lastCameraPosition != null) {
            idleAtCameraPosition(lastCameraPosition);
        }
    }
}
