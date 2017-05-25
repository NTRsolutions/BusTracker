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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    SimpleDateFormat sdf;
    Date cal_date;
    Calendar startdate,endDate,prevDate;
    HorizontalCalendar horizontalCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.calender_layout,container,false);
        month=(TextView)view.findViewById(R.id.month);
        year=(TextView)view.findViewById(R.id.year);
        left=(ImageView)view.findViewById(R.id.decrease_month);
        right=(ImageView) view.findViewById(R.id.increase_month);


        startdate=Calendar.getInstance();
        startdate.set(Calendar.DATE,1);
        startdate.set(Calendar.HOUR, 00);
        startdate.set(Calendar.MINUTE, 00);
        startdate.set(Calendar.SECOND, 0);
        startdate.set(Calendar.MILLISECOND, 0);
        startdate.add(Calendar.MONTH, 0);


        /** end after 1 month from now */
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH,0);
        endDate.add(Calendar.DATE,0);

//        prevDate = Calendar.getInstance();
//        prevDate.set(Calendar.DATE,1);
//        prevDate.set(Calendar.HOUR, 00);
//        prevDate.set(Calendar.MINUTE, 00);
//        prevDate.set(Calendar.SECOND, 0);
//        prevDate.set(Calendar.MILLISECOND, 0);
//        prevDate.add(Calendar.MONTH, -1);

        month.setText(new SimpleDateFormat("MMMM").format(startdate.getTime())+"");
        year.setText(new SimpleDateFormat("yyyy").format(startdate.getTime())+"");

        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .startDate(startdate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(7)   // Number of Dates cells shown on screen (Recommended 5)
                .dayNameFormat("EEE")	  // WeekDay text format
                .dayNumberFormat("dd")    // Date format
                .monthFormat("MMM") 	  // Month format
                .showDayName(true)	  // Show or Hide dayName text
                .showMonthName(true)	  // Show or Hide month text
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




        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                horizontalCalendar.selectDate(startdate.getTime(),true);

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getActivity(),new SimpleDateFormat("MMMM").format(endDate.getTime())+"",Toast.LENGTH_LONG).show();
//                month.setText(new SimpleDateFormat("MMMM").format(endDate.getTime()));
//                year.setText(new SimpleDateFormat("yyyy").format(endDate.getTime())+"");
//                getMonth();
                horizontalCalendar.selectDate(endDate.getTime(),true);

            }
        });

        return view;
    }

    public void getMonth(){

        sdf = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        try {
            cal_date = sdf.parse(month.getText().toString());
            Log.e("Month",sdf.format(cal_date)+"");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setMonth(cal_date);

    }

    public void setMonth(Date date){

        startdate=Calendar.getInstance();
        startdate.set(Calendar.DATE,1);
        startdate.set(Calendar.HOUR, 00);
        startdate.set(Calendar.MINUTE, 00);
        startdate.set(Calendar.SECOND, 0);
        startdate.set(Calendar.MILLISECOND, 0);
        startdate.set(Calendar.MONTH, date.getMonth());


        /** end after 1 month from now */
        endDate = Calendar.getInstance();
        endDate.set(Calendar.DATE,1);
        endDate.set(Calendar.HOUR, 00);
        endDate.set(Calendar.MINUTE, 00);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);
        endDate.set(Calendar.MONTH, date.getMonth()+1);

        prevDate = Calendar.getInstance();
        prevDate.set(Calendar.DATE,1);
        prevDate.set(Calendar.HOUR, 00);
        prevDate.set(Calendar.MINUTE, 00);
        prevDate.set(Calendar.SECOND, 0);
        prevDate.set(Calendar.MILLISECOND, 0);
        prevDate.set(Calendar.MONTH,date.getMonth()-1);


    }

    public void setCalendar(Calendar startdate,Calendar endDate){





}}
