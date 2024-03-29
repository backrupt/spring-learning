package com.itwill.thymeleaf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.thymeleaf.domain.Board;
import com.itwill.thymeleaf.domain.Guest;
import com.itwill.thymeleaf.domain.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloThymeleafController {
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("sUserId","guard");
		model.addAttribute("user",new User("aaaa", "aaaa","에이님", "aaaa@gmail.com"));
		return "index";
	}
	@GetMapping("/board_list")
	public String board_list(Model model) {
		List<Board> boardList=new ArrayList<>();
		boardList.add(new Board(1,"제목1","작성자1","내용1",new Date()));
		boardList.add(new Board(2,"제목2","작성자2","내용2",new Date()));
		boardList.add(new Board(3,"제목3","작성자3","내용3",new Date()));
		boardList.add(new Board(4,"제목4","작성자4","내용4",new Date()));
		boardList.add(new Board(5,"제목5","작성자5","내용5",new Date()));
		boardList.add(new Board(6,"제목6","작성자6","내용6",new Date()));
		boardList.add(new Board(7,"제목7","작성자7","내용7",new Date()));
		boardList.add(new Board(8,"제목8","작성자8","내용8",new Date()));
		boardList.add(new Board(9,"제목9","작성자9","내용9",new Date()));
		boardList.add(new Board(10,"제목10","작성자10","내용10",new Date()));
		model.addAttribute("boardList", boardList);
		return "board_list";
	}
	
	
	
	@RequestMapping("/hello_thymeleaf")
	public String hello(HttpServletRequest request) {
		/************String,Wrapper속성객체*************/
		request.setAttribute("a", new String("String속성객체 developer"));
		request.setAttribute("b", new Integer(2));
		request.setAttribute("c", new Boolean(true));
		request.setAttribute("d", "String속성객체 developer");
		request.setAttribute("e", 3);
		request.setAttribute("f",false);
		/***********자바빈 속성객체**********************/
		request.setAttribute("guest", new Guest(1,"KIM","2023/10/02","guard@gmail.com","http://www.gmail.com","타이틀","컨텐트"));
		request.setAttribute("user", new User("guard","1111","가아드","guard@gmail.com"));
		/***********List(array)속성객체*****************/
		List<Guest> guestList=new ArrayList<Guest>();
		guestList.add(new Guest(1,"KIM","2023/10/02","guard1@gmail.com","http://www.gmail.com","타이틀1","컨텐트1"));
		guestList.add(new Guest(2,"GIM","2023/10/03","guard2@gmail.com","http://www.gmail.com","타이틀2","컨텐트2"));
		guestList.add(new Guest(3,"FIM","2023/10/04","guard3@gmail.com","http://www.gmail.com","타이틀3","컨텐트3"));
		request.setAttribute("guestList", guestList);
		/***********Map 속성객체************************/
		Map<String,User> userMap=new HashMap<String,User>();
		userMap.put("guard1", new User("guard1","1111","가드1","guard1@gmail.com"));
		userMap.put("guard2", new User("guard2","2222","가드2","guard2@gmail.com"));
		userMap.put("guard3", new User("guard3","3333","가드3","guard3@gmail.com"));
		request.setAttribute("userMap", userMap);
	
		
		
		return "hello_thymeleaf";
	}
}