package com.leonteqsecurity.phishingdetector;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EmailReadScreen extends AppCompatActivity {

    TextView emailView;
    TextView senderView;
    TextView subjectView;
    TextView emailIconS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_read_screen);
        String email=getIntent().getStringExtra("email");
        String sender=getIntent().getStringExtra("sender");
        String subject=getIntent().getStringExtra("subject");


        emailView =(TextView) findViewById(R.id.mailContent);
        senderView =(TextView) findViewById(R.id.emailSender);
        subjectView=(TextView) findViewById(R.id.mailContent);
        emailIconS =(TextView) findViewById(R.id.emailIconS);

        senderView.setText(sender);
        subjectView.setText(subject);
        emailView.setText(email);
        emailIconS.setText(sender.substring(0,1));



    }
}