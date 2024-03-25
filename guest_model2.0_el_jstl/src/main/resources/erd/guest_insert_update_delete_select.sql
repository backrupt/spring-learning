--guest
/*
이름             널?       유형             
-------------- -------- -------------- 
GUEST_NO       NOT NULL NUMBER(10)     
GUEST_NAME              VARCHAR2(50)   
GUEST_DATE              DATE           
GUEST_EMAIL             VARCHAR2(50)   
GUEST_HOMEPAGE          VARCHAR2(50)   
GUEST_TITLE             VARCHAR2(100)  
GUEST_CONTENT           VARCHAR2(4000) 
*/
desc guest;

--insert

insert into guest values(guest_guest_no_seq.nextval,'김경호',sysdate,'guard1@gmail.com','http://www.itwill.co.kr','방명록사용법1','방명록처럼 사용하시면됩니다1.');
insert into guest values(guest_guest_no_seq.nextval,'정경호',sysdate,'guard2@gmail.com','http://www.itwill.co.kr','방명록사용법2','방명록처럼 사용하시면됩니다2.');
insert into guest values(guest_guest_no_seq.nextval,'심경호',sysdate,'guard3@gmail.com','http://www.itwill.co.kr','방명록사용법3','방명록처럼 사용하시면됩니다3.');
insert into guest values(guest_guest_no_seq.nextval,'김경호',sysdate,'guard4@gmail.com','http://www.itwill.co.kr','방명록사용법4','방명록처럼 사용하시면됩니다4.');
--pk update
update guest set guest_name='이름수정',guest_date=sysdate,guest_email='change@gmail.com',guest_homepage='http://www.change.com',guest_title='타이틀변경',guest_content='내용변경' where guest_no=1;

--pk delete 
delete from guest where guest_no=1;
--pk select
select * from guest where guest_no=2;
--name select
select * from guest where guest_name='김경호';
--select * from guest where guest_name like '%'||'경호'||'%';
--all select
select * from guest;



