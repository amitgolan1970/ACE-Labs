package com.golan.amit.ace_labs;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class AceActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnLongClickListener {

    //    ImageView ivAcePic;
    TextView tvAce[], tvLabs[];
    AceLabsHelper alh;

    /**
     * Sound
     *
     * @param savedInstanceState
     */

    MediaPlayer mp;
    SeekBar sb;
    AudioManager am;
    ImageButton ibLogoSoundOff;

    /**
     * Counter
     */
    private static final int TIMER = 100 * 1000;
    private int countDownInterval;
    CountDownTimer cTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ace);

        init();

        playAceAsync();

        playLabsAsync();

        startTimer();
    }

    private void playAceAsync() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    playAce();
                } catch (Exception e) {
                }
                return null;
            }
        }.execute();
    }

    private void playAce() {
        alh.generateAce_rnd_index();
        int cnt = 0;
        while (!alh.isAceSet()) {
            alh.generate_random_ace();
            if (cnt % 3 == 0) {
                for (int i = 0; i < alh.getRnd_ace().length; i++) {
                    setText(tvAce[i], alh.getAceCharByIndex(i));
                    SystemClock.sleep(150);
                }
            } else {
                for (int i = (alh.getRnd_ace().length - 1); i >= 0; i--) {
                    setText(tvAce[i], alh.getAceCharByIndex(i));
                    SystemClock.sleep(150);
                }
            }
            SystemClock.sleep(100);
            cnt++;
        }
    }

    private void setText(final TextView text, final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    private void playLabsAsync() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    playLabs();
                } catch (Exception e) {
                }
                return null;
            }
        }.execute();
    }

    private void playLabs() {
        alh.generateLabs_rnd_index();
        int cnt = 0;
        while (!alh.isLabsSet()) {
            alh.generate_random_labs();
            if (cnt % 3 == 0) {
                for (int i = 0; i < alh.getRnd_labs().length; i++) {
                    setText(tvLabs[i], alh.getLabsCharByIndex(i));
                    SystemClock.sleep(150);
                }
            } else {
                for (int i = (alh.getRnd_labs().length - 1); i >= 0; i--) {
                    setText(tvLabs[i], alh.getLabsCharByIndex(i));
                    SystemClock.sleep(150);
                }
            }
            SystemClock.sleep(100);
            cnt++;
        }
    }

    private void init() {
        tvAce = new TextView[]{
                findViewById(R.id.tvAce0), findViewById(R.id.tvAce1), findViewById(R.id.tvAce2)
        };
        tvLabs = new TextView[]{
                findViewById(R.id.tvLabs0), findViewById(R.id.tvLabs1), findViewById(R.id.tvLabs2), findViewById(R.id.tvLabs3)
        };
        alh = new AceLabsHelper();

        sb = findViewById(R.id.sb);
        mp = MediaPlayer.create(this, R.raw.airwolf_theme);
        mp.start();

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(max);
        sb.setProgress(max / 4);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, max / 4, 0);
        sb.setOnSeekBarChangeListener(this);

        ibLogoSoundOff = findViewById(R.id.ibLogoSoundOff);
        ibLogoSoundOff.setOnClickListener(this);
        ibLogoSoundOff.setOnLongClickListener(this);

        countDownInterval = 1000;
        cTimer = null;
    }

    private void startTimer() {
        timerDemo(TIMER);
    }

    @Override
    public void onClick(View v) {
        if (v == ibLogoSoundOff) {
            mp.stop();
            sb.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mp.release();
        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mp != null) {
            try {
                mp.start();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public boolean onLongClick(View v) {
        alh = new AceLabsHelper();
        try {
            playAceAsync();
            playLabsAsync();
        } catch (Exception e) {
        }
        return true;
    }

    private void timerDemo(final long millisInFuture) {

        cTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                long Minutes = millisInFuture / (60 * 1000) % 60;
                long Seconds = millisInFuture / 1000 % 60;
                String tmpTime = String.format("%02d:%02d", Minutes, Seconds);
            }

            @Override
            public void onFinish() {
                cancel();
                redirect();
            }
        }.start();
    }

    private void redirect() {
        Intent i = new Intent(this, PreviewActivity.class);
        startActivity(i);
    }
}
