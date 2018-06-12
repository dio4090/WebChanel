package com.example.diogo.webchanel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.diogo.webchanel.model.Service;

import java.util.ArrayList;

public class ServiceDAO {

    private static String SERVICE_ID = "id";
    private static String DESCRIPTION = "description";
    private static String VALUE = "value";
    private static String ENTERPRISE_ID = "enterprise_id";

    //GLOBAL VARIABLES
    Service service;
    ArrayList<Service> serviceList;

    private final Context Context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private static final String SERVICE_TABLE = "services";

    private static final String CREATE_ENTERPRISE_TABLE =
            "CREATE TABLE " + SERVICE_TABLE + " ("
                + SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DESCRIPTION + " TEXT NOT NULL,"
                + VALUE + " REAL,"
                + ENTERPRISE_ID + " INTEGER,"
                + "FOREIGN KEY(" +ENTERPRISE_ID+") REFERENCES enterprises(id) );";

    public ServiceDAO(Context ctx) {
        Context = ctx;
        dbHelper = new DatabaseHelper(Context, CREATE_ENTERPRISE_TABLE, SERVICE_TABLE);
    }

    public ServiceDAO openDB() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void closeDB() {
        dbHelper.close();
    }

    public void insertServices(Service s) {
        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put(DESCRIPTION, s.getDescription());
            cv.put(VALUE, s.getValue());
            cv.put(ENTERPRISE_ID, s.getEnterpriseId());
            db.insert(SERVICE_TABLE, null, cv);
        } catch(SQLiteException ex) {
            System.out.print(ex.getMessage());
        }
        closeDB();
    }

    public void updateServices(Service s) {
        openDB();
        ContentValues cv = new ContentValues();
        cv.put(DESCRIPTION, s.getDescription());
        cv.put(VALUE, s.getValue());
        cv.put(ENTERPRISE_ID, s.getEnterpriseId());
        db.update(SERVICE_TABLE, cv, SERVICE_ID +"="+s.getId(), null);
        closeDB();
    }

    public ArrayList<Service> findAllServices() {
        serviceList = new ArrayList();

        openDB();
        String sql = "SELECT * FROM " + SERVICE_TABLE;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                service = new Service();
                service.setId(cursor.getInt(cursor.getColumnIndex(SERVICE_ID)));
                service.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                service.setValue(cursor.getFloat(cursor.getColumnIndex(VALUE)));
                service.setEnterpriseId(cursor.getInt(cursor.getColumnIndex(ENTERPRISE_ID)));
                serviceList.add(service);
                cursor.moveToNext();
            }
        }
        cursor.close();
        closeDB();

        return serviceList;
    }

    public Service findServiceById(int id) {
        service = new Service();

        openDB();
        String sql = "SELECT * FROM " +SERVICE_TABLE+ " WHERE " +SERVICE_ID+ " = "+id;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            service.setId(cursor.getInt(cursor.getColumnIndex(SERVICE_ID)));
            service.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            service.setValue(cursor.getFloat(cursor.getColumnIndex(VALUE)));
            service.setEnterpriseId(cursor.getInt(cursor.getColumnIndex(ENTERPRISE_ID)));
            serviceList.add(service);
        }
        cursor.close();
        closeDB();

        return service;
    }

    public void deleteServiceById(int id) {
        openDB();
        db.execSQL("DELETE FROM " +SERVICE_TABLE+ " WHERE " +SERVICE_ID+ " = " + id);
        closeDB();
    }
}
