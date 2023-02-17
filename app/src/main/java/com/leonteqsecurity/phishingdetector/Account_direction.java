package com.leonteqsecurity.phishingdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Account_direction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_direction);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
    }

    public void toLogin(View view)
    {
        Intent intent= new Intent(this,LogInPage.class);
        startActivity(intent);
    }
    public void  toRegister(View view)
    {
        Intent intent= new Intent(this,CreateAccount.class);
        startActivity(intent);

    }
}