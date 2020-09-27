package kr.re.eslab.konanalysis.Model.Services.GpsServices;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class GpsService extends Service implements LocationListener{
    private static final String TAG = GpsService.class.getSimpleName();

    private final Context mContext;

    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;

    private Location location;
    protected LocationManager locationManager;

    private double gpsLat;
    private double gpsLng;
    private double gpsAlt;
    private double gpsSpd;
    private float gpsHad;

    private boolean checkChange = false;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 2;
    private static final long MIN_TIME_BW_UPDATES = 1000 ;
//    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    public GpsService(Context context) {
        this.mContext = context;

        isGPSEnabled = false;
        isNetworkEnabled = false;

        getLocation();
    }

    @TargetApi(23)
    public Location getLocation() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(
                        mContext, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                        mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        try{
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!isGPSEnabled && !isNetworkEnabled){
                //
            } else {
                if(isGPSEnabled){
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
                            this
                    );
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            gpsLat = location.getLatitude();
                            gpsLng = location.getLongitude();
                            gpsAlt = location.getAltitude();
                            gpsHad = location.getBearing();
                            gpsSpd = Double.parseDouble(String.format("%.2f",location.getSpeed()));
                            Log.v(TAG, "Gps Latitude: " + gpsLat +
                                    " Longitude: " + gpsLng +
                                    " Altitude: " + gpsAlt +
                                    " Speed: " + gpsSpd +
                                    " Heading angle: " + gpsHad);
                            Log.e(TAG, "GPS state" + locationManager);
                        }
                    }
                }

                // Network base
                /*
                if(isNetworkEnabled){
                    if(location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                                this
                        );
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                gpsLat = location.getLatitude();
                                gpsLng = location.getLongitude();
                                gpsAlt = location.getAltitude();
                                Log.v(TAG, "Net Latitude: " + gpsLat +
                                        " Longitude: " + gpsLng +
                                        " Altitude: " + gpsAlt);
                                Log.e(TAG, "GPS state" + locationManager);
                            }
                        }
                    }
                }
                */
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }

    public class LocalBinder extends Binder {
        public GpsService getService() {
            return GpsService.this;
        }
    }
    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public double getGpsLocation_Lat() {
        if(location != null){
            gpsLat = location.getLatitude();
        }
        return gpsLat;
    }

    public double getGpsLocation_Lng() {
        if(location != null){
            gpsLng = location.getLongitude();
        }
        return gpsLng;
    }

    public double getGpsLocation_Alt() {
        if(location != null){
            gpsAlt = location.getAltitude();
        }
        return gpsAlt;
    }

    public double getGpsSpd(){
        if(location!=null)
            gpsSpd = location.getSpeed() * 3600 / 1000;
        return gpsSpd;
    }

    public float getGpsHeading(){
        if(location!=null)
            gpsHad = location.getBearing();
        return gpsHad;
    }

    public boolean getCheckState(){
        return checkChange;
    }

    public void setChecked(){
        checkChange = false;
    }
}

