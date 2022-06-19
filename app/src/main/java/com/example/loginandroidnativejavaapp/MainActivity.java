package com.example.loginandroidnativejavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = (EditText) findViewById(R.id.editText1);
        EditText password = (EditText) findViewById(R.id.editText2);
        public void login(View view){
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
        }
    }
}