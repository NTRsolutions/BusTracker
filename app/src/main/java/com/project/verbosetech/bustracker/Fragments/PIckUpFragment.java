package com.project.verbosetech.bustracker.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.project.verbosetech.bustracker.Others.DelayAutoCompleteTextView;
import com.project.verbosetech.bustracker.Others.GeoAutoCompleteAdapter;
import com.project.verbosetech.bustracker.Others.GeoSearchResult;
import com.project.verbosetech.bustracker.R;

/**
 * Created by this pc on 14-05-17.
 */

public class PIckUpFragment extends Fragment {

    private View view;
    private Integer THRESHOLD = 2;
    private DelayAutoCompleteTextView geo_autocomplete;
//    private ImageView geo_autocomplete_clear;

    public PIckUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.location_fragment_1, container, false);
//        geo_autocomplete_clear = (ImageView) view.findViewById(R.id.geo_autocomplete_clear);

        geo_autocomplete = (DelayAutoCompleteTextView) view.findViewById(R.id.geo_autocomplete);
        geo_autocomplete.setThreshold(THRESHOLD);
        geo_autocomplete.setAdapter(new GeoAutoCompleteAdapter(getActivity())); // 'this' is Activity instance

        geo_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                geo_autocomplete.setText(result.getAddress());
            }
        });

        geo_autocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                {
//                    geo_autocomplete_clear.setVisibility(View.VISIBLE);
                }
                else
                {
//                    geo_autocomplete_clear.setVisibility(View.GONE);
                }
            }
        });

//        geo_autocomplete_clear.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                geo_autocomplete.setText("");
//            }
//        });

        return view;
    }
}
