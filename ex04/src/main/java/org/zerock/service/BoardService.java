package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	//글 입력
  public void register(BoardVO vo);
  
  //글 조회
  public BoardVO get(Long bno);
  
  //글 수정
  public boolean modify(BoardVO vo);
  
  //글 삭제
  public boolean remove(Long bno);
  
  //글 목록 조회
  public List<BoardVO> getList();

  public List<BoardVO> getListWithPaging(Criteria cri);
 
  //글 전체 건수 조회
  public int getTotal(Criteria cri);

}
