package com.example.felixarpa.proyectojedi.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    Fragment fragment = null;
    private Context context;
    private String tabTitles[] = new String[] { "Profile", "Ranking 4x4", "Ranking 6x6", "Ranking 8x8"};


    PagerAdapter(FragmentManager fragmentManager, Context cntxt) {
        super(fragmentManager);
        context = cntxt;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                fragment = new Profile();
                break;
            case 1:
                fragment = new Ranking4x4();
                break;
            case 2:
                fragment = new Ranking6x6();
                break;
            case 3:
                fragment = new Ranking8x8();
                break;
            default:
                fragment = new Profile();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
