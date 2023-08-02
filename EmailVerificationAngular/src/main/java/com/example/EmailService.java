package com.example;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	Logger log=Logger.getAnonymousLogger();
	
      @Autowired
      UserRepository repo;
      
      @Autowired
	private JavaMailSender mailSender;
     
public void registerUser(UserEntity user) {
	String token= UUID.randomUUID().toString();
	
	 user.setVerified(false);
	 user.setVerificationToken(token);
	 UserEntity obj =  repo.save(user);
	   
	 String subject="Email Verification";
	// String verificationLink="http://localhost:8080/verify?token="+token;
	 
	 String verificationLink="http://localhost:4200/verification?userId="+obj.getUserId();
	 
	 
	 String body="Please click on the link to verify your email "+verificationLink;
	 
	 sendEmail(user.getEmailId(),subject,body);	 
}   


 public void verifyEmail(Long id) throws NotFoundException {
	           log.info("inside the verify email service1");
	 UserEntity user=repo.findById(id).orElseThrow(NotFoundException::new);
               log.info("inside the verify email service2");
	        user.setVerified(true);
	           log.info("inside the verify email service3");
	        repo.save(user);
 }
 
 
        
 private void sendEmail(String to, String subject, String body) {
	           SimpleMailMessage message=new SimpleMailMessage();
	    message.setTo(to);
	    message.setSubject(subject);
	    message.setText(body);
	    System.out.println("to-"+to);
	    System.out.println("subject-"+subject);
	    System.out.println("body-"+body);
	    mailSender.send(message);
}


public boolean emailExists(String mail) {
	UserEntity existingUser= repo.findByEmailIdIgnoreCase(mail);
	if(existingUser != null) {
		return true;
	} else
		return false;
}

public void resetPassMail(String email) {
	//UserEntity ue=new UserEntity();
	UserEntity existing=repo.findByEmailIdIgnoreCase(email);
	if(existing !=null) {
		   log.info("inside the reset email if condition");
		String subject="Email verification for reset password";
		String verificationLink="http://localhost:4200/resetPass?userId="+existing.getUserId();
		String body="Please click on the link for reset your password "+verificationLink;
		sendEmail(existing.getEmailId(),subject,body);	 
	}
}


public void resetPass(UserEntity ue) {
	System.out.println("password is "+ue.getPassword());
	System.out.println("password is "+ue.getUserId());
	
	repo.updatePassword(ue.getPassword(),ue.getUserId());
	
}


public void forgotPasswordMail(String email) {
	
	UserEntity existing=repo.findByEmailIdIgnoreCase(email);
	System.out.println(existing);
	if(existing !=null) {
		String subject="Email verification for reset password";
		String verificationLink="http://localhost:4200/forgotPasswordForm?userId="+existing.getUserId();
		String body="Please click on this link for reset your password "+verificationLink;
		sendEmail(existing.getEmailId(),subject,body);
	}
	
}


public boolean login(UserEntity ue) {
	String emailId=ue.getEmailId();
	String password=ue.getPassword();
	    System.out.println("email is -"+emailId);
	    System.out.println("password is -"+password);
	    
	    UserEntity ueObj = repo.findByEmailId(emailId);
	      System.out.println("ueObj is -"+ ueObj);
	    
//	    if (repo.findbyEmailId(emailId).equals(repo.findbyPassword(password))) {
//	    	// right details return false
//	    	return false;
//	    }  
//	 // wrong details return true
//	    	return true;
	    if(ueObj != null) {
	    	if(ueObj.getPassword().equals(ue.getPassword())) {
		    	return false;
		    }else {
		    	return true;
		    }
	    }
	    else {
	    	return true;
	    }
	    	
}


public boolean checkCurrentPass(String password) {
	UserEntity existing=repo.findByPassword(password);
	if(existing != null) {
		if(existing.getPassword().equals(password)) {
			return false;
		   }
		else {
			return true;
		}
			
	}
	else {
		return true;
	}
}


public UserEntity getEmail(Long userId) {
	UserEntity ue=repo.findByUserId(userId);
	String emailId=ue.getEmailId();
	return ue;
}




      
}
