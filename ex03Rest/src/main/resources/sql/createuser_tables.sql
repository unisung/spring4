/* connect sys as sysdba */
create user book_ex identified by book_ex
default tablepsace users
temporary tablespace temp
quota unlimited on users;

grant dba to book_ex;

/*  connect book_ex/book_ex 로 접속 후 */

/* 1. 부모테이블(게시글) */ 
create table tbl_board(
  bno number(10,0),
  title varchar2(200) not null,
  content varchar2(2000) not null,
  writer varchar2(50) not null,
  regdate date default sysdate,
  updatedate date default sysdate
  );


alter table  tbl_board add constraint pk_board primary key(bno);
 create sequence seq_board;

insert into tbl_board(bno, title, content, writer)
 values(seq_board.nextval, '테스트 제목'||seq_board.currval,'테스트 내용'||seq_board.currval,'user00');

select * from tbl_board;

insert into tbl_board(bno, title, content, writer)
select seq_board.nextval, title||seq_board.currval,content||seq_board.currval,writer 
from tbl_board;
commit


/* 2. 댓글 테이블 */
create table tbl_reply(
  rno number(10,0),
  bno number(10,0) not null,
  reply varchar2(1000) not null,
  replyer varchar2(50) not null,
  regdate date default sysdate,
  updatedate date default sysdate
);

create sequence seq_reply;

alter table tbl_reply add constraint pk_reply primary key (rno);

alter table tbl_reply add constraint fk_reply_board
foreign key (bno)  references  tbl_board(bno);


select * from tbl_reply order by bno, rno;