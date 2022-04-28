package com.example.paymentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddBankFragment extends Fragment implements View.OnClickListener {
    DBHelper DB;
    Button addBankButton;
    EditText editAccountNumber, editRoutingNumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_bankaccount, container, false);

        addBankButton = root.findViewById(R.id.addBankButton);
        addBankButton.setOnClickListener(this);
        return root;

    }
    @Override
    public void onClick(View view) {
        String accountValue = editAccountNumber.getText().toString();
        String routingValue = editRoutingNumber.getText().toString();
        int finalAccountValue = Integer.parseInt(accountValue);
        int finalRoutingValue = Integer.parseInt(routingValue);
        DB.insertDataBank(finalAccountValue, finalRoutingValue);
    }
    }
