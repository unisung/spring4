package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	//입력
	public void insert(BoardVO vo);
	
	//sequence키를 이용한 입력
	public void insertSelectKey(BoardVO vo);
	
	//글 내용보기
	public BoardVO read(Long bno);
	
	//삭제처리
	public int delete(Long bno);
	
	//수정처리
	public int update(BoardVO vo);

	//리스트 
	public List<BoardVO> getListWithPaging(Criteria cri);

	//전체 조회 건수 
	public int getTotalCount(Criteria cri);
	
	//댓글 갯수 수정
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

	//조회 건수 증가
	public void updateReadCount(Long bno);

	//좋아요
	public int updateGoodCount(Long bno);
    //싫어요
	public int updateBadCount(Long bno);
}
