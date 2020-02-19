package com.example.proto3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    private String customerId = "";
    private float rideDistance;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();



             //Oncreate function

             @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_map);

         BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

         bottomNavigationView.setSelectedItemId(R.id.nav_home);

         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                 switch (menuItem.getItemId()) {
                     case R.id.nav_account:
                         startActivity(new Intent(getApplicationContext()
                                 ,Main3Activity.class));
                         overridePendingTransition(0,0);
                         return true;
                     case R.id.nav_menu:
                         startActivity(new Intent(getApplicationContext()
                                 ,Main4Activity.class));
                         overridePendingTransition(0,0);
                         return true;
                     case R.id.nav_home:
                         return true;
                 }
                 return false;
             }
         });




                 // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);



        mapFragment.getMapAsync(this);


        //mLogout = (Button) findViewById(R.id.DriverLogoutBtn); //Driver logout activity
        //mLogout.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  isloggingout = true;

                //FirebaseAuth.getInstance().signOut();
                //Intent intent = new Intent(DriversMapActivity.this, WelcomeActivity.class);
               // startActivity(intent);
                //finish();
                //return;
            //}
        //});

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
                mMap.setMyLocationEnabled(true);
            } else {
                mMap.setMyLocationEnabled(true);
            }
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setMinZoomPreference(11);
        }

















        //private void disconnectdriver ()
        //{
            //super.onStop();
            //String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            //DatabaseReference DriverAvailabilityRef = FirebaseDatabase.getInstance().getReference().child("Drivers Available");

            //GeoFire geoFire = new GeoFire(DriverAvailabilityRef);
            //geoFire.removeLocation(userID);

        //}



    }
}

