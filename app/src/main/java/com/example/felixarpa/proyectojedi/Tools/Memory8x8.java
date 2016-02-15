package com.example.felixarpa.proyectojedi.Tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.felixarpa.flipper.CoolImageFlipper;
import com.example.felixarpa.proyectojedi.DB.IntentsOpenHelper;
import com.example.felixarpa.proyectojedi.Fragments.PagerHolder;
import com.example.felixarpa.proyectojedi.MainNavigationDrawer;
import com.example.felixarpa.proyectojedi.R;

import butterknife.ButterKnife;

public class Memory8x8 extends MainNavigationDrawer {

    int[] combinations = new int[64];
    ImageView[] imageViews = new ImageView[64];
    Drawable[] drawables = new Drawable[32];
    boolean[] cartaGirada = new boolean[64];
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
        ButterKnife.bind(this);
        setContentView(R.layout.memory8x8_layout);
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

        for (int i = 0; i < 64; ++i) {
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

                    case R.id.imageView16:
                        superFuncion(16);
                        break;

                    case R.id.imageView17:
                        superFuncion(17);
                        break;

                    case R.id.imageView18:
                        superFuncion(18);
                        break;

                    case R.id.imageView19:
                        superFuncion(19);
                        break;

                    case R.id.imageView20:
                        superFuncion(20);
                        break;

                    case R.id.imageView21:
                        superFuncion(21);
                        break;

                    case R.id.imageView22:
                        superFuncion(22);
                        break;

                    case R.id.imageView23:
                        superFuncion(23);
                        break;

                    case R.id.imageView24:
                        superFuncion(24);
                        break;

                    case R.id.imageView25:
                        superFuncion(25);
                        break;

                    case R.id.imageView26:
                        superFuncion(26);
                        break;

                    case R.id.imageView27:
                        superFuncion(27);
                        break;

                    case R.id.imageView28:
                        superFuncion(28);
                        break;

                    case R.id.imageView29:
                        superFuncion(29);
                        break;

                    case R.id.imageView30:
                        superFuncion(30);
                        break;

                    case R.id.imageView31:
                        superFuncion(31);
                        break;

                    case R.id.imageView32:
                        superFuncion(32);
                        break;

                    case R.id.imageView33:
                        superFuncion(33);
                        break;

                    case R.id.imageView34:
                        superFuncion(34);
                        break;

                    case R.id.imageView35:
                        superFuncion(35);
                        break;

                    case R.id.imageView36:
                        superFuncion(36);
                        break;

                    case R.id.imageView37:
                        superFuncion(37);
                        break;

                    case R.id.imageView38:
                        superFuncion(38);
                        break;

                    case R.id.imageView39:
                        superFuncion(39);
                        break;

                    case R.id.imageView40:
                        superFuncion(40);
                        break;

                    case R.id.imageView41:
                        superFuncion(41);
                        break;

                    case R.id.imageView42:
                        superFuncion(42);
                        break;

                    case R.id.imageView43:
                        superFuncion(43);
                        break;

                    case R.id.imageView44:
                        superFuncion(44);
                        break;

                    case R.id.imageView45:
                        superFuncion(45);
                        break;

                    case R.id.imageView46:
                        superFuncion(46);
                        break;

                    case R.id.imageView47:
                        superFuncion(47);
                        break;

                    case R.id.imageView48:
                        superFuncion(48);
                        break;

                    case R.id.imageView49:
                        superFuncion(49);
                        break;

                    case R.id.imageView50:
                        superFuncion(50);
                        break;

                    case R.id.imageView51:
                        superFuncion(51);
                        break;

                    case R.id.imageView52:
                        superFuncion(52);
                        break;

                    case R.id.imageView53:
                        superFuncion(53);
                        break;

                    case R.id.imageView54:
                        superFuncion(54);
                        break;

                    case R.id.imageView55:
                        superFuncion(55);
                        break;

                    case R.id.imageView56:
                        superFuncion(56);
                        break;

                    case R.id.imageView57:
                        superFuncion(57);
                        break;

                    case R.id.imageView58:
                        superFuncion(58);
                        break;

                    case R.id.imageView59:
                        superFuncion(59);
                        break;

                    case R.id.imageView60:
                        superFuncion(60);
                        break;

                    case R.id.imageView61:
                        superFuncion(61);
                        break;

                    case R.id.imageView62:
                        superFuncion(62);
                        break;

                    case R.id.imageView63:
                        superFuncion(63);
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
                progressBar.setMax(32);
                progressBar.setProgress(intentosInt);
                intentosTV = (TextView) findViewById(R.id.intentos);
                setTitle("Memory 8x8");

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
                imageViews[16] = (ImageView) findViewById(R.id.imageView16);
                imageViews[17] = (ImageView) findViewById(R.id.imageView17);
                imageViews[18] = (ImageView) findViewById(R.id.imageView18);
                imageViews[19] = (ImageView) findViewById(R.id.imageView19);
                imageViews[20] = (ImageView) findViewById(R.id.imageView20);
                imageViews[21] = (ImageView) findViewById(R.id.imageView21);
                imageViews[22] = (ImageView) findViewById(R.id.imageView22);
                imageViews[23] = (ImageView) findViewById(R.id.imageView23);
                imageViews[24] = (ImageView) findViewById(R.id.imageView24);
                imageViews[25] = (ImageView) findViewById(R.id.imageView25);
                imageViews[26] = (ImageView) findViewById(R.id.imageView26);
                imageViews[27] = (ImageView) findViewById(R.id.imageView27);
                imageViews[28] = (ImageView) findViewById(R.id.imageView28);
                imageViews[29] = (ImageView) findViewById(R.id.imageView29);
                imageViews[30] = (ImageView) findViewById(R.id.imageView30);
                imageViews[31] = (ImageView) findViewById(R.id.imageView31);
                imageViews[32] = (ImageView) findViewById(R.id.imageView32);
                imageViews[33] = (ImageView) findViewById(R.id.imageView33);
                imageViews[34] = (ImageView) findViewById(R.id.imageView34);
                imageViews[35] = (ImageView) findViewById(R.id.imageView35);
                imageViews[36] = (ImageView) findViewById(R.id.imageView36);
                imageViews[37] = (ImageView) findViewById(R.id.imageView37);
                imageViews[38] = (ImageView) findViewById(R.id.imageView38);
                imageViews[39] = (ImageView) findViewById(R.id.imageView39);
                imageViews[40] = (ImageView) findViewById(R.id.imageView40);
                imageViews[41] = (ImageView) findViewById(R.id.imageView41);
                imageViews[42] = (ImageView) findViewById(R.id.imageView42);
                imageViews[43] = (ImageView) findViewById(R.id.imageView43);
                imageViews[44] = (ImageView) findViewById(R.id.imageView44);
                imageViews[45] = (ImageView) findViewById(R.id.imageView45);
                imageViews[46] = (ImageView) findViewById(R.id.imageView46);
                imageViews[47] = (ImageView) findViewById(R.id.imageView47);
                imageViews[48] = (ImageView) findViewById(R.id.imageView48);
                imageViews[49] = (ImageView) findViewById(R.id.imageView49);
                imageViews[50] = (ImageView) findViewById(R.id.imageView50);
                imageViews[51] = (ImageView) findViewById(R.id.imageView51);
                imageViews[52] = (ImageView) findViewById(R.id.imageView52);
                imageViews[53] = (ImageView) findViewById(R.id.imageView53);
                imageViews[54] = (ImageView) findViewById(R.id.imageView54);
                imageViews[55] = (ImageView) findViewById(R.id.imageView55);
                imageViews[56] = (ImageView) findViewById(R.id.imageView56);
                imageViews[57] = (ImageView) findViewById(R.id.imageView57);
                imageViews[58] = (ImageView) findViewById(R.id.imageView58);
                imageViews[59] = (ImageView) findViewById(R.id.imageView59);
                imageViews[60] = (ImageView) findViewById(R.id.imageView60);
                imageViews[61] = (ImageView) findViewById(R.id.imageView61);
                imageViews[62] = (ImageView) findViewById(R.id.imageView62);
                imageViews[63] = (ImageView) findViewById(R.id.imageView63);
                break;

            case 2:
                intentosTV.setText(Integer.toString(intentosInt));

                drawables[0] = getDrawable(R.drawable.cat00);
                drawables[1] = getDrawable(R.drawable.cat01);
                drawables[2] = getDrawable(R.drawable.cat02);
                drawables[3] = getDrawable(R.drawable.cat03);
                drawables[4] = getDrawable(R.drawable.cat04);
                drawables[5] = getDrawable(R.drawable.cat05);
                drawables[6] = getDrawable(R.drawable.cat06);
                drawables[7] = getDrawable(R.drawable.cat07);
                drawables[8] = getDrawable(R.drawable.cat08);
                drawables[9] = getDrawable(R.drawable.cat09);
                drawables[10]= getDrawable(R.drawable.cat10);
                drawables[11]= getDrawable(R.drawable.cat11);
                drawables[12]= getDrawable(R.drawable.cat12);
                drawables[13]= getDrawable(R.drawable.cat13);
                drawables[14]= getDrawable(R.drawable.cat14);
                drawables[15]= getDrawable(R.drawable.cat15);
                drawables[16]= getDrawable(R.drawable.cat16);
                drawables[17]= getDrawable(R.drawable.cat17);
                drawables[18]= getDrawable(R.drawable.cat18);
                drawables[19]= getDrawable(R.drawable.cat19);
                drawables[20]= getDrawable(R.drawable.cat20);
                drawables[21]= getDrawable(R.drawable.cat21);
                drawables[22]= getDrawable(R.drawable.cat22);
                drawables[23]= getDrawable(R.drawable.cat23);
                drawables[24]= getDrawable(R.drawable.cat24);
                drawables[25]= getDrawable(R.drawable.cat25);
                drawables[26]= getDrawable(R.drawable.cat26);
                drawables[27]= getDrawable(R.drawable.cat27);
                drawables[28]= getDrawable(R.drawable.cat28);
                drawables[29]= getDrawable(R.drawable.cat29);
                drawables[30]= getDrawable(R.drawable.cat30);
                drawables[31]= getDrawable(R.drawable.cat31);
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
                imageViews[16].setOnClickListener(list);
                imageViews[17].setOnClickListener(list);
                imageViews[18].setOnClickListener(list);
                imageViews[19].setOnClickListener(list);
                imageViews[20].setOnClickListener(list);
                imageViews[21].setOnClickListener(list);
                imageViews[22].setOnClickListener(list);
                imageViews[23].setOnClickListener(list);
                imageViews[24].setOnClickListener(list);
                imageViews[25].setOnClickListener(list);
                imageViews[26].setOnClickListener(list);
                imageViews[27].setOnClickListener(list);
                imageViews[28].setOnClickListener(list);
                imageViews[29].setOnClickListener(list);
                imageViews[30].setOnClickListener(list);
                imageViews[31].setOnClickListener(list);
                imageViews[32].setOnClickListener(list);
                imageViews[33].setOnClickListener(list);
                imageViews[34].setOnClickListener(list);
                imageViews[35].setOnClickListener(list);
                imageViews[36].setOnClickListener(list);
                imageViews[37].setOnClickListener(list);
                imageViews[38].setOnClickListener(list);
                imageViews[39].setOnClickListener(list);
                imageViews[40].setOnClickListener(list);
                imageViews[41].setOnClickListener(list);
                imageViews[42].setOnClickListener(list);
                imageViews[43].setOnClickListener(list);
                imageViews[44].setOnClickListener(list);
                imageViews[45].setOnClickListener(list);
                imageViews[46].setOnClickListener(list);
                imageViews[47].setOnClickListener(list);
                imageViews[48].setOnClickListener(list);
                imageViews[49].setOnClickListener(list);
                imageViews[50].setOnClickListener(list);
                imageViews[51].setOnClickListener(list);
                imageViews[52].setOnClickListener(list);
                imageViews[53].setOnClickListener(list);
                imageViews[54].setOnClickListener(list);
                imageViews[55].setOnClickListener(list);
                imageViews[56].setOnClickListener(list);
                imageViews[57].setOnClickListener(list);
                imageViews[58].setOnClickListener(list);
                imageViews[59].setOnClickListener(list);
                imageViews[60].setOnClickListener(list);
                imageViews[61].setOnClickListener(list);
                imageViews[62].setOnClickListener(list);
                imageViews[63].setOnClickListener(list);
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

        for (int i = 0; i < 64; ++i) {
            combinations[i] = -1;
            cartaGirada[i] = false;
        }

        int a;
        for (int i = 0; i < 32; ++i) {
            do {
                a = (int )(Math.random() * 64);
            } while (combinations[a] != -1);
            combinations[a] = i;
            do {
                a = (int )(Math.random() * 64);
            } while (combinations[a] != -1);
            combinations[a] = i;
        }
    }

