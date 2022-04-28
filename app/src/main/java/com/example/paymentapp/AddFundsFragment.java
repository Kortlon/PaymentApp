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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AddFundsFragment extends Fragment implements View.OnClickListener {
    DBHelper DB;
    Button addFundButton;
    EditText addAmount;
    Spinner chooseAccount;
    public static final DateTimeFormatter GENERAL_TZ_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.systemDefault());

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_addfund, container, false);
        chooseAccount = root.findViewById(R.id.menuChooseAccount);
        addFundButton = root.findViewById(R.id.addFundSubmitButton);
        addFundButton.setOnClickListener(this);
        addAmount = root.findViewById(R.id.editFundAmount);
        DB = new DBHelper(getContext());
        String intent =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        List<String> accounts = DB.getAccounts(intent);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, accounts);
        chooseAccount.setAdapter(dataAdapter);
        return root;
    }

    @Override
    public void onClick(View view) {

        String intent =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        String value = addAmount.getText().toString();
        double finalValue = Double.valueOf(value);
        Date date = new Date();
        int time = (int) (date.getTime() / 1000);
        String username = intent;
        DB.insertDataTransaction(finalValue, "Added Funds", time ,username);
        //could add intent to send to home after funds have transfered
        addAmount.setText("");




    }

}