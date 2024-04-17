package com.itwill.jpa.relation.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.relation.entity.Course;
import com.itwill.jpa.relation.entity.CourseEnrollment;

import jakarta.transaction.Transactional;

@Rollback(false)
class CourseRepositoryTest extends SpringJpaApplicationTests{
	
	
	@Autowired
	CourseRepository courseRepository;
	
	@Test
	@Transactional
	void selectCourseWithCourseEnrollments() {
		Course course1 = courseRepository.findById(1L).get();
		System.out.println(">>>"+course1);
		List<CourseEnrollment> courseEnrollments = course1.getCourseEnrollments();
		for(CourseEnrollment courseEnrollment:courseEnrollments) {
			System.out.println(">>>"+courseEnrollment.getStudent());
			
		}
	}
	

}
