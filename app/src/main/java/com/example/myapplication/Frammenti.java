package com.example.myapplication;

import android.app.FragmentManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Frammenti extends AppCompatActivity {

    public String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            numero = extras.getString("numero");
        }

        Bundle bundle = new Bundle();
        bundle.putString("numero", numero);
        FragPreferiti frag = new FragPreferiti();
        frag.setArguments(bundle);

        impostaPager();

    }

    public String getNumero(){
        return numero;
    }

    private void impostaPager(){

        final ViewPager viewPager = findViewById(R.id.pager);

        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i < pagerAdapter.getCount(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(pagerAdapter.getItemTabNameResourceId(i)));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
