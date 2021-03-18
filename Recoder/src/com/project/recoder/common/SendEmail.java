package com.project.recoder.common;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.mail.internet.AddressException;

public class SendEmail {
 
	//이메일 보내기
	public void Email(HttpSession session2, String email, String randomNum) throws Exception{
		
		  final String user   = "imsori960611@gmail.com"; //발신자 이메일
		  final String password  = "djfwkdsksla1";

		  String to     = email ;//수신자 이메일

		  
		  // Get the session object
		  Properties prop = new Properties();
		  prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "587");
		  prop.put("mail.smtp.auth", "true");
		  prop.put("mail.smtp.starttls.enable", "true");
		  prop.put("mail.smtp.starttls.trust", "smtp.gmail.com");

		  Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(user, password);
		   }
		  });

		  // Compose the message
		  try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(user)); //발신자
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); //수신자

		   // Subject
		   message.setSubject("[내방] 비밀번호 찾기 인증");
		   
		   // Text
		   message.setText("인증번호 : "+randomNum);

		   // send the message
		   Transport.send(message);
		   System.out.println("이메일 전송 성공");
		   //session2.setAttribute("randomNum", randomNum);
		   
		  } catch(AddressException e) {
			   e.printStackTrace();
			   throw e;
		  } catch (MessagingException e) {
			 e.printStackTrace();
			 throw e;
		  }catch(Exception e ) {
			  e.printStackTrace();
			  throw e;
		  }
		  
		  
  
  
 
  
	}
	
	
}
	
