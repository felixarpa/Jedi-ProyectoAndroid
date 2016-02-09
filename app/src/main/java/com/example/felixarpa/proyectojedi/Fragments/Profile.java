package com.example.felixarpa.proyectojedi.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felixarpa.proyectojedi.DB.IntentsOpenHelper;
import com.example.felixarpa.proyectojedi.R;
import com.example.felixarpa.proyectojedi.UserSetUp.EditProfile;
import com.example.felixarpa.proyectojedi.UserSetUp.LogIn;

import java.io.FileInputStream;

public class Profile extends Fragment {

    String u;
    int puntos;
    Button buttonEdit, buttonLogOut;
    TextView textViewUser, textViewName, textViewAddr, textViewPunt4x4, textViewPunt6x6, textViewPunt8x8;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView imageView;

    public Profile() { }

    @Override
    public View onCreateView(LayoutInflater layerInflater, ViewGroup viewGroup, Bundle bundle) {

        View rootView = layerInflater.inflate(R.layout.fragment_profile, viewGroup, false);
        setViewsProfile(rootView);

//        final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getActivity().getApplicationContext());
//        intentsOpenHelper.clearTable();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.edit_profile:
                        startActivity(new Intent(getActivity().getApplicationContext(), EditProfile.class));
                        break;

                    case R.id.log_out:
                        editor = getActivity().getSharedPreferences("SPSP", 0).edit();
                        editor.putBoolean("isLogedIn", false);
                        editor.putBoolean("withTwitter", false);
                        editor.apply();

                        startActivity(new Intent(getActivity().getApplicationContext(), LogIn.class));
                        getActivity().finish();
                }
            }
        };
        buttonLogOut.setOnClickListener(onClickListener);

        sharedPreferences = getActivity().getSharedPreferences("SPSP", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("withTwitter", false)) buttonEdit.setOnClickListener(onClickListener);


        return rootView;
    }

    private void setViewsProfile(View v) {

        final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getActivity().getApplicationContext());

        buttonEdit = (Button) v.findViewById(R.id.edit_profile);
        buttonLogOut = (Button) v.findViewById(R.id.log_out);
        textViewUser = (TextView) v.findViewById(R.id.username);
        textViewName = (TextView) v.findViewById(R.id.name);
        textViewAddr = (TextView) v.findViewById(R.id.address);
        textViewPunt4x4 = (TextView) v.findViewById(R.id.score4);
        textViewPunt6x6 = (TextView) v.findViewById(R.id.score6);
        textViewPunt8x8 = (TextView) v.findViewById(R.id.score8);
        imageView = (ImageView) v.findViewById(R.id.imageViewProfile);

        sharedPreferences = getActivity().getSharedPreferences("SPSP", Context.MODE_PRIVATE);
        u = sharedPreferences.getString("username", "nulo");
        Cursor cursor = intentsOpenHelper.getUsr(u);
        if (cursor.moveToFirst()) {
            textViewUser.setText(u);
            textViewName.setText(cursor.getString(cursor.getColumnIndex("name")));
            textViewAddr.setText(cursor.getString(cursor.getColumnIndex("address")));
            puntos = cursor.getInt(cursor.getColumnIndex("mejor4x4"));
            if (puntos == -1) textViewPunt4x4.setText("No points");
            else textViewPunt4x4.setText(puntos + "");
            puntos = cursor.getInt(cursor.getColumnIndex("mejor6x6"));
            if (puntos == -1) textViewPunt6x6.setText("No points");
            else textViewPunt6x6.setText(puntos + "");
            puntos = cursor.getInt(cursor.getColumnIndex("mejor8x8"));
            if (puntos == -1) textViewPunt8x8.setText("No points");
            else textViewPunt8x8.setText(puntos + "");

            // Imagen perfil
            cursor = intentsOpenHelper.getUsr(u);
            if (cursor.moveToFirst()) {
                int teimg = cursor.getInt(cursor.getColumnIndex("teImatge"));
                if (teimg == 1) {
                    try {
                        FileInputStream fis = getActivity().openFileInput(u+"profile");
                        Bitmap bitmap = BitmapFactory.decodeStream(fis);
                        fis.close();
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                imageView.setImageResource(R.drawable.sloth_signin);
            }
        }
    }
}
