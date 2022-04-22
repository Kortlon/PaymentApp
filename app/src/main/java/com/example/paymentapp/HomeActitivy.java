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

import org.w3c.dom.Text;

public class HomeActitivy extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actitivy);
        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");
       // TextView userview = (TextView) findViewById(R.id.headeruser);
       // userview.setText("username");


         Toolbar toolbar = findViewById(R.id.toolbar);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);

           ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
             drawer.addDrawerListener(toggle);
            toggle.syncState();
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