package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
        @Column(name="user_id")
  private long userId;
  private String emailId;
  private String password;
        @Column(name="first_name")
  private String firstName;
        @Column(name="last_name")
  private String lastName;	
  private boolean isEnabled;
  
  
  
public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public boolean isEnabled() {
	return isEnabled;
}
public void setEnabled(boolean isEnabled) {
	this.isEnabled = isEnabled;
} 
  
  
  
        
}
