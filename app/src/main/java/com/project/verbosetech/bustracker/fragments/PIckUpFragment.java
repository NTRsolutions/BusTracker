package com.project.verbosetech.bustracker.fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.verbosetech.bustracker.others.DelayAutoCompleteTextView;
import com.project.verbosetech.bustracker.others.GeoAutoCompleteAdapter;
import com.project.verbosetech.bustracker.others.GeoSearchResult;
import com.project.verbosetech.bustracker.R;

import java.util.List;

/**
 * Created by this pc on 14-05-17.
 */

public class PIckUpFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private View view;
    private GoogleMap Map;
    private Integer THRESHOLD = 2;
    private DelayAutoCompleteTextView geo_autocomplete;
    SupportMapFragment mapFragment;
    private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);
    ImageView gps_search;
//  private ImageView geo_autocomplete_clear;

    DelayAutoCompleteTextView textView;
    LatLng user_latlang;
    GoogleApiClient googleApiClient;
    GoogleMap.OnMyLocationChangeListener myLocationChangeListener;

    FrameLayout fm;

    Button continuee,setReminder;

    public PIckUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.location_fragment_1, container, false);
        textView = (DelayAutoCompleteTextView) view.findViewById(R.id.geo_autocomplete);
        gps_search = (ImageView) view.findViewById(R.id.gps_search);
        fm=(FrameLayout)view.findViewById(R.id.main_layout);
        continuee=(Button)view.findViewById(R.id.continuee);
        setReminder=(Button)view.findViewById(R.id.pickup_reminder);
        continuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"To be implemented",Toast.LENGTH_LONG).show();
            }
        });

        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

//      geo_autocomplete_clear = (ImageView) view.findViewById(R.id.geo_autocomplete_clear);
        geo_autocomplete = (DelayAutoCompleteTextView) view.findViewById(R.id.geo_autocomplete);
        geo_autocomplete.setThreshold(THRESHOLD);
        geo_autocomplete.setAdapter(new GeoAutoCompleteAdapter(getActivity())); // 'this' is activity instance

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
                if (s.length() > 0) {
//                    geo_autocomplete_clear.setVisibility(View.VISIBLE);
                } else {
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

        gps_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!textView.getText().toString().equals("")) {
                    search(Map, getLocationFromAddress(textView.getText().toString()));
                    Log.e("Addresssss", textView.getText().toString());

                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(fm.getWindowToken(), 0);
                } else {
                Map.setOnMyLocationChangeListener(myLocationChangeListener);
                   myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
                        @Override
                        public void onMyLocationChange(Location location) {
                            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
                            Map.addMarker(new MarkerOptions().position(loc));
                            if(Map != null){
                                Map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                            }
                        }
                    };
                }
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        try {

            mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance();
                getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
            }
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    Map = googleMap;
                    // Add a marker in Sydney, Australia, and move the camera.
                    search(Map,MOUNTAIN_VIEW);

                }
            });

            //Initializing googleApiClient
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            googleApiClient.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (Map == null) {
//            mapFragment.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap googleMap) {
//
//                    Map = googleMap;
//                    // Add a marker in Sydney, Australia, and move the camera.
//
//                    LatLng sydney = new LatLng(-34, 151);
//                    Map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//                    Map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//                }
//            });
//        }
    }

    public void search(GoogleMap googleMap,LatLng latLng){

        Map=googleMap;

        Map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        Map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in, animating the camera.
        Map.animateCamera(CameraUpdateFactory.zoomIn());

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        Map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to Mountain View
                .zoom(13)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        Map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    public LatLng getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(getActivity());
        List<Address> address;
        Address location=null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            location=address.get(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new LatLng(location.getLatitude(),location.getLongitude());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
