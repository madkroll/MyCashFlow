package com.example.araragi.mycashflow.activities;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.araragi.mycashflow.R;

/**
 * Created by Araragi on 2017-02-27.
 */

public class IncomeMainFragment extends Fragment{

    public static final String TAG = "IncomeMainFragment";

    TextView incomeDate;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.income_fragment_main, null);

    }
    @Override
    public void onStart() {
        super.onStart();


        incomeDate = (TextView) getActivity().findViewById(R.id.date_income);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        incomeDate.setText((day + "-" + month + "-" + year));

    }

}
