package com.itwill.guest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
 * 1. GET /guests 				: 방명록 리스트 	(모든데이터요청)
 * 2. GET /guests/{guest_no} 	: 방명록 상세보기	(guest_no데이터를요청)
 * 3. POST /guests 				: 방명록 쓰기		(데이터추가)
 * 4. PUT /guests/{guest_no} 	: 방명록 수정		(guest_no데이터를수정)
 * 5. DELETE /guests/{guest_no}	: 방명록 삭제		(guest_no데이터를삭제)
 * 6. GET /guests 		: 방명록 리스트
 */

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "*")
@RestController
public class GuestRestController {
	@Autowired
	private GuestService guestService;
	
	@Operation(summary = "방명록리스트",description = "방명록전체를조회합니다.")
	@GetMapping(value = "/guests",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String,Object> guest_list() throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		int status = 1;
		String msg = "성공";
		List<Guest> data = new ArrayList<>();
		
		data = guestService.guestList();
		
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data); 
		
		return resultMap;
	}
	
	@Operation(summary = "방명록상세보기",description = "방명록하나를조회합니다.")
	@Parameter(name="guest_no",description = "방명록번호")
	@GetMapping(value = "/guests/{guest_no}",produces = "application/json;charset=UTF-8")
	public Map<String,Object> guest_detail(@PathVariable(name="guest_no") int guest_no) throws Exception{ 
		Map<String,Object> resultMap = new HashMap<>();
		int status = 1;
		String msg = "성공";
		List<Guest> data = new ArrayList<>();
		
		Guest guest = guestService.guestDetail(guest_no);
		if(guest!=null) {
			data.add(guest);
		}else {
			status=2;
			msg="게시물이 존재하지않습니다.";
		}
		
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data); 
		return resultMap;
	}
	
	@Operation(summary = "방명록쓰기",description = "방명록하나를씁니다.")
	@PostMapping(value = "/guests",produces = "application/json;charset=UTF-8")
	public Map<String,Object> guest_write_action(@RequestBody Guest guest){ //form방식이면 @ModelAttribute 
		System.out.println(">>guest:"+guest);
		Map<String,Object> resultMap = new HashMap<>();
		int status = 1;
		String msg = "성공";
		List<Guest> data = new ArrayList<>();
		
		try {
			int insert_guest_no = guestService.guestWrite(guest);
			Guest newGuest = guestService.guestDetail(insert_guest_no);
			data.add(newGuest);
		} catch (Exception e) {
			e.printStackTrace();
			status=2;
			msg="방명록쓰기 실패";	
		}
		
		
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data); 
		return resultMap;
	}
	
	@Operation(summary = "방명록수정",description = "전송되는아이디에해당하는게시물수정.")
	@Parameter(name="guest_no",description = "방명록번호")
	@PutMapping(value = "/guests/{guest_no}",produces = "application/json;charset=UTF-8")
	public Map<String,Object> guest_modify_action(@PathVariable(name="guest_no") int guest_no,@RequestBody Guest guest){ //form방식이면 @ModelAttribute 
		System.out.println(">>guest_no:"+guest_no);
		System.out.println(">>guest:"+guest);
		Map<String,Object> resultMap = new HashMap<>();
		int status = 1;
		String msg = "성공";
		List<Guest> data = new ArrayList<>();
		
		try {
			guest.setGuestNo(guest_no);
			guestService.guestUpdate(guest);
			status=1;
			msg="수정성공";
			data.add(guest);
		} catch (Exception e) {
			e.printStackTrace();
			status=2;
			msg="방명록 수정 실패";	
		}
		
		
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data); 
		return resultMap;
	}
	
	@Operation(summary = "방명록삭제",description = "방명록하나를삭제합니다.")
	@DeleteMapping(value = "/guests/{guest_no}",produces = "application/json;charset=UTF-8")
	public Map<String,Object> guest_remove_action(@PathVariable(name="guest_no") int guest_no){ //form방식이면 @ModelAttribute 
		Map<String,Object> resultMap = new HashMap<>();
		int status = 1;
		String msg = "성공";
		List<Guest> data = new ArrayList<>();
		
		try {
			guestService.guestDelete(guest_no);
			status=1;
			msg="삭제 성공!";
		} catch (Exception e) {
			e.printStackTrace();
			status=2;
			msg="방명록삭제 실패";	
		}
		
		
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data); 
		return resultMap;
	}
	
	//@ExceptionHandler(Exception.class)
	public Map exceptionHandler(Exception e) {
		Map<String,Object> resultMap = new HashMap<>();
		
		int status = 3;
		String msg ="알수없는예외";
		List<Guest> data = new ArrayList<>();
		
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data); 
		return resultMap;
	}
}
