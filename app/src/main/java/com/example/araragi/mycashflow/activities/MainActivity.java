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

public class MainActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "main activity";
    DaoSQLite dao;



    TextView dateExpense;

    private ExpenseMainFragment expenseMainFragment;
    private IncomeMainFragment incomeMainFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_fragment);

        //Log.i(TAG, "-----" +  millis + "------");


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day  = c.get(Calendar.DAY_OF_MONTH);

        //textDate.setText(day + "-" + month + "-" + year);

        expenseMainFragment = new ExpenseMainFragment();
        incomeMainFragment = new IncomeMainFragment();

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        transaction.add(R.id.container_main_activity, expenseMainFragment, ExpenseMainFragment.TAG);

        transaction.commit();

        dateExpense = (TextView)findViewById(R.id.date_expence) ;
        if(dateExpense != null){
        dateExpense.setText((day + "-" + month + "-" + year));}
        else{Log.i(TAG, "-----date  text view is null------");}

        Log.i(TAG, "-----fragment transaction in main activity committed------");





    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        dateExpense.setText(day + "-" + (month + 1) + "-" + year);
    }



    public void startFragmentTransaction(View view){

        transaction = manager.beginTransaction();



        switch (view.getId()) {

            case R.id.expense_btn:
                Log.i(TAG, "-----expense butn clicked" + (manager.findFragmentByTag(IncomeMainFragment.TAG) != null) + "------");

                if(manager.findFragmentByTag(IncomeMainFragment.TAG) == null) {

                    transaction.replace(R.id.container_main_activity, expenseMainFragment);
                    Log.i(TAG, "-----inc replaced by exp------");


                }
                break;

            case R.id.income_btn:
                Log.i(TAG, "-----income btn clicked in main activity------");
                if(manager.findFragmentByTag(ExpenseMainFragment.TAG) == null) {
                    transaction.replace(R.id.container_main_activity, incomeMainFragment);
                    Log.i(TAG, "-----exp replaced by inc------");


                }
                break;
        }
        transaction.commit();

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePikerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

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

}
