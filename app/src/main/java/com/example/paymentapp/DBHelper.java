package com.example.paymentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null , 1) ;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
    MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    MyDB.execSQL("create Table cards(username TEXT primary key, cardnum TEXT)");
    MyDB.execSQL("create Table linkaccounts(AccountNum INT primary key, RoutingNUM INT, username TEXT)");
    MyDB.execSQL("create Table userdata(username TEXT primary key, email TEXT, phonenum TEXT, FName TEXT, LNAME TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
    MyDB.execSQL("drop Table if exists users");
    }

    public String checkcard(){
        Boolean set = true;
        String cardset = String.valueOf('y');
        while (set == true){
            String first16 = String.valueOf((int) (Math.random() * 10000000000000000L));
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select * from cards where cardnum = ?" , new String[] {String.valueOf(first16)});
            if (cursor.getCount() > 0)
                set = true;
            else
                cardset = first16;
                set = false;
        }
       return cardset;
    }
    public boolean insertCard(String username, String card){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("cardnum",card);

        long result = MyDB.insert("cards",null,contentValues);
        if (result == -1) return false;
        else
            return true;
    }
    public long getcard(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select cardnum from cards where username = ?",new String[] {username});
         long card = cursor.getLong(1);
            return card;
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);

        long result = MyDB.insert("users",null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",new String[] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkuserpass (String username, String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount() > 0)
            return  true;
        else
            return false;

    }
}
