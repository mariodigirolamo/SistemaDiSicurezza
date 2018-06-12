package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private String link = "https://provamario-a2c84.firebaseio.com/";

    private final String TAG = "LIFE_MAIN";

    private String numero1;
    public String numero2;

    private Button vinvia;
    private TextView vRef2;
    private TextView vRef;
    private Button vclick;
    private String disp;
    private String disp2;
    private EditText vphone;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference Movimento = database.getReference("Movimento");
    DatabaseReference Fumo = database.getReference("Fumo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

        vclick = findViewById(R.id.click);
        vRef = findViewById(R.id.Ref);
        vRef2 = findViewById(R.id.Ref2);
        vphone = findViewById(R.id.phone);
        vinvia = findViewById(R.id.invia);

        vclick.setVisibility(View.GONE);
        vRef.setVisibility(View.GONE);
        vRef2.setVisibility(View.GONE);



        //è la prima volta che lo usi?
        //Inserisci codice sul database coi 2 figli
        //se clicca sul tasto registrati first = true;
        //se clicca sul tasto login first = false;

        //Intent intent = new Intent(getApplicationContext(), Login.class);
        //startActivityForResult(intent, REQUEST_CHIAMA);
        final ValueEventListener listen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                disp =   dataSnapshot.getValue().toString();

                if (disp.equals("1")) {
                    vRef.setText("Movimento" + disp);
                }

                if (disp.equals("0")) {
                    vRef.setText("Movimento" + disp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        final ValueEventListener listen1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                disp2 = dataSnapshot.getValue().toString();


                if (disp2.equals("1")) {
                    vRef2.setText("Movimento" + disp2);
                }

                if (disp2.equals("0")) {
                    vRef2.setText("Movimento" + disp2);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        numero1 = String.valueOf(vphone.getText());

        vinvia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                numero1 = vphone.getText().toString();
                numero2 = vphone.getText().toString();
                DatabaseReference Utente1 = database.getReference(numero2);
                Utente1.setValue(numero2);
                Utente1.child("Fumo").setValue("0");
                Utente1.child("Movimento").setValue("0");
                Utente1.child("Fumo").addValueEventListener(listen);
                Utente1.child("Movimento").addValueEventListener(listen1);
                vclick.setVisibility(View.VISIBLE);
                vRef.setVisibility(View.VISIBLE);
                vRef2.setVisibility(View.VISIBLE);
                vphone.setVisibility(View.GONE);
                vinvia.setVisibility(View.GONE);
            }
        });

        DatabaseReference Utente = database.getReference(numero1);
        Utente.setValue(numero1);
        Utente.child("Fumo").setValue(0);
        Utente.child("Movimento").setValue(0);

   /*     ValueEventListener listen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                disp =   dataSnapshot.getValue().toString();

                if (disp.equals("1")) {
                    vRef.setText("Movimento" + disp);
                }

                if (disp.equals("0")) {
                    vRef.setText("Movimento" + disp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };      */

    /*    ValueEventListener listen1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                disp2 = dataSnapshot.getValue().toString();


                if (disp2.equals("1")) {
                    vRef2.setText("Movimento" + disp2);
                }

                if (disp2.equals("0")) {
                    vRef2.setText("Movimento" + disp2);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };   */

        Utente.child("Fumo").addValueEventListener(listen);
        Utente.child("Movimento").addValueEventListener(listen1);


        vclick.setOnClickListener(new refList(Utente));

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.v(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.v(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.v(TAG, "onRestart");
    }

    public class refList implements View.OnClickListener
    {

        DatabaseReference ref;
        public refList(DatabaseReference ref) {
            this.ref = ref;
        }

        @Override
        public void onClick(View v)
        {
            ref.child("Fumo").setValue("0");
            ref.child("Movimento").setValue("0");

            ref.child("Fumo").setValue("0");
            ref.child("Movimento").setValue("0");

            DatabaseReference Utente = database.getReference(numero1);
            Utente.child("Fumo").setValue(0);
            Utente.child("Movimento").setValue(0);

            vRef.setText("Niente Fumo.");
            vRef2.setText("Nessun Movimento.");
        }

    };

}