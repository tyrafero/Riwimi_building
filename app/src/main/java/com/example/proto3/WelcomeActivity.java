package com.example.proto3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity
{
    private Button welcomedriver;
    private Button welcomecustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomecustomer= (Button) findViewById(R.id.Welcome_Customer_Button);
        welcomedriver= (Button) findViewById(R.id.Welcome_Driver_button);

        welcomecustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginRegisterCustomerIntent= new Intent(WelcomeActivity.this, CustomerLogReg.class);
                startActivity(LoginRegisterCustomerIntent);
            }
        });

        welcomedriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginRegisterDriverIntent= new Intent(WelcomeActivity.this, DriverLogReg.class);
                startActivity(LoginRegisterDriverIntent);
            }
        });


    }
}
