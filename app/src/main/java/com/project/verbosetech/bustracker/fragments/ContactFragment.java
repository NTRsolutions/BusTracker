package com.project.verbosetech.bustracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 27-05-17.
 */

public class ContactFragment extends Fragment {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.contactus,container,false);
        return view;
    }
}
