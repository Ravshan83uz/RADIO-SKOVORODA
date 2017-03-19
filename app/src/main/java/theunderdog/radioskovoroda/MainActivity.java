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

    String stream =   "http://stream.radioskovoroda.com:8000/radioskovoroda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        b_play = (Button) findViewById(R.id.b_play);
        b_play.setEnabled(false);
        b_play.setText("LOADING");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream);
        b_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(started){
                    started = false;
                    mediaPlayer.pause();
                    b_play.setText("PLAY");
                }else{
                    started = true;

                }
            }
            @Override
            protected void onDestroy() {
                super.onDestroy();
                if(prepared){
                    mediaPlayer.release();
                }
            }
        } class PlayerTask {
    }

    /*
   add first code for animation.


    <set xmlns:android="http://schemas.android.com/apk/res/android"
        android:shareInterpolator="false">
<translate android:fromXDelta="-100%" android:toXDelta="0%"
        android:fromYDelta="0%" android:toYDelta="0%"
        android:duration="700"/>
</set>
*/


    /*
    add stream  music
    create By Igor Vasyo
    */
   class StreamMusic extends AppCompatActivity {
    Button b_music;

    MediaPlayer mediaPlayer;

    boolean prepared = false;
    boolean started = false;
    String stream = "http://radioskovoroda.com/music";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_music);
        b_music = (Button) findViewById(R.id.b_music);
        b_music.setEnabled(false);
        b_music.setText("LOADING");

        mediaPlayer = new MediaPlayer ();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream);

        b_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(started){
                    started = false;
                    mediaPlayer.pause();
                    b_music.setText("PLAY");

            } else {
                    started = true;
                    mediaPlayer.start();
                    b_music.setText("PAUSE");
                }

                }
        });
    }


    class PlayerTask extends AsyncTask<String , Void , Boolean > {
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
            b_music.setEnabled(true);
            b_music.setText("MUSIC");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(started){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(prepared){
            mediaPlayer.release();
        }
    }
}