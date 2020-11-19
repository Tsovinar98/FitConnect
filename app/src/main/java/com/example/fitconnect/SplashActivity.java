package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView_fitconnectLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView_fitconnectLogo = findViewById(R.id.imageView_sp_fitconnectLogo);

        ScaleAnimation scaleUp =  new ScaleAnimation(0f, .75f, 0f, .75f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleUp.setInterpolator(new AccelerateInterpolator()); //and this
        scaleUp.setStartOffset(0);
        scaleUp.setFillBefore(false);
        scaleUp.setFillAfter(true);
        scaleUp.setDuration(2500);

        AnimationSet animation = new AnimationSet(false); //change to false
        imageView_fitconnectLogo.startAnimation(scaleUp);

        int SPLASH_TIME_OUT = 2500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}