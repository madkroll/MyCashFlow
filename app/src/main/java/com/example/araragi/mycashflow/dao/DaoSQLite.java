package com.example.araragi.mycashflow.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.araragi.mycashflow.transactions.Transaction;

/**
 * Created by Araragi on 2017-02-24.
 */

public class DaoSQLite implements Dao{

    private static final String TAG = "DaoSQLite";

    public static final String KEY_ID = "id";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_TYPE = "type";
    public static final String KEY_DATE = "date";
    public static final String KEY_DESCRIPTION = "description";

    public static final int COL_ID = 1;
    public static final int COL_AMOUNT = 2;
    public static final int COL_TYPE = 3;
    public static final int COL_DATE = 4;
    public static final int COL_DESCRIPTION = 5;

    public static final int TYPE_EXPENSE = 1;
    public static final int TYPE_INCOME = 2;



    public static final String[] ALL_KEYS= new String[]{KEY_ID, KEY_AMOUNT, KEY_TYPE, KEY_DATE, KEY_DESCRIPTION};

    public static final String DATABASE_NAME = "mycashflow.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "transactions";

    public static final String DATABASE_CREATE_SCRIPT = "CREATE TABLE " + DATABASE_TABLE +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

    public Transaction getTransaction(long id){

        String where = KEY_ID + "=" + id;

        Cursor c = 	database.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return new Transaction(c);
    };



    public long insertTransaction(Transaction transaction){

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, transaction.getAmount());
        values.put(KEY_TYPE, transaction.getType());
        values.put(KEY_DATE, transaction.getDate());
        values.put(KEY_DESCRIPTION, transaction.getDescription());

        return database.insert(DATABASE_TABLE, null, values);

    }
    public boolean deleteTransaction(Transaction transaction){

        String where = KEY_ID + "=" + transaction.getId();

        return database.delete(DATABASE_TABLE, where, null) != 0;

    }


    public boolean updateTransaction(long id, Transaction transaction){

        String where = KEY_ID + "=" + id;

        ContentValues newValues = new ContentValues();
        newValues.put(KEY_AMOUNT, transaction.getAmount());
        newValues.put(KEY_TYPE, transaction.getType());
        newValues.put(KEY_DATE, transaction.getDate());
        newValues.put(KEY_AMOUNT, transaction.getDescription());

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
