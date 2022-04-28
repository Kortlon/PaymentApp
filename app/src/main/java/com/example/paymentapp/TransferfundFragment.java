package com.example.paymentapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransferfundFragment extends Fragment {
    EditText recipientId;
    EditText amount;
    Button submit;
    DBHelper DB;
    public static final DateTimeFormatter GENERAL_TZ_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.systemDefault());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transfund, container, false);
        recipientId = root.findViewById(R.id.txtRecipientId);
        amount = root.findViewById(R.id.txtAmount);
        submit = root.findViewById(R.id.submitButton);
        DB = new DBHelper(getContext());

        return root;
    }

    public void onClick(View view) {
        String username =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        String amt = amount.toString();
        Date date = new Date();
        int time = (int) (date.getTime() / 1000);
        String cardNum = DB.getcard(username);
        int balance = getBalance(cardNum);
        int transfer = balance - Integer.parseInt(amt);
        editCardBalance(cardNum, transfer);
        DB.insertDataTransaction(transfer, "Transferred funds", time, username);
    }

    private int getBalance(String cardNum) {
        SQLiteDatabase myDB = DB.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT bal FROM balance where cardnum = ?",new String[] {cardNum});
        int balance = Integer.parseInt(cursor.getString(0));
        //int transfer = balance - Integer.parseInt(amt);
        return balance;
    }

    private void editCardBalance(String cardNum, int transfer) {
        SQLiteDatabase myDB = DB.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("UPDATE balance SET bal = ? where cardnum = ?",new String[] {String.valueOf(transfer),cardNum});
    }
}
