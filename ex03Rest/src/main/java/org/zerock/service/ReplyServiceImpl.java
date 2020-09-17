package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    //AutoWired
	 private ReplyMapper mapper;
	 
	@Override
	public int insert(ReplyVO vo) {
		log.info("register...."+vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO read(Long rno) {
		log.info("read...."+rno);
		return mapper.read(rno);
	}

	@Override
	public int delete(Long rno) {
		log.info("delete...."+rno);
		return mapper.delete(rno);
	}

	@Override
	public int update(ReplyVO vo) {
		log.info("update...."+vo);
		return mapper.update(vo);
	}

	@Override
	public ReplyPageDTO getListWithPaging(Criteria cri, Long bno) {
		log.info("getListWithPaging...."+bno);
		return new ReplyPageDTO(mapper.getCountByBno(bno),
				                                   mapper.getListWithPaging(cri, bno));
	}
/*
	@Override
	public List<ReplyVO> getListWithPaging(Criteria cri, Long bno) {
		log.info("getListWithPaging...."+bno);
		return mapper.getListWithPaging(cri, bno);
	}
	
	*/
	

}
