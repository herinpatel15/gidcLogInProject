package com.example.gidcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    EditText username, email, password, repassword;
    Button register, login;
    DbHello DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        
        username = findViewById(R.id.si_username_et);
        email = findViewById(R.id.su_email_et);
        password = findViewById(R.id.si_password_et);
        repassword = findViewById(R.id.editTextTextPassword2);
        
        register = findViewById(R.id.si_login_btn);
        login = findViewById(R.id.si_go_su_et);

        DB = new DbHello(this);
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "hello register...", Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(getApplicationContext(), LogIn.class);
//                startActivity(intent1);
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                
                if(user.equals("")||email.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this, "enter proper data...", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean chekuser = DB.chekusername(user);
                        if(chekuser==false){
                            Boolean insert = DB.insertData(user, mail, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "user register successfully...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this, "user not register...", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "user allrady existed...", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "chek password...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "hello login...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
            }
        });
    }
}