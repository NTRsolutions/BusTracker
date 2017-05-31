package com.project.verbosetech.bustracker.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.project.verbosetech.bustracker.R;
import com.project.verbosetech.bustracker.others.ConnectionDetector;
import com.project.verbosetech.bustracker.others.PrefManager;

/**
 * Created by this pc on 12-05-17.
 */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    Animation animation1,animation2;
    ImageView logo;
    LinearLayout linearLayout;
    ConnectionDetector connectionDetector;
    PrefManager pref;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        logo=(ImageView)findViewById(R.id.app_icon);
        linearLayout=(LinearLayout)findViewById(R.id.layout);
        pref=new PrefManager(getApplicationContext());
        pref.setNotifyStatus(null);
        animation1 = AnimationUtils.loadAnimation(this,R.anim.shake_animation);
        animation2 = AnimationUtils.loadAnimation(this,R.anim.animate_layout);
        linearLayout.startAnimation(animation1);




        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                connectionDetector=new ConnectionDetector(SplashActivity.this);

                if(connectionDetector.isConnectingToInternet()) {
                Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(i);
                // close this activity
                finish();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                    builder.setMessage(" Connect to Internet ")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}
