package com.freehindiofflineapps.sqliteloginsignup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "register.db";
    private static final String TABLE_NAME = "registeruser";
    private static final int DATABASE_VERSION = 1;
    private static final String COLUMN_1 = "ID";
    private static final String COLUMN_2 = "username";
    private static final String COLUMN_3 = "password";
    private static final String COLUMN_4 = "email";
    private static final String COLUMN_5 = "mobile";
    private static final String COLUMN_6 = "address";
    private static final String COLUMN_7 = "gender";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,password TEXT,email TEXT,mobile TEXT,address TEXT,gender TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long adduser(String username, String password, String email, String mobile, String address, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("mobile", mobile);
        contentValues.put("address", address);
        contentValues.put("gender", gender);
        long result = sqLiteDatabase.insert("registeruser", null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public boolean checkuser(String username, String password) {
        String[] columns = {COLUMN_1};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = COLUMN_2 + "=?" + " and " + COLUMN_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {COLUMN_1,
                COLUMN_2,
                COLUMN_3,
                COLUMN_4
        };
        // sorting orders
        String sortOrder =
                COLUMN_2 + " ASC ";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_1))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_2)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_3)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_4)));


                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_2, user.getUsername());
        values.put(COLUMN_3, user.getPassword());
        values.put(COLUMN_4, user.getEmail());

        // updating row
        db.update(TABLE_NAME, values, COLUMN_2 + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_NAME, COLUMN_2 + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}