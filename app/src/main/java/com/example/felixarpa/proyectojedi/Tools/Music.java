package com.example.felixarpa.proyectojedi.Tools;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.felixarpa.flipper.CoolImageFlipper;
import com.example.felixarpa.proyectojedi.MainNavigationDrawer;
import com.example.felixarpa.proyectojedi.R;

public class Music extends MainNavigationDrawer {
    ImageView buttonPlayPause;
    Button buttonGetMusic, buttonDefaultMusic;
    MediaPlayer mediaPlayer;
    CoolImageFlipper coolImageFlipper;
    boolean gotMusic = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_layout);

        buttonGetMusic = (Button) findViewById(R.id.getMusic);
        buttonDefaultMusic = (Button) findViewById(R.id.defaultMusic);
        buttonPlayPause = (ImageView) findViewById(R.id.playpause);
        mediaPlayer = new MediaPlayer();
        coolImageFlipper = new CoolImageFlipper(getApplicationContext());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.playpause:
                        if (gotMusic) {
                            if (mediaPlayer.isPlaying()) {
                                buttonPlayPause.setImageResource(R.drawable.play);
                                mediaPlayer.pause();
                            } else {
                                buttonPlayPause.setImageResource(R.drawable.pause);
                                mediaPlayer.start();
                            }
                        }
                        break;

                    case R.id.getMusic:
                        // Defina la URI de donde cogera los datos
                        Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(intent1, 1);
                        break;

                    case R.id.defaultMusic:
                        try {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/raw/sandstorm"));
                            mediaPlayer.prepare();
                            gotMusic = true;
                            mediaPlayer.start();
                            buttonPlayPause.setImageResource(R.drawable.pause);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

        buttonPlayPause.setOnClickListener(onClickListener);
        buttonGetMusic.setOnClickListener(onClickListener);
        buttonDefaultMusic.setOnClickListener(onClickListener);
    }


    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                try {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(this,data.getData());
                    mediaPlayer.prepare();
                    gotMusic = true;
                    mediaPlayer.start();
                    buttonPlayPause.setImageResource(R.drawable.pause);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
