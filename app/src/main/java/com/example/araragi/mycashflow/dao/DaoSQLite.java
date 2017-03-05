package com.example.araragi.mycashflow.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.araragi.mycashflow.transactions.MoneyTransaction;

/**
 * Created by Araragi on 2017-02-24.
 */

public class DaoSQLite implements Dao{

    private static final String TAG = "DaoSQLite";

    public static final String KEY_ID = "_id";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_TYPE = "type";
    public static final String KEY_DATE = "date";
    public static final String KEY_DESCRIPTION = "description";

    public static final int COL_ID = 0;
    public static final int COL_AMOUNT = 1;
    public static final int COL_TYPE = 2;
    public static final int COL_DATE = 3;
    public static final int COL_DESCRIPTION = 4;

    public static final int TYPE_EXPENSE = 1;
    public static final int TYPE_INCOME = 2;



    public static final String[] ALL_KEYS= new String[]{KEY_ID, KEY_AMOUNT, KEY_TYPE, KEY_DATE, KEY_DESCRIPTION};

    public static final String DATABASE_NAME = "mycashflow.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "transactions";

    public static final String DATABASE_CREATE_SCRIPT = "CREATE TABLE " + DATABASE_TABLE +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "amount REAL, " +
            "type INTEGER NOT NULL, " +
            "date TEXT NOT NULL, " +
            "description TEXT);";





    private final Context context;

    private DatabaseOpenHelper dbhelper;
    private SQLiteDatabase database;

    public DaoSQLite(Context ctx){
        this.context = ctx;
        dbhelper = new DatabaseOpenHelper(context);
    }

    public DaoSQLite open(){
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbhelper.close();
    }

    public MoneyTransaction getTransaction(long id){

        String where = KEY_ID + "=" + id;

        Cursor c = 	database.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return new MoneyTransaction(c);
    };



    public long insertTransaction(MoneyTransaction moneyTransaction){

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, moneyTransaction.getAmount());
        values.put(KEY_TYPE, moneyTransaction.getType());
        values.put(KEY_DATE, moneyTransaction.getDate());
        values.put(KEY_DESCRIPTION, moneyTransaction.getDescription());

        return database.insert(DATABASE_TABLE, null, values);

    }
    public boolean deleteTransaction(MoneyTransaction moneyTransaction){

        String where = KEY_ID + "=" + moneyTransaction.getId();

        return database.delete(DATABASE_TABLE, where, null) != 0;

    }
    public boolean deleteTransaction(long id){

        String where = KEY_ID + "=" + id;

        return database.delete(DATABASE_TABLE, where, null) != 0;

    }



    public boolean deleteAll(){

        String where = "1";

        return database.delete(DATABASE_TABLE, where, null) != 0;

    }



    public boolean updateTransaction(long id, MoneyTransaction moneyTransaction){

        String where = KEY_ID + "=" + id;

        ContentValues newValues = new ContentValues();


        newValues.put(KEY_TYPE, moneyTransaction.getType());
        newValues.put(KEY_DATE, moneyTransaction.getDate());
        newValues.put(KEY_AMOUNT, moneyTransaction.getDescription());

        Log.i(TAG, "-----new values put------");

        newValues.put(KEY_AMOUNT, moneyTransaction.getAmount());

        Log.i(TAG, "----new amount------");



        return database.update(DATABASE_TABLE, newValues, where, null) != 0;
    }

    public Cursor getAllTransactions(){

        String where = null;
        Cursor c = database.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null,
                KEY_DATE + " DESC", null);
        if(c != null){
            c.moveToFirst();
        }
        return c;

    }
    public Cursor getAllExpenses(){

        String where = KEY_TYPE + "=" + TYPE_EXPENSE;
        Cursor c = database.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null,
                KEY_DATE + " DESC", null);
        if(c != null){
            c.moveToFirst();
        }
        return c;



    }
    public Cursor getAllIncomes(){

        String where = KEY_TYPE + "=" + TYPE_INCOME;
        Cursor c = database.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null,
                KEY_DATE + " DESC", null);
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }




}
