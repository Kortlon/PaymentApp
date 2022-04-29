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
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table cards(username TEXT primary key, cardnum TEXT)");
        MyDB.execSQL("create Table linkaccounts(AccountNum INTEGER , RoutingNUM INTEGER, username TEXT)");
        MyDB.execSQL("create Table userdata(username TEXT primary key, email TEXT, phonenum TEXT, FName TEXT, LNAME TEXT )");
        MyDB.execSQL("create Table balance(cardnum TEXT primary key, bal REAL)");
        MyDB.execSQL("create Table transactions(id INTEGER primary key, date INTEGER, amount REAL, label TEXT, username TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public String checkcard() {
        Boolean set = true;
        String cardset = "Blank";
        while (set == true) {
            long car16 = (long) (Math.random() * 10000000000000000L);
            String first16 = Long.toString(car16);
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select * from cards where cardnum = ?", new String[]{first16});
            if (cursor.getCount() > 0)
                set = true;
            else
                cardset = first16;
            set = false;
        }
        return cardset;
    }

    public boolean insertCard(String username, String card) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("cardnum", card);

        long result = MyDB.insert("cards", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public String getcard(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select cardnum from cards where username = ?", new String[]{username});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String num = cursor.getString(0);
            return num;
        } else
            return "Blank Card";
        // String card = cursor.getString(1);
        //  return card;
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkuserpass(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public List<String> getAccounts(String username) {
        List<String> accounts = new ArrayList<String>();
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select AccountNum from linkaccounts where username = ? GROUP BY AccountNum" ,new String[]{username});


        int numoflines =  cursor.getCount();
        cursor.moveToFirst();
        for(int i = 1; i <= numoflines ; i++){

            accounts.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return accounts;
    }

    public boolean insertDataTransaction(double amount, String label, int date, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("amount", amount);
        contentValues.put("label", label);
        contentValues.put("date", date);
        contentValues.put("username",username);
        db.insert("transactions", null, contentValues);


        long result = db.insert("transactions", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataBank(int accountNumber, int routingNumber, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AccountNum", accountNumber);
        contentValues.put("RoutingNUM", routingNumber);
        contentValues.put("username",username);
        db.insert("linkaccounts", null, contentValues);

        long result = db.insert("linkaccounts", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

        public ArrayList<String> getTransactions (String username) {
            ArrayList<String> transactions = new ArrayList<String>();
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select DATETIME(date,'unixepoch'), amount, label from transactions where username = ? GROUP BY date" ,new String[]{username});
            int numoflines =  cursor.getCount();
            cursor.moveToFirst();
            for(int i = 1; i <= numoflines ; i++){

                transactions.add(cursor.getString(1) + " $  " + cursor.getString(0) +"  --" + cursor.getString(2));
                cursor.moveToNext();
            }

           return transactions;
      }

        public String getTotal (String username) {
            double sum = 0;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select amount from transactions where username = ? GROUP BY date",new String[]{username} );
            int numoflines = cursor.getCount();
            cursor.moveToFirst();
            cursor.getColumnIndex("amount");
            for(int i = 1; i <= numoflines; i++){

                double num = cursor.getDouble(0);
                sum += num;
                cursor.moveToNext();
                cursor.getColumnIndex("amount");
            }
            String sumString = Double.toString(sum);
            return sumString;

        }
        public double getBalance(String cardnum){
        double bal = 0;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT bal FROM balance where cardnum = ?",new String[] {cardnum});
            bal = cursor.getDouble(0);
            return bal;
        }


    }

