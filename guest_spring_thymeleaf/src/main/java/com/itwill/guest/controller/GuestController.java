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
			List<Guest> guestList= guestService.guestList();
			model.addAttribute("guestList", guestList);
			forwardPath="guest_list";
		} catch (Exception e) {
			forwardPath="guest_error";
			e.printStackTrace();
		}
		
		return forwardPath;
	}
	/*
	 * parameter 에 guest_no 가존재하지않으면
	 */
	@GetMapping(value ="/guest_view",params = "!guest_no")
	public String guest_view() {
		return "redirect:guest_main";
	}
	/*
	 * parameter 에 guest_no 가존재하면
	 */
	@GetMapping(value="/guest_view",params = "guest_no")
	public String guest_view(@RequestParam(name = "guest_no",required = false,defaultValue = "321" ) int guest_no ,Model model) {
		String forwardPath="";
		try {
			Guest guest= guestService.guestDetail(guest_no);
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
	public String guest_write_action(@RequestParam(name="guest_name") String guest_name,
									@RequestParam(name="guest_email") String guest_email,
									@RequestParam(name="guest_homepage") String guest_homepage,
									@RequestParam(name="guest_title") String guest_title,
									@RequestParam(name="guest_content") String guest_content,
									RedirectAttributes redirectAttributes
									) {
		try {
			int guest_no=guestService.guestWrite(new Guest(0, guest_name, null, guest_email, guest_homepage, guest_title, guest_content));
			redirectAttributes.addAttribute("guest_no", guest_no);
			return "redirect:guest_view";
		} catch (Exception e) {
			e.printStackTrace();
			return "guest_error";
		}
	}
	
	@PostMapping("/guest_modify_form")
	public String guest_modify_form(@RequestParam(name="guest_no") int guest_no,Model model) {
		try {
			Guest guest=guestService.guestDetail(guest_no);
			model.addAttribute("guest", guest);
			return "guest_modify_form";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "guest_error";
		}
		
		
	}
	
	@PostMapping("/guest_modify_action")
	public String guest_modify_action(
			@RequestParam(name="guest_no") int guest_no,
			@RequestParam(name="guest_name") String guest_name,
			@RequestParam(name="guest_email") String guest_email,
			@RequestParam(name="guest_homepage") String guest_homepage,
			@RequestParam(name="guest_title") String guest_title,
			@RequestParam(name="guest_content") String guest_content,
			RedirectAttributes redirectAttributes) {
		Guest guest=new Guest(guest_no, guest_name, null, guest_email, guest_homepage, guest_title, guest_content);
		try {
			guestService.guestUpdate(guest);
			redirectAttributes.addAttribute("guest_no", guest_no);
			return  "redirect:guest_view";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "guest_error";
		}
	}
	@PostMapping("/guest_remove_action")
	public String guest_remove_action(@RequestParam(name="guest_no") int guest_no) {
		try {
			guestService.guestDelete(guest_no);
			return "redirect:guest_list";
		} catch (Exception e) {
			e.printStackTrace();
			return "forward:/WEB-INF/views/guest_error.jsp";
		}
	}
	
	
	
	@GetMapping(value = {"/guest_write_action","/guest_modify_form","/guest_modify_action","/guest_remove_action"})
	public String guest_get() {
		return "redirect:guest_main";
	}
	
	/*
	<<요청 url(command)>>
	/guest_main			 --forward --> guest_main.jsp
	/guest_list			 --forward --> guest_list.jsp
	/guest_view			 --forward --> guest_view.jsp
	/guest_write_form	 --forward --> guest_write_form.jsp
	/guest_write_action  --redirect--> guest_list
	/guest_modify_form	 --forward --> guest_modify_form.jsp
	/guest_modify_action --redirect--> guest_view
	/guest_remove_action --redirect--> guest_list
	 */
	
}
