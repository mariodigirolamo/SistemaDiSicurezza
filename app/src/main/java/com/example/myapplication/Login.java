package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.style.EasyEditSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private final String TAG = "LIFE_LOGIN";

    private final String cod = "cart321";

    private Button vbutt;
    private EditText vpass;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.v(TAG, "OnCreate");

        vbutt = findViewById(R.id.butt);
        vpass = findViewById(R.id.pass);

        vbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(vpass.getText().toString().equals(cod)){
                    finish();
                }
                else{Toast.makeText(getApplicationContext(),"Il codice inserito è errato.", Toast.LENGTH_LONG).show();}

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
}
