package kr.re.eslab.konanalysis.Controller;

public class DBCollectedData {
    private long timeStamp;

    private int uds_steerAngle;
    private char uds_turnsignal;
    private int uds_brakePressure;
    private long uds_odometer;
    private char uds_seatbelt;
    private char uds_gear;
    private int uds_speed;

    private int std_obd_data[];

    private int uds_fl_speed;
    private int uds_fr_speed;
    private int uds_rl_speed;
    private int uds_rr_speed;

    private float uds_accel;
    private float uds_throtle;
    private float uds_longitudinal;
    private float uds_yaw;
    private float uds_lateral;

    private double gpsLatitude;
    private double gpsLongitude;
    private double gpsAltitude;
    private double gpsSpeed;
    private float gpsHeading;
    private double imuGyroX;
    private double imuGyroY;
    private double imuGyroZ;

    private int resultBehavior;

    private int confidence_1;
    private int confidence_2;
    private int confidence_3;
    private int confidence_4;
    private int confidence_5;
    private int confidence_6;
    private int confidence_7;
    private int confidence_8;

    public DBCollectedData(){
        this.timeStamp = 0;

        this.uds_steerAngle = 0;
        this.uds_turnsignal = '-';
        this.uds_brakePressure = 0;
        this.uds_odometer = 0;
        this.uds_seatbelt = 'X';
        this.uds_gear = '-';

        this.std_obd_data = new int[32];

        this.uds_speed = 0;
        this.uds_fl_speed = 0;
        this.uds_fr_speed = 0;
        this.uds_rl_speed = 0;
        this.uds_rr_speed = 0;

        this.uds_accel = 0;
        this.uds_throtle = 0;
        this.uds_longitudinal = 0;
        this.uds_yaw = 0;
        this.uds_lateral = 0;

        this.gpsLatitude = 0;
        this.gpsLongitude = 0;
        this.gpsAltitude = 0;
        this.gpsSpeed = 0;
        this.gpsHeading = 0;
        this.imuGyroX = 0;
        this.imuGyroY = 0;
        this.imuGyroZ = 0;

        this.resultBehavior = 0;

        this.confidence_1 = 0;
        this.confidence_2 = 0;
        this.confidence_3 = 0;
        this.confidence_4 = 0;
        this.confidence_5 = 0;
        this.confidence_6 = 0;
        this.confidence_7 = 0;
        this.confidence_8 = 0;
    }

    // Set data
    public void setInsertTimeId(long nowTime){
        this.timeStamp = nowTime;
    }

    public void setInsertObdDefaultData(int sta, int bpp, char trs, long odo, char sbt, char egs, int spd){
        this.uds_steerAngle = sta;
        this.uds_brakePressure = bpp;
        this.uds_turnsignal = trs;
        this.uds_odometer = odo;
        this.uds_seatbelt = sbt;
        this.uds_gear = egs;
        this.uds_speed = spd;
    }

    public void setStd_obd_data(int[] obd_data){
        try {
            for (int idx = 0; idx < obd_data.length; idx++)
                this.std_obd_data[idx] = obd_data[idx];
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setInsertObdWheelSpdData(int fl, int fr, int rl, int rr){
        this.uds_fl_speed = fl;
        this.uds_fr_speed = fr;
        this.uds_rl_speed = rl;
        this.uds_rr_speed = rr;
    }

    public void setInsertObdExpandData(float thr, float acc, float lng, float yaw, float lat){
        this.uds_throtle = thr;
        this.uds_accel = acc;
        this.uds_longitudinal = lng;
        this.uds_yaw = yaw;
        this.uds_lateral = lat;
    }

    public void setInsertGpsData(double lat, double lng, double alt, double spd, float head){
        this.gpsLatitude = lat;
        this.gpsLongitude = lng;
        this.gpsAltitude = alt;
        this.gpsSpeed = spd;
        this.gpsHeading = head;
    }

    public void setInsertImuData(double x, double y, double z){
        this.imuGyroX = x;
        this.imuGyroY = y;
        this.imuGyroZ = z;
    }

    public void setResultBehavior(int info){
        this.resultBehavior = info;
    }

    public void setConfidence(int con1, int con2, int con3, int con4, int con5, int con6, int con7, int con8){
        this.confidence_1 = con1;
        this.confidence_2 = con2;
        this.confidence_3 = con3;
        this.confidence_4 = con4;
        this.confidence_5 = con5;
        this.confidence_6 = con6;
        this.confidence_7 = con7;
        this.confidence_8 = con8;
    }

    // Get data
    public long getTimeStamp(){return timeStamp;}

    public int getUds_steerAngle(){return uds_steerAngle;}
    public int getUds_brakePressure(){return uds_brakePressure;}
    public char getUds_turnsignal(){return uds_turnsignal;}
    public long getUds_odometer(){return uds_odometer;}
    public char getUds_seatbelt(){return uds_seatbelt;}
    public char getUds_gear(){return uds_gear;}

    public int getUds_speed(){return uds_speed;}

    public int[] getStd_obd_data(){return std_obd_data;}

    public int getUds_fl_speed(){return uds_fl_speed;}
    public int getUds_fr_speed(){return uds_fr_speed;}
    public int getUds_rl_speed(){return uds_rl_speed;}
    public int getUds_rr_speed(){return uds_rr_speed;}

    public float getUds_throtle(){return uds_throtle;}
    public float getUds_accel(){return uds_accel;}
    public float getUds_longitudinal(){return uds_longitudinal;}
    public float getUds_yaw(){return uds_yaw;}
    public float getUds_lateral(){return uds_lateral;}

    public double getGpsLatitude(){return gpsLatitude;}
    public double getGpsLongitude(){return gpsLongitude;}
    public double getGpsAltitude(){return gpsAltitude;}
    public double getGpsSpeed(){return gpsSpeed;}
    public float getGpsHeading(){return gpsHeading;}

    public double getImuGyroX(){return imuGyroX;}
    public double getImuGyroY(){return imuGyroY;}
    public double getImuGyroZ(){return imuGyroZ;}

    public int getResultBehavior(){return resultBehavior;}

    public int getConfidence_1(){return confidence_1;}
    public int getConfidence_2(){return confidence_2;}
    public int getConfidence_3(){return confidence_3;}
    public int getConfidence_4(){return confidence_4;}
    public int getConfidence_5(){return confidence_5;}
    public int getConfidence_6(){return confidence_6;}
    public int getConfidence_7(){return confidence_7;}
    public int getConfidence_8(){return confidence_8;}
}