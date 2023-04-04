package com.leonteqsecurity.phishingdetector;

public class Email {
    private String sender;
    private String mailContent;
    private String subject;
    private String timeStamp;
    private String receiver;

    public Email(String sender, String mailContent, String subject, String timeStamp, String receiver) {
        this.sender = sender;
        this.mailContent = mailContent;
        this.subject = subject;
        this.timeStamp = timeStamp;
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getMailContent() {
        return mailContent;
    }

    public String getSubject() {
        return subject;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getReceiver() {
        return receiver;
    }
}
