package com.example.paymentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText username,password,repassword,addAmount;
    Button signin, signup, addFundButton;
    DBHelper DB;
    Spinner choseAccount;
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
        choseAccount = (Spinner) findViewById(R.id.menuChoseAccount);
        addFundButton = (Button) findViewById(R.id.addFundSubmitButton);
        addAmount = (EditText) findViewById(R.id.editFundAmount);
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
                                Toast.makeText(MainActivity.this,"User already exists!!", Toast.LENGTH_SHORT).show();
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

        addFundButton.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                String amount = addAmount.getText().toString();
                String accountNumber = choseAccount.toString();
                // idk how to add these to the transaction database
            }
        });

    }

    private void loadAccountSpinner(){
        DBHelper db = new DBHelper(getApplicationContext());
        List<String> accounts = db.getAccounts();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, accounts);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choseAccount.setAdapter(dataAdapter);
    }

}