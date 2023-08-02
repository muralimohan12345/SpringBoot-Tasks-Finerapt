package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;


 @Entity
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @ToString
public class Television {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
   private int TelevisionId;
   private String TelevisionName;
   private int TelevisionPrice;
   private byte[] TelevisionImage;
   
   
public int getTelevisionId() {
	return TelevisionId;
}
public void setTelevisionId(int televisionId) {
	TelevisionId = televisionId;
}
public String getTelevisionName() {
	return TelevisionName;
}
public void setTelevisionName(String televisionName) {
	TelevisionName = televisionName;
}
public int getTelevisionPrice() {
	return TelevisionPrice;
}
public void setTelevisionPrice(int televisionPrice) {
	TelevisionPrice = televisionPrice;
}
public byte[] getTelevisionImage() {
	return TelevisionImage;
}
public void setTelevisionImage(byte[] televisionImage) {
	TelevisionImage = televisionImage;
}
}
