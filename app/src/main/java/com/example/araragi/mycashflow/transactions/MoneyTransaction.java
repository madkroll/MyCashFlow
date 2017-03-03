package com.example.araragi.mycashflow.transactions;

import android.database.Cursor;
import android.icu.util.Calendar;

import com.example.araragi.mycashflow.dao.DaoSQLite;

/**
 * Created by Araragi on 2017-02-24.
 */

public class MoneyTransaction {

    public static final int TYPE_EXPENSE = 1;
    public static final int TYPE_INCOME = 2;

    private int id;
    private double amount;
    private int type;
    private String date;
    private String description;

    public MoneyTransaction(){};

    public MoneyTransaction(double amount, int type, String date, String description) {

        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }
    public MoneyTransaction(double amount, int type, Calendar c, String description) {

        String date = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH);

        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }


    public MoneyTransaction(double amount, int type, String date) {

        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = "";

    }


    public MoneyTransaction(int id, double amount, int type, String date, String description) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public MoneyTransaction(Cursor cursor){
        this.id = cursor.getInt(DaoSQLite.COL_ID);
        this.amount = cursor.getDouble(DaoSQLite.COL_AMOUNT);
        this.type = cursor.getInt(DaoSQLite.COL_TYPE);
        this.date = cursor.getString(DaoSQLite.COL_DATE);
        this.description = cursor.getString(DaoSQLite.COL_DESCRIPTION);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
