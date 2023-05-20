package com.example.surveyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class location extends FragmentActivity implements OnMapReadyCallback {

    //public int LOCATION_REQUEST_CODE = 10001;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            if(locationResult == null){

                return;
            }

            for (Location location :locationResult.getLocations()){



            }
        }
    };

    Location currentLocation;
    //FusedLocationProviderClient fusedLocationProviderClient;

    public static final int REQUEST_CODE = 101;

    public Values values =  new Values();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        final Intent intent = getIntent();

        final String username =  intent.getExtras().getString("UserName");
        final String age = intent.getExtras().getString("Age");
        final String nature_of_environment = intent.getExtras().getString("nature_of_environment");


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        final Button location_btn = (Button) findViewById(R.id.location_btn);
        ImageButton img_btn = (ImageButton) findViewById(R.id.img_btn);

        Button next_button = (Button)  findViewById(R.id.next_button);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(values.getLatitudeValue == null && values.getLongitudeValue == null){
                    AlertDialog.Builder msg = new AlertDialog.Builder(location.this);
                    msg.setMessage(Html.fromHtml("<font color='#D81B60'>Please Fetch Current Location First !</font><br>"));//+age));
                    msg.setNegativeButton("Retry",null);
                    msg.create();
                    msg.show();
                }

                else {

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(ActivityCompat.checkSelfPermission(location.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(location.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                            androidx.appcompat.app.AlertDialog.Builder msg = new androidx.appcompat.app.AlertDialog.Builder(location.this);

                            msg.setMessage(Html.fromHtml("<font color='#D81B60'>Please Grant Permissions In Order to Proceed !</font>"));
                            msg.setNegativeButton("OK",null);
                            msg.create();
                            msg.show();

                            ActivityCompat.requestPermissions(location.this,new String[]{Manifest.permission.RECORD_AUDIO},100);
                            ActivityCompat.requestPermissions(location.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
                            ActivityCompat.requestPermissions(location.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                        }
                        else {


                            Intent intent1 = new Intent(location.this, RecordActivity.class);
                            intent1.putExtra("UserName", username);
                            intent1.putExtra("Age", age);
                            intent1.putExtra("Environment", nature_of_environment);
                            intent1.putExtra("Latitude", values.getLatitudeValue);
                            intent1.putExtra("Longitude", values.getLongitudeValue);
                            startActivity(intent1);
                        }}

                }
            }
        });

        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkSetting();
                fetchLastLocation();

                /*AlertDialog.Builder builder = new AlertDialog.Builder(location.this);

                builder.setMessage("This is Alert Dialog Builder");
                builder.setPositiveButton("OK", null);
                builder.create();
                builder.show(); */

            }
        });

        //fetchLastLocation();

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 Toast.makeText(getApplicationContext(),"Fetching Current Location !", Toast.LENGTH_SHORT);

                 checkSetting();
                fetchLastLocation();
            }
        });

    }

    private void fetchLastLocation() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

    // Check Permissions Now

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);

    // permission has been granted, continue as usual
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            // Check Permissions Now

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE);

            // permission has been granted, continue as usual
        }


        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null){

                    currentLocation = location;
                    Toast.makeText(getApplicationContext(),"Latitude : "+currentLocation.getLatitude()+" \n" +"Longitude : "+currentLocation.getLongitude(),Toast.LENGTH_LONG).show();

                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_map);
                    supportMapFragment.getMapAsync(com.example.surveyapplication.location.this);

                    AlertDialog.Builder builder = new AlertDialog.Builder(location.this);

                    builder.setMessage(Html.fromHtml("<font color='#D81B60'>Current Location !</font>" +"<br> <br> <font color='#D81B60'>Latitude: </font><font>" + currentLocation.getLatitude() + "</font><br><font color='#D81B60'>Longitude: </font><font>" + currentLocation.getLongitude() + "</font>")); //"Arrange Appointment With Following Information ? );
                    builder.setPositiveButton("OK",null);
                    builder.create();
                    builder.show();

                    values.setGetLatitudeValue(currentLocation.getLatitude());
                    values.setGetLongitudeValue(currentLocation.getLongitude());



                }else  {

                    AlertDialog.Builder builder = new AlertDialog.Builder(location.this);

                    builder.setMessage(Html.fromHtml("<font color='#D81B60'>Please Turn on Your Location and Wait then Try Again Later after few Seconds!</font>"));
                    builder.setPositiveButton("OK",null);
                    builder.create();
                    builder.show();



                }

                stopLocationUpdates();

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng =  new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Current Location !");

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case REQUEST_CODE:

                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    fetchLastLocation();

                }

                break;

        }
    }

    public void checkSetting(){

        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = settingsClient.checkLocationSettings(locationSettingsRequest);

        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                //device settings are now satisfied for location updates

                startLocationUpdates();

            }
        });

        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if(e instanceof ResolvableApiException){

                    ResolvableApiException resolvableApiException = (ResolvableApiException) e;

                    try {
                        resolvableApiException.startResolutionForResult(location.this,101);
                    } catch (IntentSender.SendIntentException ex) {
                        new AlertDialog.Builder(location.this)
                                .setTitle("")
                                .setMessage(ex.getMessage().toString())
                                .create()
                                .show();
                    }

                }
            }
        });


    }

    public void startLocationUpdates(){

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());

    }

    public void stopLocationUpdates(){

        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
}
