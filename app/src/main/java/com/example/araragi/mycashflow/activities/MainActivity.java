package com.example.araragi.mycashflow.activities;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.araragi.mycashflow.R;
import com.example.araragi.mycashflow.dao.DaoSQLite;
import com.example.araragi.mycashflow.transactions.MoneyTransaction;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "main activity";
    DaoSQLite dao;

    TextView textDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);

        //Log.i(TAG, "-----" +  millis + "------");

        textDate = (TextView) findViewById(R.id.textView3);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day  = c.get(Calendar.DAY_OF_MONTH);

        textDate.setText(day + "-" + month + "-" + year);




    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        textDate.setText(day + "-" + month + "-" + year);
    }



    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePikerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }



}
