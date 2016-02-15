package org.es.koishi.mycar.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.es.koishi.mycar.R;

public class Fr_01_LocationAcquisition extends Fragment
        implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    //region constants
    private static final String kTAG = Fr_01_LocationAcquisition.class.getSimpleName();
    //endregion

    //region members
    private GoogleApiClient myGoogleClient;
    private SupportMapFragment myMapFragment;
    private Location myCurrentLocation;
    private GoogleMap myMap;
    //endregion


    //region FRAGMENT_LIFE_CICLE
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Connect to Google API Services in order to get current location
        //https://developer.android.com/intl/es/training/location/retrieve-current.html#play-services
        if (myGoogleClient == null) {
            myGoogleClient = new GoogleApiClient.Builder(this.getContext())
                                    .addConnectionCallbacks(this)
                                    .addOnConnectionFailedListener(this)
                                    .addApi(LocationServices.API)
                                    .build();
        }

        FloatingActionButton myFab1 = (FloatingActionButton) this.getActivity().findViewById(R.id.fr_01_fab_01);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fr_01_location_acquisition, container, false);

        FragmentManager myFragmentMngr = this.getActivity().getSupportFragmentManager();
        Fragment myContainer1 = myFragmentMngr.findFragmentById(R.id.content_frame);
        //Fragment myContainer2 = myContainer1.getChildFragmentManager().findFragmentById(R.id.fr_01_container);
        myMapFragment = (SupportMapFragment) myContainer1.getChildFragmentManager().findFragmentById(R.id.fr_01_map);
        //myMapFragment.getMapAsync(this);
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
        myGoogleClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        myGoogleClient.disconnect();
    }

    @Override
    public void onResume() {
        super.onResume();
        myGoogleClient.connect();
    }

    //endregion FRAGMENT_LIFE_CICLE


    //region OnMapReadyCallback_Methods
    @Override
    public void onMapReady(GoogleMap googleMap) {

        myMap = googleMap;

        //Enable 'My Location' layer (after checking permissions are granted)
        //https://developers.google.com/maps/documentation/android-api/location#runtime-permission
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            myMap.setMyLocationEnabled(true);
        } else {
            //Show message
        }

        Log.i(kTAG + " - onMapReady", "Google API is connecting: " + myGoogleClient.isConnecting());
        Log.i(kTAG + " - onMapReady", "Google API is connected: " + myGoogleClient.isConnected());

        if (myCurrentLocation != null) {
            Log.i(kTAG + " - onMapReady", "My current location is: " + myCurrentLocation.toString());
            LatLng myCoord = this.getCoordinates(myCurrentLocation);
            Log.i(kTAG + " - onMapReady", "My current location is: " + myCoord.toString());
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCoord, 10f));
            myMap.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null);
        }



        /*
                // Add a marker in Sydney, Australia, and move the camera.
                LatLng myCoord = new LatLng(-34d, 151d);

        //myCurrentLocation = googleMap.getMyLocation();
        //LatLng myCoord = new LatLng(myCurrentLocation.getLatitude(), myCurrentLocation.getLongitude());

        googleMap.addMarker(new MarkerOptions().position(myCoord).title("Marker in Sydney"));
        //googleMap.addMarker(new MarkerOptions().position(myCoord).title(this.getCurrentLocation().toString()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCoord, 10f));
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(15f),2000, null);
        Log.i(kTAG, "Moving camera to position " + myCoord.toString());
        */
    }
    //endregion

    //region GoogleApiClient.ConnectionCallbacks (retrieving current location)
    @Override
    public void onConnected(Bundle bundle) {

        Log.i(kTAG + " - onConnected", "Google API is connecting: " + myGoogleClient.isConnecting());
        Log.i(kTAG + " - onConnected", "Google API is connected: " + myGoogleClient.isConnected());

        if (myGoogleClient != null) {
            if (myGoogleClient.isConnected()) {
                try {
                    myCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(myGoogleClient);
                    Log.i(kTAG, "My current location is: " + myCurrentLocation.toString());
                } catch (SecurityException secEx) {
                    Log.e(kTAG, "Unable to get current location", secEx);
                }
            }
        }

        //Call 'onMapReady' method
        myMapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(kTAG, "Location service suspended");
    }
    //endregion

    //region GoogleApiClient.OnConnectionFailedListener
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(kTAG, "Location service failed");
    }
    //endregion


    //region custom_methods
    private void setUpMap(GoogleMap forMap, LatLng usingCoordinates) {
        if (forMap == null) {

        }
    }

    private Location getCurrentLocation() {
        Location myLastLocation = null;
        myGoogleClient.connect();
        /*try {
            //if (myGoogleClient.isConnected()) {
                myLastLocation = LocationServices.FusedLocationApi.getLastLocation(myGoogleClient);
                Log.i(kTAG, "My current location is: " + myLastLocation.toString());
            //}
        } catch (SecurityException secEx) {
            Log.e(kTAG, "Unable to get current location", secEx);
        }*/
        myGoogleClient.disconnect();
        return  myCurrentLocation;
    }

    private LatLng getCoordinates(Location fromLocation) {
        LatLng result = null;
        if (fromLocation != null){
            result = new LatLng(fromLocation.getLatitude(), fromLocation.getLongitude());
        }
        return result;
    }
    //endregion
}
