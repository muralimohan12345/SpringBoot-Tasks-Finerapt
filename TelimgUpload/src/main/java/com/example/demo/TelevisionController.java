package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DTO.TelevisionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("http://localhost:4200/")
@RestController

public class TelevisionController {
     @Autowired
     TelevisionService service;
        
     @PostMapping("add")
     public String add(@RequestParam("imageFile") MultipartFile file,
    		           @RequestParam("televisionObj") String televisionObj   ) throws IOException 
     {
    	 	return service.add(file, televisionObj);	 
     }
     
     @GetMapping("get")
     public List<TelevisionDTO> getall(){
    	 return service.getall();
     }
}
