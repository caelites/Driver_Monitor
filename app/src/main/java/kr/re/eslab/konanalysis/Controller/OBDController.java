package kr.re.eslab.konanalysis.Controller;

import android.util.Log;

import java.io.UnsupportedEncodingException;

public class OBDController {
    private static final String TAG = OBDController.class.getSimpleName();

    private String receiveMessage;

    private String leHexData = "";

    private int steerAngle = 0;
    private char turnSignal = '-';
    private int brakePressure = 0;
    private long odometer = 0;
    private int speed;
    private char gearposition = '-';
    private char seatbelt = '-';

    private int fl_speed;
    private int fr_speed;
    private int rl_speed;
    private int rr_speed;

    private float throtle;
    private float accel;
    private float longitudinal;
    private float yaw;
    private float lateral;

    private int std_obd_data[];

    private int resultBehavior;

    private int confidence_1;
    private int confidence_2;
    private int confidence_3;
    private int confidence_4;
    private int confidence_5;
    private int confidence_6;
    private int confidence_7;
    private int confidence_8;

    private boolean ResObd = false;

    public boolean reqObdData(byte[] txValue){
        try {
            std_obd_data = new int[32];

            receiveMessage = new String(txValue, "ISO-8859-1");
            String receivebyte = "";

            for (int value : receiveMessage.toCharArray())
                receivebyte += String.format("%02X", value);

            leHexData = receivebyte;
            Log.d("BLE RX",receivebyte);

            switch (receivebyte.charAt(1)) {
                case '4':
                    steerAngle = Integer.parseInt(receivebyte.substring(8, 10) + receivebyte.substring(6, 8),16);
                    if(steerAngle >= 32768) steerAngle -= 65535;

                    switch (receivebyte.substring(10, 12)) {
                        case "02":
                            turnSignal = 'R';
                            break;
                        case "01":
                            turnSignal = 'L';
                            break;
                        default:
                            turnSignal = '-';
                            break;
                    }

                    brakePressure = (int) Long.parseLong(
                            receivebyte.substring(14,16) +
                            receivebyte.substring(12,14),16);
                    odometer = (int) Long.parseLong(
                            receivebyte.substring(30,32) +
                            receivebyte.substring(28,30) +
                            receivebyte.substring(26,28) +
                            receivebyte.substring(24,26) +
                            receivebyte.substring(22,24) +
                            receivebyte.substring(20,22) +
                            receivebyte.substring(18,20) +
                            receivebyte.substring(16,18),16);

                    switch (receivebyte.substring(32, 34)) {
                        case "01":
                            seatbelt = 'O';
                            break;
                        default:
                            seatbelt = 'X';
                            break;
                    }

                    switch (receivebyte.substring(34, 36)) {
                        case "00":
                            gearposition = 'P';
                            break;
                        case "01":
                            gearposition = 'R';
                            break;
                        case "02":
                            gearposition = 'N';
                            break;
                        case "03":
                            gearposition = 'D';
                            break;
                        default:
                            gearposition = '-';
                            break;
                    }
;
                    for(int i=36, j=0;i<receivebyte.length();i+=4, j++){
                        std_obd_data[j] = (int) Long.parseLong(receivebyte.substring(i+2,i+4) + receivebyte.substring(i,i+2),16);
//                        Log.d(TAG, "OBD-II " + j + " DATA: " + std_obd_data[j]);
                    }
//                    speed = (int) Long.parseLong(receivebyte.substring(16,18),16);
//                    fl_speed = (int) Long.parseLong(receivebyte.substring(18,20),16);
//                    fr_speed = (int) Long.parseLong(receivebyte.substring(20,22),16);
//                    rl_speed = (int) Long.parseLong(receivebyte.substring(22,24),16);
//                    rr_speed = (int) Long.parseLong(receivebyte.substring(24,26),16);
//
//                    throtle = reverseFloat(receivebyte.substring(26,34));
//                    accel = reverseFloat(receivebyte.substring(34, 42));
//                    longitudinal = reverseFloat(receivebyte.substring(42,50));
//                    yaw = reverseFloat(receivebyte.substring(50,58));
//                    lateral = reverseFloat(receivebyte.substring(58,66));

//                    resultBehavior = (int) Long.parseLong(receivebyte.substring(66,68),16);
//
//                    confidence_1 = (int) reverseInt(receivebyte.substring(74,82));
//                    confidence_2 = (int) reverseInt(receivebyte.substring(82,90));
//                    confidence_3 = (int) reverseInt(receivebyte.substring(90,98));
//                    confidence_4 = (int) reverseInt(receivebyte.substring(98,106));
//                    confidence_5 = (int) reverseInt(receivebyte.substring(106,114));
//                    confidence_6 = (int) reverseInt(receivebyte.substring(114,122));
//                    confidence_7 = (int) reverseInt(receivebyte.substring(122,130));
//                    confidence_8 = (int) reverseInt(receivebyte.substring(130,138));

                    Log.d(TAG, "HEX Data: " + receivebyte + "Length: " + receivebyte.length());
                    Log.d(TAG, "Steer angle: " + steerAngle +
                            " Turn signal: " + turnSignal +
                            " Brake Pressure : " + brakePressure +
                            " Odometer : " + odometer +
                            " Seatbelt : " + seatbelt +
                            " Gearposition : " + gearposition +
                            " OBD-II length: " + (receivebyte.length() - 36)
//                            " Speed : " + speed +
//                            " FL wheel spped : " + fl_speed +
//                            " FR wheel spped : " + fr_speed +
//                            " RL wheel spped : " + rl_speed +
//                            " RR wheel spped : " + rr_speed +
//                            " Accel : " + accel +
//                            " Throtle : " + throtle +
//                            " Laterall : " + longitudinal +
//                            " Yaw : " + yaw +
//                            " Laterall2 : " + lateral +
//                            " Result : " + resultBehavior +
//                            " Con1 : " + confidence_1 +
//                            " Con2 : " + confidence_2 +
//                            " Con3 : " + confidence_3 +
//                            " Con4 : " + confidence_4 +
//                            " Con5 : " + confidence_5 +
//                            " Con6 : " + confidence_6 +
//                            " Con7 : " + confidence_7 +
//                            " Con8 : " + confidence_8
                    );
                    break;

                default:
                    break;
            }
            ResObd = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ResObd = false;
        }
        return ResObd;
    }

