package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.TaskStackBuilder;
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
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //private String link = "https://provamario-a2c84.firebaseio.com/";

    private final String TAG = "LIFE_MAIN";

    private String numero1;
    public String numero2;

    private FloatingActionButton vcrono;
    private Button vinvia;
    private TextView vRef2;
    private TextView vRef;
    private Button vclick;
    private String disp;
    private String disp2;
    private EditText vphone;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference Movimento = database.getReference("Movimento");
    //DatabaseReference Fumo = database.getReference("Fumo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        vRef.setBackgroundColor(Color.parseColor("#FFFFFF"));
        vRef2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        vRef.setText("Niente fumo rilevato.");
        vRef2.setText("Nessun movimento rilevato");

        //Intent intent = new Intent(getApplicationContext(), Login.class);
        //startActivityForResult(intent, REQUEST_CHIAMA);
        final ValueEventListener listen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                disp =   dataSnapshot.getValue().toString();

                if (disp.equals("1")) {
                    vRef.setText("Attenzione, fumo rilevato!");
                    vRef.setBackgroundColor(Color.RED);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                    Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                    bigText.bigText("Emergenza!");
                    bigText.setBigContentTitle("Anomalia fumo.");
                    bigText.setSummaryText("Rilevata anomalia di tipo fumo, eseguire controllo dell'area.");

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

                disp2 = dataSnapshot.getValue().toString();


                if (disp2.equals("1")) {
                    vRef2.setText("Attenzione, movimento rilevato!");
                    vRef2.setBackgroundColor(Color.RED);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext().getApplicationContext(), "notify_001");
                    Intent ii = new Intent(getApplicationContext().getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                    bigText.bigText("Emergenza!");
                    bigText.setBigContentTitle("Anomalia movimento.");
                    bigText.setSummaryText("Rilevata anomalia di tipo movimento, eseguire controllo dell'area.");

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
            vRef.setText("Niente fumo rilevato.");
            vRef2.setText("Nessun movimento rilevato.");
            vRef.setBackgroundColor(Color.parseColor("#FFFFFF"));
            vRef2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

    };



}