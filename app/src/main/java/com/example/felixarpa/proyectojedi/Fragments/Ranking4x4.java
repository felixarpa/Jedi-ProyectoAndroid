package com.example.felixarpa.proyectojedi.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.felixarpa.proyectojedi.DB.IntentsOpenHelper;
import com.example.felixarpa.proyectojedi.R;

import java.util.ArrayList;

public class Ranking4x4 extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    Button buttonClearRanking;

    public Ranking4x4() { }

    @Override
    public View onCreateView(LayoutInflater layerInflater, ViewGroup viewGroup, Bundle bundle) {

        View rootView = layerInflater.inflate(R.layout.fragment_ranking, viewGroup, false);

        ArrayList<Persona> L = new ArrayList<>(0);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        buttonClearRanking = (Button) rootView.findViewById(R.id.clearRanking);

        IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getActivity());
        Cursor cursor = intentsOpenHelper.getRanking4x4();
        Persona per;
        if (cursor.moveToFirst()) {
            do {
                String u = cursor.getString(cursor.getColumnIndex("username"));
                String n = cursor.getString(cursor.getColumnIndex("name"));
                int p = cursor.getInt(cursor.getColumnIndex("mejor4x4"));
                per = new Persona(u, n, p);
                L.add(per);
            } while (cursor.moveToNext());
        }

        recyclerView.setAdapter(new MiAdapter(L));

        buttonClearRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getActivity().getApplicationContext());
                intentsOpenHelper.clearRanking4x4();

                startActivity(new Intent(getActivity().getApplicationContext(), PagerHolder.class));
                getActivity().finish();
            }
        });

        return rootView;
    }
}
