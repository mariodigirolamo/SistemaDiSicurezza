package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numeroDiTab = 2;

    public PagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0 :
                return new FragPreferiti();

            case 1 :
                return new FragGrafico();

            default :
                return null;
        }
    }

    @Override
    public int getCount(){
        return numeroDiTab;
    }

    public int getItemTabNameResourceId(int position){
        switch(position){
            case 0 :
                return R.string.tab_preferiti;

            case 1:
                return R.string.tab_grafico;

            default :
                return R.string.tab_unknown;
        }
    }
}
