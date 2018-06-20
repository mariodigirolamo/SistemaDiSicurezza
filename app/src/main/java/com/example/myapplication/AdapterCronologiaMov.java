package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCronologiaMov extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final String[] crono;

    public AdapterCronologiaMov(Context context, String[] values, String[] crono) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.crono = crono;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.righe_cronologiamov, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.id);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icona1);

        String s = values[position];
        String c = crono[position];

        textView.setText(crono[position]);

        return rowView;
    }
}