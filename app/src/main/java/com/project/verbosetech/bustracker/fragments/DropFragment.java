package com.project.verbosetech.bustracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.project.verbosetech.bustracker.R;
import com.project.verbosetech.bustracker.others.DelayAutoCompleteTextView;

/**
 * Created by this pc on 14-05-17.
 */

public class DropFragment extends Fragment {

    private View view;
    Button setReminder,no_button;
    AlertDialog alertDialog;
    private Integer THRESHOLD = 2;
    private DelayAutoCompleteTextView geo_autocomplete;
    ImageView gps_search;
    FrameLayout fm;
    DelayAutoCompleteTextView textView;
    LatLng user_latlang;
    GoogleApiClient googleApiClient;
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
        textView = (DelayAutoCompleteTextView) view.findViewById(R.id.geo_autocomplete);
        gps_search = (ImageView) view.findViewById(R.id.gps_search);
        fm = (FrameLayout) view.findViewById(R.id.layout21);
        setReminder=(Button)view.findViewById(R.id.drop_reminder);
        no_button=(Button)view.findViewById(R.id.no);
        linearLayout=(LinearLayout)view.findViewById(R.id.layout);

        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("daba","ddaba");
                linearLayout.setVisibility(View.GONE);


            }
        });




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
                getChildFragmentManager().beginTransaction().replace(R.id.map2, mapFragment).commit();
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
