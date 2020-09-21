package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

      //Spring 4.3이상 에서 자동 D.I
	private BoardMapper mapper;
	
	//새글 등록
	@Override
	public void register(BoardVO vo) {
		log.info("register....."+vo);
		
		//매퍼메소드 호출
		mapper.insertSelectKey(vo);
	}

	//글 내용 보기
	@Transactional
	@Override
	public BoardVO get(Long bno) {
		log.info("get....." +bno);
		
         mapper.updateReadCount(bno);
		//매퍼 메소드 호출 및 값 리턴
		return mapper.read(bno);
	}

	//글 내용 수정
	@Override
	public boolean modify(BoardVO vo) {
		log.info("modify....." + vo);
		
		//매퍼 메소드 호출 및 값 리턴
		return mapper.update(vo)==1;
	}

	//글 삭제
	@Override
	public boolean remove(Long bno) {
	   log.info("remove....."+bno);
		
	   //매퍼 메소드 호출 및 값 리턴
		return mapper.delete(bno)==1;
	}

	//글 목록 조회
	@Override
	public List<BoardVO> getList() {
		log.info("getList....");
		
		//매퍼 메소드 호출 및 값 리턴
		return mapper.getList();
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		log.info("getListWithPaging...");
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
	   log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public int updateGoodCount(Long bno) {
		log.info("good count update...");
		return mapper.updateGoodCount(bno);
	}

	@Override
	public int updateBadCount(Long bno) {
		log.info("bad count update...");
		return mapper.updateBadCount(bno);
	}

	@Override
	public BoardVO getGoodBadCnt(Long bno) {
		log.info("getGoodBadCnt...");
		//매퍼 메소드 호출 및 값 리턴
		return mapper.read(bno);
	}

	


}
