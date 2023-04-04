package com.leonteqsecurity.phishingdetector;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FragmentSentMails extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<EmailItems> emailitems;
    private FloatingActionButton btnCompose;
    public String URL="http://192.168.43.225:5001/api/fetch/mail";
    DbHelper db;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_sent_mails, container, false);
        CreateList();
//        composeEmail();
        String email="martinleontech23@gmail.com";
//        ReceiveEmails(email);



        return  view;
    }


    public void CreateList()
    {
        emailitems = new ArrayList<>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new EmailListAdaptor(emailitems,getContext());
        recyclerView.setAdapter(adapter);

        db=new DbHelper(getContext());
        List<Email> Emails=db.allEmails();
        if (Emails == null || Emails.isEmpty()) {
            Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
        } else {
            // Loop through all emails in the Emails list
            for (Email email : Emails) {
                System.out.println("Sender: " + email.getSender());
                System.out.println("Content: " + email.getMailContent());
                System.out.println("Subject: " + email.getSubject());
                System.out.println("Timestamp: " + email.getTimeStamp());
                System.out.println("Receiver: " + email.getReceiver());

                char firstLetter = email.getSender().charAt(0);
                String firstLetterAsString = Character.toString(firstLetter).toUpperCase();

                EmailItems messageList =new EmailItems(email.getSubject(),email.getMailContent(),firstLetterAsString,email.getSender());
                emailitems.add(messageList);


                // Do something with each email here
            }
        }
        adapter=new EmailListAdaptor(emailitems,getContext());
        recyclerView.setAdapter(adapter);




    }

    public  void composeEmail()
    {
        FloatingActionButton btn=(FloatingActionButton) view.findViewById(R.id.email_fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(),ComposeEmail.class);
                startActivity(intent);
            }
        });
    }

//loop from the server

    public String CurrentTime()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String time = String.format("%02d:%02d", hour, minute);
        return  time;

    }



    public void ReceiveEmails(String email)
    {
        Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject requestBody=new JSONObject();
        try {
            requestBody.put("email",email);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, URL, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String leon=response.toString();
                try {
                    String messages = response.getString("emails");

                    try {
                        JSONArray jsonArray = new JSONArray(messages);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String email = jsonObject.getString("email");
                            String flag = jsonObject.getString("flag");
                            String name = jsonObject.getString("name");
                            String sender = jsonObject.getString("sender");
                            String subject = jsonObject.getString("subject");
                            char firstLetter = sender.charAt(0);
                            String firstLetterAsString = Character.toString(firstLetter).toUpperCase();

                            if(flag.equals("0"))
                            {
//                                Toast.makeText(getContext(), "Identified a phishing  email", Toast.LENGTH_SHORT).show();
                                System.out.println();

                            }else
                            {
                                EmailItems messageList =new EmailItems(subject,email,firstLetterAsString,sender);
                                emailitems.add(messageList);
                            }


                            // Do something with the message data
                            System.out.println("Email: " + email);
                            System.out.println("Flag: " + flag);
                            System.out.println("Name: " + name);
                            System.out.println("sender: " + sender);
                            System.out.println("Subject: " + subject);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





                    Toast.makeText(getContext(), messages, Toast.LENGTH_SHORT).show();
                    adapter=new EmailListAdaptor(emailitems,getContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Check your network", Toast.LENGTH_SHORT).show();
                System.out.println(error);


            }
        });
        queue.add(jsonObjectRequest);


    }













}