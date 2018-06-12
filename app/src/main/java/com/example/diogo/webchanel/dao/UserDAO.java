package com.example.diogo.webchanel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.widget.Toast;

import com.example.diogo.webchanel.model.User;

import java.util.ArrayList;

public class UserDAO {
    public static String USER_ID = "id";
    public static String NAME = "name";
    public static String ADDRESS = "address";
    public static String EMAIL = "email";
    public static String PASSWORD = "password";
    public static String PHONE = "phone";
    public static String UPDATED_AT = "updatedAt";


    //GLOBAL VARIABLES
    User user;
    ArrayList<User> userList;

    private final Context Context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "webchanel.db";
    private static final String USER_TABLE = "users";


    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + USER_TABLE + " (" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT,"
                    + ADDRESS + " TEXT,"
                    + EMAIL + " TEXT UNIQUE NOT NULL,"
                    + PASSWORD + " TEXT NOT NULL,"
                    + PHONE + " TEXT,"
                    + UPDATED_AT + " DATETIME );";

    public UserDAO(Context ctx) {
        Context = ctx;
        dbHelper = new DatabaseHelper(Context, CREATE_USER_TABLE, USER_TABLE);
    }

    public UserDAO openDB() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void closeDB() {
        dbHelper.close();
    }

    //INSERT USER ON SQLITE DB
    public void insertUser(User u, Context context) {
        try {
            openDB();
            ContentValues cv = new ContentValues();
            cv.put(NAME, u.getName());
            cv.put(ADDRESS, u.getAddress());
            cv.put(EMAIL, u.getEmail());
            cv.put(PASSWORD, u.getPassword());
            cv.put(PHONE, u.getPhone());
            cv.put(UPDATED_AT, u.getUpdatedAt());
            db.insert(USER_TABLE, null, cv);
            closeDB();
        } catch (SQLException mSQLException) {
            if(mSQLException instanceof SQLiteConstraintException){
                System.out.println("Error on createUser(): " + mSQLException.getMessage());
                Toast.makeText(context, "Erro ao realizar o cadastro: " + mSQLException.getMessage(), Toast.LENGTH_LONG).show();
            }else if(mSQLException instanceof SQLiteDatatypeMismatchException) {
                System.out.println("Error on createUser(): ");
                Toast.makeText(context, "Erro ao realizar o cadastro: " + mSQLException.getMessage(), Toast.LENGTH_LONG).show();
            }else{
                throw mSQLException;
            }
        }
    }

    public void updateUser(User u) {
        openDB();
        ContentValues cv = new ContentValues();
        cv.put(NAME, u.getName());
        cv.put(ADDRESS, u.getAddress());
        cv.put(EMAIL, u.getEmail());
        cv.put(PASSWORD, u.getPassword());
        cv.put(PHONE, u.getPhone());
        cv.put(UPDATED_AT, u.getUpdatedAt());
        db.update(USER_TABLE, cv, USER_ID+"="+u.getId(), null);
        closeDB();
    }

    public ArrayList<User> findAllUsers() {
        userList = new ArrayList();

        openDB();
        String sql = "SELECT * FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                userList.add(user);
                cursor.moveToNext();
            }
        }

        cursor.close();
        closeDB();

        return userList;
    }

    public User findUserById(int id) {
        user = new User();

        openDB();
        String sql = "SELECT * FROM " +USER_TABLE+ " WHERE " +USER_ID+ " = " + id;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            user.setId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
        }

        cursor.close();
        closeDB();

        return user;
    }

    public User findUserByEmail(String email) {
        openDB();
        String sql = "SELECT * FROM " +USER_TABLE+ " WHERE " +EMAIL+ " = '" + email + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            user.setId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
        }

        cursor.close();
        closeDB();

        return user;
    }

    public User findUserByName(String name) {
        openDB();
        String sql = "SELECT * FROM " +USER_TABLE+ " WHERE " +NAME+ " = " + name;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            user.setId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
        }

        cursor.close();
        closeDB();

        return user;
    }

    public void deleteUserById(int id) {
        openDB();
        db.execSQL("DELETE FROM " +USER_TABLE+ " WHERE " +USER_ID+ " = " + id);
        closeDB();
    }
}
