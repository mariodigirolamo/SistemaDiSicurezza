package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPersonale extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final String[] button;

    public AdapterPersonale(Context context, String[] values, String[] button) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.button = button;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.righe_sensori, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.id);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icona);
        ImageView imageButton = (ImageView) rowView.findViewById(R.id.statob);

        String s = values[position];
        String b = button[position];

        if(s == "Fumo"){
            imageView.setImageResource(R.drawable.gas);
        }

        if(s == "Movimento"){
            imageView.setImageResource(R.drawable.movimento);
            textView.setText("Non è stato rilevata alcuna anomalia di movimento.");
        }

        if(b.equals("1")){
            imageButton.setImageResource(R.drawable.danger_button);
            textView.setText("Anomalia Rilevata!");
        }
        if(b.equals("0")){
            imageButton.setImageResource(R.drawable.safe_button);
        }

        return rowView;
    }
}