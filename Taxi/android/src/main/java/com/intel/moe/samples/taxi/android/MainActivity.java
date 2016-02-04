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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<OrderParameter> parameters = null;

    static final int DEPARTURE_PARAMETER = 0;
    static final int DESTINATION_PARAMETER = 1;

    // This is the Adapter being used to display the list's data
    private ArrayAdapter<OrderParameter> adapter;

    private LatLng departureCoordinate = null;
    private LatLng destinationCoordinate = null;

    static final int DEPARTURE_LOCATION_REQUEST = 0;
    static final int DESTINATION_LOCATION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parameters = new ArrayList<>(5);
        setInitialOrderParameters(parameters);

        adapter = new OrderParametersAdapter(this, R.layout.row, parameters);

        ListView listView = (ListView) findViewById(R.id.elements_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void setInitialOrderParameters(List<OrderParameter> parameters) {
        parameters.add(new OrderParameter(R.drawable.ic_place_black_48dp, "Pickup location", "Current location"));
        parameters.add(new OrderParameter(R.drawable.ic_map_black_48dp, "Destination", "Specify"));
        parameters.add(new OrderParameter(R.drawable.ic_schedule_black_48dp, "Time", "17:00"));
        parameters.add(new OrderParameter(R.drawable.ic_phone_black_48dp, "Phone number", "+7 (955) 555-55-55"));
        parameters.add(new OrderParameter(R.drawable.ic_comment_black_48dp, "Comment", "Non-smoking driver"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private void pickDepartureLocation() {
        Intent intent = new Intent();
        intent.setClass(this, MapsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("actionBarTitle", "Set pickup location");
        if (departureCoordinate != null) {
            bundle.putParcelable("startLocation", departureCoordinate);
        }
        intent.putExtras(bundle);

        startActivityForResult(intent, DEPARTURE_LOCATION_REQUEST);
    }

    private void pickDestinationLocation() {
        Intent intent = new Intent();
        intent.setClass(this, MapsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("actionBarTitle", "Set destination");
        if (destinationCoordinate != null) {
            bundle.putParcelable("startLocation", destinationCoordinate);
        }
        intent.putExtras(bundle);

        startActivityForResult(intent, DESTINATION_LOCATION_REQUEST);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case DEPARTURE_PARAMETER : {
                pickDepartureLocation();
                break;
            }
            case DESTINATION_PARAMETER : {
                pickDestinationLocation();
                break;
            }
            default: break;
        }
    }

    private void handleDepartureResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            departureCoordinate = bundle.getParcelable("selectedCoordinate");
            String departureAddress = bundle.getString("addressOutput");

            parameters.get(DEPARTURE_PARAMETER).setDetail(departureAddress);
            adapter.notifyDataSetChanged();
        }
    }

    private void handleDestinationResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            destinationCoordinate = bundle.getParcelable("selectedCoordinate");
            String destinationAddress = bundle.getString("addressOutput");

            parameters.get(DESTINATION_PARAMETER).setDetail(destinationAddress);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DEPARTURE_LOCATION_REQUEST : {
                handleDepartureResult(resultCode, data);
                break;
            }
            case DESTINATION_LOCATION_REQUEST: {
                handleDestinationResult(resultCode, data);
                break;
            }
            default: break;
        }
    }
}
