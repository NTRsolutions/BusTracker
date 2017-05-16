package com.project.verbosetech.bustracker.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.project.verbosetech.bustracker.Models.Profile;
import com.project.verbosetech.bustracker.Others.ProfileRecycleGrid;
import com.project.verbosetech.bustracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this pc on 14-05-17.
 */

public class profileActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView profile_image;

    private RecyclerView.LayoutManager layoutManager;
    ProfileRecycleGrid adapter;
    RecyclerView recyclerView;
    List<Profile> profileList;

    private static final String urlProfileImg = "https://c.tadst.com/gfx/750w/fatherson.jpg?1";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        setSupportActionBar(toolbar);
        profile_image=(ImageView)findViewById(R.id.img_profile);

        recyclerView=(RecyclerView)findViewById(R.id.profile_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(profileActivity.this,MainActivity.class));
                finish();
            }
        });

        getProfileCards();
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

    public void getProfileCards(){

        profileList=new ArrayList<>();
        profileList.add(new Profile("Rajesh Gupta",urlProfileImg,"Father","+91 903 335 6708","rajeshgupta@gmail.com"));
        profileList.add(new Profile("Rajesh Gupta",urlProfileImg,"Father","+91 903 335 6708","rajeshgupta@gmail.com"));
        adapter=new ProfileRecycleGrid(profileList,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
