package com.example.paymentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddBankFragment extends Fragment implements View.OnClickListener {
    DBHelper DB;
    Button addBankButton;
    EditText editAccountNumber, editRoutingNumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.add_bankaccount, container, false);

        editAccountNumber = root.findViewById(R.id.editAccountNumber);
        editRoutingNumber = root.findViewById(R.id.editRoutingNumber);
            addBankButton = root.findViewById(R.id.addBankButton);
            addBankButton.setOnClickListener(this);
        DB = new DBHelper(getContext());
            return root;

    }
    @Override
    public void onClick(View view) {

      String accountValue = editAccountNumber.getText().toString();
        String routingValue = editRoutingNumber.getText().toString();
         int finalAccountValue = Integer.parseInt(accountValue);
        int finalRoutingValue = Integer.parseInt(routingValue);


        String username =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);

            DB.insertDataBank(finalAccountValue, finalRoutingValue, username);
            replaceFragment(new AccountFragment());


    }
    public void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commitNow();
    }
    }
