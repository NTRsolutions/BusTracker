package com.project.verbosetech.bustracker.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.verbosetech.bustracker.Fragments.BusTrackFragment;
import com.project.verbosetech.bustracker.Fragments.Calender_Fragment;
import com.project.verbosetech.bustracker.Fragments.ContactsFragment;
import com.project.verbosetech.bustracker.Others.CircleTransform;
import com.project.verbosetech.bustracker.Others.PrefManager;
import com.project.verbosetech.bustracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this pc on 19-05-17.
 */

public class StudentTrackingActivity extends AppCompatActivity {

    Toolbar toolbar;
    PrefManager pref;
    String image_address = "http://media.gettyimages.com/photos/male-high-school-student-portrait-picture-id98680202?s=170667a";
    ImageView student_image;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_card_layout);
        pref = new PrefManager(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(pref.getName());
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        setSupportActionBar(toolbar);
        student_image = (ImageView) findViewById(R.id.student_image);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        createViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_host);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(StudentTrackingActivity.this, MainActivity.class));
                finish();
            }
        });

        Glide.with(getApplicationContext()).load(image_address)
                .centerCrop()
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(getApplicationContext()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(student_image);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    private void createTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Tab 1");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_dash26, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Tab 2");
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_category, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Tab 3");
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_order, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new BusTrackFragment(), "Tab 1");
        adapter.addFrag(new ContactsFragment(), "Tab 2");
        adapter.addFrag(new Calender_Fragment(), "Tab 3");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_query_builder_orange_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_local_phone_grey_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_date_range_grey_24dp);
    }
}

