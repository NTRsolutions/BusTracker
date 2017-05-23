package com.project.verbosetech.bustracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 11-05-17.
 */

public class SignInActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.sign_in);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignInActivity.this,OtpActivity.class));
                finish();
            }
        });

    }
}
