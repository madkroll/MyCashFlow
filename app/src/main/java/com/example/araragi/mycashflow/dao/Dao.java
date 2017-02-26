package com.example.araragi.mycashflow.dao;

import android.database.Cursor;

import com.example.araragi.mycashflow.transactions.Transaction;

/**
 * Created by Araragi on 2017-02-25.
 */

public interface Dao {

    Transaction getTransaction(long id);
    long insertTransaction(Transaction transaction);
    boolean deleteTransaction(Transaction transaction);
    boolean updateTransaction(long id, Transaction transaction);

    Cursor getAllTransactions();
    Cursor getAllExpenses();
    Cursor getAllIncomes();





}
