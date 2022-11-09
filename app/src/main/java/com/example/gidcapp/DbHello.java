package com.example.gidcapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHello extends SQLiteOpenHelper {

    public static final String MYDATA = "MyLogin.db";

    public DbHello(@Nullable Context context) {
        super(context, "MyLogin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table myusers(username Text primary key, email Text, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists myusers");
    }

    public Boolean insertData(String username, String emial, String password){

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("email", emial);
        contentValues.put("password", password);

        long result = myDB.insert("myusers", null, contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean chekusername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from myusers where username = ?", new String[]{username});

        if(cursor.getCount() > 0 )
            return true;
        else
            return false;

    }

    public Boolean chekusernamepassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from myusers where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
