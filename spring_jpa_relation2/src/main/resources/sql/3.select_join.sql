select dob from students;
select name from students where stud_id=1;
select STUDENTS_STUD_ID_SEQ.currval from dual;
/*
students + addresses[inner join]
*/
select stud_id, name, email,phone, a.addr_id, street, city, state, zip, country
FROM students s 
inner join addresses a 
on s.addr_id=a.addr_id;

/*
students + addresses [left outer join]
*/
select stud_id, name, email,phone, a.addr_id, street, city, state, zip, country
FROM students s 
left outer join addresses a 
on s.addr_id=a.addr_id;
     
/*
students + addresses [right outer join]
*/    
select stud_id, name, email,phone, a.addr_id, street, city, state, zip, country
  		FROM students s 
      right outer join addresses a 
      on s.addr_id=a.addr_id;   
 
/*
students + addresses [full outer join]
*/    
select stud_id, name, email,phone, a.addr_id, street, city, state, zip, country
  		FROM students s 
      full outer join addresses a 
      on s.addr_id=a.addr_id;   

--findStudentByIdWithAddress--
/*
stdents + addresses join
*/
select stud_id, name, email,phone, a.addr_id, street, city, state, zip, country
  		FROM students s 
      left outer join addresses a 
      on s.addr_id=a.addr_id
      where s.stud_id=1;
 
--findStudentByIdWithCourses 
/*
students + courses  inner join
*/     
select * from 
students s inner join 
course_enrollment ce
on s.stud_id=ce.stud_id 
join courses c
on ce.course_id = c.course_id
where s.stud_id=7;

/*
students + courses outer join
*/
select  s.stud_id,s.name ,s.email,s.phone,s.dob ,
        c.course_id,c.name ,c.description,c.start_date,c.end_date
		from students s 
		left outer join course_enrollment ce
		on s.stud_id = ce.stud_id
		left outer join courses c
		on ce.course_id=c.course_id where s.stud_id=1;
       
----findCourseByIdWithStudents 
select c.*,s.* from courses c 
		join course_enrollment ce on c.course_id=ce.course_id
		join students s on ce.stud_id=s.stud_id
		where c.course_id=1;


       
/*
tutor + courses join
*/
/*
inner join
*/
SELECT t.tutor_id, t.name as tutor_name, email,course_id, c.name, description, start_date, end_date
      FROM tutors t 
      join courses c 
      on t.tutor_id=c.tutor_id; 
      
/*
outer join
*/      
SELECT t.tutor_id, t.name as tutor_name, email,course_id, c.name, description, start_date, end_date
      FROM tutors t 
      left outer join courses c 
      on t.tutor_id=c.tutor_id;
      
      
           
SELECT t.tutor_id, t.name as tutor_name, email,course_id, c.name, description, start_date, end_date
      FROM tutors t 
      left outer join courses c 
      on t.tutor_id=c.tutor_id
      where t.tutor_id=1;  
      
/*
course + tutor join
*/
SELECT course_id, c.name, c.description, c.start_date, c.end_date,t.tutor_id, t.name as tutor_name, t.email
      FROM tutors t 
      left outer join courses c 
      on t.tutor_id=c.tutor_id
      where c.course_id=1; 

/*
 addresses + students join [1:N]
 */      
select a.addr_id,street,city,state,zip,country,stud_id,name,email,dob 
		from addresses a left outer join students s 
		on a.addr_id=s.addr_id 
		where a.addr_id=4;
/*
 addresses + tutors join [1:N]
 */      
select a.addr_id,street,city,state,zip,country,tutor_id,name,email,phone 
		from addresses a left outer join tutors t 
		on a.addr_id=t.addr_id 
		where a.addr_id=1;        
/*
tutors + addresses + courses 
*/
SELECT t.tutor_id, t.name as tutor_name, email, a.addr_id, street, city, state, zip, country,
       			course_id, c.name, description, start_date, end_date
      FROM tutors t 
      left outer join addresses a 
      on t.addr_id=a.addr_id
	  left outer join courses c on t.tutor_id=c.tutor_id
where t.tutor_id=1;
        
  
  

      
      
-- Courses검색      
SELECT * FROM COURSES
WHERE TUTOR_ID = 1
AND NAME LIKE '%Quick%'
AND START_DATE >= TO_DATE('2015/05/01');
      
      
      
      
      
      
      
      