    public byte[] getQuery(String SendString){

        int strdata1 = 0;
        int strdara2 = 0;

        byte[] bytedata = new byte[SendString.length()];

        for (int i = 0; i < SendString.length(); i+=2) {
            strdata1 = (int) SendString.charAt(i);
            strdara2 = (int) SendString.charAt(i+1);

            bytedata[i/2] = (byte)(makebyte(strdata1)*16 + makebyte(strdara2));
        }

        return bytedata;
    }

    public int makebyte(int strdata){
        int a = strdata;

        if(strdata >= 65 && strdata <= 90){
            a-=55;
        }
        else if(strdata >= 97 && strdata <= 122){
            a-=87;
        }
        else if(strdata >= 48 && strdata <= 57){
            a-=48;
        }
        return a;
    }

    private float reverseFloat(String heXvalue){
        String reverseValue = "";
        reverseValue += heXvalue.substring(6, 8);
        reverseValue += heXvalue.substring(4, 6);
        reverseValue += heXvalue.substring(2, 4);
        reverseValue += heXvalue.substring(0, 2);
        Long temp = Long.parseLong(reverseValue,16);
        float result = Float.intBitsToFloat(temp.intValue());
        return result;
    }

    private int reverseInt(String heXvalue){
        String reverseValue = "";
        reverseValue += heXvalue.substring(6, 8);
        reverseValue += heXvalue.substring(4, 6);
        reverseValue += heXvalue.substring(2, 4);
        reverseValue += heXvalue.substring(0, 2);
        int result = (int)Long.parseLong(reverseValue,16);
        return result;
    }

    // get data
    public String getLeHexData(){return leHexData;}
    public int getSteerAngle(){return steerAngle;}
    public char getTurnSignal(){return turnSignal;}
    public int getBrakePressure(){return brakePressure;}
    public long getOdometer(){return odometer;}
    public char getGearposition(){return gearposition;}
    public char getSeatbelt(){return seatbelt;}
    public int getSpeed(){return speed;}

    public int getFl_speed(){return fl_speed;}
    public int getFr_speed(){return fr_speed;}
    public int getRl_speed(){return rl_speed;}
    public int getRr_speed(){return rr_speed;}

    public float getThrotle(){return throtle;}
    public float getAccel(){return accel;}
    public float getLongitudinal(){return longitudinal;}
    public float getYaw(){return yaw;}
    public float getLateral(){return lateral;}

    public int[] getStd_obd_data(){return std_obd_data;}

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
