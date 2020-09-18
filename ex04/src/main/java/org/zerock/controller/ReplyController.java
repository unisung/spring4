package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies/")
@Log4j
@AllArgsConstructor
class ReplyController {
    private ReplyService service;
    
    //댓글 등록
    @PostMapping(value="/new", consumes = "application/json"
    		                ,produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo){
    	log.info("ReplyVO: "+vo);
    	//댓글 등록 처리
    	int insertCount = service.insert(vo);
    	
    	log.info("Reply Insert COUNT: "+ insertCount);
    	
    	return insertCount==1?new ResponseEntity<String>("success",HttpStatus.OK)
    			   : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    	
    	
    }
    
    //댓글 리스트
  /*  @GetMapping(value="/page/{bno}/{page}",
    		              produces= {MediaType.APPLICATION_ATOM_XML_VALUE,
    		            		             MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page,
    		                                                        @PathVariable("bno") Long bno){
    	Criteria cri=new Criteria(page,10);
    	
    	log.info("get Reply List bno: "+bno);
    	
    	log.info("cri:"+cri);
    	
    	return new ResponseEntity<>(service.getListWithPaging(cri, bno),
    			                                          HttpStatus.OK);
    	
    } */
    
    @GetMapping(value="/page/{bno}/{page}",
            produces= {MediaType.APPLICATION_ATOM_XML_VALUE,
          		             MediaType.APPLICATION_JSON_UTF8_VALUE})
public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page,
                                                      @PathVariable("bno") Long bno){
Criteria cri=new Criteria(page,10);

log.info("get Reply List bno: "+bno);

log.info("cri:"+cri);

ReplyPageDTO dto=service.getListWithPaging(cri, bno);
log.info("ReplyDTO: "+dto);
return new ResponseEntity<>(service.getListWithPaging(cri, bno),
	                                          HttpStatus.OK);

}
    
    //댓글 보기
    @GetMapping(value="/{rno}",
    		              produces= {MediaType.APPLICATION_ATOM_XML_VALUE,
                                            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
    	log.info("get :" + rno);
    	
    	return new ResponseEntity<ReplyVO>(service.read(rno),HttpStatus.OK);
    }
    
    
    //댓글 삭제
    @DeleteMapping(value="/{rno}",produces= {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
    	log.info("remove: "+rno);
    	
    	return service.delete(rno)==1?
    			   new ResponseEntity<String>("success",HttpStatus.OK)
    	     :new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    //댓글 수정
    @ RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH},
    		value="/{rno}",
    		consumes = "application/json",
    		produces = {MediaType.TEXT_PLAIN_VALUE}
    		)
    public ResponseEntity<String> modify(@RequestBody ReplyVO  vo,
    		                                                    @PathVariable("rno") Long rno){
    	vo.setRno(rno);
    	log.info("rno: "+rno);
    	log.info("modify:"+vo);
    	
    	return service.update(vo)==1?
 			   new ResponseEntity<String>("success",HttpStatus.OK)
 	     :new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}




