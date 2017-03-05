package com.example.araragi.mycashflow.activities;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.araragi.mycashflow.R;
import com.example.araragi.mycashflow.dao.DaoSQLite;
import com.example.araragi.mycashflow.fragments.DatePikerFragment;
import com.example.araragi.mycashflow.fragments.ExpenseMainFragment;
import com.example.araragi.mycashflow.fragments.IncomeMainFragment;
import com.example.araragi.mycashflow.transactions.MoneyTransaction;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "main activity";


    private ExpenseMainFragment expenseMainFragment;
    private IncomeMainFragment incomeMainFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;


    DaoSQLite dao;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.i(TAG, "---- on create menu was called-----");
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_main_act, menu);
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.go_to_list:
                Intent intent = new Intent(this, TransactionsListActivity.class);
                startActivity(intent);;
                return true;


            case R.id.test_menu_item:
                Toast.makeText(this, "---Test item was clicked---", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_fragment);


        dao = new DaoSQLite(this);
        dao.open();


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
        dao.close();

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



    public void moneyTransactionToDao(MoneyTransaction mt){


        dao.open();
        Log.i(TAG, "----open db----");
        long i = dao.insertTransaction(mt);

        Log.i(TAG, "----insert transaction with id = " + i + " ----");

        MoneyTransaction moneyTransaction = dao.getTransaction(i);

        Log.i(TAG, "----get transaction with id = " + i + ", amount is " + moneyTransaction.getAmount() );

        dao.close();
    }


}
