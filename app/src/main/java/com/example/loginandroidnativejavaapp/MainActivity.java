package com.example.loginandroidnativejavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText formEmail;
    EditText formPassword;
    TextView register;
    Button loginBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        formEmail = findViewById(R.id.txt_email);
        formPassword = findViewById(R.id.txt_password);
        register = (TextView) findViewById(R.id.view_register);
        loginBtn = (Button) findViewById(R.id.btn_login);
        setOnClickListenerOnRegisterTextButtonAndGoToRegisterActivity();
        setOnClickListenerOnLoginButton();

       /* public void login(View view){
            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                //correct password
            } else{
                //wrong password
            }
        }

        int counter=3;
        counter--;
        if(counter==0){
            //TODO: disable the button, close the application
        }*/
    }

    private void setOnClickListenerOnRegisterTextButtonAndGoToRegisterActivity() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    private void setOnClickListenerOnLoginButton() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = formEmail.getText().toString().trim();
                String password = formPassword.getText().toString().trim();
                String username = email.substring(0, email.indexOf("@"));
                goToWelcomePageIfUserIsRegistered(email, password, username);
            }

            private void goToWelcomePageIfUserIsRegistered(String email, String password, String username) {
                if(db.isUserRegistered(email, password)){
                    // We can create an Intent and go to another activity or screen, else show a Toast Message
                    Intent moveToWelcomePage = new Intent(this, WelcomeActivity.class);
                    moveToWelcomePage.putExtra("username", username);
                    startActivity(moveToWelcomePage);
                    Toast.makeText(MainActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}