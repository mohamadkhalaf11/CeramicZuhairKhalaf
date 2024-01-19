package com.example.ceramiczuhairkhalaf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map , container , false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                setDefaultLocation(googleMap);
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude+" KG " + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));

                        /*
                        //  add to origin - zoom to l       ocation
                        CameraUpdate point = CameraUpdateFactory.newLatLng(new LatLng(32.512734773380835, 35.2985690073221));

                        // moves camera to coordinates
                        googleMap.moveCamera(point);
                        // animates camera to coordinates
                        googleMap.animateCamera(point);

*/
                    }
                });


            }
        });
        return view;
    }

    private void setDefaultLocation(GoogleMap googleMap) {
        googleMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        LatLng ll = new LatLng(32.51277314380773, 35.298411819118385);
        markerOptions.position(ll);
        markerOptions.title(ll.latitude+" KG " + ll.longitude);
        LatLng latLng2 = new LatLng(32.51277314380773, 35.298411819118385);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 17));
    }
}