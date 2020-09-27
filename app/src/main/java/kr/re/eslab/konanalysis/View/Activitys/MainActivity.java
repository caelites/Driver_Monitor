/**
 * @author : Chanwoo.Kim
 * @version : 1.0
 * @date : 2019.09.05
 * @mail : chanwoo.kim@eslab.re.kr
 * Description
 * This app can control car data logging for KONA, which manufacture from Hyundai.
 */
package kr.re.eslab.konanalysis.View.Activitys;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.konanalysis.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import kr.re.eslab.konanalysis.Controller.DBCollectedData;
import kr.re.eslab.konanalysis.Controller.DataController;
import kr.re.eslab.konanalysis.Controller.OBDController;
import kr.re.eslab.konanalysis.Model.DBHelper;
import kr.re.eslab.konanalysis.Model.PermissionCheck;
import kr.re.eslab.konanalysis.Model.Services.BleServices.BleState;
import kr.re.eslab.konanalysis.Model.Services.BleServices.BluetoothLeService;
import kr.re.eslab.konanalysis.View.Fragments.DataFragment;
import kr.re.eslab.konanalysis.View.Fragments.DriverFragment;
import kr.re.eslab.konanalysis.View.Fragments.FragListener;
import kr.re.eslab.konanalysis.View.Fragments.GpsFragment;
import kr.re.eslab.konanalysis.View.Fragments.ImuFragment;
import kr.re.eslab.konanalysis.View.Fragments.MainFragment;

import static kr.re.eslab.konanalysis.View.Activitys.RequestStates.REQ_ENABLE_BT;
import static kr.re.eslab.konanalysis.View.Activitys.RequestStates.REQ_GPS_REFRESH;
import static kr.re.eslab.konanalysis.View.Activitys.RequestStates.REQ_SELECT_BT;

