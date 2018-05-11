package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.IgnoreExtraProperties;


public class MainActivity extends AppCompatActivity {

    private String link = "https://provamario-a2c84.firebaseio.com/";

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

        vclick = findViewById(R.id.click);
        vRef = findViewById(R.id.Ref);
        vRef2 = findViewById(R.id.Ref2);
        vphone = findViewById(R.id.phone);

        vclick.setVisibility(View.GONE);
        vRef.setVisibility(View.GONE);
        vRef2.setVisibility(View.GONE);



        //è la prima volta che lo usi?
        //Inserisci codice sul database coi 2 figli
        //se clicca sul tasto registrati first = true;
        //se clicca sul tasto login first = false;




        ValueEventListener listen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    disp =   dataSnapshot.getValue().toString();

                    vRef.setText("Fumo" + disp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ValueEventListener listen1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                disp2 = dataSnapshot.getValue().toString();


                if (disp2.equals("1")) {
                    vRef2.setText("Movimento" + disp2);
                }

                if (disp2.equals("0")) {
                    vRef2.setText("Nessun movimento" + disp2);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        Fumo.addValueEventListener(listen);
        Movimento.addValueEventListener(listen1);

        vphone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                boolean handles = false;
                if(i == EditorInfo.IME_ACTION_DONE) {

                    String inputText = vphone.getText().toString();

                    DatabaseReference Utente = database.getReference(inputText);
                    Utente.setValue(inputText);
                    Utente.child("Fumo").setValue(0);
                    Utente.child("Movimento").setValue(0);

                    vclick.setVisibility(View.VISIBLE);
                    vRef.setVisibility(View.VISIBLE);
                    vRef2.setVisibility(View.VISIBLE);
                    vphone.setVisibility(View.GONE);

                }
                return false;
            }
        });

        vclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vRef.setText("Nessun Movimento.");
                vRef2.setText("Nessun Movimento.");


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
