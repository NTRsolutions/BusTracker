package com.project.verbosetech.bustracker.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.project.verbosetech.bustracker.fragments.DropFragment;
import com.project.verbosetech.bustracker.fragments.PIckUpFragment;
import com.project.verbosetech.bustracker.R;

import static com.project.verbosetech.bustracker.R.drawable.drop_bkgrnd;
import static com.project.verbosetech.bustracker.R.drawable.pickup_bkgrnd;


/**
 * Created by this pc on 14-05-17.
 */

public class LocationActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button pickup,drop;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar5);
        toolbar.setTitle("Edit Location");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        setSupportActionBar(toolbar);

        pickup=(Button)findViewById(R.id.pick_up);
        drop=(Button)findViewById(R.id.drop);

        Fragment fragment = new PIckUpFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame_location, fragment);
        fragmentTransaction.commitAllowingStateLoss();

        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickup.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_location_on_orange_24dp, 0, 0, 0);
                pickup.setTextColor(getResources().getColor(R.color.colorPrimary));
                pickup.setBackground(getResources().getDrawable(pickup_bkgrnd));
                drop.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_location_on_orang_24dp, 0, 0, 0);
                drop.setTextColor(getResources().getColor(R.color.splashTitle));
                drop.setBackground(getResources().getDrawable(drop_bkgrnd));
                Fragment fragment = new PIckUpFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_location, fragment);
                fragmentTransaction.commitAllowingStateLoss();

            }
        });

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drop.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_location_on_orange_24dp, 0, 0, 0);
                drop.setTextColor(getResources().getColor(R.color.colorPrimary));
                drop.setBackground(getResources().getDrawable(pickup_bkgrnd));
                pickup.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_location_on_orang_24dp, 0, 0, 0);
                pickup.setTextColor(getResources().getColor(R.color.splashTitle));
                pickup.setBackground(getResources().getDrawable(drop_bkgrnd));
                Fragment fragment = new DropFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_location, fragment);
                fragmentTransaction.commitAllowingStateLoss();

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LocationActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initilizeMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
