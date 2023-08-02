package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200/")
@RestController
public class PhoneController {
     @Autowired
     PhoneDAO service;
     
     @PostMapping("add")
     public PhoneCategory addproduct(@RequestBody PhoneCategory ph) {
    	 return service.insert(ph);
     }
     @GetMapping("get")	 
	 public List<PhoneCategory> getallproducts(){
		 return service.getall();
	 }
     
}
