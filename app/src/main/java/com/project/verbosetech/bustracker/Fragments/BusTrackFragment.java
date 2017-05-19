package com.project.verbosetech.bustracker.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 19-05-17.
 */

public class BusTrackFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.bus_track_layout,container,false);
        return view;
    }
}
