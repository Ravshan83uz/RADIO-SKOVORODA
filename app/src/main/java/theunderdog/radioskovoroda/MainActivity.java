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