
/* tbl_board에 댓글 갯수 저장 칼럼 추가 */
alter table tbl_board add(replycnt number default 0);

alter table tbl_board add(readcnt number default 0);
alter table tbl_board rename column readcnt to readcount;

/* 좋아요/싫어요 칼럼 추가 */
alter table tbl_board add(good number default 0);
alter table tbl_board add(bad number default 0);

/* 댓글 건수 저장*/
update tbl_board set replycnt =(select count(rno) from tbl_reply where tbl_reply.bno=tbl_board.bno);
commit

/* 댓글이 있는 게시글 리스트 */
select * from tbl_board where replycnt >0;

select * from tbl_board, tbl_reply where tbl_board.bno=tbl_reply.bno;

select /*+index_desc(tbl_board pk_board) */ 
  		          rownum rn, bno, title, content, writer, regdate,updateDate,replycnt,readcount
  		  from tbl_board
  		
  		  update tbl_board set readcount=readcount+1 where bno=910;
  		  
  		  
  		  
  	select bno, title, content, writer, regdate,updateDate,replycnt,readcount,good,bad
        from
      (
  		 select /*+index_desc(tbl_board pk_board) */ 
  		          rownum rn, bno, title, content, writer, regdate,updateDate,replycnt,readcount,good,bad
  		  from tbl_board
  		where  	 	rownum<=10
  		)
  		where rn > 0
  		;
  		
  		
 select * from tbl_board where bno=910;