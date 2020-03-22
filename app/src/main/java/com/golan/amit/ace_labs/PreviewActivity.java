package com.golan.amit.ace_labs;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class PreviewActivity extends AppCompatActivity {

    ImageView ivPreview;
    Animation anim;

    /**
     * Timer
     * @param savedInstanceState
     */
    private static final int TIMER = 20 * 1000;
    private int countDownInterval;
    CountDownTimer cTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        init();

        animate();

//        redirect();

        startTimer();
    }

    private void init() {
        ivPreview = findViewById(R.id.ivPreviewAutomationId);
        anim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        countDownInterval = 1000;
        cTimer = null;
    }

    private void startTimer() {
        timerDemo(TIMER);
    }

    private void animate() {
        ivPreview.startAnimation(anim);
    }

    private void redirect() {
        Intent i = new Intent(this, AceActivity.class);
        startActivity(i);
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

}
