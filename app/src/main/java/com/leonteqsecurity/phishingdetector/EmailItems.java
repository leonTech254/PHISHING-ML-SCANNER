package com.leonteqsecurity.phishingdetector;

public class EmailItems {
    private String emailSubject;
    private String emailContentReview;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    private  String sender;

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailContentReview() {
        return emailContentReview;
    }

    public void setEmailContentReview(String emailContentReview) {
        this.emailContentReview = emailContentReview;
    }

    public String getEmailIconLetter() {
        return emailIconLetter;
    }

    public void setEmailIconLetter(String emailIconLetter) {
        this.emailIconLetter = emailIconLetter;
    }

    private  String emailIconLetter;

    public EmailItems(String emailSubject, String emailContentReview, String emailIconLetter,String Emailsender) {
        this.emailSubject = emailSubject;
        this.emailContentReview = emailContentReview;
        this.emailIconLetter = emailIconLetter;
        this.sender=Emailsender;
    }
}
