package com.example.gidcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    DbHello DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

        username = findViewById(R.id.si_username_et);
        password = findViewById(R.id.si_password_et);

        login = findViewById(R.id.si_login_btn);
        register = findViewById(R.id.si_go_su_et);

        DB = new DbHello(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LogIn.this, "enter properly..", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean chekuserpass = DB.chekusernamepassword(user, pass);
                    if(chekuserpass==true){
                        Toast.makeText(LogIn.this, "log in successfully...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LogIn.this, "Invalid cradintial...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}