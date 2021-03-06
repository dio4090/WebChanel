package com.example.diogo.webchanel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.diogo.webchanel.model.Enterprise;

import java.util.ArrayList;

public class EnterpriseDAO {
    public static String ENTERPRISE_ID = "id";
    public static String NAME = "name";
    public static String SOCIAL_NAME = "socialName";
    public static String ADDRESS = "address";
    public static String CEP = "cep";
    public static String DISTRICT = "district";
    public static String CITY = "city";
    public static String UF = "uf";
    public static String CNPJ = "cnpj";
    public static String TYPE = "type";
    public static String UPDATED_AT = "updatedAt";
    public static String USER_ID = "user_id";

    //GLOBAL VARIABLES
    Enterprise enterprise;
    ArrayList<Enterprise> enterpriseList;

    private final Context Context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private static final String ENTERPRISE_TABLE = "enterprises";

    private static final String CREATE_ENTERPRISE_TABLE =
            "CREATE TABLE " + ENTERPRISE_TABLE + " (" +
                    ENTERPRISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT NOT NULL,"
                    + SOCIAL_NAME + " TEXT UNIQUE NOT NULL,"
                    + ADDRESS + " TEXT,"
                    + CEP + " TEXT NOT NULL,"
                    + DISTRICT + " TEXT,"
                    + CITY + " TEXT,"
                    + UF + " TEXT,"
                    + CNPJ + " TEXT UNIQUE NOT NULL,"
                    + TYPE + " TEXT,"
                    + UPDATED_AT + " DATETIME,"
                    + USER_ID + " INTEGER, "
                    + "FOREIGN KEY(" +USER_ID+") REFERENCES users(id) );";



    public EnterpriseDAO(Context ctx) {
        Context = ctx;
        dbHelper = new DatabaseHelper(Context, CREATE_ENTERPRISE_TABLE, ENTERPRISE_TABLE);
    }

    public EnterpriseDAO openDB() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void closeDB() {
        dbHelper.close();
    }


