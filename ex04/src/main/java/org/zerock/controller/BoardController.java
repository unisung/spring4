package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")//클래스 레벨 어노테이션
public class BoardController {
	
	private BoardService service;
	
	//등록폼으로 이동
	@GetMapping("/register")// url경로: /board/register
	public void register() {}//void타입 메소드 인 경우 URI와 같은 jsp로 이동, /WEB-INF/views/register.jsp
	
	//등록폼에서 값을 입력후 컨트롤러로 넘어온 파라미터를 받아서 db에 저장 후 redirect로 /board/list로 이동
	@PostMapping("/register")
	public String registerPost(BoardVO vo,RedirectAttributes rttr) {
		log.info("register:" + vo);
		
		service.register(vo);
		
		rttr.addFlashAttribute("result", vo.getBno());
		
		return "redirect:/board/list";
	}
	
	//글목록 보기
	//@GetMapping("/list")// url경로: /board/list
	//public void list(Model model) {//list.jsp로 이동, /WEB-INF/views/list.jsp
	//	log.info("list:");
		
	//	model.addAttribute("list",service.getList());//service의 getList()메소드 호출 결과를 model에 저장.
	//}
	
	//@GetMapping("/list")
	//public void list(Criteria cri,Model model) {
	//	log.info("list:"+cri);
	//	model.addAttribute("list", service.getListWithPaging(cri));
	//}
	
	@GetMapping("/list")
	public void list(Criteria cri,Model model) {
		log.info("list:"+cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		
		int total = service.getTotal(cri);//페이지번호, 페이당 건수로 조회
		log.info("total:"+total);
		
		log.info("pageDTO:"+new PageDTO(cri, total));
		model.addAttribute("pageMaker",new PageDTO(cri, total));
		
	}
	
	
	//글 상세 보기
	//@GetMapping("/get")// url /board/get
	   @GetMapping({"/get","/modify"}) // url /board/get 혹은 /board/modify 두 url경로에 대해 호출되는 메소드 
		public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
			log.info("/get or /modify");
			
			model.addAttribute("board", service.get(bno));
		}
		
		
	//@GetMapping("/get")// url /board/get
	/*
	@GetMapping({"/get","/modify"}) // url /board/get 혹은 /board/modify 두 url경로에 대해 호출되는 메소드 
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("/get or /modify");
		
		model.addAttribute("board", service.get(bno));
	}
	*/
	
	/*
	 * @GetMapping("/modify") // url /board/get 혹은 /board/modify 두 url경로에 대해 호출되는
	 * 메소드 public void modify(@RequestParam("bno") Long bno, Model model) {
	 * log.info("/get or /modify");
	 * 
	 * model.addAttribute("board", service.get(bno)); }
	 */
	
	//글 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("/modify"); 
		
		log.info("x:"+cri);
		
		boolean result=service.modify(vo);
		
		//정상처리시 success, 비정상 처리시 null
		if(result) rttr.addFlashAttribute("result", "success");
		
		//원래의 페이지로 돌아가기위한 파라미터 설정
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		log.info(cri);
		return "redirect:/board/list";
	}
	
	//글 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("/remove"); 
		
		boolean result=service.remove(bno);
		
		//정상처리시 success, 비정상 처리시 null
		if(result) rttr.addFlashAttribute("result", "success");
		
		//원래의 페이지로 돌아가기위한 파라미터 설정
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	//좋아요
    @ RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH},
    		                      value="/good/{bno}" )
    public ResponseEntity<BoardVO> good(@PathVariable("bno") Long bno){
    	log.info(bno);
    	
    	service.updateGoodCount(bno);
 		return	   new ResponseEntity<BoardVO>(service.get(bno),HttpStatus.OK);
    }
	
    //싫어요
    @ RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH},
    								value="/bad/{bno}")
    public ResponseEntity<BoardVO> bad(@PathVariable("bno") Long bno){
    	log.info(bno);
    	
    	service.updateBadCount(bno);
 		return	   new ResponseEntity<BoardVO>(service.get(bno),HttpStatus.OK);
    }
}
