package com.example.felixarpa.proyectojedi;

import com.example.felixarpa.proyectojedi.Fragments.PagerHolder;
import com.example.felixarpa.proyectojedi.Tools.Calculadora;
import com.example.felixarpa.proyectojedi.Tools.Memory4x4;
import com.example.felixarpa.proyectojedi.Tools.Memory6x6;
import com.example.felixarpa.proyectojedi.Tools.Memory8x8;
import com.example.felixarpa.proyectojedi.Tools.Music;
import com.example.felixarpa.proyectojedi.UserSetUp.LogIn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainNavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String sharedPreferencesName = "SPSP";

    public String dondeEstoy = "Sloth Project";
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        setViews();
    }

    @Override
    public void setContentView(int layoutResID) {

        DrawerLayout drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        FrameLayout frameLayout = (FrameLayout) drawerLayout.findViewById(R.id.frame_layout_base);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        super.setContentView(drawerLayout);
        setViews();
    }

    protected void setViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuProfile:
                if (!dondeEstoy.equals("Profile")) {
                    dondeEstoy = "Profile";
                    startActivity(new Intent(getApplicationContext(), PagerHolder.class));
                    finish();
                }
                break;

            case R.id.menuMemory4x4:
                if (!dondeEstoy.equals("Memory4x4")) {
                    dondeEstoy = "Memory4x4";
                    startActivity(new Intent(getApplicationContext(), Memory4x4.class));
                    finish();
                }
                break;

            case R.id.menuMemory6x6:
                if (!dondeEstoy.equals("Memory6x6")) {
                    dondeEstoy = "Memory6x6";
                    startActivity(new Intent(getApplicationContext(), Memory6x6.class));
                    finish();
                }
                break;

            case R.id.menuMemory8x8:
                if (!dondeEstoy.equals("Memory8x8")) {
                    dondeEstoy = "Memory8x8";
                    startActivity(new Intent(getApplicationContext(), Memory8x8.class));
                    finish();
                }
                break;

            case R.id.menuCalc:
                if (!dondeEstoy.equals("Calc")) {
                    dondeEstoy = "Calc";
                    startActivity(new Intent(getApplicationContext(), Calculadora.class));
                    finish();
                }
                break;

            case R.id.menuMusic:
                if (!dondeEstoy.equals("Music")) {
                    dondeEstoy = "Music";
                    startActivity(new Intent(getApplicationContext(), Music.class));
                    finish();
                }

                finish();
                break;

            case R.id.logOutNav:
                SharedPreferences.Editor editor = getSharedPreferences("SPSP", 0).edit();
                editor.putBoolean("isLogedIn", false);
                editor.apply();

                startActivity(new Intent(getApplicationContext(), LogIn.class));
                finish();
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
