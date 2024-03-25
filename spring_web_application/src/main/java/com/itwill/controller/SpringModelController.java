package com.itwill.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SpringModelController {
	
	@GetMapping("/model_request")
	public String request(HttpServletRequest request) {
		request.setAttribute("req", "리정혁씨 안녕하십네까");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_model")
	public String model_model(Model model) {
		System.out.println("###"+model.getClass().getSimpleName());
		model.addAttribute("model", "모델받아라");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_map")
	public String model_map(Map map) {
		map.put("map", "맵받아라");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_modelmap")
	public String model_modelmap(ModelMap modelMap) {
		modelMap.put("modelmap", "모델맵받아라~");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_modelandview")
	public ModelAndView modelAndView(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("modelandview", "모델앤드뷰 받으쇼");
		modelAndView.setViewName("forward:/WEB-INF/views/spring_model.jsp");
		return modelAndView;
	}
	@GetMapping("/model_all")
	public String model_all(HttpServletRequest request,Model model,Map map,ModelMap modelMap) {
		request.setAttribute("req", "req임니당");
		model.addAttribute("model", "model임니당");
		map.put("map", "map임니당");
		modelMap.addAttribute("modelmap", "modelmap임니당");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
}
