/*
add stream radio
 */
package theunderdog.radioskovoroda;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button b_play;
    MediaPlayer mediaPlayer;

    boolean prepared = false;
    boolean started = false;

    String stream = "http://stream.radioskovoroda.com:8000/radioskovoroda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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




    /*
    add stream  music
    create By Igor Vasyo
    */
    class StreamMusic extends AppCompatActivity {
        Button m_music;

        MediaPlayer mediaPlayer;

        boolean prepared = false;
        boolean started = false;
        String stream = "http://radioskovoroda.com/music";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            m_music = (Button) findViewById(R.id.m_music);
            m_music.setEnabled(false);
            m_music.setText("LOADING");

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            new PlayerTask().execute(stream);

            m_music.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (started) {
                        started = false;
                        mediaPlayer.pause();
                        m_music.setText("PLAY");

                    } else {
                        started = true;
                        mediaPlayer.start();
                        m_music.setText("PAUSE");
                    }

                }
            });
        }


      class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mediaPlayer.setDataSource(strings[0]);
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
            m_music.setEnabled(true);
            m_music.setText("MUSIC");
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

