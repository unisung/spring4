/* sample 테이블 생성 */
create table tbl_sample1(col1 varchar2(500));
create table tbl_sample2(col1 varchar2(50));

select * from tbl_sample1
union all
select * from tbl_sample2;

delete from tbl_sample1;
delete from tbl_sample2;
commit