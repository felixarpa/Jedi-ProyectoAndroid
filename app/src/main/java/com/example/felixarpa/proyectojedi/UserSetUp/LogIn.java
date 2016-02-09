package com.example.felixarpa.proyectojedi.UserSetUp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felixarpa.proyectojedi.DB.IntentsOpenHelper;
import com.example.felixarpa.proyectojedi.Fragments.PagerHolder;
import com.example.felixarpa.proyectojedi.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import io.fabric.sdk.android.Fabric;

public class LogIn extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "MMcYpAQtm4i8glhQlagelqLyd";
    private static final String TWITTER_SECRET = "DaQagtCytX2fb5gfRApvQAcOSzmXBgpnNXIaRgi5Mpu2lfprOs";


    EditText loginUsr;
    EditText loginPwd;
    Button loginButton;
    Button became;
    String u, twitterUrl;
    Bitmap bitmap;
    SharedPreferences sharedPreferences;
    private TwitterLoginButton loginButtonTwitter;
    View.OnClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.login_layout);

        setTwitterButton();

//        final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());
//        intentsOpenHelper.clearTable();

        sharedPreferences = getSharedPreferences("SPSP", Context.MODE_PRIVATE);
        checkLogIn();


        loginUsr = (EditText) findViewById(R.id.loginUsername);
        loginPwd = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        became = (Button) findViewById(R.id.became);

        setListener();

        loginButton.setOnClickListener(listener);
        became.setOnClickListener(listener);

    }

    private void setListener() {
        final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.loginButton:
                        String usuario = loginUsr.getText().toString();
                        String contrasena = loginPwd.getText().toString();
                        if (intentsOpenHelper.correctPassword(usuario, contrasena)) {
                            SharedPreferences.Editor editor = getSharedPreferences("SPSP", 0).edit();
                            Cursor cursor = intentsOpenHelper.getUsr(usuario);
                            if (cursor.moveToFirst()) {

                                editor.putString("username", cursor.getString(cursor.getColumnIndex("username")));
                                editor.putBoolean("usaToast", true);
                                editor.putBoolean("isLogedIn", true);
                                editor.putBoolean("withTwitter", false);
                                editor.apply();

                                startActivity(new Intent(getApplicationContext(), PagerHolder.class));
                                finish();

                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LogIn.this);
                            builder.setMessage("Wrong username/password or the user does not exists. You can became a sloth if you want.");
                            builder.setTitle("Wrong username/password");
                            builder.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int witch) {

                                }
                            });
                            builder.setPositiveButton("Became a sloth!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int witch) {
                                    startActivity(new Intent(getApplicationContext(), BecameSloth.class));
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                        break;

                    case R.id.became:
                        startActivity(new Intent(getApplicationContext(), BecameSloth.class));
                        break;

                }
            }
        };

    }

    private void checkLogIn() {
        if (sharedPreferences.getBoolean("isLogedIn",false)) {
            Intent intent = new Intent(this, PagerHolder.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    public void setTwitterButton() {
        loginButtonTwitter = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButtonTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = result.data;
                u = "twitter:" + session.getUserName();
                Log.v("LogIn/Twitter", "Encontrado usuario: " + session.getUserName());

                String msg = "@" + session.getUserName() + " logged in!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                ContentValues contentValues = new ContentValues();
                contentValues.put("username", u);
                contentValues.put("name", session.getUserName());
                contentValues.put("password", session.getUserId());
                contentValues.put("address", "");
                contentValues.put("mejor4x4", -1);
                contentValues.put("mejor6x6", -1);
                contentValues.put("mejor8x8", -1);
                contentValues.put("teImatge", "N");

                final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());
                if (intentsOpenHelper.putUsr(contentValues)) Log.v("LogIn/Twitter", "Se ha iniciado sesion con el usuario: "+u);

                SharedPreferences.Editor editor = getSharedPreferences("SPSP", 0).edit();
                editor.putString("username", u);
                editor.putBoolean("usaToast", true);
                editor.putBoolean("isLogedIn", true);
                editor.putBoolean("withTwitter", true);
                editor.apply();

                twittetProfilePic(session);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("LogIn/Twitter", "Error: ", exception);
            }
        });

    }

    private void twittetProfilePic(TwitterSession session) {
        new MyTwitterApiClient(session).getUsersService().show(session.getUserId(), null, true,
                new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        twitterUrl = result.data.profileImageUrlHttps.replace("_normal","");
                        downloadImage();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.v("LogIn/Twitter", "Error al obtener el URL de la imagen" + exception);
                    }
                });
    }

    private Handler mhHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 96) {
                FileOutputStream fos;
                try {
                    fos = openFileOutput(u+"profile",Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Base dades
                final IntentsOpenHelper intentsOpenHelper = new IntentsOpenHelper(getApplicationContext());
                intentsOpenHelper.updateImageSi(u);

                Toast.makeText(getApplicationContext(), "Download complete", Toast.LENGTH_LONG).show();

                Log.v("Cambio de Activity", "LogIn --> PagerHolder (Profile)");
                Intent intent = new Intent(getApplicationContext(), PagerHolder.class);
                startActivity(intent);
                finish();
            }
        }
    };


    public void downloadImage() {
        new Thread(new Runnable() {
            private Bitmap loadImageFromNetwork(String url) {
                try {
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void run() {
                bitmap = loadImageFromNetwork(twitterUrl);
                Message msg = new Message();
                msg.what = 96;
                mhHandler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButtonTwitter.onActivityResult(requestCode, resultCode, data);
    }
}
