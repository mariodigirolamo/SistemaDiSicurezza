package com.example.myapplication;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.IgnoreExtraProperties;

public class MainActivity extends AppCompatActivity {

    private TextView vIndicatore;
    private Button vclick;
    private String disp;
    private String disp2;
    private boolean first;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference Movimento = database.getReference("Movimento");
    DatabaseReference Fumo = database.getReference("Fumo");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //è la prima volta che lo usi?
        //Inserisci codice sul database coi 2 figli
        //se clicca sul tasto registrati first = true;
        //se clicca sul tasto login first = false;

        DatabaseReference Utente1 = database.getReference("Utente1");
        Utente1.setValue("Mario");
        Utente1.child("Fumo").setValue("0");
        Utente1.child("Moviento").setValue("0");



        vIndicatore = findViewById(R.id.Indicatore);
        vclick = findViewById(R.id.click);

        ValueEventListener listen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 {
                    disp =   dataSnapshot.getValue().toString();



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        Fumo.addValueEventListener(listen);
        Movimento.addValueEventListener(listen);

        vclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vIndicatore.setText(disp);


                //Movimento.setValue(disp);
                //Fumo.setValue(disp2);



            }
        });




    }
}

@IgnoreExtraProperties
class sensori {

    public int Fumo;
    public int Movimento;

    public sensori() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public sensori(int Fumo, int Movimento) {
        this.Fumo = Fumo;
        this.Movimento = Movimento;
    }

};
