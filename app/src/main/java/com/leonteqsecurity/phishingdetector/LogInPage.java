package com.leonteqsecurity.phishingdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends AppCompatActivity {
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
    }


    public  void  tousegoogle(View view)
    {
        Toast.makeText(this, "Working on it", Toast.LENGTH_SHORT).show();
    }


    public  void btnLogin(View view)
    {
        EditText email= (EditText) findViewById(R.id.email);
        EditText password= (EditText) findViewById(R.id.password);
        String emailString=email.getText().toString();
        String passwordString=password.getText().toString();
        if(emailString.equals("") || passwordString.equals(""))
        {
            Toast.makeText(this, "all field required", Toast.LENGTH_SHORT).show();
        }else
        {
            db=new DbHelper(this);
            db.getRegisterUser(emailString);
        }




        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);

    }

}