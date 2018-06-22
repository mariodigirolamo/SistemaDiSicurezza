package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    //private String [] segnalazioni;

    private ListView vlistapref;

    private TextView testo;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public FragPreferiti(){
        //richiesto vuoto
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*ArrayList<String> arraypref = new ArrayList<String>(Arrays.asList(segnalazioni));

        AdapterCronologia adapter = new AdapterCronologia(getContext(), arraypref);

        vlistapref.setAdapter(adapter);*/

        return inflater.inflate(R.layout.frag_preferiti, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        vlistapref = getView().findViewById(R.id.listaPreferiti);

        /*if( getArguments() != null) {
            numero1 = getArguments().getString("KEY_NAME");
            Toast.makeText(getContext(), "valore" + numero1, Toast.LENGTH_LONG).show();
        }*/

        numero1 = readFromFile(getContext());

        Button vbutton2 = getView().findViewById(R.id.button2);
        vbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "valore" + numero1, Toast.LENGTH_LONG).show();
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

                            //Toast.makeText(getContext(), "Dato cambiato", Toast.LENGTH_LONG).show();
                            for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {

                                segnalazioni [i] = zoneSnapshot.getValue(String.class);
                                i++;

                            }

                            ArrayList<String> lista = new ArrayList<String>(Arrays.asList(segnalazioni));
                            AdapterCronologia adapter = new AdapterCronologia(getContext(), lista);

                            vlistapref.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }
        });
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
