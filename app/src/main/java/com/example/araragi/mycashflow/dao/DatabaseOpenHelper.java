package com.example.araragi.mycashflow.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.araragi.mycashflow.dao.DaoSQLite.DATABASE_CREATE_SCRIPT;
import static com.example.araragi.mycashflow.dao.DaoSQLite.DATABASE_NAME;
import static com.example.araragi.mycashflow.dao.DaoSQLite.DATABASE_VERSION;

/**
 * Created by Araragi on 2017-02-24.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseOpenHelper";

    DatabaseOpenHelper(Context context){
        super(context, DaoSQLite.DATABASE_NAME, null, DaoSQLite.DATABASE_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(DaoSQLite.DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion){
        Log.w(TAG, "Upgrading application's database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data!");

        _db.execSQL("DROP TABLE IF EXISTS " + DaoSQLite.DATABASE_TABLE);

        onCreate(_db);
    }


}
