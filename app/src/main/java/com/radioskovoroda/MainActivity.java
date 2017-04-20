
/*
add stream radio
 */
package com.radioskovoroda;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity   {

    Button b_play;
    MediaPlayer mediaPlayer;
    CurrentSong currentSongText;


    boolean prepared = false;
    boolean started = false;

    String stream = "http://stream.radioskovoroda.com:8000/radioskovoroda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        b_play = (Button) findViewById(R.id.b_play);
        b_play.setEnabled(false);
        b_play.setText(R.string.loading_status);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        new PlayerTask().execute(stream);
        b_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (started) {
                    started = false;
                    mediaPlayer.pause();
                    b_play.setText("PLAY");
                } else {
                    started = true;
                    mediaPlayer.start();
                    b_play.setText("PAUSE");
                }
            }
        });
        Button btn = (Button) findViewById(R.id.b_switch);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StreamMusic.class));
            }
        });
        TextView txtView = (TextView) findViewById(R.id.textView);
        txtView.setText("Hello World");
    }



    class PlayerTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... Strings) {
            try {
                mediaPlayer.setDataSource(Strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            b_play.setEnabled(true);
            b_play.setText("PLAY");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (started) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (started) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (prepared) {
            mediaPlayer.release();
        }
    }


}