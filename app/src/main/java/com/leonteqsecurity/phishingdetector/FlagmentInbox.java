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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class FlagmentInbox extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<EmailItems> emailitems;
    private FloatingActionButton btnCompose;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_flagment_inbox, container, false);
        CreateList();
        composeEmail();



        return  view;
    }


    public void CreateList()
    {
        emailitems = new ArrayList<>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        for(int i=0;i<=10;i++)
        {
            EmailItems messageList =new EmailItems("Hello Martin","kindly fin the attached documment my nameis martin i love hacking and also doing alot of coding this is my first email text","K","martinleontech23@gmail.com");
            emailitems.add(messageList);

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





}