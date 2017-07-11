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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.verbosetech.bustracker.R;
import com.project.verbosetech.bustracker.models.Profile;
import com.project.verbosetech.bustracker.others.PrefManager;
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
    PrefManager pref;

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
        pref=new PrefManager(getActivity());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //opening a alert dialog to add the profile card
                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.profile_new_dialog_layout, null);
                promptsView.setPadding(0,0,0,0);
                ImageView edit=(ImageView)promptsView.findViewById(R.id.edit);
                final EditText name=(EditText)promptsView.findViewById(R.id.name);
                final EditText relation=(EditText)promptsView.findViewById(R.id.relation);
                final EditText contact=(EditText)promptsView.findViewById(R.id.contact_number);
                final EditText mail=(EditText)promptsView.findViewById(R.id.email_add);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        pref.setPName(name.getText().toString());
                        pref.setRelation(relation.getText().toString());
                        pref.setPContact(contact.getText().toString());
                        pref.setEmail(mail.getText().toString());
                        alertDialog.dismiss();
                        getProfileCards();
                    }
                });

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
                alertDialogBuilder.setView(promptsView);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                alertDialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            }
        });

        getProfileCards();
        return view;
    }

    public void getProfileCards(){

        profileList=new ArrayList<>();

        //filling the profile cards with dummy data
        profileList.add(new Profile("Rajesh Gupta",urlProfileImg,"Father","+91 903 335 6708","rajeshgupta@gmail.com"));
        profileList.add(new Profile("Rajesh Gupta",urlProfileImg,"Father","+91 903 335 6708","rajeshgupta@gmail.com"));
        if(pref.getPName()!=null)
        {
            profileList.add(new Profile(pref.getPName(),urlProfileImg,pref.getRelation(),pref.getPContact(),pref.getEmail()));
        }
        adapter=new ProfileRecycleGrid(profileList,getActivity());
        recyclerView.setAdapter(adapter);
    }
}
