package com.project.verbosetech.bustracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.verbosetech.bustracker.R;

import static com.project.verbosetech.bustracker.R.drawable.drop_bkgrnd;
import static com.project.verbosetech.bustracker.R.drawable.pickup_bkgrnd;

/**
 * Created by this pc on 27-05-17.
 */

public class LocationFragment extends Fragment {

    private View view;
    Button pickup,drop;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.location_main,container,false);

        intializeButtons();

        Fragment fragment = new PIckUpFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame_location, fragment);
        fragmentTransaction.commitAllowingStateLoss();

        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickup.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_pickup_active, 0, 0, 0);
                pickup.setTextColor(getResources().getColor(R.color.colorPrimary));
                pickup.setBackground(getResources().getDrawable(pickup_bkgrnd));
                drop.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_drop_down_inactive, 0, 0, 0);
                drop.setTextColor(getResources().getColor(R.color.splashTitle));
                drop.setBackground(getResources().getDrawable(drop_bkgrnd));
                Fragment fragment = new PIckUpFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_location, fragment);
                fragmentTransaction.commitAllowingStateLoss();

            }
        });

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drop.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_drop_down_active, 0, 0, 0);
                drop.setTextColor(getResources().getColor(R.color.colorPrimary));
                drop.setBackground(getResources().getDrawable(pickup_bkgrnd));
                pickup.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_pickup_inactive, 0, 0, 0);
                pickup.setTextColor(getResources().getColor(R.color.splashTitle));
                pickup.setBackground(getResources().getDrawable(drop_bkgrnd));
                Fragment fragment = new DropFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_location, fragment);
                fragmentTransaction.commitAllowingStateLoss();

            }
        });
        return view;
    }

    public void changeButton()
    {
        intializeButtons();
        drop.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_drop_down_active, 0, 0, 0);
        drop.setTextColor(getResources().getColor(R.color.colorPrimary));
        drop.setBackground(getResources().getDrawable(pickup_bkgrnd));
        pickup.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_pickup_inactive, 0, 0, 0);
        pickup.setTextColor(getResources().getColor(R.color.splashTitle));
        pickup.setBackground(getResources().getDrawable(drop_bkgrnd));
    }

    public void intializeButtons(){


        pickup=(Button)view.findViewById(R.id.pick_up);
        drop=(Button)view.findViewById(R.id.drop);
    }
}
