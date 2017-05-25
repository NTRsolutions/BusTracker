package com.project.verbosetech.bustracker.activity;

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
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.verbosetech.bustracker.fragments.BusTrackFragment;
import com.project.verbosetech.bustracker.fragments.Calender_Fragment;
import com.project.verbosetech.bustracker.fragments.ContactsFragment;
import com.project.verbosetech.bustracker.others.CircleTransform;
import com.project.verbosetech.bustracker.others.PrefManager;
import com.project.verbosetech.bustracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this pc on 20-05-17.
 */

public class ViewInMapActivity extends AppCompatActivity {

    Toolbar toolbar;
    PrefManager pref;
    private TabLayout tabLayout;
    String image_address = "http://media.gettyimages.com/photos/male-high-school-student-portrait-picture-id98680202?s=170667a";
    ImageView student_image;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_in_map_layout);
        pref = new PrefManager(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(pref.getName());
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        setSupportActionBar(toolbar);
        student_image = (ImageView) findViewById(R.id.student_image);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewInMapActivity.this, StudentTrackingActivity.class);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//                    View student_image  = findViewById(R.id.student_image);
//                    View status = findViewById(R.id.status);
//                    View name=findViewById(R.id.student_name);
//                    View class_sec=findViewById(R.id.class_section);
//                    View tab_host=findViewById(R.id.tab_host);
//
//                    Pair<View, String> pair1 = Pair.create(student_image, student_image.getTransitionName());
//                    Pair<View, String> pair2 = Pair.create(name, name.getTransitionName());
//                    Pair<View, String> pair3 = Pair.create(status, status.getTransitionName());
//                    Pair<View, String> pair4 = Pair.create(class_sec, class_sec.getTransitionName());
//                    Pair<View, String> pair5 = Pair.create(tab_host, tab_host.getTransitionName());
//
//                    ActivityOptionsCompat options = ActivityOptionsCompat.
//                            makeSceneTransitionAnimation(ViewInMapActivity.this, pair1, pair2, pair3,pair4,pair5);
//                    startActivity(intent, options.toBundle());
//                }
//                else {
//                    startActivity(intent);
//                }

                startActivity(intent);
                finish();
            }
        });

        Glide.with(getApplicationContext()).load(image_address)
                .centerCrop()
                .crossFade()
                .thumbnail(0.3f)
                .bitmapTransform(new CircleTransform(getApplicationContext()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(student_image);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        createViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_host);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if(tabLayout.getSelectedTabPosition()==0){
                    tabLayout.getTabAt(0).setIcon(R.drawable.ic_query_builder_orange_24dp);
                    tabLayout.getTabAt(1).setIcon(R.drawable.ic_local_phone_grey_24dp);
                    tabLayout.getTabAt(2).setIcon(R.drawable.ic_date_range_grey_24dp);}
                if(tabLayout.getSelectedTabPosition()==1){
                    tabLayout.getTabAt(0).setIcon(R.drawable.ic_query_builder_grey_24dp);
                    tabLayout.getTabAt(1).setIcon(R.drawable.ic_local_phone_orange_24dp);
                    tabLayout.getTabAt(2).setIcon(R.drawable.ic_date_range_grey_24dp);}
                if(tabLayout.getSelectedTabPosition()==2){
                    tabLayout.getTabAt(0).setIcon(R.drawable.ic_query_builder_grey_24dp);
                    tabLayout.getTabAt(1).setIcon(R.drawable.ic_local_phone_grey_24dp);
                    tabLayout.getTabAt(2).setIcon(R.drawable.ic_date_range_orange_24dp);}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setupTabIcons();
    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_query_builder_orange_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_local_phone_grey_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_date_range_grey_24dp);
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
}
