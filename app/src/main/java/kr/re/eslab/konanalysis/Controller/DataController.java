package kr.re.eslab.konanalysis.Controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.google.android.gms.maps.GoogleMap;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import kr.re.eslab.konanalysis.Model.Services.GpsServices.GpsService;

public class DataController{
    private static final String TAG = DataController.class.getSimpleName();

    private Timer timer;

    // Gps location
    public GpsService mGpsService;
    private GoogleMap mGoogleMap;

    // IMU data
    private SensorManager sensorManager;
    private Sensor gSensor; // gravity
    private Sensor aSensor; // accelerometer
    private Sensor vSensor; // vector

    // Time data
    private long nowTime;
    private long runTime;
    private SimpleDateFormat dbNameStartTime = new SimpleDateFormat("yyyyMMddHHmmss");

    // Init data
    public DataController(Context context) {
        runTime = 0;
        nowTime = System.currentTimeMillis();
        mGpsService = new GpsService(context);
        timer = new Timer();

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        gSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        aSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        vSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    // IMU

    // GPS
    public double
    getGpsLocation_Lat() {
        return mGpsService.getGpsLocation_Lat();
    }

    public double getGpsLocation_Lng() {
        return mGpsService.getGpsLocation_Lng();
    }

    public double getGpsLocation_Alt() {
        return mGpsService.getGpsLocation_Alt();
    }

    public double getGpsLocation_Spd() {
        return mGpsService.getGpsSpd();
    }

    public float getGpsLocation_Heading(){ return mGpsService.getGpsHeading(); }

    public void stopTime(){
        timer.cancel();
    }

    public void startLogged(){
        runTime = System.currentTimeMillis();
    }

    public long getNowTime(){return nowTime;}

    public long getLogTime(){return (nowTime - runTime);}

    public String getdbNameStartTime(){return dbNameStartTime.format(nowTime);}

    public void setmGoogleMap(GoogleMap googleMap){mGoogleMap = googleMap;}

    public GoogleMap getmGoogleMap(){return mGoogleMap;}

    // Timer
    public void startTime() {
        TimerTask updateTime = new TimerTask() {
            @Override
            public void run() {
                nowTime = System.currentTimeMillis();
            }
        };
        timer.schedule(updateTime, 0, 50);
    }
}
