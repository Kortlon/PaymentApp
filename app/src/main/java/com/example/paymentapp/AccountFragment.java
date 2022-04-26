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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       // setText();
        String intent =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_S);
        String s1 = intent.substring(0, 4);
        String s2 = intent.substring(4, 8);
        String s3 = intent.substring(8, 12);
        String s4 = intent.substring(12, 16);
        String finalcard = s1 + " - " + s2 + " - " + s3 + " - " + s4;
       View root = inflater.inflate(R.layout.fragment_account, container, false);
       TextView outputtext = root.findViewById(R.id.cardnum);
        outputtext.setText(finalcard);
       return root;
    }

    public void setText() {
        super.onStart();
        TextView card = (TextView) getView().findViewById(R.id.cardnum);

        // String intent =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        String intent = "THis is a new string";
        //  String user = intent.ext
        LayoutInflater inflat = getLayoutInflater();
        View view = inflat.inflate(R.layout.fragment_account,null);
        TextView text2 = (TextView) view.findViewById(R.id.accountname1);
        text2.setText(intent);
        // String cardnum = DB.getcard();


    }
    public void setcardnum(){

    }
}
