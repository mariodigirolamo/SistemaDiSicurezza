package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class FragPreferiti extends Fragment{

    private static final String KEY_NAME = "numero";

    private String numero1 = "prova";


    private ListView vlistapref;

    private TextView testo;

    AdapterPrefe adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public FragPreferiti(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_preferiti, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        vlistapref = getView().findViewById(R.id.listaPreferiti);

        numero1 = readFromFile(getContext());

        Button vbutton2 = getView().findViewById(R.id.button2);
        vbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numero1 != null){
                    final DatabaseReference ref2 = database.getReference(numero1);

                    DatabaseReference refpref = ref2.child("Preferiti");

                    DatabaseReference refChild = refpref.child("yara1");

                    refpref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            int i = 0;

                            String [] segnalazioni = new String [10];
                            for(int j = 0; j < 10; j++){segnalazioni[j] = "0";};

                            for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {

                                segnalazioni [i] = zoneSnapshot.getValue(String.class);

                                i++;

                            }

                            int ind = 0;

                            for(int j = 0; j < 10; j++){

                                if(segnalazioni[j].equals("0")){

                                    j = 10;

                                }

                                else{

                                    ind++;

                                }

                            }

                            String [] temp = new String[ind];

                            for(int j = 0; j < ind; j++){

                                temp [j] = segnalazioni [j];

                            }


                            ArrayList<String> lista = new ArrayList<String>(Arrays.asList(temp));
                            if (getActivity() != null) {
                                adapter = new AdapterPrefe(getContext(), lista);
                            }

                            vlistapref.setAdapter(adapter);

                            registerForContextMenu(vlistapref);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getActivity().getMenuInflater().inflate(R.menu.menu_delete_pref, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.action_delete:

                String name = adapter.getItem(info.position).toString();
                adapter.remove(adapter.getItem(info.position));
                adapter.notifyDataSetChanged();
                numero1 = readFromFile(getContext());
                DatabaseReference num = database.getReference(numero1);
                DatabaseReference pref = num.child("Preferiti");
                pref.child(name).removeValue();

        }

        return super.onContextItemSelected(item);

    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("numeroSDS.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
