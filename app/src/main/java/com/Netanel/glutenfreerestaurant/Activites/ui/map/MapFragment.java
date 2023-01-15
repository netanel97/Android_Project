package com.Netanel.glutenfreerestaurant.Activites.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.databinding.MapFragmentBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapFragment extends Fragment {

    private MapFragmentBinding binding;
    private ArrayList<LatLng> locationArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = MapFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SupportMapFragment supportMapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps));
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                setMapLocation(googleMap);
            }
        });
        return root;
    }

    public void setMapLocation(GoogleMap googleMap) {
        LatLng restaurant = new LatLng(Constants.restaurantLat, Constants.restaurantLong);
        LatLng customer = new LatLng(Constants.User_Lat, Constants.User_lon);
        locationArrayList.add(restaurant);
        locationArrayList.add(customer);

        for (int i = 0; i < locationArrayList.size(); i++) {
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions()
                    .position(locationArrayList.get(i)).title("hello"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(locationArrayList.get(i))      // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



//
//            // below line is use to add marker to each location of our array list.
//            googleMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
//
//            // below line is use to zoom our camera on map.
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
//
//            // below line is use to move our camera to the specific location.
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
//            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
//            //   CameraPosition cameraPosition =new CameraPosition.Builder().zoom(15).build();

        }
    }


//        googleMap.clear();
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(Constants.restaurantLat,Constants.restaurantLong)).title("hello"));
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(Constants.restaurantLat,Constants.restaurantLong))      // Sets the center of the map to location user
//                .zoom(15)                   // Sets the zoom
//                .build();                   // Creates a CameraPosition from the builder
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    //  }
}
