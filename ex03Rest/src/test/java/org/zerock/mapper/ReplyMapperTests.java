package org.zerock.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml"}	
		)
@Log4j
public class ReplyMapperTests {
	//댓글 
	private Long[] bnoArr= {1051L,1052L,1053L,1054L,1055L};
	
	@Setter(onMethod_=@Autowired)
 private ReplyMapper mapper;
	
	@Ignore
	@Test
	public void testMapper() {
		log.info("mapper:==>"+mapper);
	}
	
	@Ignore
	@Test
	public void testCreate() {
	  //  List<ReplyVO> list = new ArrayList<>();
	    for(int i=0;i<10;i++) {
	    	ReplyVO vo = new ReplyVO();
	    	// 게시물 번호
	    	vo.setBno(bnoArr[i%5]);
	    	vo.setReply("댓글테스트"+i);
	    	vo.setReplyer("replyer"+i);
	    	
	    	mapper.insert(vo);
	    }
	    
	}
	
	@Ignore
	@Test
	public void testRead() {
		log.info("댓글 조회:");
		log.info(mapper.read(1L));
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		log.info("댓글 수정:" );
		ReplyVO reply = new ReplyVO();
		reply.setRno(1L);
		reply.setReply("수정된 댓글");
		
		log.info("수정된 건수: "+mapper.update(reply));
		log.info("수정결과: "+mapper.read(reply.getRno()));
	}
	
	@Ignore
	@Test
	public void testDelete() {
		log.info(" 댓글 삭제: ");
		
		log.info("삭제 건수: "+mapper.delete(1L));
		

	}
	
	@Test
	public void testList() {
		Criteria cri=new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 1051L);
		
		for(ReplyVO reply:replies) {
			  log.info(reply);
		}
	}
	
}
