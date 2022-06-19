package com.example.loginandroidnativejavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText formEmailForRegistering;
    EditText formPasswordForRegistering;
    EditText formCnfPassword;
    TextView login;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(RegisterActivity.this);
        formEmailForRegistering = findViewById(R.id.txt_email);
        formPasswordForRegistering = findViewById(R.id.txt_password);
        formCnfPassword= findViewById(R.id.txt_cnf_password);
        login = findViewById(R.id.view_login);
        registerBtn = findViewById(R.id.btn_register);

        setOnClickListenerOnLoginButtonAndGoToMainActivity();

        setOnClickListenerOnRegisterButtonAndGoToMainActivityAsFunctionOfSuccessOrNot();
    }

    private void setOnClickListenerOnRegisterButtonAndGoToMainActivityAsFunctionOfSuccessOrNot() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = formEmailForRegistering.getText().toString().trim();
                String password = formPasswordForRegistering.getText().toString().trim();
                String cnfPassword = formCnfPassword.getText().toString().trim();

                /*
                E-mail/Password validaion (We can add different forms of validation here)
                But since validation is not the context here, skipping it
                */

                checkPasswordMatchingAndGoToMainActivityAsFunctionOfThat(email, password, cnfPassword);
            }

            private void checkPasswordMatchingAndGoToMainActivityAsFunctionOfThat(String email, String password, String cnfPassword) {
                if(password.equals(cnfPassword)){
                    long result = db.registerUser(email, password);
                    if(result>0){
                        Toast.makeText(RegisterActivity.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
                        Intent loginPage = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(loginPage);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error while Registering", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password did not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setOnClickListenerOnLoginButtonAndGoToMainActivity() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}