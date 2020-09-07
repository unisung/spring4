/* 시퀀스 생성*/
create sequence seq_board;
 
/* 테이블 생성 */
create table tbl_board (
  bno number(10,0),
  title varchar2(200) not null,
  content varchar2(2000) not null,
  writer varchar2(50) not null,
  regdate date default sysdate, 
  updatedate date default sysdate
);
 
/* 기본키 생성 */
alter table tbl_board add constraint pk_board primary key (bno);

/* 데이타 입력 */
insert into tbl_board (bno, title, content, writer) 
values (seq_board.nextval, '테스트 제목'||seq_board.currval,'테스트 내용'||seq_board.currval,'user00');

select * from tbl_board;
