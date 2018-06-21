package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterCronologiaMov extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> crono;

    public AdapterCronologiaMov(Context context, ArrayList<String> crono) {
        super(context, -1, crono);
        this.context = context;
        this.crono = crono;
    }

    public ArrayList<String> getValues (){
        return crono;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.righe_cronologiamov, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.id);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icona1);

        textView.setText(crono.get(position));

        return rowView;
    }
}