package com.itwill.jpa.relation.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.relation.entity.Address;
import com.itwill.jpa.relation.entity.Course;
import com.itwill.jpa.relation.entity.CourseEnrollment;
import com.itwill.jpa.relation.entity.Student;
import com.itwill.jpa.relation.entity.Tutor;

class CourseEnrollmentRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	CourseEnrollmentRepository courseEnrollmentRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void saveCourseEnrollmentWithStudentAndCourse1() {
		Student student9 = studentRepository.findById(8L).get();
		Course course = courseRepository.findById(6L).get();
		CourseEnrollment courseEnrollment = CourseEnrollment.builder()
				.student(student9)
				.course(course)
				.build();
		CourseEnrollment courseEnrollment2 = CourseEnrollment.builder()
				.student(student9)
				.course(course)
				.build();
		courseEnrollmentRepository.save(courseEnrollment);
		//courseEnrollmentRepository.save(courseEnrollment2);
	
		
		
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void saveCourseEnrollmentWithStudentAndCourse2() {
			
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void selectCourseEnrollmentWithStudentAndCourse() {
		CourseEnrollment courseEnrollment = courseEnrollmentRepository.findById(1L).get();
		System.out.println(">>>"+courseEnrollment);
		System.out.println(">>>Student:"+courseEnrollment.getStudent());
		System.out.println(">>>Course:"+courseEnrollment.getCourse());
	}
}










