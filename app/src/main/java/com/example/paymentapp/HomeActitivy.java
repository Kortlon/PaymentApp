package com.example.paymentapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class HomeActitivy extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout drawer;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_actitivy);


         Toolbar toolbar = findViewById(R.id.toolbar);

         setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

           ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
             drawer.addDrawerListener(toggle);
            toggle.syncState();
            setUser();

            if (savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_message);
            }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addbank:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddBankFragment()).commit();
                break;
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountFragment()).commit();
                break;
            case R.id.fundadd:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddFundsFragment()).commit();
                break;
            case R.id.fundtransfer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TransferfundFragment()).commit();
                break;
            case R.id.fundhistory:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FundhistFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setUser(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        Intent intent = getIntent();

        String text = intent.getStringExtra(LoginAct.EXTRA_TEXT);
        TextView textview1 = (TextView) header.findViewById(R.id.headeruser);
        textview1.setText(text);

        String cardnum = intent.getStringExtra(LoginAct.EXTRA_S);
        TextView textview2 = (TextView) header.findViewById(R.id.topheader);
        textview2.setText(cardnum);



    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
}