package com.example.proto3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriversMapActivity extends FragmentActivity implements OnMapReadyCallback
         {

    private GoogleMap mMap;
    Location lastLocation;
    LocationRequest locationRequest;

    private FusedLocationProviderClient mFusedLocationClient;

    private Button mLogout;
    private boolean isloggingout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mapFragment.getMapAsync(this);

        mLogout = (Button) findViewById(R.id.DriverLogoutBtn);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isloggingout = true;

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DriversMapActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

             private void checkLocationPermission()
             {
                 if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                 {
                     if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)) {
                         new AlertDialog.Builder(this)
                                 .setTitle("Giver permission")
                                 .setMessage("Give permission")
                                 .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialog, int which) {
                                         ActivityCompat.requestPermissions(DriversMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                                     }
                                 })
                                 .create()
                                 .show();
                     }
                     else{
                         ActivityCompat.requestPermissions(DriversMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                     }
                 }
             }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); //best location<also drains a lot of battery>
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            } else {
                checkLocationPermission();
            }
        }


       // @Override
        //public void onLocationChanged (Location location)
        //{
          //  lastLocation = location;
           // LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

            //String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            //DatabaseReference DriverAvailabilityRef = FirebaseDatabase.getInstance().getReference().child("Drivers Available");

            //GeoFire geoFire = new GeoFire(DriverAvailabilityRef);
            //geoFire.setLocation(userID, new GeoLocation(location.getLatitude(), location.getLongitude()));

        //}

       // private void disconnectdriver ()
        //{
          //  super.onStop();
            //String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            //DatabaseReference DriverAvailabilityRef = FirebaseDatabase.getInstance().getReference().child("Drivers Available");

            //GeoFire geoFire = new GeoFire(DriverAvailabilityRef);
            //geoFire.removeLocation(userID);

        //}




    }
}

