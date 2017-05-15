package com.project.verbosetech.bustracker.Fragments;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.verbosetech.bustracker.Others.DelayAutoCompleteTextView;
import com.project.verbosetech.bustracker.Others.GeoAutoCompleteAdapter;
import com.project.verbosetech.bustracker.Others.GeoSearchResult;
import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 14-05-17.
 */

public class PIckUpFragment extends Fragment implements LocationListener,OnMapReadyCallback {

    private View view;
    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;
    private Integer THRESHOLD = 2;
    private DelayAutoCompleteTextView geo_autocomplete;
//    private ImageView geo_autocomplete_clear;

    public PIckUpFragment() {
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
        view= inflater.inflate(R.layout.location_fragment_1, container, false);

        try {
            // Loading map
//            initializeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        geo_autocomplete_clear = (ImageView) view.findViewById(R.id.geo_autocomplete_clear);

        geo_autocomplete = (DelayAutoCompleteTextView) view.findViewById(R.id.geo_autocomplete);
        geo_autocomplete.setThreshold(THRESHOLD);
        geo_autocomplete.setAdapter(new GeoAutoCompleteAdapter(getActivity())); // 'this' is Activity instance

        geo_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                geo_autocomplete.setText(result.getAddress());
            }
        });

        geo_autocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                {
//                    geo_autocomplete_clear.setVisibility(View.VISIBLE);
                }
                else
                {
//                    geo_autocomplete_clear.setVisibility(View.GONE);
                }
            }
        });

//        geo_autocomplete_clear.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                geo_autocomplete.setText("");
//            }
//        });

        return view;
    }

//        private void initializeMap() {
//            SupportMapFragment supportMapFragment =
//                    (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
//            googleMap = supportMapFragment.getMapAsync();
//            googleMap.setMyLocationEnabled(true);
//            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//            Criteria criteria = new Criteria();
//            String bestProvider = locationManager.getBestProvider(criteria, true);
//            Location location = locationManager.getLastKnownLocation(bestProvider);
//            if (location != null) {
//                onLocationChanged(location);
//            }
//            locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
//            if (supportMapFragment == null) {
//                Toast.makeText(getActivity(),
//                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//        }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
