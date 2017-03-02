package com.example.araragi.mycashflow.activities;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class MainActivity extends FragmentActivity {

    public static final String TAG = "main activity";
    DaoSQLite dao;





    private ExpenseMainFragment expenseMainFragment;
    private IncomeMainFragment incomeMainFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_fragment);


        expenseMainFragment = new ExpenseMainFragment();
        incomeMainFragment = new IncomeMainFragment();

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        transaction.add(R.id.container_main_activity, expenseMainFragment, ExpenseMainFragment.TAG);
        transaction.commit();


    }



    public void onExpenseBtnClick(View v) {

        transaction = manager.beginTransaction();


        if (manager.findFragmentByTag(IncomeMainFragment.TAG) != null) {

            transaction.replace(R.id.container_main_activity, expenseMainFragment, ExpenseMainFragment.TAG);
            Log.i(TAG, "-----inc replaced by exp------");
            transaction.commit();

        }


    }
    public void onIncomeBtnClick(View v) {

        transaction = manager.beginTransaction();



        if (manager.findFragmentByTag(ExpenseMainFragment.TAG) != null) {

            transaction.replace(R.id.container_main_activity, incomeMainFragment, IncomeMainFragment.TAG);
            Log.i(TAG, "-----exp replaced by inc------");
            transaction.commit();
        }

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePikerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

}
