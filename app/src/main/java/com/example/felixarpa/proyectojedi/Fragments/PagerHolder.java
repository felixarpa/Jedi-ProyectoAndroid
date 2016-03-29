package com.example.felixarpa.proyectojedi.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.felixarpa.proyectojedi.MainNavigationDrawer;
import com.example.felixarpa.proyectojedi.R;

public class PagerHolder extends MainNavigationDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        setViewsPager();
    }

    private void setViewsPager() {

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), PagerHolder.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutMain);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK);
        tabLayout.setupWithViewPager(viewPager);
    }

}