public class MainActivity extends AppCompatActivity implements FragListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String PERMISSION_DIALOG = "dialog";

    private MenuItem btMenu;

    // BLE connecter
    public static BluetoothLeService mBluetoothLeService = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothDevice mBluetoothDevice = null;
    private String deviceAddress  = null;
    private BleState mState = BleState.STATE_DISCONNECTED;
    private byte[] txValue;

    // View fragment
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private MainFragment mainPage = new MainFragment();
    private DriverFragment driverPage = new DriverFragment();
    private DataFragment dataPage = new DataFragment();
    private GpsFragment gpsPage = new GpsFragment();
    private ImuFragment imuPage = new ImuFragment();

    // Data control
    private static DataController mDataControl;
    private static OBDController mOBDData;
    private static DBCollectedData mDBCollectedData;

    // DB saver
    private static DBHelper mDBHelper;
    private boolean isLogOn = false;
    private boolean isDataRx = false;

    // Permissions check
    private PermissionCheck permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(permissionCheck()){
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            mDataControl = new DataController(getApplicationContext());

            mDataControl.startTime();
            mOBDData = new OBDController();
            mDBCollectedData = new DBCollectedData();
            service_init();

            btMenu = findViewById(R.id.menu_scan);

            BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.add(R.id.main_fragment, driverPage);
        mFragmentTransaction.add(R.id.main_fragment, dataPage);
        mFragmentTransaction.add(R.id.main_fragment, gpsPage);
        mFragmentTransaction.add(R.id.main_fragment, imuPage);

        mFragmentTransaction.show(driverPage);
        mFragmentTransaction.hide(dataPage);
        mFragmentTransaction.hide(gpsPage);
        mFragmentTransaction.hide(imuPage);

        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();

        mLongPressTimer = new Timer();
        mLongPressTimer.schedule(updateView, 0, 100);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case R.id.navigation_driver:
                mFragmentTransaction.show(driverPage);
                mFragmentTransaction.hide(dataPage);
                mFragmentTransaction.hide(gpsPage);
                mFragmentTransaction.hide(imuPage);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                return true;
            case R.id.navigation_data:
                mFragmentTransaction.hide(driverPage);
                mFragmentTransaction.show(dataPage);
                mFragmentTransaction.hide(gpsPage);
                mFragmentTransaction.hide(imuPage);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                return true;
            case R.id.navigation_gps:
                mFragmentTransaction.hide(driverPage);
                mFragmentTransaction.hide(dataPage);
                mFragmentTransaction.show(gpsPage);
                mFragmentTransaction.hide(imuPage);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                return true;
            case R.id.navigation_imu:
                mFragmentTransaction.hide(driverPage);
                mFragmentTransaction.hide(dataPage);
                mFragmentTransaction.hide(gpsPage);
                mFragmentTransaction.show(imuPage);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                return true;
        }
        return false;
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                btMenu = item;
                if (!mBluetoothAdapter.isEnabled()) {
                    Log.i(TAG, "");
                    Intent intentBleEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intentBleEnable, REQ_ENABLE_BT);
                } else {
                    if (mBluetoothDevice != null) {
                        mBluetoothLeService.disconnect();
                    }
                    Intent intentBleNew = new Intent(MainActivity.this, DeviceScanActivity.class);
                    startActivityForResult(intentBleNew, REQ_SELECT_BT);
                }
                return true;
            case R.id.menu_notifications:
                return true;
            case R.id.menu_setting:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "Exit APP");
        super.onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        unbindService(mBluetoothLeServiceConnection);
        mDataControl.stopTime();
        mHandler.getLooper().quitSafely();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case REQ_SELECT_BT:
                //When the DeviceScanActivity return, with the selected device address
                if (resultCode == Activity.RESULT_OK && data != null) {
                    deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
                    mBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);

                    Log.d(TAG, "... onActivityResultdevice.address==" + mBluetoothDevice + "mBluetoothLeServiceValue" + mBluetoothLeService);
                    mBluetoothLeService.connect(deviceAddress);
                }
                break;
            case REQ_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Bluetooth has turned on ", Toast.LENGTH_SHORT).show();

                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, "Problem in BT Turning ON ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case REQ_GPS_REFRESH:
                break;
            default:
                Log.e(TAG, "wrong request code");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        mBluetoothLeService.onDestroy();
    }

    private ServiceConnection mBluetoothLeServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder rawBinder) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) rawBinder).getService();
            Log.d(TAG, "onServiceConnected mBluetoothLeService= " + mBluetoothLeService);
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
        }

        public void onServiceDisconnected(ComponentName classname) {
            mBluetoothLeService = null;
        }
    };

    private final BroadcastReceiver btGattReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            runOnUiThread(new Runnable() {
                public void run() {
                    if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                        mState = BleState.STATE_CONNECTED;

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Thread
                                mBluetoothLeService.writeRXCharacteristic(mOBDData.getQuery("2D"));
                                // mBluetoothLeService.writeRXCharacteristic(mOBDData.getQuery("255F48594E5F414343454e5452424431365443495532315F48"));
                                Log.d(TAG, "BLE Query Send");
                                //mBluetoothLeService.writeRXCharacteristic(mOBDData.getQuery("1F03"));
                            }
                        },2000);

                        Log.d(TAG, "BLE GATT connected");
                        btMenu.setIcon(R.drawable.ic_bluetooth_connected_24dp);
                    } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                        mState = BleState.STATE_DISCONNECTED;
                        Log.d(TAG, "BLE GATT disconnected");
                        mBluetoothLeService.close();
                        btMenu.setIcon(R.drawable.ic_bluetooth_disconnected_24dp);
                        mBluetoothLeService.connect(deviceAddress);
                        isDataRx = false;
                    } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                        mBluetoothLeService.enableTXNotification();
                    } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                        try {
                            txValue = intent.getByteArrayExtra(BluetoothLeService.EXTRA_DATA);
                            isDataRx = mOBDData.reqObdData(txValue);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (BluetoothLeService.DEVICE_DOES_NOT_SUPPORT_UART.equals(action)) {
                        showMessage("Device doesn't support UART. Disconnecting");
                        mBluetoothLeService.disconnect();
                    }
                }
            });
        }
    };

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void service_init() {
        Intent bindIntent = new Intent(this, BluetoothLeService.class);
        bindService(bindIntent, mBluetoothLeServiceConnection, Context.BIND_AUTO_CREATE);
        startService(bindIntent);
        LocalBroadcastManager.getInstance(this).registerReceiver(btGattReceiver, makeGattUpdateIntentFilter());
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(BluetoothLeService.DEVICE_DOES_NOT_SUPPORT_UART);
        return intentFilter;
    }

    private boolean permissionCheck(){
        if(Build.VERSION.SDK_INT >= 23){
            permission = new PermissionCheck(this, this);
            if(!permission.checkPermission()){
                permission.requestPermission();
                return false;
            } else{
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] PERMISSIONS, @NonNull int[] grantResults) {
        if(!permission.permissionResult(requestCode, PERMISSIONS, grantResults)) {
            permission.requestPermission();
        } else {
            try{
                if (!mBluetoothAdapter.isEnabled()) {
                    Log.i(TAG, "onResume - BT not enabled yet");
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQ_ENABLE_BT);
                }
                mDataControl.mGpsService.getLocation();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // Timer tasks
    private Timer mLongPressTimer = null;

    private TimerTask updateView = new TimerTask()
    {
        public void run() {
            Bundle driverBundle = new Bundle(4);
            driverBundle.putLong("timeNow",mDataControl.getNowTime());
            driverBundle.putLong("timeLog",mDataControl.getLogTime());
//            driverBundle.putInt("driver",mOBDData.getResultBehavior());
//            driverBundle.putInt("confidence1",mOBDData.getConfidence_1());
//            driverBundle.putInt("confidence2",mOBDData.getConfidence_2());
//            driverBundle.putInt("confidence3",mOBDData.getConfidence_3());
//            driverBundle.putInt("confidence4",mOBDData.getConfidence_4());
//            driverBundle.putInt("confidence5",mOBDData.getConfidence_5());
//            driverBundle.putInt("confidence6",mOBDData.getConfidence_6());
//            driverBundle.putInt("confidence7",mOBDData.getConfidence_7());
//            driverBundle.putInt("confidence8",mOBDData.getConfidence_8());
            driverPage.setArguments(driverBundle);
            mHandler.sendMessage(mHandler.obtainMessage());
            driverPage.updateTime();

            Bundle gpsBundle = new Bundle(4);
            gpsBundle.putLong("timeNow",mDataControl.getNowTime());
            gpsBundle.putDouble("gpsLat", mDataControl.getGpsLocation_Lat());
            gpsBundle.putDouble("gpsLng", mDataControl.getGpsLocation_Lng());
            gpsBundle.putFloat("gpsHad", mDataControl.getGpsLocation_Heading());
            gpsBundle.putDouble("gpsSpd", mDataControl.getGpsLocation_Spd());
            gpsBundle.putFloat("gpsHad", mDataControl.getGpsLocation_Heading());
            gpsPage.setArguments(gpsBundle);
            mHandler.sendMessage(mHandler.obtainMessage());
            gpsPage.updateMap();

            if (mState == BleState.STATE_CONNECTED) {
                dataPage.updateData(mOBDData.getLeHexData(), mOBDData.getSteerAngle(), mOBDData.getBrakePressure(), mOBDData.getTurnSignal(),
                        mOBDData.getOdometer(), mOBDData.getGearposition(), mOBDData.getSeatbelt(),mOBDData.getSpeed(),
                        mOBDData.getFl_speed(), mOBDData.getFr_speed(), mOBDData.getRl_speed(), mOBDData.getRr_speed(),
                        mOBDData.getAccel(), mOBDData.getThrotle(), mOBDData.getLateral() ,mOBDData.getLongitudinal(), mDataControl.getGpsLocation_Heading(), mOBDData.getYaw(),
                        mOBDData.getResultBehavior(),
                        mOBDData.getConfidence_1(), mOBDData.getConfidence_2(), mOBDData.getConfidence_3(), mOBDData.getConfidence_4(),
                        mOBDData.getConfidence_5(), mOBDData.getConfidence_6(), mOBDData.getConfidence_7(), mOBDData.getConfidence_8());
            }

            mDBCollectedData.setInsertTimeId(mDataControl.getLogTime());
            mDBCollectedData.setInsertObdDefaultData(mOBDData.getSteerAngle(), mOBDData.getBrakePressure(), mOBDData.getTurnSignal(), mOBDData.getOdometer(),mOBDData.getSeatbelt(),mOBDData.getGearposition(), mOBDData.getSpeed());
            mDBCollectedData.setStd_obd_data(mOBDData.getStd_obd_data());
            mDBCollectedData.setInsertObdWheelSpdData(mOBDData.getFl_speed(),mOBDData.getFr_speed(),mOBDData.getRl_speed(),mOBDData.getRr_speed());
            mDBCollectedData.setInsertObdExpandData(mOBDData.getThrotle(),mOBDData.getAccel(),mOBDData.getLongitudinal(),mOBDData.getYaw(),mOBDData.getLateral());
            mDBCollectedData.setInsertGpsData(mDataControl.getGpsLocation_Lat(),mDataControl.getGpsLocation_Lng(),mDataControl.getGpsLocation_Alt(),mDataControl.getGpsLocation_Spd(),mDataControl.getGpsLocation_Heading());
            mDBCollectedData.setInsertImuData(0,0,0);
            mDBCollectedData.setResultBehavior(mOBDData.getResultBehavior());
            mDBCollectedData.setConfidence(mOBDData.getConfidence_1(),mOBDData.getConfidence_2(),mOBDData.getConfidence_3(),mOBDData.getConfidence_4(),
                                            mOBDData.getConfidence_5(),mOBDData.getConfidence_6(),mOBDData.getConfidence_7(),mOBDData.getConfidence_8());

            if(mDBHelper != null && isLogOn && isDataRx) {
                try {
                    mDBHelper.insertData(mDBCollectedData);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Logging","Data Logging Failed");
                }
            }
        }
    };

    // Handler
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    /**
     * {@link FragListener}
     * */
    @Override
    public boolean onLoggerStarted() {
        final File dir = this.getExternalFilesDir(null);
//        if(true) {
        if(mState == BleState.STATE_CONNECTED) {
            mDataControl.startLogged();
            mDBHelper = new DBHelper(this, (dir == null ? "" : (dir.getAbsolutePath() + "/Logging.db")), null, 1);
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            mDBHelper.onLogCreate(db, "D" + mDataControl.getdbNameStartTime());
            isLogOn = true;
            gpsPage.onLogged(isLogOn);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setClearLogger(){
        isLogOn = false;
        gpsPage.onLogged(false);
    }

    // TODO: Logging path screenshot at end of logging
    @Override
    public void onLogShotWay() {

    }
}



