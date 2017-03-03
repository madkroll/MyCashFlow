package com.example.araragi.mycashflow.activities;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.araragi.mycashflow.R;
import com.example.araragi.mycashflow.dao.DaoSQLite;
import com.example.araragi.mycashflow.transactions.MoneyTransaction;

public class MainActivity extends FragmentActivity {

    public static final String TAG = "main activity";


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

    @Override
    protected void onDestroy() {
        super.onDestroy();


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

    public void typePizdaInLog(){
        Log.i(TAG, "-----Pizzzzdaaaaa-----");
    }


}
