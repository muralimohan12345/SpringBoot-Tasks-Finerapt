package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PhoneCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
  private int phoneId;
  private String phoneName;
  private int phonePrice;
public int getPhoneId() {
	return phoneId;
}
public void setPhoneId(int phoneId) {
	this.phoneId = phoneId;
}
public String getPhoneName() {
	return phoneName;
}
public void setPhoneName(String phoneName) {
	this.phoneName = phoneName;
}
public int getPhonePrice() {
	return phonePrice;
}
public void setPhonePrice(int phonePrice) {
	this.phonePrice = phonePrice;
}
  
  
}
