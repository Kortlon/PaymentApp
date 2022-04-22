package com.example.paymentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAct  extends AppCompatActivity {
    EditText username, password;
    Button btnlogin, btnsignup;

    DBHelper DB;
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.login1);
        btnsignup = (Button) findViewById(R.id.gotosignup);
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(LoginAct.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkuserpass(user,pass);
                    if(checkuserpass ==true){
                        Toast.makeText(LoginAct.this,"Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActitivy.class);
                        intent.putExtra("username",user);
                        startActivity(intent);




                    }else{
                        Toast.makeText(LoginAct.this,"Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });
    }
}

