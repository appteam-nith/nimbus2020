package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.R;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView t_n, t_k, e_n, e_k;
        Animation animation, animation1, animation2, animation3;

        t_n = findViewById(R.id.t_n);
        t_k = findViewById(R.id.t_k);
        e_n = findViewById(R.id.e_n);
        e_k = findViewById(R.id.e_k);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fast_anim);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slow_anim);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fast_anim_h);
        animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slow_anim_h);

        e_n.startAnimation(animation);
        e_k.startAnimation(animation1);
        t_n.startAnimation(animation2);
        t_k.startAnimation(animation3);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.ease_in, R.anim.ease_out);
            }
        }, 2500);
    }
}
