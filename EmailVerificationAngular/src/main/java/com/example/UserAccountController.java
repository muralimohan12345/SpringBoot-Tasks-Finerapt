package com.example;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200/")
@RequestMapping("api/users")
@RestController
public class UserAccountController {
    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailService emailService;
	
	Logger log =Logger.getAnonymousLogger();
	
@PostMapping("/register")
 public void registerUser(@RequestBody UserEntity user) {
	  emailService.registerUser(user);
	 
}

@GetMapping("/verify/{id}")
 public void verifyEmail(@PathVariable Long id) throws NotFoundException {
	emailService.verifyEmail(id);
}
  
/*
 * @GetMapping("/verify") public void verifyEmail(@RequestParam("token") String
 * token) { emailService.verifyEmail(token); }
 */

 
 
 @GetMapping("/checkEmail")
 public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
	       log.info("email is " +email);
	 boolean emailExists=emailService.emailExists(email);
	return ResponseEntity.ok(emailExists);	 
 }
 
  @GetMapping("/resetPassMail")
    public void resetPassMail(@RequestParam("email") String email) {
	    log.info("email for reset mail is "+email);
	  System.out.println( "email is " +email);
	  emailService.resetPassMail(email);
  }
   
  @PostMapping("/reset")
    public void resetPass(@RequestBody UserEntity ue) {
	  //  log.info("password is "+password);
	    emailService.resetPass(ue);  
  }
  
  @GetMapping("/forgotPasswordMail")
  public void forgotPassword(@RequestParam("email") String email) {
	   emailService.forgotPasswordMail(email);
  }
  
  @PostMapping("/login")
   public boolean login(@RequestBody UserEntity ue) {
	  return emailService.login(ue);
  }
  
  @GetMapping("/checkCurrentPass")
   public ResponseEntity<Boolean> checkCurrentPass(@RequestParam("password") String password){
	  System.out.println("password is "+ password);
	 boolean passwordExist= emailService.checkCurrentPass(password);
	return ResponseEntity.ok(passwordExist);
	   
  }
  
  @GetMapping("getEmail/{id}")
   public ResponseEntity<?> getEmail(@PathVariable("id") Long userId)throws NotFoundException{
	  System.out.println("inside the getEmail method");
	  System.out.println("user id is "+ userId);
	   UserEntity ue=emailService.getEmail(userId);
	  return ResponseEntity.ok(ue); 
  }
  
  
 }
	

