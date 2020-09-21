package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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
	// make folder --------
	    File uploadPath = new File(uploadFolder, getFolder());
	    log.info("upload path: " + uploadPath);

	   
	   for(MultipartFile multipartFile : uploadFile) {
		   
		     log.info("----------------------------------");
		     log.info("Upload File Name: "+multipartFile.getOriginalFilename());
		     log.info("Upload File Size: "+ multipartFile.getSize());
		     
		     UUID uuid =UUID.randomUUID();
		     log.info(uuid);
		     
		     String upLoadFileName=uuid.toString()+"_"+multipartFile.getOriginalFilename();
		     
		     
		     File saveFile = new File(uploadPath, upLoadFileName);
		     
		     log.info(saveFile.getAbsolutePath());
		     try {
		    	    
		    	   //파일 저장경로 없을 시 경로 생성 후 저장 
		    	    if(!uploadPath.exists()) uploadPath.mkdirs();
		    	   multipartFile.transferTo(saveFile);
		    	   
		    	   //thumbnail로 저장
		    	   if(checkImageType(saveFile)) {
		    		   FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+upLoadFileName));
		    		   Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
		    		   thumbnail.close();
		    	   }
		     }catch(Exception e) {
		    	 log.error(e.getMessage());
		     }
	   }
	   
   }
   
   private String getFolder() {
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   
	   Date date = new Date();
	   
	   String str = sdf.format(date);
	   
	   return str.replace("-", File.separator);
   }

   //전송된 파일의 타입(image인지 아닌지여부확인 메소드) 
   private boolean checkImageType(File file) {
	   try {
		   		
		    String contentType = Files.probeContentType(file.toPath());
		    return contentType.startsWith("image");
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return false;
   }
   
}
