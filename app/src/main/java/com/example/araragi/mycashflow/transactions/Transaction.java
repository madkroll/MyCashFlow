package com.example.araragi.mycashflow.transactions;

import android.database.Cursor;

import com.example.araragi.mycashflow.dao.DaoSQLite;

/**
 * Created by Araragi on 2017-02-24.
 */

public class Transaction {

    private int id;
    private float amount;
    private int type;
    private String date;
    private String description;

    public Transaction(){};

    public Transaction(float amount, int type, String date, String description) {

        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public Transaction(float amount, int type, String date) {

        this.amount = amount;
        this.type = type;
        this.date = date;

    }


    public Transaction(int id, float amount, int type, String date, String description) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public Transaction(Cursor cursor){
        this.id = cursor.getInt(DaoSQLite.COL_ID);
        this.amount = cursor.getFloat(DaoSQLite.COL_AMOUNT);
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

    public float getAmount() {
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
