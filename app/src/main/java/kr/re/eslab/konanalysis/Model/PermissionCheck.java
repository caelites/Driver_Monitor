package kr.re.eslab.konanalysis.Model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionCheck{

    private Context context;
    private Activity activity;

    private final int PERMISSION_ALL = 1001;

    private String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE
    };

    private List permissionList;

    public PermissionCheck(Activity _activity_, Context _context_){
        this.activity = _activity_;
        this.context = _context_;
    }

    public boolean checkPermission(){
        int result;
        permissionList = new ArrayList<>();

        for(String pm : PERMISSIONS){
            result = ContextCompat.checkSelfPermission(context, pm);
            if(result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm);
            }
        }

        if(!permissionList.isEmpty()){
            return false;
        }
        return true;
    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(new String[permissionList.size()]), PERMISSION_ALL);
    }

    public boolean permissionResult(int requestCode, @NonNull String[] PERMISSIONS, @NonNull int[] grantResults){
        if(requestCode == PERMISSION_ALL && (grantResults.length > 0)){
            for(int i=0; i < grantResults.length ; i++){
                if(grantResults[i] == -1){
                    return false;
                }
            }
        }
        return true;
    }
}
