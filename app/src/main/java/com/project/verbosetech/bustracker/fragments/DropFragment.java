package com.project.verbosetech.bustracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 14-05-17.
 */

public class DropFragment extends Fragment {

    private View view;
    Button setReminder,no_button;
    AlertDialog alertDialog;

    SupportMapFragment mapFragment;
    private GoogleMap Map;
    LinearLayout linearLayout;

    public DropFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.location_fragment_2, container, false);
        setReminder=(Button)view.findViewById(R.id.drop_reminder);
        no_button=(Button)view.findViewById(R.id.no);
        linearLayout=(LinearLayout)view.findViewById(R.id.layout);
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.dropdialog_layout, null);
                Button skip=(Button)promptsView.findViewById(R.id.skip);
                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });

                no_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        linearLayout.setVisibility(View.GONE);

                    }
                });

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
                alertDialogBuilder.setView(promptsView);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        try {

            mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map2);
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance();
                getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
            }
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    Map = googleMap;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
