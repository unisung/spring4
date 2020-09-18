
/* tbl_board에 댓글 갯수 저장 칼럼 추가 */
alter table tbl_board add(replycnt number default 0);

/* 댓글 건수 저장*/
update tbl_board set replycnt =(select count(rno) from tbl_reply where tbl_reply.bno=tbl_board.bno);
commit

/* 댓글이 있는 게시글 리스트 */
select * from tbl_board where replycnt >0;

select * from tbl_board, tbl_reply where tbl_board.bno=tbl_reply.bno;

