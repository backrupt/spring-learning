package com.itwill.jpa.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.relation.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
