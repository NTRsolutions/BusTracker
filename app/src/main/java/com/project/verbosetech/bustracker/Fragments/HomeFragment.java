package com.project.verbosetech.bustracker.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.verbosetech.bustracker.Models.Student;
import com.project.verbosetech.bustracker.Others.HomeRecycleGrid;
import com.project.verbosetech.bustracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this pc on 11-05-17.
 */

public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView.LayoutManager layoutManager;
    HomeRecycleGrid adapter;
    RecyclerView recyclerView;
    List<Student> studentList;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.card_recycler_view,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.card_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getInformation();
        return view;

    }

    public void getInformation()
    {
        studentList=new ArrayList<>();
        studentList.add(new Student("Sachin Parekh ","Class 10th B Division","At School"));
        studentList.add(new Student("Sachin Parekh ","Class 10th B Division","At School"));
        adapter = new HomeRecycleGrid(getActivity(), studentList, new HomeRecycleGrid.VenueAdapterClickCallbacks() {

            @Override
            public void onCardClick(String p) {

                //Task when card will be clicked

            }
        });

        recyclerView.setAdapter(adapter);
    }
}