    public void insertEnterprise(Enterprise e) {
        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put(NAME, e.getName());
            cv.put(SOCIAL_NAME, e.getSocialName());
            cv.put(ADDRESS, e.getAddress());
            cv.put(CEP, e.getCep());
            cv.put(DISTRICT, e.getDistrict());
            cv.put(CITY, e.getCity());
            cv.put(UF, e.getUf());
            cv.put(CNPJ, e.getCnpj());
            cv.put(TYPE, e.getType());
            cv.put(UPDATED_AT, e.getUpdatedAt());
            cv.put(USER_ID, e.getUserId());
            db.insert(ENTERPRISE_TABLE, null, cv);
        } catch(SQLiteException ex) {
            System.out.print(ex.getMessage());
        }
        closeDB();
    }

    public void updateEnterprise(Enterprise e) {
        openDB();
        ContentValues cv = new ContentValues();
        cv.put(NAME, e.getName());
        cv.put(SOCIAL_NAME, e.getSocialName());
        cv.put(ADDRESS, e.getAddress());
        cv.put(CEP, e.getCep());
        cv.put(DISTRICT, e.getDistrict());
        cv.put(CITY, e.getCity());
        cv.put(UF, e.getUf());
        cv.put(CNPJ, e.getCnpj());
        cv.put(TYPE, e.getType());
        cv.put(UPDATED_AT, e.getUpdatedAt());
        cv.put(USER_ID, e.getUserId());
        db.update(ENTERPRISE_TABLE, cv, ENTERPRISE_ID+"="+e.getId(), null);
        closeDB();
    }

    public ArrayList<Enterprise> findAllEnterprises() {
        enterpriseList = new ArrayList();

        openDB();
        String sql = "SELECT * FROM " + ENTERPRISE_TABLE;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                enterprise = new Enterprise();
                enterprise.setId(cursor.getInt(cursor.getColumnIndex(ENTERPRISE_ID)));
                enterprise.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                enterprise.setSocialName(cursor.getString(cursor.getColumnIndex(SOCIAL_NAME)));
                enterprise.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                enterprise.setCep(cursor.getInt(cursor.getColumnIndex(CEP)));
                enterprise.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT)));
                enterprise.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
                enterprise.setUf(cursor.getString(cursor.getColumnIndex(UF)));
                enterprise.setCnpj(cursor.getString(cursor.getColumnIndex(CNPJ)));
                enterprise.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
                enterprise.setUpdatedAt(cursor.getString(cursor.getColumnIndex(UPDATED_AT)));
                enterprise.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
                enterpriseList.add(enterprise);
                cursor.moveToNext();
            }
        }

        cursor.close();
        closeDB();

        return enterpriseList;
    }

    public Enterprise findEnterpriseById(int id) {
        enterprise = new Enterprise();

        openDB();
        String sql = "SELECT * FROM " +ENTERPRISE_TABLE+ " WHERE " +ENTERPRISE_ID+ " = " + id;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            enterprise.setId(cursor.getInt(cursor.getColumnIndex(ENTERPRISE_ID)));
            enterprise.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            enterprise.setSocialName(cursor.getString(cursor.getColumnIndex(SOCIAL_NAME)));
            enterprise.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
            enterprise.setCep(cursor.getInt(cursor.getColumnIndex(CEP)));
            enterprise.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT)));
            enterprise.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
            enterprise.setUf(cursor.getString(cursor.getColumnIndex(UF)));
            enterprise.setCnpj(cursor.getString(cursor.getColumnIndex(CNPJ)));
            enterprise.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            enterprise.setUpdatedAt(cursor.getString(cursor.getColumnIndex(UPDATED_AT)));
            enterprise.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
        }

        cursor.close();
        closeDB();

        return enterprise;
    }

    public Enterprise findEnterpriseByName(String name) {
        enterprise = new Enterprise();

        openDB();
        String sql = "SELECT * FROM " +ENTERPRISE_TABLE+ " WHERE " +NAME+ " = " + name;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            enterprise.setId(cursor.getInt(cursor.getColumnIndex(ENTERPRISE_ID)));
            enterprise.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            enterprise.setSocialName(cursor.getString(cursor.getColumnIndex(SOCIAL_NAME)));
            enterprise.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
            enterprise.setCep(cursor.getInt(cursor.getColumnIndex(CEP)));
            enterprise.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT)));
            enterprise.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
            enterprise.setUf(cursor.getString(cursor.getColumnIndex(UF)));
            enterprise.setCnpj(cursor.getString(cursor.getColumnIndex(CNPJ)));
            enterprise.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            enterprise.setUpdatedAt(cursor.getString(cursor.getColumnIndex(UPDATED_AT)));
            enterprise.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
        }

        cursor.close();
        closeDB();

        return enterprise;
    }

    public Enterprise findEnterpriseByCnpj(String cnpj) {
        enterprise = new Enterprise();

        openDB();
        String sql = "SELECT * FROM " +ENTERPRISE_TABLE+ " WHERE " +CNPJ+ " = " + cnpj;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            enterprise.setId(cursor.getInt(cursor.getColumnIndex(ENTERPRISE_ID)));
            enterprise.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            enterprise.setSocialName(cursor.getString(cursor.getColumnIndex(SOCIAL_NAME)));
            enterprise.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
            enterprise.setCep(cursor.getInt(cursor.getColumnIndex(CEP)));
            enterprise.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT)));
            enterprise.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
            enterprise.setUf(cursor.getString(cursor.getColumnIndex(UF)));
            enterprise.setCnpj(cursor.getString(cursor.getColumnIndex(CNPJ)));
            enterprise.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            enterprise.setUpdatedAt(cursor.getString(cursor.getColumnIndex(UPDATED_AT)));
            enterprise.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
        }

        cursor.close();
        closeDB();

        return enterprise;
    }

    public Enterprise findEnterpriseByUserId(int userId) {
        enterprise = new Enterprise();

        openDB();
        String sql = "SELECT * FROM " +ENTERPRISE_TABLE+ " WHERE " +USER_ID+ " = " + userId;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            enterprise.setId(cursor.getInt(cursor.getColumnIndex(ENTERPRISE_ID)));
            enterprise.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            enterprise.setSocialName(cursor.getString(cursor.getColumnIndex(SOCIAL_NAME)));
            enterprise.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
            enterprise.setCep(cursor.getInt(cursor.getColumnIndex(CEP)));
            enterprise.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT)));
            enterprise.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
            enterprise.setUf(cursor.getString(cursor.getColumnIndex(UF)));
            enterprise.setCnpj(cursor.getString(cursor.getColumnIndex(CNPJ)));
            enterprise.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            enterprise.setUpdatedAt(cursor.getString(cursor.getColumnIndex(UPDATED_AT)));
            enterprise.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
        }

        cursor.close();
        closeDB();

        return enterprise;
    }

    public void deleteEnterpriseById(int id) {
        openDB();
        db.execSQL("DELETE FROM " +ENTERPRISE_TABLE+ " WHERE " +ENTERPRISE_ID+ " = " + id);
        closeDB();
    }
}
