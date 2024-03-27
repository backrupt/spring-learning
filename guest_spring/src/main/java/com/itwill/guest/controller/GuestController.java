package com.itwill.guest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;

@Controller
public class GuestController {
	@Autowired
	private GuestService guestService;
	
	public GuestController() {
		System.out.println("### GuestController()생성자");
	}
	
	@GetMapping("/guest_main")
	public String guest_main() {
		return "guest_main";
	}
	@GetMapping("/guest_list")
	public String guest_list(Model model) {
		String forwardPath="";
		try {
			List<Guest> guestList = guestService.guestList();
			model.addAttribute("guestList", guestList);
			forwardPath="guest_list";
		} catch (Exception e) {
			forwardPath="guest_error";
			e.printStackTrace();
		}
		return forwardPath;
	}
	/*
	 * parameter에 guest_no 가 존재하지 않으면
	 */
	@GetMapping(value="/guest_view",params = "!guest_no")
	public String guest_view() {
		return "redirect:guest_main";
	}
	/*
	 * parameter에 guest_no 가 존재하면
	 */
	@GetMapping(value="/guest_view",params="guest_no")
	public String guest_view(@RequestParam(required = false/*,defaultValue = "321"*/) int guest_no,Model model) {
		String forwardPath="";
		try {
			Guest guest = guestService.guestDetail(guest_no);
			model.addAttribute("guest", guest);
			forwardPath="guest_view";
		} catch (Exception e) {
			forwardPath="guest_error";
			e.printStackTrace();
		}
		return forwardPath;
	}
	@GetMapping("/guest_write_form")
	public String guest_write_form() {
		return "guest_write_form";
	}
	@PostMapping("/guest_write_action")
	public String guest_write_action(Guest guest,RedirectAttributes redirectAttributes) {
		String forwardPath="";
		try {
			int guest_no = guestService.guestWrite(guest);
			redirectAttributes.addAttribute("guest_no", guest_no);
			//redirect시 파라메터 넘겨주기
			forwardPath="redirect:guest_view";
		} catch (Exception e) {
			forwardPath="guest_error";
			e.printStackTrace();
		}
		return forwardPath;
	}
	@PostMapping("/guest_modify_form")	
	public String guest_modify_form(@RequestParam int guest_no,Model model) {
		String forwardPath="";
		try {
			Guest guest = guestService.guestDetail(guest_no);
			model.addAttribute("guest", guest);
			forwardPath="guest_modify_form";
		} catch (Exception e) {
			forwardPath="guest_error";
			e.printStackTrace();
		}
		return forwardPath;
	}
	@PostMapping("/guest_modify_action")
	public String guest_modify_action(Guest guest,RedirectAttributes redirectAttributes) {
		String forwardPath="";
		try {
			guestService.guestUpdate(guest);
			redirectAttributes.addAttribute("guest_no", guest.getGuest_no());
			forwardPath="redirect:guest_view";
		} catch (Exception e) {
			forwardPath="guest_error";
			e.printStackTrace();
		}
		return forwardPath;
	}
	@PostMapping("/guest_remove_action")
	public String guest_remove_action(@RequestParam int guest_no) {
		String forwardPath="";
		try {
			guestService.guestDelete(guest_no);
			forwardPath="redirect:guest_list";
		} catch (Exception e) {
			forwardPath="guest_error";
			e.printStackTrace();
		}
		return forwardPath;
	}
	
	@GetMapping(value = {"/guest_remove_action","/guest_modify_action","/guest_write_action","/guest_modify_form"})
	public String guest_get() {
		return "redirect:guest_main";
	}
	/*
	 * <<요청 url(command)>>
		/guest_main		  		-->forward --> guest_main.jsp
		/guest_list		  		-->forward --> guest_list.jsp
		/guest_view		  		-->forward --> guest_view.jsp
		/guest_write_form  		-->forward --> guest_write_form.jsp
		/guest_write_action 	-->redirect--> guest_list
		/guest_modify_form  	-->forward --> guest_modify_form.jsp
		/guest_modify_action	-->redirect--> guest_view
		/guest_remove_action	-->redirect--> guest_list
	 */
}
