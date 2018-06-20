package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Grafico extends AppCompatActivity {

    private TextView vstatom;
    private TextView vstatof;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        vstatom = findViewById(R.id.statomovimento);
        vstatof = findViewById(R.id.statofumo);

        ListView listafumo = (ListView) findViewById(R.id.fumolist);
        ListView listamov = (ListView) findViewById(R.id.movlist);

        Bundle extras = getIntent().getExtras();
        if(extras != null){


            String datom1 = String.valueOf(extras.getInt("datom1"));
            String datom2 = String.valueOf(extras.getInt("datom2"));
            String datom3 = String.valueOf(extras.getInt("datom3"));
            String datom4 = String.valueOf(extras.getInt("datom4"));
            String datom5 = String.valueOf(extras.getInt("datom5"));

            String dato1 = String.valueOf(extras.getInt("dato1"));
            String dato2 = String.valueOf(extras.getInt("dato2"));
            String dato3 = String.valueOf(extras.getInt("dato3"));
            String dato4 = String.valueOf(extras.getInt("dato4"));
            String dato5 = String.valueOf(extras.getInt("dato5"));


            String dataf1 = extras.getString("dataf1");
            String dataf2 = extras.getString("dataf2");
            String dataf3 = extras.getString("dataf3");
            String dataf4 = extras.getString("dataf4");
            String dataf5 = extras.getString("dataf5");

            String datam1 = extras.getString("datam1");
            String datam2 = extras.getString("datam2");
            String datam3 = extras.getString("datam3");
            String datam4 = extras.getString("datam4");
            String datam5 = extras.getString("datam5");

            String [] datifumo = new String [] {dato1, dato2, dato3, dato4, dato5};
            String [] datimovimento = new String [] {datom1, datom2, datom3, datom4, datom5};

            String [] datefumo = new String [] {dataf1, dataf2, dataf3, dataf4, dataf5};
            String [] datemov = new String [] {datam1, datam2, datam3, datam4, datam5};

            AdapterCronologia adapterf = new AdapterCronologia(this, datifumo, datefumo);
            AdapterCronologiaMov adaptermov = new AdapterCronologiaMov(this, datimovimento, datemov);

            listafumo.setAdapter(adapterf);
            listamov.setAdapter(adaptermov);

        }

    }
}
