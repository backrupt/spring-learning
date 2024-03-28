package com.itwill.thymeleaf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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