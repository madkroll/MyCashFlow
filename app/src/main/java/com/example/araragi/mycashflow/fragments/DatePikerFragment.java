package com.example.araragi.mycashflow.fragments;

import android.app.Application;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.araragi.mycashflow.R;

/**
 * Created by Araragi on 2017-02-26.
 */

public class DatePikerFragment extends DialogFragment  implements DatePickerDialog.OnDateSetListener{


        TextView textView;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            return new DatePickerDialog(getActivity(),this , year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            if(getActivity().findViewById(R.id.date_expence) != null) {

                Log.i("date picker", "-----date exp view not null-----");

                textView = (TextView) getActivity().findViewById(R.id.date_expence);
                textView.setText((day + "-" + (month + 1) + "-" + year));
            }
            if(getActivity().findViewById(R.id.date_income) != null) {

                Log.i("date picker", "-----date income view not null-----");

                textView = (TextView) getActivity().findViewById(R.id.date_income);
                textView.setText((day + "-" + (month + 1) + "-" + year));
            }
        }
    }





