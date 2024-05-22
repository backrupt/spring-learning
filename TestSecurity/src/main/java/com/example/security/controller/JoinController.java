package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.security.dto.JoinDTO;
import com.example.security.service.JoinService;

@Controller
public class JoinController {
	
	@Autowired
	JoinService joinService;
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/joinProc")
	public String joinProcess(JoinDTO joinDTO) {
		
		System.out.println(joinDTO.getUsername());
		joinService.joinProcess(joinDTO);
		
		return "redirect:/login";
	}
}
