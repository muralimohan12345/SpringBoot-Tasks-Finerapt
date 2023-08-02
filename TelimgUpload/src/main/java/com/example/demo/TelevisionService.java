package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.DTO.TelevisionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TelevisionService {
	
	Logger log=Logger.getAnonymousLogger();
	
	@Autowired
	TelevisionRepository repo;
	
   public String add( MultipartFile imageFile,  String televisionObj) throws IOException{
	   ObjectMapper  obj = new ObjectMapper();
		Television television = obj.readValue( televisionObj  , Television.class);
	television.setTelevisionImage(imageFile.getBytes()); 
		
//		File newFile = File.createTempFile(imageFile.getOriginalFilename(), ".jpg");
//		 String pdfBAse64  = "";
//		 imageFile.transferTo(newFile);
//		 FileInputStream fis = new FileInputStream ( newFile );
//		 System.out.println(fis);
//		 byte[] byteArray = new byte[(int)newFile.length()];
//		 fis.read(byteArray);
//		  pdfBAse64 = Base64.getEncoder().encodeToString(byteArray);
//		  television.setTelevisionImage(byteArray);
//		  
//			newFile.delete();
	    repo.save(television);
		return "Data saved in DB";   
   }
    
   public List<TelevisionDTO> getall(){
	                        log.info("inside the service get all method");
	   List<Television> list =   repo.findAll();
	                       
	   List<TelevisionDTO> dtoList = new ArrayList<TelevisionDTO>();
	                        log.info("array list created for TelevisionDTO");
	                       
	   for(Television  tel : list) {
	   	                    log.info("inside the for loop");
		   String src = "data:image/png;base64,"+Base64.getEncoder().encodeToString(tel.getTelevisionImage());
		   TelevisionDTO telObj = new TelevisionDTO();
		                    log.info("Television object created");   
		   telObj.setTelevisionId(tel.getTelevisionId());
		   telObj.setTelevisionName(tel.getTelevisionName());
		   telObj.setTelevisionPrice(tel.getTelevisionPrice());
		   
		   telObj.setTelevisionImage(src);
		   dtoList.add(telObj);
		   
		                     log.info("Values added to dtoList");
	   }
	   return dtoList;
   }
   
}

// 				doctorSaveDto.setImageStrng("data:image/png;base64,"+Base64.getEncoder().encodeToString(doc.getImage()));

