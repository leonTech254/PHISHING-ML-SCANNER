package com.leonteqsecurity.phishingdetector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmailListAdaptor extends RecyclerView.Adapter<EmailListAdaptor.EmailListHolder> {

    public EmailListAdaptor(List<EmailItems> emailItems, Context context) {
        this.emailitems = emailItems;
        this.context = context;
    }

    private List<EmailItems> emailitems;
    private Context context;


    @NonNull
    @Override
    public EmailListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.email_lists_all,parent,false);
        return new EmailListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailListHolder holder, int position) {
        EmailItems EmailItems=emailitems.get(position);
        holder.emailHeader.setText(EmailItems.getEmailSubject());
        String emailSender=EmailItems.getSender();


        if(EmailItems.getEmailContentReview().length()>=95)
        {
            holder.emailPreview.setText(EmailItems.getEmailContentReview().substring(0,95));
        }else
        {
            holder.emailPreview.setText(EmailItems.getEmailContentReview());
        }

        holder.emailIconLetter.setText(EmailItems.getEmailIconLetter());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, emailSender, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,EmailReadScreen.class);
                intent.putExtra("sender",emailSender);
                intent.putExtra("subject",EmailItems.getEmailSubject());
                intent.putExtra("email",EmailItems.getEmailContentReview());
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return emailitems.size();
    }

    public  class EmailListHolder extends RecyclerView.ViewHolder{

        public TextView emailHeader;
        public TextView emailPreview;
        public   TextView emailIconLetter;




        public EmailListHolder(@NonNull View itemView) {
            super(itemView);
            emailHeader= itemView.findViewById(R.id.emailSubject);
            emailPreview= itemView.findViewById(R.id.emailPreview);
            emailIconLetter= itemView.findViewById(R.id.emailIconLetter);

        }
    }
}
