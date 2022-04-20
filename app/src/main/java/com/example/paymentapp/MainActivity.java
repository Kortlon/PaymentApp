package com.example.paymentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button signin, signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup  = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.login);
        DB = new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this,"please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"registered successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActitivy.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(MainActivity.this,"User already exist!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{Toast.makeText(MainActivity.this,"Password Don't Match", Toast.LENGTH_SHORT).show();
                  }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), LoginAct.class);
               startActivity(intent);
              //  Toast.makeText(MainActivity.this,"sending to login", Toast.LENGTH_SHORT).show();

            }
        });
    }
}