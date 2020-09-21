package org.zerock.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {
   @GetMapping("/uploadForm")
   public void uploadForm() {
	   	log.info("upload Form");
   }
	
   
   @PostMapping("/uploadFormAction")
   public String uploadFormAction(MultipartFile[] uploadFile, Model model) {
	   String uploadFolder ="c:\\upload";
	   
	   for(MultipartFile multipartFile : uploadFile) {
		   
		     log.info("----------------------------------");
		     log.info("Upload File Name: "+multipartFile.getOriginalFilename());
		     log.info("Upload File Size: "+ multipartFile.getSize());
		     
		     File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
		     try {
		    	   multipartFile.transferTo(saveFile);
		     }catch(Exception e) {
		    	 log.error(e.getMessage());
		     }
	   }
	   
	   return "redirect:/uploadForm";
   }
   
   @GetMapping("/uploadAjax")
	   public void uploadAjax() {
		   log.info("upload ajax");
	   }
   
   @PostMapping("/uploadAjaxAction")
   public void uploadAjaxPost(MultipartFile[] uploadFile) {
	   log.info("upload ajax post.......");
	   
	   String uploadFolder ="c:\\upload";
	   
	   for(MultipartFile multipartFile : uploadFile) {
		   
		     log.info("----------------------------------");
		     log.info("Upload File Name: "+multipartFile.getOriginalFilename());
		     log.info("Upload File Size: "+ multipartFile.getSize());
		     
		     File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
		     try {
		    	   multipartFile.transferTo(saveFile);
		     }catch(Exception e) {
		    	 log.error(e.getMessage());
		     }
	   }
	   
   }

}
