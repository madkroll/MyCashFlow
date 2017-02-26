package com.example.araragi.mycashflow.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.araragi.mycashflow.R;
import com.example.araragi.mycashflow.dao.Dao;
import com.example.araragi.mycashflow.dao.DaoSQLite;
import com.example.araragi.mycashflow.transactions.Transaction;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "main activity";
    DaoSQLite dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i(TAG, "-----onCreate method------");

        Transaction income1 = new Transaction(320, 2, "2016-08-13");
        Transaction expense1 = new Transaction(110, 1, "2016-09-14");

        Transaction income2 = new Transaction(51, 2, "2016-08-17");
        Transaction expense2 = new Transaction(87, 1, "2016-09-14");
        Transaction income3 = new Transaction(700, 2, "2016-08-13", "Sutherland Global");
        Transaction expense3 = new Transaction(450, 1, "2016-09-14", "Food");


        Log.i(TAG, "-----Transaction objects were created------");

        dao = new DaoSQLite(this);
        dao.open();

        Log.i(TAG, "-----Dao object was initialized and open------");

        dao.insertTransaction(income1);
        dao.insertTransaction(expense1);

        Log.i(TAG, "-----Inc1 and Exp1 were inserted------");

        String s = dao.getAllTransactions().toString();

        Log.i(TAG, "-----" + s + "------");

    }
}
