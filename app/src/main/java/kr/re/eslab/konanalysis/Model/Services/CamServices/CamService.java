package kr.re.eslab.konanalysis.Model.Services.CamServices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class CamService extends Service {
    private final static String TAG = CamService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
