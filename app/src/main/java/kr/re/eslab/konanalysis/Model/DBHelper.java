package kr.re.eslab.konanalysis.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import kr.re.eslab.konanalysis.Controller.DBCollectedData;

// DB control
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();

    // Table
    private String TABLE_NAME;
    private String COLUMN_TIMESTMAP = "timestamp";

    private String COLUMN_UDS_STEER = "steerwheel";
    private String COLUMN_UDS_BRAKE = "brakepressure";
    private String COLUMN_UDS_TURN = "turnsignal";
    private String COLUMN_UDS_ODOMETER = "odometer";
    private String COLUMN_UDS_SEATBELT  = "seatbelt";
    private String COLUMN_UDS_GEAR  = "gearposition";

    private String COLUMN_SPEED     = "speed";
    private String COLUMN_FL_SPEED  = "fl_wheel_speed";
    private String COLUMN_FR_SPEED  = "fr_wheel_speed";
    private String COLUMN_RL_SPEED  = "rl_wheel_speed";
    private String COLUMN_RR_SPEED  = "rr_wheel_speed";
    private String COLUMN_THROTLE   = "throtle";
    private String COLUMN_ACCEL     = "accel";
    private String COLUMN_LONGITUDINAL = "longitudinal";
    private String COLUMN_YAW       = "yaw";
    private String COLUMN_LATERAL   = "lateral";

    private String COLUMN__OBD_ENGINE_COOLANT_TEMPERATURE__ = "ENGINE_COOLANT_TEMPERATURE";
    private String COLUMN__OBD_SHORT_TERM_FUEL_TRIM_1__ = "SHORT_TERM_FUEL_TRIM_1";
    private String COLUMN__OBD_LONG_TERM_FUEL_TRIM_1__ = "LONG_TERM_FUEL_TRIM_1";
    private String COLUMN__OBD_SHORT_TERM_FUEL_TRIM_2__ = "SHORT_TERM_FUEL_TRIM_2";
    private String COLUMN__OBD_LONG_TERM_FUEL_TRIM_2__ = "LONG_TERM_FUEL_TRIM_2";
    private String COLUMN__OBD_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE__ = "INTAKE_MANIFOLD_ABSOLUTE_PRESSURE";
    private String COLUMN__OBD_ENGINE_RPM__ = "ENGINE_RPM";
    private String COLUMN__OBD_VEHICLE_SPEED__ = "VEHICLE_SPEED";
    private String COLUMN__OBD_INTAKE_AIR_TEMPERATURE__ = "INTAKE_AIR_TEMPERATURE";
    private String COLUMN__OBD_MAF__ = "MAF";
    private String COLUMN__OBD_THROTTLE_POSITION__ = "THROTTLE_POSITION";
    private String COLUMN__OBD_OXYGEN_SENSOR_1_2_BANK__ = "OXYGEN_SENSOR_1_2_BANK";
    private String COLUMN__OBD_RUN_TIME_SINCE_ENGINE_START__ = "RUN_TIME_SINCE_ENGINE_START";
    private String COLUMN__OBD_FUEL_TANK_LEVEL_INPUT__ = "FUEL_TANK_LEVEL_INPUT";
    private String COLUMN__OBD_CONTROL_MODULE_VOLTAGE__ = "CONTROL_MODULE_VOLTAGE";
    private String COLUMN__OBD_FUEL_TYPE__ = "FUEL_TYPE";
    private String COLUMN__OBD_ENGINE_FURE_RATE__ = "ENGINE_FURE_RATE";

    private String COLUMN_GPS_LATITUDE = "gpslatitude";
    private String COLUMN_GPS_LONGITUDE = "gpslongitude";
    private String COLUMN_GPS_ALTITUDE = "gpsaltitude";
    private String COLUMN_GPS_SPEED = "gpsspeed";
    private String COLUMN_GPS_HEADING = "gpsheading";

    private String COLUMN_IMU_X     = "imugyrox";
    private String COLUMN_IMU_Y     = "imugyroy";
    private String COLUMN_IMU_Z     = "imugyroz";
    private String COLUMN_RESULT    = "result";

    private String CONFIDENCE_1     = "confidence1";
    private String CONFIDENCE_2     = "confidence2";
    private String CONFIDENCE_3     = "confidence3";
    private String CONFIDENCE_4     = "confidence4";
    private String CONFIDENCE_5     = "confidence5";
    private String CONFIDENCE_6     = "confidence6";
    private String CONFIDENCE_7     = "confidence7";
    private String CONFIDENCE_8     = "confidence8";

    private SQLiteDatabase db;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_TIMESTMAP + " INTEGER PRIMARY KEY, "
                + COLUMN_STEER     + " TEXT, "
                + COLUMN_BRAKE     + " TEXT, "
                + COLUMN_TURN      + " TEXT, "
                + COLUMN_LATITUDE  + " TEXT, "
                + COLUMN_LONGITUDE + " TEXT, "
                + COLUMN_ALTITUDE  + " TEXT, "
                + COLUMN_SPEED     + " TEXT, "
                + COLUMN_IMU_X     + " TEXT, "
                + COLUMN_IMU_Y     + " TEXT, "
                + COLUMN_IMU_Z     + " TEXT"
                + ");");
        Log.d(TAG,"Created table name: " + TABLE_NAME);
        */
    }

    public void onLogCreate(SQLiteDatabase db, String name) {
        TABLE_NAME = name;
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_TIMESTMAP + " INTEGER PRIMARY KEY, "
                + COLUMN_UDS_STEER + " TEXT, "
                + COLUMN_UDS_BRAKE + " TEXT, "
                + COLUMN_UDS_TURN + " TEXT, "
                + COLUMN_UDS_ODOMETER + " TEXT, "
                + COLUMN_UDS_SEATBELT + " TEXT, "
                + COLUMN_UDS_GEAR + " TEXT, "
                + COLUMN__OBD_ENGINE_COOLANT_TEMPERATURE__ + " TEXT, "
                + COLUMN__OBD_SHORT_TERM_FUEL_TRIM_1__ + " TEXT, "
                + COLUMN__OBD_LONG_TERM_FUEL_TRIM_1__ + " TEXT, "
                + COLUMN__OBD_SHORT_TERM_FUEL_TRIM_2__ + " TEXT, "
                + COLUMN__OBD_LONG_TERM_FUEL_TRIM_2__ + " TEXT, "
                + COLUMN__OBD_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE__ + " TEXT, "
                + COLUMN__OBD_ENGINE_RPM__ + " TEXT, "
                + COLUMN__OBD_VEHICLE_SPEED__ + " TEXT, "
                + COLUMN__OBD_INTAKE_AIR_TEMPERATURE__ + " TEXT, "
                + COLUMN__OBD_MAF__ + " TEXT, "
                + COLUMN__OBD_THROTTLE_POSITION__ + " TEXT, "
                + COLUMN__OBD_OXYGEN_SENSOR_1_2_BANK__ + " TEXT, "
                + COLUMN__OBD_RUN_TIME_SINCE_ENGINE_START__ + " TEXT, "
                + COLUMN__OBD_FUEL_TANK_LEVEL_INPUT__ + " TEXT, "
                + COLUMN__OBD_CONTROL_MODULE_VOLTAGE__ + " TEXT, "
                + COLUMN__OBD_FUEL_TYPE__ + " TEXT, "
                + COLUMN__OBD_ENGINE_FURE_RATE__ + " TEXT, "
//                + COLUMN_SPEED     + " TEXT, "
//                + COLUMN_FL_SPEED  + " TEXT, "
//                + COLUMN_FR_SPEED  + " TEXT, "
//                + COLUMN_RL_SPEED  + " TEXT, "
//                + COLUMN_RR_SPEED  + " TEXT, "
//                + COLUMN_THROTLE   + " TEXT, "
//                + COLUMN_ACCEL     + " TEXT, "
//                + COLUMN_LONGITUDINAL + " TEXT, "
//                + COLUMN_YAW       + " TEXT, "
//                + COLUMN_LATERAL   + " TEXT, "
                + COLUMN_GPS_LATITUDE + " TEXT, "
                + COLUMN_GPS_LONGITUDE + " TEXT, "
                + COLUMN_GPS_ALTITUDE + " TEXT, "
                + COLUMN_GPS_SPEED + " TEXT, "
                + COLUMN_GPS_HEADING + " TEXT, "
//                + COLUMN_IMU_X     + " TEXT, "
//                + COLUMN_IMU_Y     + " TEXT, "
//                + COLUMN_IMU_Z     + " TEXT, "
                + COLUMN_RESULT    + " TEXT"
//                + CONFIDENCE_1     + " TEXT, "
//                + CONFIDENCE_2     + " TEXT, "
//                + CONFIDENCE_3     + " TEXT, "
//                + CONFIDENCE_4     + " TEXT, "
//                + CONFIDENCE_5     + " TEXT, "
//                + CONFIDENCE_6     + " TEXT, "
//                + CONFIDENCE_7     + " TEXT, "
//                + CONFIDENCE_8     + " TEXT"
                + ");");
        Log.d(TAG,"Created table name: " + TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(DBCollectedData inputData){
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TIMESTMAP, String.valueOf(inputData.getTimeStamp()));
        contentValues.put(COLUMN_UDS_STEER, String.valueOf(inputData.getUds_steerAngle()));
        contentValues.put(COLUMN_UDS_BRAKE, String.valueOf(inputData.getUds_brakePressure()));
        contentValues.put(COLUMN_UDS_TURN, String.valueOf(inputData.getUds_turnsignal()));
        contentValues.put(COLUMN_UDS_ODOMETER, String.valueOf(inputData.getUds_odometer()));
        contentValues.put(COLUMN_UDS_SEATBELT, String.valueOf(inputData.getUds_seatbelt()));
        contentValues.put(COLUMN_UDS_GEAR, String.valueOf(inputData.getUds_gear()));
        contentValues.put(COLUMN__OBD_CONTROL_MODULE_VOLTAGE__, String.valueOf(inputData.getStd_obd_data()[0]));
        contentValues.put(COLUMN__OBD_SHORT_TERM_FUEL_TRIM_1__, String.valueOf(inputData.getStd_obd_data()[1]));
        contentValues.put(COLUMN__OBD_LONG_TERM_FUEL_TRIM_1__, String.valueOf(inputData.getStd_obd_data()[2]));
        contentValues.put(COLUMN__OBD_SHORT_TERM_FUEL_TRIM_2__, String.valueOf(inputData.getStd_obd_data()[3]));
        contentValues.put(COLUMN__OBD_LONG_TERM_FUEL_TRIM_2__, String.valueOf(inputData.getStd_obd_data()[4]));
        contentValues.put(COLUMN__OBD_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE__, String.valueOf(inputData.getStd_obd_data()[5]));
        contentValues.put(COLUMN__OBD_ENGINE_RPM__, String.valueOf(inputData.getStd_obd_data()[6]));
        contentValues.put(COLUMN__OBD_VEHICLE_SPEED__, String.valueOf(inputData.getStd_obd_data()[7]));
        contentValues.put(COLUMN__OBD_INTAKE_AIR_TEMPERATURE__, String.valueOf(inputData.getStd_obd_data()[8]));
        contentValues.put(COLUMN__OBD_MAF__, String.valueOf(inputData.getStd_obd_data()[9]));
        contentValues.put(COLUMN__OBD_THROTTLE_POSITION__, String.valueOf(inputData.getStd_obd_data()[10]));
        contentValues.put(COLUMN__OBD_OXYGEN_SENSOR_1_2_BANK__, String.valueOf(inputData.getStd_obd_data()[11]));
        contentValues.put(COLUMN__OBD_RUN_TIME_SINCE_ENGINE_START__, String.valueOf(inputData.getStd_obd_data()[12]));
        contentValues.put(COLUMN__OBD_FUEL_TANK_LEVEL_INPUT__, String.valueOf(inputData.getStd_obd_data()[13]));
        contentValues.put(COLUMN__OBD_CONTROL_MODULE_VOLTAGE__, String.valueOf(inputData.getStd_obd_data()[14]));
        contentValues.put(COLUMN__OBD_FUEL_TYPE__, String.valueOf(inputData.getStd_obd_data()[15]));
        contentValues.put(COLUMN__OBD_ENGINE_FURE_RATE__, String.valueOf(inputData.getStd_obd_data()[16]));
//        contentValues.put(COLUMN_SPEED, String.valueOf(inputData.getUds_speed()));
//        contentValues.put(COLUMN_FL_SPEED, String.valueOf(inputData.getUds_fl_speed()));
//        contentValues.put(COLUMN_FR_SPEED, String.valueOf(inputData.getUds_fr_speed()));
//        contentValues.put(COLUMN_RL_SPEED, String.valueOf(inputData.getUds_rl_speed()));
//        contentValues.put(COLUMN_RR_SPEED, String.valueOf(inputData.getUds_rr_speed()));
//        contentValues.put(COLUMN_THROTLE, String.valueOf(inputData.getUds_throtle()));
//        contentValues.put(COLUMN_ACCEL, String.valueOf(inputData.getUds_accel()));
//        contentValues.put(COLUMN_LONGITUDINAL, String.valueOf(inputData.getUds_longitudinal()));
//        contentValues.put(COLUMN_YAW, String.valueOf(inputData.getUds_yaw()));
//        contentValues.put(COLUMN_LATERAL, String.valueOf(inputData.getUds_lateral()));
        contentValues.put(COLUMN_GPS_LATITUDE, String.valueOf(inputData.getGpsLatitude()));
        contentValues.put(COLUMN_GPS_LONGITUDE, String.valueOf(inputData.getGpsLongitude()));
        contentValues.put(COLUMN_GPS_ALTITUDE, String.valueOf(inputData.getGpsAltitude()));
        contentValues.put(COLUMN_GPS_SPEED, String.valueOf(inputData.getGpsSpeed()));
        contentValues.put(COLUMN_GPS_HEADING, String.valueOf(inputData.getGpsHeading()));
//        contentValues.put(COLUMN_IMU_X, String.valueOf(inputData.getImuGyroX()));
//        contentValues.put(COLUMN_IMU_Y, String.valueOf(inputData.getImuGyroY()));
//        contentValues.put(COLUMN_IMU_Z, String.valueOf(inputData.getImuGyroZ()));
        contentValues.put(COLUMN_RESULT, String.valueOf(inputData.getResultBehavior()));
//        contentValues.put(CONFIDENCE_1, String.valueOf(inputData.getConfidence_1()));
//        contentValues.put(CONFIDENCE_2, String.valueOf(inputData.getConfidence_2()));
//        contentValues.put(CONFIDENCE_3, String.valueOf(inputData.getConfidence_3()));
//        contentValues.put(CONFIDENCE_4, String.valueOf(inputData.getConfidence_4()));
//        contentValues.put(CONFIDENCE_5, String.valueOf(inputData.getConfidence_5()));
//        contentValues.put(CONFIDENCE_6, String.valueOf(inputData.getConfidence_6()));
//        contentValues.put(CONFIDENCE_7, String.valueOf(inputData.getConfidence_7()));
//        contentValues.put(CONFIDENCE_8, String.valueOf(inputData.getConfidence_8()));
        Log.d(TAG, String.valueOf(contentValues));
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}