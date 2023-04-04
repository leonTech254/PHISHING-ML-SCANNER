package com.leonteqsecurity.phishingdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LogInPage extends AppCompatActivity {
    DbHelper db;
    private SharedPreferences sharedPreferences;

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
            List<String> credetials=db.getRegisterUser(emailString);
            if (credetials == null || credetials.isEmpty()) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            } else {
                String dbPassword = credetials.get(0);
                if (passwordString.equals(dbPassword)) {
                    sharedPreferences =getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email",emailString);
                    editor.apply();



                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Wrong username/password", Toast.LENGTH_SHORT).show();
                }
            }


        }






    }

}