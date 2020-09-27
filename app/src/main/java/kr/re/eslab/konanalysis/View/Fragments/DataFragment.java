package kr.re.eslab.konanalysis.View.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.konanalysis.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import kr.re.eslab.konanalysis.View.ViewModel.AppViewModel;

public class DataFragment extends Fragment {
    private static final String TAG = DataFragment.class.getSimpleName();

    private AppViewModel mAppViewModel;
    private boolean takeBleState = false;

    private boolean isBtConnected = false;

    // View
    private TextView data_OBD_LeHexData;
    private TextView data_OBD_Odomeater;
    private TextView data_OBD_SteerWheel;
    private TextView data_OBD_Brake;
    private TextView data_OBD_Blinker;
    private TextView data_OBD_Speed;
    private TextView data_OBD_GearPosition;
    private TextView data_OBD_Seatbelt;
    private TextView data_OBD_FL_speed;
    private TextView data_OBD_FR_speed;
    private TextView data_OBD_RL_speed;
    private TextView data_OBD_RR_speed;
    private TextView data_OBD_accel;
    private TextView data_OBD_throtle;
    private TextView data_OBD_lateral;
    private TextView data_OBD_longitudinal;
    private TextView data_OBD_yaw;
    private TextView data_OBD_result;

    private TextView data_GPS_heading;

    private String leHexData = "";
    private float steerAngle = 0;
    private int brakePressure = 0;
    private char turnSignal = '-';
    private long odometer = 0;
    private char gearposition = '-';
    private char seatbelt = '-';
    private int speed = 0;
    private int fl_speed = 0;
    private int fr_speed = 0;
    private int rl_speed = 0;
    private int rr_speed = 0;
    private float accel = 0;
    private float throtle = 0;
    private float longitudinal = 0;
    private float headingangle = 0;
    private float lateral = 0;
    private float yaw_rate = 0;

    private int resultBehavior = 0;

    private int confidence_1 = 0;
    private int confidence_2 = 0;
    private int confidence_3 = 0;
    private int confidence_4 = 0;
    private int confidence_5 = 0;
    private int confidence_6 = 0;
    private int confidence_7 = 0;
    private int confidence_8 = 0;

    public DataFragment() {}

    public static DriverFragment newInstance() {
        return new DriverFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_page, container, false);

        Log.d(TAG,"onCreateView");

//        data_OBD_LeHexData = (TextView)view.findViewById(R.id.obd_rx_data);
        data_OBD_Odomeater = (TextView)view.findViewById(R.id.obd_odometer);
        data_OBD_SteerWheel = (TextView)view.findViewById(R.id.obd_steer_wheel);
        data_OBD_Brake = (TextView)view.findViewById(R.id.obd_brake);
        data_OBD_Blinker = (TextView)view.findViewById(R.id.obd_blinker);
        data_OBD_GearPosition = (TextView)view.findViewById(R.id.obd_gear_position);
        data_OBD_Seatbelt = (TextView)view.findViewById(R.id.obd_seat_belt);
//        data_OBD_Speed = (TextView)view.findViewById(R.id.obd_speed);
//        data_OBD_FL_speed = (TextView)view.findViewById(R.id.obd_fl_spped);
//        data_OBD_FR_speed = (TextView)view.findViewById(R.id.obd_fr_spped);
//        data_OBD_RL_speed = (TextView)view.findViewById(R.id.obd_rl_spped);
//        data_OBD_RR_speed = (TextView)view.findViewById(R.id.obd_rr_spped);
//        data_OBD_accel = (TextView)view.findViewById(R.id.obd_accel);
//        data_OBD_throtle = (TextView)view.findViewById(R.id.obd_throtle);
//        data_OBD_lateral = (TextView)view.findViewById(R.id.obd_lateral);
//        data_OBD_longitudinal = (TextView)view.findViewById(R.id.obd_longitudinal);
//        data_OBD_yaw = (TextView)view.findViewById(R.id.obd_yaw);
//        data_OBD_result = (TextView)view.findViewById(R.id.obd_result);

//        data_OBD_LeHexData.setText("-----");
        data_OBD_Odomeater.setText("-----");
        data_OBD_SteerWheel.setText("-----");
        data_OBD_Brake.setText("-----");
        data_OBD_Blinker.setText("-----");
//        data_OBD_Speed.setText("-----");
        data_OBD_GearPosition.setText("-----");
        data_OBD_Seatbelt.setText("-----");
//        data_OBD_FL_speed.setText("-----");
//        data_OBD_FR_speed.setText("-----");
//        data_OBD_RL_speed.setText("-----");
//        data_OBD_RR_speed.setText("-----");
//        data_OBD_accel.setText("-----");
//        data_OBD_throtle.setText("-----");
//        data_OBD_lateral.setText("-----");
//        data_OBD_longitudinal.setText("-----");
//        data_OBD_yaw.setText("-----");
//        data_OBD_result.setText("-----");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAppViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        // TODO: Use the DataController
    }

    public void updateData(String ledata,float sta, int bpp, char trs, long odo, char gear, char sbt, int spd,
                           int fl, int fr, int rl, int rr, float acc, float thr, float lat, float lng, float had, float yaw, int result,
                           int con1, int con2, int con3, int con4, int con5, int con6, int con7, int con8){
        leHexData = ledata;
        steerAngle = sta;
        turnSignal = trs;
        brakePressure = bpp;
        odometer = odo;
        gearposition = gear;
        seatbelt = sbt;
        speed = spd;
        fl_speed = fl;
        fr_speed = fr;
        rl_speed = rl;
        rr_speed = rr;
        accel = acc;
        throtle = thr;
        lateral = lat;
        longitudinal = lng;
        headingangle = had;
        yaw_rate = yaw;
        resultBehavior = result;
        confidence_1 = con1;
        confidence_2 = con2;
        confidence_3 = con3;
        confidence_4 = con4;
        confidence_5 = con5;
        confidence_6 = con6;
        confidence_7 = con7;
        confidence_8 = con8;

        Message msg = updateHandle.obtainMessage();
        updateHandle.sendMessage(msg);
    }

    // Handler
    Handler updateHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(isVisible()){
                try{
//                    data_OBD_LeHexData.setText(leHexData);
                    data_OBD_Odomeater.setText(String.valueOf(odometer));
                    data_OBD_SteerWheel.setText(String.valueOf(steerAngle)+ "º");
                    data_OBD_Brake.setText(String.valueOf(brakePressure));
                    data_OBD_Blinker.setText(String.valueOf(turnSignal));
                    data_OBD_GearPosition.setText(String.valueOf(gearposition));
                    data_OBD_Seatbelt.setText(String.valueOf(seatbelt));
//                data_OBD_Speed.setText(String.valueOf(speed));
//                data_OBD_FL_speed.setText(String.valueOf(fl_speed));
//                data_OBD_FR_speed.setText(String.valueOf(fr_speed));
//                data_OBD_RL_speed.setText(String.valueOf(rl_speed));
//                data_OBD_RR_speed.setText(String.valueOf(rr_speed));
//                data_OBD_accel.setText(String.valueOf(accel));
//                data_OBD_throtle.setText(String.valueOf(throtle) + " % ");
//                data_OBD_lateral.setText(String.valueOf(lateral));
//                data_OBD_longitudinal.setText(String.valueOf(longitudinal));
//                data_OBD_yaw.setText(String.valueOf(yaw_rate));
//                data_OBD_result.setText(String.valueOf(resultBehavior));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    };
}
