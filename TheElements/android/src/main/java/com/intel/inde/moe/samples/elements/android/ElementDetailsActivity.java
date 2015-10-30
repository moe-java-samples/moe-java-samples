package com.intel.inde.moe.samples.elements.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ElementDetailsActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);

        Bundle b = getIntent().getExtras();

        TextView atomicNumber = (TextView) findViewById(R.id.atomicNumber);
        atomicNumber.setText(Integer.toString(b.getInt("atomicNumber")));

        TextView symbol = (TextView) findViewById(R.id.symbol);
        symbol.setText(b.getString("symbol"));

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(b.getString("name"));

        TextView atomicWeight = (TextView) findViewById(R.id.atomicWeight);
        atomicWeight.setText("Atomic Weight: " + b.getString("atomicWeight"));

        TextView state = (TextView) findViewById(R.id.state);
        state.setText("State: " + b.getString("state"));

        TextView period = (TextView) findViewById(R.id.period);
        period.setText("Period: " + Integer.toString(b.getInt("period")));

        TextView group = (TextView) findViewById(R.id.group);
        group.setText("Group: " + Integer.toString(b.getInt("group")));

        TextView discoveryYear = (TextView) findViewById(R.id.discoveryYear);
        discoveryYear.setText("Discovered: " + b.getString("discoveryYear"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_element_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
