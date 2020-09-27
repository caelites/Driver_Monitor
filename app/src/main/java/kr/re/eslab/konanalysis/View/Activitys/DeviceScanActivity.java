package kr.re.eslab.konanalysis.View.Activitys;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konanalysis.R;

import java.util.ArrayList;
import java.util.List;

import kr.re.eslab.konanalysis.Model.Services.BleServices.BluetoothLeService;

public class DeviceScanActivity extends Activity {
    private final static String TAG = DeviceScanActivity.class.getSimpleName();

    private BleDeviceListAdapter mBleDeviceListAdapter;
    private BluetoothLeService mBluetoothLeService;
    private BluetoothAdapter mBluetoothAdapter;
    private List<BluetoothDevice> mBleDevices;

    private static final long SCAN_PREIOD = 10000;
    private static final int REQ_ENABLE_BT = 2;

    private boolean mScanning;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_ble_scan);

        setResult(Activity.RESULT_CANCELED);
        mHandler = new Handler();

        // If BLE not support to user smartphone, Alerted message
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if(mBluetoothAdapter == null){
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }

        popUpList();
        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScanning==false) scanLeDevice(true);
                else finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_ENABLE_BT && requestCode == Activity.RESULT_CANCELED){
            finish();
            return;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BluetoothDevice device = mBleDeviceListAdapter.mLeDevices.get(position);
            mBluetoothAdapter.stopLeScan(mLeScanCallback);

            Bundle b = new Bundle();
            b.putString(BluetoothDevice.EXTRA_DEVICE, mBleDeviceListAdapter.mLeDevices.get(position).getAddress());

            Intent result = new Intent();
            result.putExtras(b);
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    };


    private void popUpList(){
        Log.d(TAG,"popUpList");
        mBleDevices = new ArrayList<BluetoothDevice>();
        mBleDeviceListAdapter = new BleDeviceListAdapter(this,mBleDevices);
        scanLeDevice(true);

        ListView newDevicesListView = findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(mBleDeviceListAdapter);
        newDevicesListView.setOnItemClickListener(mDeviceClickListener);

        scanLeDevice(true);
    }

    private void scanLeDevice(final boolean enable){
        final Button cancelButton = findViewById(R.id.btn_cancel);
        if(enable){
            mHandler.postDelayed(()->{
                mScanning = false;
                mBluetoothAdapter.stopLeScan(mLeScanCallback);

                cancelButton.setText(R.string.scan_scan);

            }, SCAN_PREIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
            cancelButton.setText(R.string.scan_cancel);
        } else {
            mScanning = false;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
            cancelButton.setText(R.string.scan_cancel);
        }
    }

    private class BleDeviceListAdapter extends BaseAdapter{
        private Context context;
        private List<BluetoothDevice> mLeDevices;
        private LayoutInflater mInflator;

        public BleDeviceListAdapter(Context context, List<BluetoothDevice> devices) {
            this.context = context;
            mInflator = LayoutInflater.from(context);
            this.mLeDevices = devices;
        }

        public void addDevice(BluetoothDevice device) {
            if(!mLeDevices.contains(device)) {
                if (device.getName() != null && device.getName().length() > 0)
                    mLeDevices.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mInflator.inflate(R.layout.ble_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.deviceImage = (ImageView) view.findViewById(R.id.ble_attribute_image);
                viewHolder.deviceName = (TextView) view.findViewById(R.id.ble_item_name);
                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.ble_item_address);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            BluetoothDevice device = mLeDevices.get(i);
            final String deviceName = device.getName();
            final String deviceAddress = device.getAddress();
            if (deviceName != null && deviceName.length() > 0){
                viewHolder.deviceName.setText(deviceName);
                viewHolder.deviceAddress.setText(deviceAddress);
            } else {
                viewHolder.deviceName.setText(R.string.scan_unkown);
                viewHolder.deviceAddress.setText(R.string.scan_unkown);
            }
            return view;
        }
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBleDeviceListAdapter.addDevice(device);
                    mBleDeviceListAdapter.notifyDataSetChanged();
                }
            });
        }
    };

    static class ViewHolder {
        ImageView deviceImage;
        TextView deviceName;
        TextView deviceAddress;
    }
}
