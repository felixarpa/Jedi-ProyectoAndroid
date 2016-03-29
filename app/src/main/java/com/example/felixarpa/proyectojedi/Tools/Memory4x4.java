package com.example.felixarpa.proyectojedi.Tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.felixarpa.flipper.CoolImageFlipper;
import com.example.felixarpa.proyectojedi.*;
import com.example.felixarpa.proyectojedi.DB.IntentsOpenHelper;
import com.example.felixarpa.proyectojedi.Fragments.PagerHolder;
import com.example.felixarpa.proyectojedi.R;

public class Memory4x4 extends MainNavigationDrawer {

    int[] combinations = new int[16];
    ImageView[] imageViews = new ImageView[16];
    Drawable[] drawables = new Drawable[8];
    boolean[] cartaGirada = new boolean[16];
    CoolImageFlipper coolImageFlipper;
    ProgressBar progressBar;
    TextView intentosTV;
    int intentosInt = 0;
    int primeraCarta = -1;
    int segundaCarta = -1;
    boolean unaGirada = false;
    //String perfil = "cat";
    View.OnClickListener list, nulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory4x4_layout);
        setViewsMemory(1);
        setCardsMemory();
        setViewsMemory(2);
        setListenerMemory();
        setViewsMemory(3);
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putIntArray("combinations", combinations);
        outstate.putBooleanArray("cartaGirada", cartaGirada);
        outstate.putInt("progBar", progressBar.getProgress());
        outstate.putInt("intentosInt", intentosInt);
        outstate.putInt("primeraCarta", primeraCarta);
        outstate.putInt("segundaCarta", segundaCarta);
        outstate.putBoolean("unaGirada", unaGirada);
        outstate.putString("intentosTV", intentosTV.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        combinations = outstate.getIntArray("combinations");
        cartaGirada = outstate.getBooleanArray("cartaGirada");
        progressBar.setProgress(outstate.getInt("progBar"));
        intentosInt = outstate.getInt("intentosInt");
        primeraCarta = outstate.getInt("primeraCarta");
        segundaCarta = outstate.getInt("segundaCarta");
        unaGirada = outstate.getBoolean("unaGirada");
        intentosTV.setText(outstate.getString("intentosTV"));

        for (int i = 0; i < 16; ++i) {
            if (cartaGirada[i]) imageViews[i].setImageDrawable(drawables[combinations[i]]);
        }
    }

    private void setListenerMemory() {
        list = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageView00:
                        superFuncion(0);
                        break;

                    case R.id.imageView01:
                        superFuncion(1);
                        break;

                    case R.id.imageView02:
                        superFuncion(2);
                        break;

                    case R.id.imageView03:
                        superFuncion(3);
                        break;

                    case R.id.imageView04:
                        superFuncion(4);
                        break;

                    case R.id.imageView05:
                        superFuncion(5);
                        break;

                    case R.id.imageView06:
                        superFuncion(6);
                        break;

                    case R.id.imageView07:
                        superFuncion(7);
                        break;

                    case R.id.imageView08:
                        superFuncion(8);
                        break;

                    case R.id.imageView09:
                        superFuncion(9);
                        break;

                    case R.id.imageView10:
                        superFuncion(10);
                        break;

                    case R.id.imageView11:
                        superFuncion(11);
                        break;

                    case R.id.imageView12:
                        superFuncion(12);
                        break;

                    case R.id.imageView13:
                        superFuncion(13);
                        break;

                    case R.id.imageView14:
                        superFuncion(14);
                        break;

                    case R.id.imageView15:
                        superFuncion(15);
                        break;

                }

                intentosTV.setText(Integer.toString(intentosInt));
                if (progressBar.getProgress() == progressBar.getMax()) gameWin();
            }
        };
    }

    private void setViewsMemory(int i) {
        switch (i) {
            case 1:
                coolImageFlipper = new CoolImageFlipper(getApplicationContext());
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setMax(8);
                progressBar.setProgress(intentosInt);
                intentosTV = (TextView) findViewById(R.id.intentos);
                setTitle("Memory 4x4");

                imageViews[0]  = (ImageView) findViewById(R.id.imageView00);
                imageViews[1]  = (ImageView) findViewById(R.id.imageView01);
                imageViews[2]  = (ImageView) findViewById(R.id.imageView02);
                imageViews[3]  = (ImageView) findViewById(R.id.imageView03);
                imageViews[4]  = (ImageView) findViewById(R.id.imageView04);
                imageViews[5]  = (ImageView) findViewById(R.id.imageView05);
                imageViews[6]  = (ImageView) findViewById(R.id.imageView06);
                imageViews[7]  = (ImageView) findViewById(R.id.imageView07);
                imageViews[8]  = (ImageView) findViewById(R.id.imageView08);
                imageViews[9]  = (ImageView) findViewById(R.id.imageView09);
                imageViews[10] = (ImageView) findViewById(R.id.imageView10);
                imageViews[11] = (ImageView) findViewById(R.id.imageView11);
                imageViews[12] = (ImageView) findViewById(R.id.imageView12);
                imageViews[13] = (ImageView) findViewById(R.id.imageView13);
                imageViews[14] = (ImageView) findViewById(R.id.imageView14);
                imageViews[15] = (ImageView) findViewById(R.id.imageView15);
                break;

            case 2:
                intentosTV.setText(Integer.toString(intentosInt));

                drawables[0] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat00);
                drawables[1] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat01);
                drawables[2] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat02);
                drawables[3] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat03);
                drawables[4] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat04);
                drawables[5] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat05);
                drawables[6] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat06);
                drawables[7] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.cat07);
                break;

            case 3:
                imageViews[0] .setOnClickListener(list);
                imageViews[1] .setOnClickListener(list);
                imageViews[2] .setOnClickListener(list);
                imageViews[3] .setOnClickListener(list);
                imageViews[4] .setOnClickListener(list);
                imageViews[5] .setOnClickListener(list);
                imageViews[6] .setOnClickListener(list);
                imageViews[7] .setOnClickListener(list);
                imageViews[8] .setOnClickListener(list);
                imageViews[9] .setOnClickListener(list);
                imageViews[10].setOnClickListener(list);
                imageViews[11].setOnClickListener(list);
                imageViews[12].setOnClickListener(list);
                imageViews[13].setOnClickListener(list);
                imageViews[14].setOnClickListener(list);
                imageViews[15].setOnClickListener(list);
                break;

        }
    }

    private void superFuncion(int i) {
        if (!cartaGirada[i]) {
            int x = combinations[i];
            if (!unaGirada) {
                coolImageFlipper.flipImage(drawables[x], imageViews[i]);
                unaGirada = true;
                cartaGirada[i] = true;
                primeraCarta = i;
            }
            else {
                coolImageFlipper.flipImage(drawables[x], imageViews[i]);
                segundaCarta = i;
                if (combinations[primeraCarta] == x) { // Acierto
                    cartaGirada[segundaCarta] = true;
                    progressBar.setProgress(progressBar.getProgress()+1);
                }
                else { // Fallo
                    MiTarea miTarea = new MiTarea();
                    miTarea.execute(-1, 1000);
                    cartaGirada[primeraCarta] = false;
                    cartaGirada[segundaCarta] = false;
                }
                unaGirada = false;
                ++intentosInt;
            }

        }
    }

    private void setCardsMemory() {

        for (int i = 0; i < 16; ++i) {
            combinations[i] = -1;
            cartaGirada[i] = false;
        }

        int a;
        for (int i = 0; i < 8; ++i) {
            do {
                a = (int )(Math.random() * 16);
            } while (combinations[a] != -1);
            combinations[a] = i;
            do {
                a = (int )(Math.random() * 16);
            } while (combinations[a] != -1);
            combinations[a] = i;
        }
    }

    private void gameWin() {
        String titl = "Congratulations!";
        SharedPreferences sharedPreferences = getSharedPreferences("SPSP", Context.MODE_PRIVATE);
        IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());
        String usr = sharedPreferences.getString("username", "nulo");
        int aux = intentsOpenHelper.puntosActuales4x4(usr);
        if (intentosInt < aux || aux == -1) {
            intentsOpenHelper.updateScore4x4(usr, intentosInt);
            titl = "New Record!";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory4x4.this);
        builder.setMessage("You have won this game in " + intentosInt + " attempts");
        builder.setTitle(titl);
        builder.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {
                retry();
            }
        });
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {
                startActivity(new Intent(getApplicationContext(), PagerHolder.class));
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void retry() {
        Log.v("Retry", "1");
        for (int i = 0; i < 16; ++i) {
            MiTarea miTarea = new MiTarea();
            miTarea.execute(i, 50);
            combinations[i] = -1;
            cartaGirada[i] = false;
        }
        Log.v("Retry", "2");

        int a;
        for (int i = 0; i < 8; ++i) {
            do {
                a = (int )(Math.random() * 16);
            } while (combinations[a] != -1);
            combinations[a] = i;
            do {
                a = (int )(Math.random() * 16);
            } while (combinations[a] != -1);
            combinations[a] = i;
        }
        Log.v("Retry", "3");
        setViewsMemory(3);
        Log.v("Retry", "4");
        intentosInt = 0;
        Log.v("Retry", "5");
        intentosTV.setText(0+"");
        Log.v("Retry", "6");
        progressBar.setProgress(0);
        Log.v("Retry", "7");
        unaGirada = false;
        Log.v("Retry", "8");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memory_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retry_item:
                retry();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class MiTarea extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(params[1], 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (params[0] == -1) return "partida";
            else return params[0].toString();

        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("partida")) {
                coolImageFlipper.flipImage(getDrawable(R.drawable.question_mark), imageViews[segundaCarta]);
                coolImageFlipper.flipImage(getDrawable(R.drawable.question_mark), imageViews[primeraCarta]);
                for (int i = 0; i < 16; ++i) imageViews[i].setOnClickListener(list);
                primeraCarta = -1;
                segundaCarta = -1;
            }
            else {
                int i = Integer.parseInt(result);
                coolImageFlipper.flipImage(getDrawable(R.drawable.question_mark), imageViews[i]);
            }
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPreExecute() {
            for (int i = 0; i < 16; ++i) imageViews[i].setOnClickListener(nulo);
            super.onPreExecute();
        }
    }
}
