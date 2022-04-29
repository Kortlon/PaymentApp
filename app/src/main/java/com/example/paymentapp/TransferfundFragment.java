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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        String transferusername = recipientId.getText().toString();
        boolean checkuser = DB.checkusername(transferusername);
        if(checkuser == true){
            double ammt = Double.parseDouble(amt);
            Date date = new Date();
            int time = (int) (date.getTime() / 1000);
            String cardNum = DB.getcard(username);
            double balance = DB.getBalance(username);

            double transfer = -1 * ammt;
            double revtransfer = ammt;
            if (balance < revtransfer){
                Toast.makeText (getActivity (),"Not Enough Money", Toast.LENGTH_SHORT).show ();
            }
            if(ammt < 0){
                Toast.makeText (getActivity (),"Money Not Valid", Toast.LENGTH_SHORT).show ();

            }
            if(balance >= revtransfer){
                DB.insertDataTransaction(revtransfer, "Transferred recieved funds", time, transferusername);
                DB.insertDataTransaction(transfer, "Transferred sent funds", time, username);
                Toast.makeText (getActivity (),"Submitted", Toast.LENGTH_SHORT).show ();
                replaceFragment(new AccountFragment());
            }
        }
        else{
            Toast.makeText (getActivity (),"enter a user that exist!!", Toast.LENGTH_SHORT).show ();
        }



    }
public void replaceFragment(Fragment fragment)
{
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_container,fragment);
    fragmentTransaction.commitNow();
}
}
