package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itwill.exception.BusinessException1;
import com.itwill.exception.BusinessException2;

@Controller
public class SpringLocalExceptionController {
	@GetMapping("/business1")
	public String business_method1() throws BusinessException1 {
		boolean b=true;
		if(b) {
			throw new BusinessException1("업무예외1");
		}
		return "business_result1";
	}
	@GetMapping("/business2")
	public String business_method2() throws Exception {
		boolean b=true;
		if(b) {
			throw new BusinessException2("업무예외2");
		}
		return "business_result2";
	}
	@GetMapping("/business3")
	public String business_method3() {
		String name=null;
		int length=name.length();
		return "business_result3";
	}
	/************예외처리*************/
	@ExceptionHandler(BusinessException1.class)
	public String handle_business_exception1(BusinessException1 e,Model model) {
		model.addAttribute("error_msg", e.getMessage());
		return "business_error_result";
	}
	@ExceptionHandler(BusinessException2.class)
	public ModelAndView handle_business_exception2(BusinessException2 e,Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("business_error_result");
		mv.addObject("error_msg",e.getMessage());
		return mv;
	}
	/*
	@ExceptionHandler(RuntimeException.class)
	public String handle_exception(Exception e,Model model) {
		model.addAttribute("error_msg", e.getMessage());
		return "business_error_result";
	} 로컬이기때문에 다른 컨트롤러 클래스에선 사용 불가해서 이렇게 안씀
	*/
}
