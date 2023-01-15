package com.sersoft.customprogressbar;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;

import android.os.Handler;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private AnimationDrawable frameAnimation;
    private Button startButton;
    private Button stopButton;
    private Handler handler;
    private Runnable runnable;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        startButton = findViewById(R.id.btnStart);
        stopButton = findViewById(R.id.btnStop);
        ImageView img = findViewById(R.id.imageView);
        img.setBackgroundResource(R.drawable.animation_file);
        frameAnimation = (AnimationDrawable) img.getBackground();

        startButton.setOnClickListener(view -> {
            progress = 0;
            progressBar.setProgress(progress);
            frameAnimation.start();
            handler = new Handler();
            runnable = () -> {
                progress++;
                progressBar.setProgress(progress);
                if (progress < 100) {
                    handler.postDelayed(runnable, 50);
                } else {
                    frameAnimation.stop();
                }
            };
            handler.post(runnable);
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 0;
                progressBar.setProgress(progress);
                frameAnimation.stop();
                handler.removeCallbacks(runnable);
            }
        });
    }
}




