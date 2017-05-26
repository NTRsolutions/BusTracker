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
import android.widget.ImageView;
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
    LayoutInflater li;
    View promptsView;
    AlertDialog.Builder alertDialogBuilder;

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

                li = LayoutInflater.from(getActivity());
                promptsView = li.inflate(R.layout.pickup_dialog, null);
                Button skip = (Button) promptsView.findViewById(R.id.skip);
                final TextView distance=(TextView)promptsView.findViewById(R.id.distance);
                ImageView add=(ImageView)promptsView.findViewById(R.id.add);
                ImageView sub=(ImageView)promptsView.findViewById(R.id.sub);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double cal_dist,d;
                        String a[]=distance.getText().toString().split(" ");
                        d=Double.parseDouble(a[0]);
                        cal_dist=d+1;
                        distance.setText(cal_dist+" km");

                    }
                });

                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double cal_dist=0.0,d;
                        String a[]=distance.getText().toString().split(" ");
                        d=Double.parseDouble(a[0]);
                        if(d>=1.0)
                            cal_dist=d-1;
                        distance.setText(cal_dist+" km");

                    }
                });
                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });

                alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                alertDialogBuilder.setView(promptsView);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                break;

            case R.id.drop_change:

                li = LayoutInflater.from(getActivity());
                promptsView = li.inflate(R.layout.dropdialog_layout, null);
                Button skipd = (Button) promptsView.findViewById(R.id.skip);
                final TextView distance1=(TextView)promptsView.findViewById(R.id.distance);
                ImageView add1=(ImageView)promptsView.findViewById(R.id.add);
                ImageView sub1=(ImageView)promptsView.findViewById(R.id.sub);
                add1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double cal_dist,d;
                        String a[]=distance1.getText().toString().split(" ");
                        d=Double.parseDouble(a[0]);
                        cal_dist=d+1;
                        distance1.setText(cal_dist+" km");

                    }
                });

                sub1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double cal_dist=0.0,d;
                        String a[]=distance1.getText().toString().split(" ");
                        d=Double.parseDouble(a[0]);
                        if(d>=1.0)
                            cal_dist=d-1;
                        distance1.setText(cal_dist+" km");

                    }
                });
                skipd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });

                alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                alertDialogBuilder.setView(promptsView);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

        }
    }
}
