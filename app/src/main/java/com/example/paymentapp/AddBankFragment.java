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

public class AddBankFragment extends Fragment {
    DBHelper DB;
    Button addBankButton;
    EditText editAccountNumber, editRoutingNumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_addfund, container, false);

    }
    private void addBankAccount() {
        addBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountValue = editAccountNumber.getText().toString();
                String routingValue = editRoutingNumber.getText().toString();
                int finalAccountValue = Integer.parseInt(accountValue);
                int finalRoutingValue = Integer.parseInt(routingValue);
                DB.insertDataBank(finalAccountValue, finalRoutingValue);
            }
        });
    }
}
