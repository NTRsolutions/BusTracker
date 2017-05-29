package com.project.verbosetech.bustracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 11-05-17.
 */

public class OtpActivity extends AppCompatActivity {

    FloatingActionButton fab;
    Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        fab=(FloatingActionButton)findViewById(R.id.fab2);
        toolbar=(Toolbar)findViewById(R.id.title_bar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(OtpActivity.this,SignInActivity.class));
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(OtpActivity.this,MainActivity.class));
                finish();
            }
        });

    }
}
