package com.leonteqsecurity.phishingdetector;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ComposeEmail extends AppCompatActivity {
    public static String filePath;
    TextView fromEmail;
    public String URL="https://api2.leonteqsecurity.com/api/mail";
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_email);
    }




    public void  btnAttachFile(View view)
    {
        Toast.makeText(this, "Working on this", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,1);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Get the selected file's path from the intent's data.
            String fileAttachedString="File Attached: ";
            Uri uri = data.getData();
            String filePath = uri.getPath();

            Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
            TextView textView=(TextView) findViewById(R.id.fileAttachedId);
            textView.setText(fileAttachedString+filePath);

            // Perform any necessary operations on the file.
            // ...
        }

    }


    public  void mail(View view)
    {
        File file = new File(filePath);

    }
    public String CurrentTime()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String time = String.format("%02d:%02d", hour, minute);
        return  time;

    }
    public void RegisterdAccounts(View view) {
        TextView fromEmail=(TextView) findViewById( R.id.fromEmail);

        db=new DbHelper(this);
        List<String> emails = db.getAccountEmails();
        int numAccounts = emails.size();
        int randomIndex = new Random().nextInt(numAccounts);
        String randomEmail = emails.get(randomIndex);
        System.out.println("Selected email: " + randomEmail);
        Toast.makeText(this, randomEmail, Toast.LENGTH_SHORT).show();
        fromEmail.setText(randomEmail);
    }


    public void sendEmail(String receiver,String subject,String mail,String from)
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject requestBody=new JSONObject();
        try {
            requestBody.put("mail",mail);
            requestBody.put("subject",subject);
            requestBody.put("receiver",receiver);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, URL, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String leon=response.toString();
                try {
                    String message = response.getString("bot");
                    String newMessage=message+"|friend";

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "mail not sent check", Toast.LENGTH_SHORT).show();
                System.out.println(error);


            }
        });
        queue.add(jsonObjectRequest);

//  adding the mail to the database
        db=new DbHelper(this);
        String currentTime=CurrentTime();
        boolean dbResponse=db.putEmails(mail,from,"yes","no",currentTime,"no");
        if (dbResponse)
        {
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "Failed to post", Toast.LENGTH_SHORT).show();
        }
    }


    public void SendMailBtn(View view)
    {
        EditText emailField= (EditText) findViewById(R.id.emailField);
        EditText receiverMail= (EditText) findViewById(R.id.toEmail);
        EditText subjectMail= (EditText) findViewById(R.id.Subject);

        if(emailField.getText().toString().equals("") || receiverMail.getText().toString().equals("") || subjectMail.getText().toString().equals(""))
        {
            Toast.makeText(this, "Set all parameter before sending the mail", Toast.LENGTH_SHORT).show();
        }else
        {
           String mail=emailField.getText().toString();
           String receiver=receiverMail.getText().toString();
          String  subject=subjectMail.getText().toString();
            fromEmail =(TextView) findViewById(R.id.fromEmail);

          String from=fromEmail.getText().toString();
            sendEmail(receiver,subject,mail,from);

        }

    }



}