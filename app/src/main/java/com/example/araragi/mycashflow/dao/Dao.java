package com.example.araragi.mycashflow.dao;

import android.database.Cursor;

import com.example.araragi.mycashflow.transactions.MoneyTransaction;

/**
 * Created by Araragi on 2017-02-25.
 */

public interface Dao {

    MoneyTransaction getTransaction(long id);
    long insertTransaction(MoneyTransaction moneyTransaction);
    boolean deleteTransaction(MoneyTransaction moneyTransaction);
    boolean deleteTransaction(long id);
    boolean updateTransaction(long id, MoneyTransaction moneyTransaction);

    Cursor getAllTransactions();
    Cursor getAllExpenses();
    Cursor getAllIncomes();





}
