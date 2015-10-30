package com.intel.inde.moe.samples.elements.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.intel.inde.moe.samples.elements.common.AtomicElement;
import java.util.List;


public class ElementArrayAdapter extends ArrayAdapter<AtomicElement> {

    private Context context;
    private List<AtomicElement> AtomicElements;

    public ElementArrayAdapter(Context context, int resource, List<AtomicElement> objects) {
        super(context, resource, objects);
        this.context = context;
        this.AtomicElements = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = convertView != null ? convertView : inflater.inflate(R.layout.rowlayout, parent, false);

        AtomicElement AtomicElement = AtomicElements.get(position);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.label);
        nameTextView.setText(AtomicElement.getName());

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        String state = AtomicElement.getState();

        switch (state) {
            case "Artificial":
                imageView.setImageResource(R.mipmap.artificial37);
                break;
            case "Gas":
                imageView.setImageResource(R.mipmap.gas37);
                break;
            case "Liquid":
                imageView.setImageResource(R.mipmap.liquid37);
                break;
            case "Solid":
                imageView.setImageResource(R.mipmap.solid37);
                break;
            default:
                break;
        }

        TextView symbol = (TextView) rowView.findViewById(R.id.symbol);
        symbol.setText(AtomicElement.getSymbol());

        TextView atomicNumber = (TextView) rowView.findViewById(R.id.atomicNumber);
        atomicNumber.setText(Integer.toString(AtomicElement.getAtomicNumber()));

        return rowView;
    }


}
