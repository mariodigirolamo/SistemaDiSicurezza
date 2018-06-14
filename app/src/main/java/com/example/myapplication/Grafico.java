package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Grafico extends AppCompatActivity {

    private TextView vprova;
    private Button vfuga;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Bundle extras = getIntent().getExtras();
        if(extras != null){


            int datom1 = extras.getInt("datom1");
            int datom2 = extras.getInt("datom2");
            int datom3 = extras.getInt("datom3");
            int datom4 = extras.getInt("datom4");
            int datom5 = extras.getInt("datom5");

            int dato1 = extras.getInt("dato1");
            int dato2 = extras.getInt("dato2");
            int dato3 = extras.getInt("dato3");
            int dato4 = extras.getInt("dato4");
            int dato5 = extras.getInt("dato5");

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

            vfuga = findViewById(R.id.fuga);
            vprova = findViewById(R.id.prova);

            String datos1 = String.valueOf(dato1);
            String datos2 = String.valueOf(dato2);
            String datos3 = String.valueOf(dato3);
            String datos4 = String.valueOf(dato4);
            String datos5 = String.valueOf(dato5);

            vprova.setText(datos1 + datos2 + datos3 + datos4 + datos5 + datam2);


            vfuga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    finish();
                }
            });


        }

    }
}
