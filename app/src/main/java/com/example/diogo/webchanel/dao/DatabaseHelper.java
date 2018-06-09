package com.example.diogo.webchanel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "webchanel.db";
    private static final int DATABASE_VERSION = 4;

    private String CREATE_TABLE = "";
    private String TABLE_NAME = "";

    DatabaseHelper(Context context, String createTable, String tableName) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.CREATE_TABLE = createTable;
        this.TABLE_NAME = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
