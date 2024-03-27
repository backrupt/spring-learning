package com.itwill.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwill.user.User;
import com.itwill.user.UserService;
import com.itwill.user.exception.ExistedUserException;
import com.itwill.user.exception.PasswordMismatchException;
import com.itwill.user.exception.UserNotFoundException;

import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;
/*
  /user_main 
  /user_write_form 
  /user_write_action 
  /user_login_form
  /user_login_action 
  /user_logout_action 
  /user_view 
  /user_modify_form
  /user_modify_action 
  /user_remove_action
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/user_main")
	public String user_main_g() {
		return "user_main";
	}
	@PostMapping("/user_main")
	public String user_main_p() {
		return "user_main";
	}
	@GetMapping("/user_write_form")
	public String user_write_form() {
		return "user_write_form";
	}
	@PostMapping("/user_write_action")
	public String user_write_action(User user,Model model) throws Exception {
		int result = userService.create(user);
		if(result == -1) {
			//아이디 중복
			model.addAttribute("msg",user.getUserId()+" 는 이미 존재하는 아이디입니다.");
			model.addAttribute("fuser", user);
			return "user_write_form";
		} else if(result == 1) {
			//가입 성공
			return "redirect:user_login_form";
		}
		return "user_login_form";
	}
	@GetMapping("/user_login_form")
	public String user_login_form() {
		return "user_login_form";
	}
	@PostMapping("/user_login_action")
	public String user_login_action(User user,Model model,HttpSession session)  throws Exception{
		int result = userService.login(user.getUserId(), user.getPassword());
		if(result == 0) {
			//0 : 아이디 존재 안함
			model.addAttribute("msg1", user.getUserId()+" 는 존재하지않는 아이디입니다.");
			model.addAttribute("fuser", user);
			return "user_login_form";
		} else if(result == 1) {
			//1 : 패스워드 불일치
			model.addAttribute("msg2", "패스워드가 일치하지 않습니다.");
			model.addAttribute("fuser", user);
			return "user_login_form";
		} else if(result == 2) {
			//2 : 로그인성공(세션)
			session.setAttribute("sUserId", user.getUserId());
			return "redirect:user_main";
		}
		return "user_main";
	}
	@GetMapping("/user_view")
	public String user_view(HttpSession session,Model model) throws Exception{
		/*******login check******/
		String sUserId = (String)session.getAttribute("sUserId");
		if(sUserId==null) {
			return "redirect:user_login_form";
		}
		User user = userService.findUser(sUserId);
		model.addAttribute("loginUser", user);
		return "user_view";
	}
	@PostMapping("/user_modify_form")
	public String user_modify_form(HttpSession session,User user,Model model) throws Exception{
		/*******login check******/
		String sUserId = (String)session.getAttribute("sUserId");
		if(sUserId==null) {
			return "redirect:user_login_form";
		}
		User tempuser = userService.findUser(sUserId);
		model.addAttribute("loginUser", tempuser);
		return "user_modify_form";
	}
	
	@PostMapping("/user_modify_action")
	public String user_modify_action(HttpSession session,User user) throws Exception{
		/*******login check******/
		String sUserId = (String)session.getAttribute("sUserId");
		if(sUserId==null) {
			return "redirect:user_login_form";
		}
		user.setUserId(sUserId);
		userService.update(user);
		return "redirect:user_view";
	}
	
	@PostMapping("/user_remove_action")
	public String user_remove_action(HttpSession session)throws Exception {
		/*******login check******/
		String sUserId = (String)session.getAttribute("sUserId");
		if(sUserId==null) {
			return "redirect:user_login_form";
		}
		userService.remove(sUserId);
		session.invalidate();
		return "redirect:user_main";
	}
	
	@GetMapping("/user_logout_action")
	public String user_logout_action(HttpSession session) {
		/*******login check******/
		String sUserId = (String)session.getAttribute("sUserId");
		if(sUserId==null) {
			return "redirect:user_login_form";
		}
		session.invalidate();
		return "redirect:user_main";
	}
	
	
	
	
}















