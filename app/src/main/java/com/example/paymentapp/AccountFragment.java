package com.example.paymentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {
    DBHelper db;
    TextView bal, ballabel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        db = new DBHelper(getContext());
       // setText();
        String intent =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_S);
        String username = getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        String s1 = intent.substring(0, 4);
        String s2 = intent.substring(4, 8);
        String s3 = intent.substring(8, 12);
        String s4 = intent.substring(12, 16);
        String finalcard = s1 + " - " + s2 + " - " + s3 + " - " + s4;
       View root = inflater.inflate(R.layout.fragment_account, container, false);
       TextView outputtext = root.findViewById(R.id.cardnum);
        outputtext.setText(finalcard);
       String balance = db.getTotal(username);
       bal = root.findViewById(R.id.balance);
        bal.setText(balance);
        ballabel = root.findViewById(R.id.balancelabel);
       ballabel.setText("Balance: ");
       return root;
    }



}
