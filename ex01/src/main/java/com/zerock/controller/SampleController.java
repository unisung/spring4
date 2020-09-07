package com.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zerock.domain.SampleDTO;
import com.zerock.domain.SampleDTOList;
import com.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/sample/*") //클래스레벨 URL매핑,/sample/aaa,/sample/bbb
public class SampleController {
	
	//클래스레벨의 URL매핑 후 메소드레벨에서 빈 문자열 매핑, /sample/
	@RequestMapping("")
	public void basic() {
		log.info("basic..........");
	}
	
	//클래스레벨 URL매핑 후 메소드레벨 /basic -> /sample/basic
	@RequestMapping(value="/basic",
			                   method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.......");
	}//메소드가 void이면 url경로가 view명이 됨. basic.jsp가 됨.
	
	// /sample/basicOnlyGet
	//@RequestMapping(value="/basicOnlyGet",method=RequestMethod.GET)
	@GetMapping("/basicOnlyGet")//스프링 4.3 이후에 추가된 매핑 어노테이션, @PostMapping()
	public void basicGet2() {
		log.info("basic get only get.......");
	}//basicOnlyGet.jsp
	
	
	//controller의 파라미터 수집
	@GetMapping("/ex01") 
	public String ex01(SampleDTO dto) {//SampleDTO속성명과 같은 파라미터는 자동 매핑되어서 넘어옴.
		log.info(""+dto);
		/* JSP/Servlet에서의 파라미터 수집,
		 * String name=request.getParameter("name"); int
		 * age=Integer.parseInt(reqest.getParameter("age")); dto = new
		 * SampleDTO(name,age);
		 */
		return "sample/ex01";//views/ex01.jsp
	}
	
	//@RequestParam("파라미터명") 타입 변수명,
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name,
		                          @RequestParam("age") int age){
	  log.info("name:"+name);
	  log.info("age:"+age);

	  return "/sample/ex02";	
	}
	
	//파라미터로 넘어온 객체 를  List로 받기
	//동일한 이름으로 여러개 값이 넘어는 경우 ?ids=값1&ids=값2&ids=값3,...
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids, Model model) {
		log.info("ids:"+ids);
		model.addAttribute("ids",ids);//view로 객체를 넘기는 객체 Model
		
		return "/sample/ex02List";
	}
	
	@GetMapping("/ex02Array")// Controller에서 view로 객체 전달 및 이동을 위한 객체(spring) ModelAndView(객체, view) , Model(객체)//request
	public ModelAndView ex02Array(@RequestParam("ids") String[] ids,Model model) {
		log.info("array ids: "+Arrays.toString(ids));//배열을 문자열로 변환 Arrays.toString() 메소드
		model.addAttribute("ids", ids);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/sample/ex02Array");
		return mav;
	}
	
	
	//?list[0].name=값1&list[1].name=값2
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos:"+list);
		return "/sample/ex02Bean";
	}
	
	//문자열형태로 넘어온 파라미터값을 Date타입에 맞추는 설정어노테이션
	/*
	  @InitBinder 
	  public void intiBinder(WebDataBinder binder) {//web데이타를 바인딩시키는 객체
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  binder.registerCustomEditor(java.util.Date.class, new
	  CustomDateEditor(dateFormat, false)); 
	  }
	  */
	 
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: "+todo);
		return "ex03";
	}
	
	//view로 데이타 전달
	//파라미터로 넘어온 속성값이 객체의 속성으로 매핑되어서 넘어왔다가. view까지 전달(객체인경우)
	//기본타입은 넘어온값이 view까지 전달안됨. @ModeAttribute() 어노테이션으로 View까지 전달가능
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page,Model model) {//기본타입은 getter/setter가 없음.
		log.info("dto: "+dto);
		log.info("page: "+page);
		//model.addAttribute("page",page);//auto-boxing
		
		return "/sample/ex04";
	}
	
	// 리턴타입이 void인 메소드는 url경로와 같은 jsp파일로 이동됨.
	@RequestMapping("/ex05")//  /sample/ex05
	public void ex05() {
		log.info("/ex05.....");
		//return "/sample/ex05";
	}
	
	//ajax를 이용한 데이타 전송시 사용
	//RequestMapping메소드에서 객체를 리턴하는 경우
	//@ResponseBody 사용시 pom.xml에 jackson-databind모듈 추가
	@RequestMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06.......");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	//객체를 응답으로 전달할때  body부분과 header부분의 상태값만 전달하는 방식
	//ResponseEntity객체를 사용하여 응답.
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07.......");
		
		
		String msg="{\"name\":\"홍길동\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
		
	}
	
	//파일 업로드 
	@GetMapping("/exUpload")//sample/exUpload.jsp
	public void exUpload() {
		log.info("/exupload.......");
	}
	
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		/*
		 * files.forEach(new Consumer<MultipartFile>() {
		 * 
		 * @Override public void accept(MultipartFile t) {
		 * log.info("-------------------------");
		 * log.info("name:"+t.getOriginalFilename()); log.info("size:"+t.getSize()); }
		 * });
		 */
		files.forEach(t-> {
		log.info("-------------------------");
		log.info("name:"+t.getOriginalFilename()); 
		log.info("size:"+t.getSize()); 
		long now = new java.util.Date().getTime();
		try {
			// abc.jpg => abc    jpg
			String fileName=t.getOriginalFilename().substring(0,t.getOriginalFilename().indexOf("."));//abc
			String ext=t.getOriginalFilename().substring(t.getOriginalFilename().indexOf("."));//.jpg
			String fileBbName=t.getOriginalFilename();//abc.jpg;
			
			t.transferTo(new File("c:\\upload\\"+fileName+now+ext));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		});
	}
	
	@GetMapping("/home")// /sample/home?name=AAA&age=10
	public String home(RedirectAttributes rttr) {//redirect한 view페이지까지 값 전달 객체.
		rttr.addFlashAttribute("name", "AAA");//현재 컨트롤러에서 세팅된 값이 redirect된 페이지로 전달
		rttr.addFlashAttribute("age", 10);
		return "redirect:/";
	}
}
