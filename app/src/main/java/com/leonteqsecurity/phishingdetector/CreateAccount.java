package com.leonteqsecurity.phishingdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }


    }
    public void btnRegister(View view)
    {
        EditText username=(EditText) findViewById(R.id.Rusername);
        EditText email=(EditText) findViewById(R.id.Remail);
        EditText password=(EditText) findViewById(R.id.Rpassword);
        String passwordString=password.getText().toString();
        String emailString=email.getText().toString();
        String usernameString=username.getText().toString();
        if(passwordString.equals("") || emailString.equals("") || usernameString.equals(""))
        {
            Toast.makeText(this, "all field required", Toast.LENGTH_SHORT).show();

        }else
        {
            db=new DbHelper(this);
            boolean response =db.RegisterUser(usernameString,emailString,passwordString);
            if (response)
            {
                Toast.makeText(this, "Successfull registerd", Toast.LENGTH_SHORT).show();

            }else
            {
                Toast.makeText(this, "Failed to register", Toast.LENGTH_SHORT).show();
            }
        }


        Intent intent =new Intent();
        startActivity(intent);

    }

}