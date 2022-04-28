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

import java.util.List;

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
        String[] test = new String[]{"hello", "darkness", "my", "old", "friend"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, test);
        chooseAccount.setAdapter(dataAdapter);
        return root;


    }

    //private void loadAccountSpinner() {

    //  List<String> accounts = DB.getAccounts();
    // String[] test = new String[]{"hello", "darkness", "my" , "old", "friend"};




    @Override
    public void onClick(View view) {
        if (!(addAmount.getText().toString() == "")){
            String value = addAmount.getText().toString();
            double finalValue = Double.parseDouble(value);
            DB.insertDataTransaction(finalValue, "Added Funds");
        }
        addAmount.setText("");
    }

}