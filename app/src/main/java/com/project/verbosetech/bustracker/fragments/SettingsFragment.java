package com.project.verbosetech.bustracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 11-05-17.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View view;
    TextView select,pickup_change,drop_change;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.settings_layout,container,false);
        select=(TextView)view.findViewById(R.id.select_all);
        checkBox1=(CheckBox)view.findViewById(R.id.pickupbox);
        checkBox2=(CheckBox)view.findViewById(R.id.dropbox);
        checkBox3=(CheckBox)view.findViewById(R.id.reachedbox);
        checkBox4=(CheckBox)view.findViewById(R.id.leftbox);
        pickup_change=(TextView)view.findViewById(R.id.pickup_change);
        drop_change=(TextView)view.findViewById(R.id.drop_change);
        drop_change=(TextView)view.findViewById(R.id.select_all);

        select.setOnClickListener(this);
        pickup_change.setOnClickListener(this);
        drop_change.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.select_all:

                checkBox1.setChecked(true);
                checkBox2.setChecked(true);
                checkBox3.setChecked(true);
                checkBox4.setChecked(true);

                break;

            case R.id.pickup_change:

                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.pickup_dialog, null);
                Button skip = (Button) promptsView.findViewById(R.id.skip);
                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                alertDialogBuilder.setView(promptsView);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                break;

            case R.id.drop_change:

                LayoutInflater li2 = LayoutInflater.from(getActivity());
                View promptView = li2.inflate(R.layout.dropdialog_layout, null);
                Button skipd = (Button) promptView.findViewById(R.id.skip);
                skipd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });

                AlertDialog.Builder alertDialogBuilderd = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                alertDialogBuilderd.setView(promptView);
                alertDialog = alertDialogBuilderd.create();
                alertDialog.show();

        }
    }
}
