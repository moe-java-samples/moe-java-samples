package com.intel.moe.samples.elements.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.intel.moe.samples.elements.common.AtomicElement;
import com.intel.moe.samples.elements.common.ElementComparatorByAtomicNumber;
import com.intel.moe.samples.elements.common.ElementComparatorByName;
import com.intel.moe.samples.elements.common.ElementComparatorByState;
import com.intel.moe.samples.elements.common.ElementComparatorBySymbol;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private final static String TAG = "MainActivity";

    // This is the Adapter being used to display the list's data
    ArrayAdapter<AtomicElement> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "--- onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        ListView listView = (ListView) findViewById(R.id.elements_list);
        listView.setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        if (PeriodicElements.elements == null) {
            PeriodicElements.setupElementsArray(getAssets());
        }
        adapter = new ElementArrayAdapter(this, R.layout.rowlayout, PeriodicElements.elements);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        setupUI();
    }

    private void setupUI() {
        final TextView header = (TextView) findViewById(R.id.header);

        Button nameButton = (Button) findViewById(R.id.nameButton);
        nameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                header.setText("Sorted by Name");
                adapter.sort(new ElementComparatorByName());
                adapter.notifyDataSetChanged();
            }
        });

        Button numberButton = (Button) findViewById(R.id.numberButton);
        numberButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                header.setText("Sorted by Atomic Number");
                adapter.sort(new ElementComparatorByAtomicNumber());
                adapter.notifyDataSetChanged();
            }
        });

        Button symbolButton = (Button) findViewById(R.id.symbolButton);
        symbolButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                header.setText("Sorted by Atomic Symbol");
                adapter.sort(new ElementComparatorBySymbol());
                adapter.notifyDataSetChanged();
            }
        });

        Button stateButton = (Button) findViewById(R.id.stateButton);
        stateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                header.setText("Grouped by State");
                adapter.sort(new ElementComparatorByState());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AtomicElement AtomicElement = adapter.getItem(position);
        Log.d(TAG, "--- Click on " + AtomicElement.getName());

        Intent intent = new Intent();
        intent.setClass(this, ElementDetailsActivity.class);

        Bundle b = new Bundle();

        b.putInt("atomicNumber", AtomicElement.getAtomicNumber());
        intent.putExtras(b);

        b.putString("atomicWeight", AtomicElement.getAtomicWeight());
        intent.putExtras(b);

        b.putString("discoveryYear", AtomicElement.getDiscoveryYear());
        intent.putExtras(b);

        b.putInt("group", AtomicElement.getGroup());
        intent.putExtras(b);

        b.putString("name", AtomicElement.getName());
        intent.putExtras(b);

        b.putInt("period", AtomicElement.getPeriod());
        intent.putExtras(b);

        b.putString("state", AtomicElement.getState());
        intent.putExtras(b);

        b.putString("symbol", AtomicElement.getSymbol());
        intent.putExtras(b);

        startActivity(intent);
    }
}
