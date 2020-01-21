package com.example.proto3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class CustomerLogReg extends AppCompatActivity {


    private Button Customerlogin;
    private Button Customerregister;
    private TextView Customerregisterlink;
    private TextView Customerstatus;
    private EditText EmailCustomer;
    private EditText PasswordCustomer;
    private FirebaseAuth mAuth;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_log_reg);
        mAuth= FirebaseAuth.getInstance();

        Customerlogin= (Button) findViewById(R.id.CustomerLoginBtn);
        Customerregister=(Button) findViewById(R.id.CustomerRegBtn);
        Customerregisterlink=(TextView) findViewById(R.id.AskCustomer);
        Customerstatus=(TextView) findViewById(R.id.Customer_Login);
        EmailCustomer= findViewById(R.id.CustomerEmail);
        PasswordCustomer= findViewById(R.id.CustomerPassword);
        LoadingBar= new ProgressDialog(this);

        Customerregister.setVisibility(View.INVISIBLE);
        Customerregister.setEnabled(false);

        Customerregisterlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customerlogin.setVisibility(View.INVISIBLE);
                Customerregisterlink.setVisibility(View.INVISIBLE);
                Customerstatus.setText("Register Customer:");

                Customerregister.setVisibility(View.VISIBLE);
                Customerregister.setEnabled(true);

            }
        });

        Customerregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email= EmailCustomer.getText().toString();
                String Password= PasswordCustomer.getText().toString();

                RegisterCustomer(Email, Password);


            }
        });







    }

    private void RegisterCustomer(String email, String password) {

        if(TextUtils.isEmpty(email))
            Toast.makeText(CustomerLogReg.this, "Please fill up your Email....",Toast.LENGTH_SHORT).show();

        if(TextUtils.isEmpty(password))
            Toast.makeText(CustomerLogReg.this, "Please fill up your Password....",Toast.LENGTH_SHORT).show();
        else
            LoadingBar.setTitle("Customer");
        LoadingBar.setMessage("Please wait, while system is registering your data");
        LoadingBar.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(CustomerLogReg.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            LoadingBar.dismiss();
                        }

                        else {
                            Toast.makeText(CustomerLogReg.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            LoadingBar.dismiss();
                        }
                    }
                });


    }
}