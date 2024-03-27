package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.dto.Guest;

@Controller
public class RequestParamModelAttributeController {
	@GetMapping("/parameter_guest_write_form")
	public String parameter_guest_write_form() {
		return "forward:/WEB-INF/views/guest_write_form.jsp";
	}
	@PostMapping("/parameter_guest_write_action")
	public String parameter_guest_write_action(
			@RequestParam(name="guest_name") String name,
			@RequestParam(name="guest_email")String guest_email,
			@RequestParam String guest_homepage,
			@RequestParam(name="guest_title") String guest_title,
			@RequestParam(name="guest_content") String guest_content
			) {
		
/*
		
		<< 방명록쓰기 >>
			    @RequestParam String name,
				@RequestParam(name="guest_email") String guest_email,
				@RequestParam(name="guest_homepage") String guest_homepage,
				@RequestParam(name="guest_title") String guest_title,
				@RequestParam(name="guest_content") String guest_content
		   
			    String name = request.getParameter("guest_name");  
				String guest_email = request.getParameter("guest_email");
				String guest_homepage = request.getParameter("guest_homepage");
				String guest_title = request.getParameter("guest_title");
				String guest_content = request.getParameter("guest_content");
		 */
		Guest writeGuest=Guest.builder()
									.guest_content(guest_content)
									.guest_email(guest_email)
									.guest_homepage(guest_homepage)
									.guest_title(guest_title)
									.guest_name(name)
									.build();
		/*
		 * GuestService 메소드 호출
		 */
		System.out.println("### @RequestParam ###");
		System.out.println("### writeGuest:"+writeGuest);
		return "forward:/WEB-INF/views/guest_write_result.jsp";
	}
	@PostMapping("/model_attribute_guest_write_action")
	public String model_attribute_guest_write_action(@ModelAttribute Guest writeGuest ) { //ModelAttribute 어노테이션 생략가능
		/*
		 << 방명록쓰기 >>
		   1. Guest guest=new Guest();
		 
		   2. String guest_name = request.getParameter("guest_name");
			  String guest_email = request.getParameter("guest_email");
			  String guest_homepage = request.getParameter("guest_homepage");
			  String guest_title = request.getParameter("guest_title");
			  String guest_content = request.getParameter("guest_content");
		   
		   3. guest.setGuest_name(guest_name);
		      guest.setGuest_email(guest_email);
		      guest.setGuest_homepage(guest_homepage);
		      guest.setGuest_title(guest_title);
		      guest.setGuest_content(guest_content);
		   
		   4. request.setAttribute("guest",guest);    
		 */
		/*
		   1. 인자로선언된DTO객체[Guest]생성(기본생성자)
		   2. 모든 파라메타 받기
		   3. 받은파라메타 데이타를 파라메타이름과 일치하는
		      Guest객체의 속성메쏘드(setter method)호출해서대입
		   4. Guest클래스이름 첫글자를 소문자로변경한이름(guest)으로
		      request객체에 속성(attribute)등록
		      request.setAttribute("guest",guest);
		 */
		System.out.println("### writeGuest:"+writeGuest);
		return "forward:/WEB-INF/views/guest_write_result.jsp";
	}
}
