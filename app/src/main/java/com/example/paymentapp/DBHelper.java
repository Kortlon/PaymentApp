package com.example.paymentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
    MyDB.execSQL("create Table balance(cardnum TEXT primary key, bal REAL)");
    MyDB.execSQL("create Table transactions(id INTEGER primary key, amount REAL, label TEXT)");

    }

    // EditText amo = amount;
    //*
    // carnum = getcard(username);
    // bal = cardgetbal(cardnum)
    // transferamount = balance - amount
    // editcardbalance(cardnum, transfer)
    // transactionadd( amount, label)
    // user;
    //
    //

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
    MyDB.execSQL("drop Table if exists users");
    }

    public String checkcard(){
        Boolean set = true;
        String cardset = "Blank";
        while (set == true){
            long car16 = (long) (Math.random() * 10000000000000000L);
            String first16 = Long.toString(car16);
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select * from cards where cardnum = ?" , new String[] {first16});
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
    public String getcard(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select cardnum from cards where username = ?",new String[] {username});
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            String num = cursor.getString(0);
            return  num;
        }
        else
           return "Blank Card";
        // String card = cursor.getString(1);
          //  return card;
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

    public List<String> getAccounts(){
        List<String> accounts = new ArrayList<String>();
        String selectQuery = "select AccountNum from linkaccounts";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                accounts.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accounts;
    }

    public boolean insertDataTransaction(double amount, String label){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("amount", amount);
        contentValues.put("label", label);
        db.insert("transactions", null, contentValues);

        contentValues.put(amountCol, amount);
        contentValues.put(transactionLabel, label);

        long result = db.insert("transactions",null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertDataBank(int accountNumber, int routingNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AccountNum", accountNumber);
        contentValues.put("RoutingNUM", routingNumber);
        db.insert("linkaccounts", null, contentValues);
        long result = db.insert("linkaccounts",null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    public ArrayList<String> getTransactions() {
        ArrayList<String> transactions = new ArrayList<String>();
        String selectQuery = "select * from transactions";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                transactions.add(cursor.getString(2) + "          $" + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return transactions;
    }

    public String getTotal(){
        double sum = 0;
        String selectQuery = "select * from transactions";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                sum += Double.parseDouble(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        String sumString = String.valueOf(sum);
        return sumString;

    }
}
