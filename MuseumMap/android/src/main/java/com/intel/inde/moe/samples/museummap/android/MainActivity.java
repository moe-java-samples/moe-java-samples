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

package com.intel.inde.moe.samples.museummap.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.intel.inde.moe.samples.museummap.android.db.AndroidSQLiteDatabaseHelper;
import com.intel.inde.moe.samples.museummap.common.MuseumSearchEngine;
import com.intel.inde.moe.samples.museummap.common.model.Museum;
import com.intel.inde.moe.samples.museummap.common.model.db.DataSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private Marker currentMarker = null;
    private EditText nameMarker;
    private DataSource source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();

        final Context ctx = getApplicationContext();
        source = new DataSource(new AndroidSQLiteDatabaseHelper(ctx, "local.db"));
        source.open();

        nameMarker = (EditText) findViewById(R.id.editText);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                currentMarker = marker;
                nameMarker.setText(marker.getTitle());
                return false;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                currentMarker = null;
            }
        });

        Button addButton = (Button)findViewById(R.id.btnPlus);
        Button removeButton = (Button)findViewById(R.id.btnMinus);
        Button updateButton = (Button)findViewById(R.id.btnUpdate);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap == null)
                    return;

                mMap.addMarker(new MarkerOptions().position(mMap.getCameraPosition().target)
                        .title(nameMarker.getText().toString()));
                source.createMuseum(new Museum(nameMarker.getText().toString(),
                        mMap.getCameraPosition().target.latitude,
                        mMap.getCameraPosition().target.longitude));
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap == null || currentMarker == null)
                    return;

                ArrayList<Museum> museums = source.getMuseumsByAllParameters(currentMarker.getTitle(),
                        currentMarker.getPosition().latitude, currentMarker.getPosition().longitude);
                for (Museum museum : museums) {
                    source.deleteMuseum(museum.getId());
                }

                currentMarker.remove();
                currentMarker = null;
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng centerCoordinate = mMap.getCameraPosition().target;

                Double[] loadTaskParams = { centerCoordinate.latitude, centerCoordinate.longitude };
                AsyncTask loader = new LoadTask();
                loader.execute(loadTaskParams);
            }
        });

        ArrayList<Museum> museums = source.getAllMuseum();
        System.out.println(museums.size());
        for (Museum museum : museums) {
            source.createMuseum(museum);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(museum.getLatitude(), museum.getLongitude()))
                    .title(museum.getName()));
        }
    }

    private class LoadTask extends AsyncTask<Double, Void, Void> {
        private ArrayList<Museum> museums = null;
        private String errorMessage = "";


        @Override
        protected Void doInBackground(Double... params) {
            museums = MuseumSearchEngine.find(params[0], params[1]);
            if (museums == null) {
                errorMessage = MuseumSearchEngine.getLastError();
            } else {
                for(int i = 0; i < museums.size(); i++) {
                    ArrayList<Museum> existMuseums = source.getMuseumsByAllParameters(
                            museums.get(i).getName(),
                            museums.get(i).getLatitude(),
                            museums.get(i).getLongitude());
                    if (existMuseums.size() != 0) {
                        museums.get(i).setId(existMuseums.get(0).getId());
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (errorMessage.equals("")) {
                for (Museum museum : museums) {
                    if (museum.getId() == -1) {
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(museum.getLatitude(), museum.getLongitude()))
                                .title(museum.getName()));
                    }
                    source.createMuseum(museum);
                }
            } else
                handleError(errorMessage);
        }
    }

    private void handleError(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Error!")
                .setMessage("Cannot get museums: " + errorMessage)
                .setCancelable(false)
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
