package com.radioskovoroda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button b_play;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    AudioManager audioManager;
    private static String URL = "http://api.radioskovoroda.com:3000/api/stream/current/all";
    TextView textView;
    ArrayList<HashMap<String, String>> radiolist;
    private static final String TAG = Radio.class.getSimpleName();
    private ProgressDialog progress;


    boolean prepared = false;
    boolean started = false;

    String stream = "http://stream.radioskovoroda.com:8000/radioskovoroda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
        b_play = (Button) findViewById(R.id.b_play);
        radiolist = new ArrayList<>();
        textView = (TextView) findViewById(R.id.textView);
        new GetRadio().execute();


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

    public void initControls() {
        try {
            seekBar = (SeekBar) findViewById(R.id.seekBar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            seekBar.setMax(audioManager
                    .getStreamMaxVolume(audioManager.STREAM_MUSIC));
            seekBar.setProgress(audioManager
                    .getStreamVolume(audioManager.STREAM_MUSIC));
            seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }



    }
    private class GetRadio extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(MainActivity.this);
            progress.setMessage("Please wait...");
            progress.setCancelable(false);
            progress.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HTTPHANDLER httphandler = new HTTPHANDLER();

            String jsonStr = httphandler.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);


                JSONArray radio = jsonObj.getJSONArray("radiolist");


                for (int i = 0; i < radiolist.length(); i++) {
                    JSONObject jsonObject = jsonObj.getJSONObject(String.valueOf(i));
                    String artist = radio.getString(Integer.parseInt("artist"));
                    String track = radio.getString(Integer.parseInt("track"));

                    HashMap<String, String> radiolist = new HashMap<>();
                    radiolist.put("artist", artist);
                    radiolist.put("track", track);
                    radiolist.add(radiolist);


                }

            } catch (final JSONException e) {
                e.printStackTrace();
            }


        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (progress.isShowing())
                progress.dismiss();
            
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, radiolist,
                    R.layout.main_activity, new String[]{"artist", "track"
                    }, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            textView.setAdapter(adapter);
        }
