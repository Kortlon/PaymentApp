package com.example.paymentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FundhistFragment extends Fragment {
    DBHelper DB;
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
        DB = new DBHelper(getContext());/*
        ArrayList<String> listItems= DB.getTransactions();
        ListView listView = (ListView) getActivity().findViewById(R.id.transactionlist);
        adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, listItems);
        if (listView != null)
            listView.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_fundhist, container, false);*/

        View root = inflater.inflate(R.layout.fragment_fundhist, container, false);

        ListView listView = root.findViewById(R.id.transactionlist);

        String intent =  getActivity().getIntent().getStringExtra(LoginAct.EXTRA_TEXT);
        ArrayList<String> listItems= DB.getTransactions(intent);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listItems);
        if (listItems != null)
            listView.setAdapter(dataAdapter);

        return root;


    }

}