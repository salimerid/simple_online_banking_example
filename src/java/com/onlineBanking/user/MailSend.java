/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineBanking.user;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author salim ahmed
 */
public class MailSend {

    public void SendMail(String AccountNum, String pass) {

        final String username = "hhhh@yahoo.com";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("kkk"));
            message.setSubject("Akber Ali Bank Ltd.");
            message.setContent("Welcome to Akber Ali Bank Ltd. Now you are Active Use. Please Login"
                    + "<h4 style='color:Green'> Account Number : "+AccountNum+"<h4> <br/>"
                    + " <h4 style='color:Green'> PassWord : "+pass+"<h4> ",
                    "text/html");

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
