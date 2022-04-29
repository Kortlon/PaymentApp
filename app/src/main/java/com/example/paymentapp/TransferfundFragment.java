package com.example.paymentapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

public class TransferfundFragment extends Fragment implements View.OnClickListener {
    EditText recipientId;
    EditText amount;
    Button submit;
    DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_transfund, container, false);
        recipientId = root.findViewById(R.id.txtRecipientId);
        amount = root.findViewById(R.id.txtAmount);
        submit = root.findViewById(R.id.submitButton);
        DB = new DBHelper(getContext());
        submit.setOnClickListener(this);
        return root;
    }

    public void onClick(View view) {
        String username = getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        String amt = amount.getText().toString();
        double ammt = Double.parseDouble(amt);
        Date date = new Date();
        int time = (int) (date.getTime() / 1000);
        String cardNum = DB.getcard(username);
       // double balance = DB.getBalance(cardNum);
        double transfer = -1 * ammt;

        DB.insertDataTransaction(transfer, "Transferred funds", time, username);
        Toast.makeText (getActivity (),"Submitted", Toast.LENGTH_SHORT).show ();


    }


}
