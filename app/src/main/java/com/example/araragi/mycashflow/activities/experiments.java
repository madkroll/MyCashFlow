package com.example.araragi.mycashflow.activities;

import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.util.Log;

import com.example.araragi.mycashflow.transactions.MoneyTransaction;

import static com.example.araragi.mycashflow.activities.MainActivity.TAG;

/**
 * Created by Araragi on 2017-02-26.
 */

public class experiments {

 /*   Log.i(TAG, "-----" +  DateFormat.getInstance().format(System.currentTimeMillis()) + "------");

    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);


    Log.i(TAG, "-----" +  year + "------" + month + "------" + day + "------");

    long da = 1000000000;

    c.setTimeInMillis(System.currentTimeMillis() + da);

    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH);
    day = c.get(Calendar.DAY_OF_MONTH);
*/
    public void cursorToTransactions(Cursor cursor){

        cursor.moveToFirst();

        do{

            Log.i(TAG, "-----cursor cycle------");
            MoneyTransaction t = new MoneyTransaction(cursor);
            Log.i(TAG, "----" + t.getId() + "  " + t.getAmount() + "  "
                    + t.getType() + "  " + t.getDate() + "  " + t.getDescription() + "-----");
        }while(cursor.moveToNext());

    }



}
