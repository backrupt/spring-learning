package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;

import com.itwill.view.CustomView1;
import com.itwill.view.CustomView2;
import com.itwill.view.CustomView3;

@Controller
public class SpringReturnViewObjectController {
	@GetMapping("/view1")
	public View returnView1(Model model) {
		model.addAttribute("msg", "영광영광할렐루루루루룰루야");
		CustomView1 customView1 = new CustomView1();
		return customView1;
	}
	@GetMapping("/view2")
	public View returnView2() {
		CustomView2 customView2 = new CustomView2();
		customView2.setForwardPath("/xxxx");
		return customView2;
	}
	@GetMapping("/view3")
	public View returnView3() {
		CustomView3 customView3 = new CustomView3();
		customView3.setRedirectPath("/yyyy");
		return customView3;
	}

}
