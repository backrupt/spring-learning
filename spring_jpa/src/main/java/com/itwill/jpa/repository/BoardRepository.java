package com.itwill.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
