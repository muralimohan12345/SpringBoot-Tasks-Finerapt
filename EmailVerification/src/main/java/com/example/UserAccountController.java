package com.example;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAccountController {
    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailService emailService;
	
	Logger log =Logger.getAnonymousLogger();
	
	@RequestMapping(value = "/register"/* , method=RequestMethod.GET */)
 public ModelAndView displayRegestration(ModelAndView modelAndView, UserEntity userEntity) {
	  modelAndView.addObject("userEntity", userEntity);
	  modelAndView.setViewName("register.html");
	               log.info("inside the register 1");
	  return modelAndView;
  } 
  
  @RequestMapping(value="/register", method = RequestMethod.POST)
  public ModelAndView registerUser(ModelAndView modelAndView, UserEntity userEntity) {
	  UserEntity existingUser=userRepository.findByEmailIdIgnoreCase(userEntity.getEmailId());
	              log.info("inside the register 2");
	     if(existingUser != null) {
	    	      log.info("inside the if condition");
	    	 modelAndView.addObject("message","This email already exists!");
	    	 modelAndView.setViewName("error");
	     }
	     else {
	    	 userRepository.save(userEntity);
	    	       log.info("inside the save");
	     ConfirmationToken confirmationToken=new ConfirmationToken(userEntity);
	        
	      confirmationTokenRepository.save(confirmationToken);
	                log.info("inside the confirmation token");
	   SimpleMailMessage mailMessage =new SimpleMailMessage();  
	                log.info("inside the mail message 1");
	      mailMessage.setTo(userEntity.getEmailId());
	                log.info("inside the set to");
	      mailMessage.setSubject("Complete Registration!");
	                log.info("inside the set subject");
	      mailMessage.setFrom("murali.surabathina@gmail.com");
	                 log.info("inside the set from");
	      mailMessage.setText("To confirm your account, please click here : "
	              +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
	                 log.info("inside the set text");
	      emailService.sendEmail(mailMessage);
	      			 log.info("inside the send email");
	      modelAndView.addObject("emailId", userEntity.getEmailId());
	                log.info("inside the mail message 2");
	      modelAndView.setViewName("successfulRegisteration");
	        }
	      return modelAndView;
	     }
  @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
  {
      ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

      if(token != null)
      {
      	UserEntity user = userRepository.findByEmailIdIgnoreCase(token.getUserEntity().getEmailId());
          user.setEnabled(true);
          userRepository.save(user);
          modelAndView.setViewName("accountVerified");
      }
      else
      {
          modelAndView.addObject("message","The link is invalid or broken!");
          modelAndView.setViewName("error");
      }

      return modelAndView;
  }
  }
	

