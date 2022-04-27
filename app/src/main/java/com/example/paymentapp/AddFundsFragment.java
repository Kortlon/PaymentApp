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

public class AddFundsFragment extends Fragment {
    DBHelper DB;
    Button addFundButton;
    EditText addAmount;
    Spinner chooseAccount;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_addfund, container, false);




    }

   /* private void loadAccountSpinner(){

        List<String> accounts = DB.getAccounts();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accounts);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseAccount.setAdapter(dataAdapter);
    }
    private void addFundsTransaction(){
        addFundButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String value= addAmount.getText().toString();
                int finalValue=Integer.parseInt(value);
                DB.insertDataTransaction(finalValue, "Added Funds");
            }
        });
    }
*/

}
