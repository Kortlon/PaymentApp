package com.example.paymentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AddFundsFragment extends Fragment implements View.OnClickListener {
    DBHelper DB;
    Button addFundButton;
    EditText addAmount;
    Spinner chooseAccount;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_addfund, container, false);
        chooseAccount = root.findViewById(R.id.menuChooseAccount);
        addFundButton = root.findViewById(R.id.addFundSubmitButton);
        addFundButton.setOnClickListener(this);
        addAmount = root.findViewById(R.id.editFundAmount);
        DB = new DBHelper(getContext());
        List<String> accounts = DB.getAccounts();
      //  String[] test = new String[]{"hello", "darkness", "my", "old", "friend"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, accounts);
        chooseAccount.setAdapter(dataAdapter);
        return root;


    }

    //private void loadAccountSpinner() {

    //  List<String> accounts = DB.getAccounts();
    // String[] test = new String[]{"hello", "darkness", "my" , "old", "friend"};




    @Override
    public void onClick(View view) {
        String intent =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        String value = addAmount.getText().toString();
        double finalValue = Double.parseDouble(value);
        TimeZone tz = TimeZone.getTimeZone("CST");
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String date = df.format(new Date());

        String username = intent;
        DB.insertDataTransaction(finalValue, "Added Funds", date ,username);
        addAmount.setText("");
    }

}