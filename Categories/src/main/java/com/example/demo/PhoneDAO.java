package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhoneDAO {
	   @Autowired
	   PhoneRepository repo;
	   
	   public List<PhoneCategory> getall() {
		   return repo.findAll();
	   }
	   
	   public PhoneCategory insert(PhoneCategory p) {
		   return repo.save(p);
	   }
    
}
