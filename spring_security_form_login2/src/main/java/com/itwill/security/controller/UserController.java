package com.itwill.security.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*

@PreAuthorize("hasRole('USER')") == @Secured("ROLE_USER")
@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')") == @Secured({"ROLE_USER","ROLE_ADMIN"})
@PreAuthorize("hasAnyAuthority('ROLE_USER')")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')" OR hasAnyAuthority('ROLE_USER')") == @Secured({"ROLE_USER","ROLE_ADMIN"})
*/
@Controller
public class UserController {
	
	@GetMapping("/")
	public String main( Authentication authentication,Principal principal) {
		System.out.println(authentication);
		System.out.println(principal);
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login-form";
	}
	@GetMapping("/login-error")
	public String loginError() {
		return "login";
	}
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/user-page")
	public String userPage() {
		return "user-page";
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/admin-page")
	public String adminPage(){
		return "admin-page";
	}
	
	@ResponseBody
	@GetMapping("/authentication")
	public Authentication authentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	
	
	
}
