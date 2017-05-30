package com.project.verbosetech.bustracker.fragments;

import android.content.Context;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.verbosetech.bustracker.R;
import com.project.verbosetech.bustracker.others.DelayAutoCompleteTextView;
import com.project.verbosetech.bustracker.others.GeoAutoCompleteAdapter;
import com.project.verbosetech.bustracker.others.GeoSearchResult;
import com.project.verbosetech.bustracker.others.GoogleMapsPath;

import java.util.List;

/**
 * Created by this pc on 14-05-17.
 */

public class DropFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private View view;
    Button setReminder,no_button,yes_button;
    AlertDialog alertDialog;
    private Integer THRESHOLD = 2;
    private DelayAutoCompleteTextView geo_autocomplete;
    ImageView gps_search;
    DelayAutoCompleteTextView textView;
    LatLng user_latlang;
    GoogleApiClient googleApiClient;
    SupportMapFragment mapFragment;
    private GoogleMap Map;
    LinearLayout linearLayout;
    RelativeLayout relativeLayout;

    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);

    FrameLayout fm;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

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
        yes_button=(Button)view.findViewById(R.id.yes);
        linearLayout=(LinearLayout)view.findViewById(R.id.layout);
        relativeLayout=(RelativeLayout)view.findViewById(R.id.layout2);

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"To be implemented",Toast.LENGTH_LONG).show();
            }
        });

        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("daba","ddaba");
                linearLayout.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });


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

                    Toast.makeText(getActivity(),currentLatitude+" ,"+currentLongitude,Toast.LENGTH_LONG).show();
                    search(Map,new LatLng(currentLatitude,currentLongitude));

                    GoogleMapsPath googleMapsPath=new GoogleMapsPath(getActivity(),Map,new LatLng(currentLatitude,currentLongitude),new LatLng(currentLatitude+0.005,currentLongitude+0.0005));

                    Map.addMarker(new MarkerOptions().position(new LatLng(currentLatitude+0.005,currentLongitude+0.0005)).title("Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            }
        });

        textView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (!textView.getText().toString().equals("")) {
                        search(Map, getLocationFromAddress(textView.getText().toString()));
                        Log.e("Addresssss", textView.getText().toString());

                        GoogleMapsPath googleMapsPath=new GoogleMapsPath(getActivity(),Map,getLocationFromAddress(textView.getText().toString()),new LatLng(currentLatitude+0.005,currentLongitude+0.0005));
                        Map.addMarker(new MarkerOptions().position(new LatLng(currentLatitude+0.005,currentLongitude+0.0005)).title("Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(fm.getWindowToken(), 0);}
                }
                return false;
            }
        });



        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.dropdialog_layout, null);
                Button skip=(Button)promptsView.findViewById(R.id.skip);
                final TextView distance=(TextView)promptsView.findViewById(R.id.distance);
                ImageView add=(ImageView)promptsView.findViewById(R.id.add);
                ImageView sub=(ImageView)promptsView.findViewById(R.id.sub);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double cal_dist,d;
                        String a[]=distance.getText().toString().split(" ");
                        d=Double.parseDouble(a[0]);
                        cal_dist=d+0.5;
                        distance.setText(cal_dist+" km");

                    }
                });

                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double cal_dist=0.0,d;
                        String a[]=distance.getText().toString().split(" ");
                        d=Double.parseDouble(a[0]);
                        if(d>=1.0)
                            cal_dist=d-0.5;
                        distance.setText(cal_dist+" km");

                    }
                });
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
                    // Add a marker in Sydney, Australia, and move the camera.
//                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    Map.setMyLocationEnabled(true);
//                    View mapView = mapFragment.getView();
//                    if (mapView != null &&
//                            mapView.findViewById(1) != null) {
//                        // Get the button view
//                        View locationButton = ((View) mapView.findViewById(1).getParent()).findViewById(2);
//                        // and next place it, on bottom right (as Google Maps app)
//                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
//                                locationButton.getLayoutParams();
//                        // position on right bottom
//                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
//                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//                        layoutParams.setMargins(0,0,0,0);
//                    }

                    search(Map,MOUNTAIN_VIEW);


                }
            });

            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    // The next two lines tell the new client that “this” current class will handle connection stuff
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    //fourth line adds the LocationServices API endpoint from GooglePlayServices
                    .addApi(LocationServices.API)
                    .build();

            // Create the LocationRequest object
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                    .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search(GoogleMap googleMap,LatLng latLng){

        Map=googleMap;

        Map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        Map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in, animating the camera.
        Map.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//        Map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

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
    public void onConnected(Bundle bundle) {

        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);

        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), 9000);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        //Now lets connect to the API
        googleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
            googleApiClient.disconnect();
        }


    }

    @Override
    public void onLocationChanged(Location location) {

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        Toast.makeText(getActivity(), currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();

    }
}