    private void gameWin() {
        String titl = "Congratulations!";
        SharedPreferences sharedPreferences = getSharedPreferences("SPSP", Context.MODE_PRIVATE);
        IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());
        String usr = sharedPreferences.getString("username", "nulo");
        int aux = intentsOpenHelper.puntosActuales8x8(usr);
        if (intentosInt < aux || aux == -1) {
            intentsOpenHelper.updateScore8x8(usr, intentosInt);
            titl = "New Record!";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory8x8.this);
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
        for (int i = 0; i < 64; ++i) {
            MiTarea miTarea = new MiTarea();
            miTarea.execute(i, 50);
            combinations[i] = -1;
            cartaGirada[i] = false;
        }

        int a;
        for (int i = 0; i < 32; ++i) {
            do {
                a = (int )(Math.random() * 64);
            } while (combinations[a] != -1);
            combinations[a] = i;
            do {
                a = (int )(Math.random() * 64);
            } while (combinations[a] != -1);
            combinations[a] = i;
        }
        setViewsMemory(3);
        intentosInt = 0;
        intentosTV.setText(0 + "");
        progressBar.setProgress(0);
        unaGirada = false;
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
                for (int i = 0; i < 64; ++i) imageViews[i].setOnClickListener(list);
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
            for (int i = 0; i < 64; ++i) imageViews[i].setOnClickListener(nulo);
            super.onPreExecute();
        }
    }
}
