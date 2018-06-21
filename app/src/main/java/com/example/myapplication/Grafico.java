package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Grafico extends AppCompatActivity {

    private TextView vstatom;
    private TextView vstatof;

    private String toggle = "boh";

    AdapterCronologia adapterf;
    AdapterCronologiaMov adaptermov;

    /*private String [] test1 = new String [] {"000","000","000","000","000"};
    private ArrayList<String> test = new ArrayList<String> (Arrays.asList(test1));

    final AdapterCronologia adapterf = new AdapterCronologia(this, test);
    final AdapterCronologiaMov adaptermov = new AdapterCronologiaMov(this, test);*/

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        vstatom = findViewById(R.id.statomovimento);
        vstatof = findViewById(R.id.statofumo);

        ListView listafumo = findViewById(R.id.fumolist);
        ListView listamov = findViewById(R.id.movlist);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

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

            // nullpointer per context

            String [] datefumo = new String[] {dataf1, dataf2, dataf3, dataf4, dataf5};
            String [] datemov = new String[] {datam1, datam2, datam3, datam4, datam5};

            ArrayList<String> arrayfumo = new ArrayList<String>(Arrays.asList(datefumo));
            ArrayList<String> arraymov = new ArrayList<String>(Arrays.asList(datemov));

            adapterf = new AdapterCronologia(this, arrayfumo);
            adaptermov = new AdapterCronologiaMov(this, arraymov);

            listafumo.setAdapter(adapterf);
            listamov.setAdapter(adaptermov);

            listamov.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    adaptermov.remove(adaptermov.getItem(i));
                    adaptermov.notifyDataSetChanged();
                }
            });

            listafumo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    adapterf.remove(adapterf.getItem(i));
                    adapterf.notifyDataSetChanged();
                }
            });

            this.registerForContextMenu(listafumo);
            this.registerForContextMenu(listamov);

            listafumo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toggle = "Fumo";
                    return false;
                }
            });

            listamov.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toggle = "Movimento";
                    return false;
                }
            });

        }

        adapterf.notifyDataSetChanged();
        adaptermov.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.action_delete:

                if(toggle == "Fumo"){
                    adapterf.remove(adapterf.getItem(info.position));
                    adapterf.notifyDataSetChanged();
                }
                if(toggle == "Movimento"){
                    adaptermov.remove(adaptermov.getItem(info.position));
                    adaptermov.notifyDataSetChanged();
                }

                break;
        }

        return super.onContextItemSelected(item);

    }
}
