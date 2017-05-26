package com.project.verbosetech.bustracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 12-05-17.
 */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    Animation animation;
    ImageView logo;
    LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        logo=(ImageView)findViewById(R.id.app_icon);
        linearLayout=(LinearLayout)findViewById(R.id.layout);

        animation = AnimationUtils.loadAnimation(this,R.anim.shake_animation);
        linearLayout.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
