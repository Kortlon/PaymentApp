package com.example.paymentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FundhistFragment extends Fragment {
    DBHelper DB;
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    MyRecyclerViewAdapter adapter;
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
        ArrayList<String> listItems= DB.getTransactions();


        // set up the RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), listItems);
        if (listItems != null)
            recyclerView.setAdapter(adapter);



        return root;
    }

}