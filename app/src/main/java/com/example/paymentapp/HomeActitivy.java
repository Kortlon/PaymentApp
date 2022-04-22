package com.example.paymentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;



public class HomeActitivy extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actitivy);


         Toolbar toolbar = findViewById(R.id.toolbar);

         setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);

           ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
             drawer.addDrawerListener(toggle);
            toggle.syncState();
            setUser();
    }

    public void setUser(){
        Intent intent = getIntent();
        //String text = intent.getStringExtra(LoginAct.EXTRA_TEXT);
        String text = "username goes here";
        TextView textview1 = (TextView) findViewById(R.id.headeruser);
        textview1.setText(text);
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