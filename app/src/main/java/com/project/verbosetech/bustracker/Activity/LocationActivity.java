package com.project.verbosetech.bustracker.Activity;

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

import com.google.android.gms.maps.MapFragment;
import com.project.verbosetech.bustracker.Fragments.DropFragment;
import com.project.verbosetech.bustracker.Fragments.PIckUpFragment;
import com.project.verbosetech.bustracker.R;


/**
 * Created by this pc on 14-05-17.
 */

public class LocationActivity extends AppCompatActivity {

    Toolbar toolbar;
    private MapFragment googleMap;
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

        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                Fragment fragment = new DropFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_location, fragment);
                fragmentTransaction.commitAllowingStateLoss();

            }
        });

//        try {
//            // Loading map
//            initilizeMap();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LocationActivity.this,MainActivity.class));
                finish();
            }
        });
    }

//    private void initilizeMap() {
//        if (googleMap == null) {
//            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
//                    R.id.map));
//
//            // check if map is created successfully or not
//            if (googleMap == null) {
//                Toast.makeText(getApplicationContext(),
//                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//    }
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
