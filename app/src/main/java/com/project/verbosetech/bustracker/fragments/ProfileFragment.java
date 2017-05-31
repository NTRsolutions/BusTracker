package com.project.verbosetech.bustracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.verbosetech.bustracker.R;
import com.project.verbosetech.bustracker.models.Profile;
import com.project.verbosetech.bustracker.others.ProfileRecycleGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this pc on 27-05-17.
 */

public class ProfileFragment extends Fragment {

    private View view;
    ImageView profile_image;

    private RecyclerView.LayoutManager layoutManager;
    ProfileRecycleGrid adapter;
    RecyclerView recyclerView;
    List<Profile> profileList;
    ImageView add;
    AlertDialog alertDialog;

    private static final String urlProfileImg = "https://c.tadst.com/gfx/750w/fatherson.jpg?1";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.profile_layout,container,false);
        profile_image=(ImageView)view.findViewById(R.id.img_profile);
        add=(ImageView)view.findViewById(R.id.add);

        recyclerView=(RecyclerView)view.findViewById(R.id.profile_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.profile_new_dialog_layout, null);
                promptsView.setPadding(0,0,0,0);
                ImageView edit=(ImageView)promptsView.findViewById(R.id.edit);
                EditText name=(EditText)promptsView.findViewById(R.id.name);
                EditText relation=(EditText)promptsView.findViewById(R.id.relation);
                EditText contact=(EditText)promptsView.findViewById(R.id.contact_number);
                EditText mail=(EditText)promptsView.findViewById(R.id.email_add);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        alertDialog.dismiss();
                    }
                });

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
                alertDialogBuilder.setView(promptsView);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        getProfileCards();
        return view;
    }

    public void getProfileCards(){

        profileList=new ArrayList<>();
        profileList.add(new Profile("Rajesh Gupta",urlProfileImg,"Father","+91 903 335 6708","rajeshgupta@gmail.com"));
        profileList.add(new Profile("Rajesh Gupta",urlProfileImg,"Father","+91 903 335 6708","rajeshgupta@gmail.com"));
        adapter=new ProfileRecycleGrid(profileList,getActivity());
        recyclerView.setAdapter(adapter);
    }
}
