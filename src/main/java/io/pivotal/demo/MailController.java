package io.pivotal.demo;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MailController {
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	@ResponseBody
	public String ping()
	{
		return "The server is running";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/send")
	@ResponseBody
	public String sendEmail(@RequestParam("host") String host, 
							@RequestParam("subject") String subject,  
							@RequestParam("from") String from, 
							@RequestParam("to") String to, 
							@RequestParam("body") String body)
	{
		  String data = "host: " + host + " subject " + subject + " from " + from + " to " + to + " body " + body;
		  // Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	      // final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        
		  // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      /* 
	       
	      // Other properties we might night to set...
	      properties.setProperty("mail.smtps.host", "smtp.gmail.com");
	      properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	      properties.setProperty("mail.smtp.port", "465");
	      properties.setProperty("mail.smtp.socketFactory.port", "465");
	      properties.setProperty("mail.smtps.auth", "true");
	      
	      */

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);
	      
	      try{
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	          // Set Subject: header field
	          message.setSubject("This is the Subject Line!");

	          // Now set the actual message
	          message.setText("This is actual message");

	          // Send message
	          Transport.send(message);
	          return "Data: " + data + "<br>.Sent message successfully....";
	       }catch (MessagingException mex) {
	    	  
	    	   String errorMsg = "An error occurred:" +  mex.getMessage() + "<br>" + mex.getStackTrace();
	    	   return data + "<br>" + errorMsg;
	       }
	}
	
}
