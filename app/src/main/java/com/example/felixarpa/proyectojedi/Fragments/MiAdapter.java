package com.example.felixarpa.proyectojedi.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felixarpa.proyectojedi.R;

import java.util.ArrayList;

public class MiAdapter extends RecyclerView.Adapter<MiAdapter.AdapterViewHolder> {

    ArrayList<Persona> people;

    MiAdapter(ArrayList<Persona> pp) {
        setDataSet(pp);
    }

    public void setDataSet(ArrayList<Persona> pp) {
        people = pp;
        notifyDataSetChanged();
    }


    @Override
    public MiAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fila_layout, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MiAdapter.AdapterViewHolder holder, int position) {
        holder.filaUser.setText(people.get(position).getUsername());
        holder.filaName.setText(people.get(position).getName());
        holder.filaScore.setText(people.get(position).getScore()+"");
    }



    @Override
    public int getItemCount() {
        return people.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView filaUser;
        public TextView filaName;
        public TextView filaScore;
        public View view;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.filaUser = (TextView) itemView.findViewById(R.id.filaUser);
            this.filaName = (TextView) itemView.findViewById(R.id.filaName);
            this.filaScore = (TextView) itemView.findViewById(R.id.filaScore);
        }
    }
}
