package org.es.koishi.mycar.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.es.koishi.mycar.R;

public class Fr_02_LocationNavigation extends Fragment
        implements OnMapReadyCallback {

    //region constants
    private static final String kTAG = Fr_02_LocationNavigation.class.getSimpleName();
    //endregion

    //region members
    private SupportMapFragment myMapFragment;
    private Location myLocation;
    private GoogleMap myMap;
    //endregion


    //region FRAGMENT_LIFE_CICLE
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fr_02_location_navigation, container, false);

        FragmentManager myFragmentMngr = this.getActivity().getSupportFragmentManager();
        Fragment myContainer = myFragmentMngr.findFragmentById(R.id.content_frame);
        myMapFragment = (SupportMapFragment) myContainer.getChildFragmentManager().findFragmentById(R.id.fr_01_map);
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
        myMapFragment.getMapAsync(this);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        myMapFragment.getMapAsync(this);
    }
    //endregion FRAGMENT_LIFE_CICLE


    //region OnMapReadyCallback_Methods
    @Override
    public void onMapReady(GoogleMap googleMap) {

        myMap = googleMap;

        if (myLocation != null) {
            Log.i(kTAG + " - onMapReady", "My current location is: " + myLocation.toString());
            LatLng myCoord = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            Log.i(kTAG + " - onMapReady", "My current location is: " + myCoord.toString());

            googleMap.addMarker(new MarkerOptions().position(myCoord).title(myLocation.toString()));

            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCoord, 10f));
            myMap.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null);
        }
    }
    //endregion

}
