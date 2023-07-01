package org.unibl.etf.email.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	public static void sendEmail(String from, String to, String subject, String message, String answer) {

		String password = "nmymovzbomvlrjyk";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(from, password);
		    }
		});

		try {
		    Message mess = new MimeMessage(session);

		    mess.setFrom(new InternetAddress(from));
		    mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		    mess.setSubject(subject);
		    mess.setText("Question: " + message + "\n\n" + "Answer: " + answer);

		    Transport.send(mess);
		} catch (MessagingException e) {
		    e.printStackTrace();
		}
	}
}
