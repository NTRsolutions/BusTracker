package com.project.verbosetech.bustracker.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.verbosetech.bustracker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;

/**
 * Created by this pc on 19-05-17.
 */

public class Calender_Fragment extends Fragment {

    private View view;
    TextView month,year;
    ImageView left,right;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.calender_layout,container,false);
        month=(TextView)view.findViewById(R.id.month);
        year=(TextView)view.findViewById(R.id.year);
        left=(ImageView)view.findViewById(R.id.decrease_month);
        right=(ImageView) view.findViewById(R.id.increase_month);

        /** end after 1 month from now */
        final Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        Log.e("End Date",endDate+"");

        /** start before 1 month from now */
        final Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Log.e("Start Date",startDate+"");

        Calendar calendar=Calendar.getInstance();

        month.setText(new SimpleDateFormat("MMMM").format(calendar.getTime())+"");
        year.setText(new SimpleDateFormat("yyyy").format(calendar.getTime())+"");

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),new SimpleDateFormat("MMMM").format(startDate.getTime())+"",Toast.LENGTH_LONG).show();


            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),new SimpleDateFormat("MMMM").format(endDate.getTime())+"",Toast.LENGTH_LONG).show();

            }
        });



        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(7)   // Number of Dates cells shown on screen (Recommended 5)
                .dayNameFormat("EEE")	  // WeekDay text format
                .dayNumberFormat("dd")    // Date format
                .monthFormat("MMM") 	  // Month format
                .showDayName(true)	  // Show or Hide dayName text
                .showMonthName(false)	  // Show or Hide month text
                .textColor(Color.DKGRAY, Color.BLACK)    // Text color for none selected Dates, Text color for selected Date.
                .selectedDateBackground(Color.TRANSPARENT)  // Background color of the selected date cell.
                .selectorColor(Color.WHITE)// Color of the selection indicator bar (default to colorAccent).
                .textSizeDayNumber(15.0f)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                //do something

                Toast.makeText(getActivity(),date.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Date date, int position) {
                return true;
            }
        });

        return view;
    }

}
