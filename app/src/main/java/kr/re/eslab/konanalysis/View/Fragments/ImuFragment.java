package kr.re.eslab.konanalysis.View.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.konanalysis.R;

import kr.re.eslab.konanalysis.View.ViewModel.AppViewModel;

public class ImuFragment extends Fragment {
    private static final String TAG = ImuFragment.class.getSimpleName();

    private AppViewModel mAppViewModel;

    public static ImuFragment newInstance() {
        return new ImuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imu_page, container, false);

        Log.d(TAG,"onCreateView");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAppViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        // TODO: Use the DataController
    }

}
