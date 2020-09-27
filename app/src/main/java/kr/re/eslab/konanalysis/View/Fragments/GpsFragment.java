package kr.re.eslab.konanalysis.View.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konanalysis.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import kr.re.eslab.konanalysis.Controller.DataController;
import kr.re.eslab.konanalysis.View.ViewModel.AppViewModel;

public class GpsFragment extends Fragment
    implements OnMapReadyCallback {
    private static final String TAG = GpsFragment.class.getSimpleName();

    private AppViewModel mAppViewModel;
    private boolean isLoggindOn = false;

    // View
    private TextView GPS_Lat;
    private TextView GPS_Lng;
    private TextView GPS_Had;
    private TextView GPS_Spd;

    // Map
    private GoogleMap mGoogleMap;
    private SupportMapFragment mSupportMapFragment;
    private FragmentActivity myContext;

    private LatLng startedLocation;
    private LatLng previousLocation;

    private MarkerOptions loggedStart;
    private PolylineOptions polylineOptions;

    private DataController mDataController;

    private boolean pinMaked = false;
    private boolean pinmaker = false;
    private int viewMapLevel = 15;

    public static DriverFragment newInstance() {
        return new DriverFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gps_page, container, false);

        Log.d(TAG,"onCreateView");

        try{
            mSupportMapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.gps_map);
            mSupportMapFragment.getMapAsync(this);
            Log.d(TAG,"Create Map Success");
        } catch(Exception e){
            Log.d(TAG,"Create Map Fail");
            e.printStackTrace();
        }

        GPS_Lat = (TextView)view.findViewById(R.id.fragment_gps_latitude);
        GPS_Lng = (TextView)view.findViewById(R.id.fragment_gps_longitude);
        GPS_Had = (TextView)view.findViewById(R.id.fragment_gps_heading);
        GPS_Spd = (TextView)view.findViewById(R.id.fragment_gps_speed);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataController = new DataController(this.getActivity());
        mGoogleMap = mDataController.getmGoogleMap();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAppViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        // TODO: Use the DataController
    }

    @Override
    public void onDestroyView() {
        if(isLoggindOn)
            mDataController.setmGoogleMap(mGoogleMap);
        else {
            mGoogleMap.clear();
            mDataController.setmGoogleMap(mGoogleMap);
        }
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(mGoogleMap==null) {
            mGoogleMap = googleMap;

            GPS_Lat.setText(String.format("%.5f", getArguments().getDouble("gpsLat")));
            GPS_Lng.setText(String.format("%.5f", getArguments().getDouble("gpsLng")));
            GPS_Had.setText(String.format("%.1f", getArguments().getFloat("gpsHad")));
            GPS_Spd.setText(String.format("%.0f", getArguments().getDouble("gpsSpd")));

            double Lat = getArguments().getDouble("gpsLat");
            double Lng = getArguments().getDouble("gpsLng");

            LatLng nowLocation = new LatLng(Lat, Lng);

            if(isLoggindOn&&pinMaked){
                mGoogleMap.addMarker(loggedStart);
                mGoogleMap.addPolyline(polylineOptions);
            }
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(nowLocation));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(viewMapLevel));
        }
    }

    public void updateMap(){
        Message msg = updateHandle.obtainMessage();
        updateHandle.sendMessage(msg);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public void onLogged(boolean isLogOn){
        isLoggindOn = isLogOn;
        pinmaker = isLogOn;

        if(isLoggindOn) {
            double Lat = getArguments().getDouble("gpsLat");
            double Lng = getArguments().getDouble("gpsLng");

            startedLocation = new LatLng(Lat, Lng);
        }
    }

    // TODO: Logging path screenshot at end of logging
    public void onScreenShot(){
    }

    // Handler
    Handler updateHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mGoogleMap != null) {
                GPS_Lat.setText(String.format("%.5f", getArguments().getDouble("gpsLat")));
                GPS_Lng.setText(String.format("%.5f", getArguments().getDouble("gpsLng")));
                GPS_Had.setText(String.format("%.1f", getArguments().getFloat("gpsHad")));
                GPS_Spd.setText(String.format("%.0f", getArguments().getDouble("gpsSpd")));

                double Lat = getArguments().getDouble("gpsLat");
                double Lng = getArguments().getDouble("gpsLng");
                long nowTime = getArguments().getLong("timeNow");

                LatLng nowLocation = new LatLng(Lat, Lng);

                if(isLoggindOn){
                    if(pinmaker){
                        previousLocation = new LatLng(Lat,Lng);

                        loggedStart = new MarkerOptions();
                        loggedStart.position(startedLocation);
                        loggedStart.title("LoggedStarted");

                        mGoogleMap.addMarker(loggedStart);

                        pinMaked = true;
                        pinmaker = false;
                    }

                    polylineOptions = new PolylineOptions().add(previousLocation).add(nowLocation).width(10).color(Color.GREEN).geodesic(true);
                    mGoogleMap.addPolyline(polylineOptions);
                    previousLocation = nowLocation;
                } else {
                    if(pinMaked){
                        mGoogleMap.clear();
                        pinMaked = false;
                    }
                }

                if((nowTime/1000)%60%15 == 0) {
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(nowLocation));
                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(viewMapLevel));

                }
            }
        }
    };
}
