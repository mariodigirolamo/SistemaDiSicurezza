package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.TaskStackBuilder;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.NotificationCompat.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //private String link = "https://provamario-a2c84.firebaseio.com/";

    private final String TAG = "LIFE_MAIN";

    private int test = 0;
    private String numero1;
    public String numero2;
    private int tick = 0;
    private int tickm = 0;

    private FloatingActionButton vcrono;
    private Button vinvia;
    private TextView vRef2;
    private TextView vRef;
    private Button vclick;
    private String disp = "meh";
    private String disp2 = "Meh";
    private EditText vphone;

    final int store[] = new int [5];
    final int storem[] = new int [5];
    final String dataf [] = new String [5];
    final String datam [] = new String [5];

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference Movimento = database.getReference("Movimento");
    //DatabaseReference Fumo = database.getReference("Fumo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //stiamo caricando il layout della mia activity_main

        /*final int store[] = new int [5];
        final int storem[] = new int [5];
        final String dataf [] = new String [5];
        final String datam [] = new String [5];*/

        //Ho creato dei vettori nei quali mettere i dati del fumo e del movimento con le rispetteve date
        //ed ora nelle quali ho inserito il dato

        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

        vcrono = findViewById(R.id.crono);
        vclick = findViewById(R.id.click);
        vRef = findViewById(R.id.Ref);
        vRef2 = findViewById(R.id.Ref2);
        vphone = findViewById(R.id.phone);
        vinvia = findViewById(R.id.invia);

        vcrono.setEnabled(false);
        vclick.setEnabled(false);
        vcrono = findViewById(R.id.crono);
        vcrono.setVisibility(View.GONE);
        vclick.setVisibility(View.GONE);
        vRef.setVisibility(View.GONE);
        vRef2.setVisibility(View.GONE);
        vRef.setBackgroundColor(Color.WHITE);
        vRef2.setBackgroundColor(Color.WHITE);
        vRef.setText("\n" + "Niente fumo rilevato.");
        vRef2.setText("\n" + "Nessun movimento rilevato");

        //Intent intent = new Intent(getApplicationContext(), Login.class);
        //startActivityForResult(intent, REQUEST_CHIAMA);

        //Di seguito scrivo l'ascoltatore di cambio dati nel database

        final ValueEventListener listen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != null) {
                    disp = dataSnapshot.getValue().toString();
                }

                if (disp.equals("1")) {

                    store[tick] = 1;
                    dataf[tick] = Calendar.getInstance().getTime().toString();

                    if(tick < 5) {
                        tick = tick + 1;
                    }

                    if(tick == 5){tick = 0; test = 1;}

                    vRef.setText("\n" + "Attenzione, fumo rilevato!");
                    vRef.setBackgroundColor(Color.RED);

                    //Sto creando la notifica

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                    Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                    bigText.bigText("Emergenza!");
                    bigText.setBigContentTitle("Anomalia fumo.");
                    bigText.setSummaryText("Rilevata anomalia di tipo fumo, eseguire controllo dell'area.");

                    Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                    mBuilder.setSound(uri);
                    mBuilder.setAutoCancel(true);
                    mBuilder.setContentIntent(pendingIntent);
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                    mBuilder.setContentTitle("Emergenza!");
                    mBuilder.setContentText("Anomalia fumo.");
                    mBuilder.setPriority(Notification.PRIORITY_MAX);
                    mBuilder.setStyle(bigText);

                    NotificationManager mNotificationManager =
                            (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("notify_001",
                                "Channel human readable title",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        mNotificationManager.createNotificationChannel(channel);
                    }

                    mNotificationManager.notify(0, mBuilder.build());


                }

                /*if (disp.equals("0")) {
                    vRef.setText("Non è stato rilevato fumo.");
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        final ValueEventListener listen1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != null) {
                    disp2 = dataSnapshot.getValue().toString();
                }


                if (disp2.equals("1")) {

                    storem[tickm] = 1;
                    datam[tickm] = Calendar.getInstance().getTime().toString();

                    if(tickm < 5) {
                        tickm = tickm + 1;
                    }

                    if(tickm == 5){tickm = 0; test = 1;}

                    vRef2.setText("\n" + "Attenzione, movimento rilevato!");
                    vRef2.setBackgroundColor(Color.RED);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext().getApplicationContext(), "notify_001");
                    Intent ii = new Intent(getApplicationContext().getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                    bigText.bigText("Emergenza!");
                    bigText.setBigContentTitle("Anomalia movimento.");
                    bigText.setSummaryText("Rilevata anomalia di tipo movimento, eseguire controllo dell'area.");

                    Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                    mBuilder.setSound(uri);
                    mBuilder.setAutoCancel(true);
                    mBuilder.setContentIntent(pendingIntent);
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                    mBuilder.setContentTitle("Emergenza!");
                    mBuilder.setContentText("Anoamalia Movimento.");
                    mBuilder.setPriority(Notification.PRIORITY_MAX);
                    mBuilder.setStyle(bigText);

                    NotificationManager mNotificationManager =
                            (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("notify_001",
                                "Channel human readable title",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        mNotificationManager.createNotificationChannel(channel);
                    }

                    mNotificationManager.notify(1, mBuilder.build());
                }

                /*if (disp2.equals("0")) {
                    vRef2.setText("Non è stato rilevato movimento.");
                }*/

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
                vcrono.setVisibility(View.VISIBLE);
                vcrono.setEnabled(true);
                vclick.setEnabled(true);


            }
        });

        DatabaseReference Utente = database.getReference(numero1);

        vclick.setOnClickListener(new refList(Utente));

        vcrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((tick == 5 && tickm == 5) && test == 0 || test == 1){
                    Intent i = new Intent(getApplicationContext(), Grafico.class);

                    i.putExtra("datom1", storem[0]);
                    i.putExtra("datom2", storem[1]);
                    i.putExtra("datom3", storem[2]);
                    i.putExtra("datom4", storem[3]);
                    i.putExtra("datom5", storem[4]);

                    i.putExtra("datam1", datam[0]);
                    i.putExtra("datam2", datam[1]);
                    i.putExtra("datam3", datam[2]);
                    i.putExtra("datam4", datam[3]);
                    i.putExtra("datam5", datam[4]);

                    i.putExtra("dataf1", dataf[0]);
                    i.putExtra("dataf2", dataf[1]);
                    i.putExtra("dataf3", dataf[2]);
                    i.putExtra("dataf4", dataf[3]);
                    i.putExtra("dataf5", dataf[4]);

                    i.putExtra("dato1", store[0]);
                    i.putExtra("dato2", store[1]);
                    i.putExtra("dato3", store[2]);
                    i.putExtra("dato4", store[3]);
                    i.putExtra("dato5", store[4]);
                    startActivity(i);

                    tick = 0;
                    tickm = 0;
                    test = 1;
                }


                if ((tick == 0 && tickm == 0) && test == 0){Toast.makeText(getApplicationContext(),"Non ci sono anomalie da mostrare.", Toast.LENGTH_LONG).show();}
                if(((tick >0 && tick <5) || (tickm >0 && tickm <5)) && test == 0){
                    Toast.makeText(getApplicationContext(),"Non ci sono abbastanza dati da mostrare.", Toast.LENGTH_LONG).show();
                }
            }
        });

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
            DatabaseReference Utente = database.getReference(numero1);
            Utente.child("Fumo").setValue(0);
            Utente.child("Movimento").setValue(0);
            vRef.setText("\n" + "Niente fumo rilevato.");
            vRef2.setText("\n" +"Nessun movimento rilevato.");
            vRef.setBackgroundColor(Color.WHITE);
            vRef2.setBackgroundColor(Color.WHITE);
        }

    };



}