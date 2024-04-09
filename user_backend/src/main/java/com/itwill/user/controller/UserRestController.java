package com.itwill.user.controller;

import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.user.User;
import com.itwill.user.UserService;
import com.itwill.user.exception.ExistedUserException;
import com.itwill.user.exception.PasswordMismatchException;
import com.itwill.user.exception.UserNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

/*
POST 	/user/login 		- login  user 
GET   	/user/logout		- logout user 
POST 	/user 				- create user 
PUT 	/user/{id} 			- modify user by {id}
GET 	/user/{id} 			- GETs the details of the user with {id}
DELETE 	/user/{id} 			- Delete the user with id 
*/

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@Operation(summary = "회원가입")
	@PostMapping
	public ResponseEntity<Response> user_write_action(@RequestBody User user) throws Exception{
		userService.create(user);
		Response response=new Response();
		response.setStatus(ResponseStatusCode.CREATED_USER);
		response.setMessage(ResponseMessage.CREATED_USER);
		response.setData(user);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity= 
				new ResponseEntity<Response>(response,httpHeaders,HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@Operation(summary = "회원수정")
	@LoginCheck
	@PutMapping("/{userId}")
	public ResponseEntity<Response> user_modify_action(@RequestBody User user,@PathVariable(name = "userId") String userId) throws Exception{
		user.setUserId(userId);
		userService.update(user);		
		Response response=new Response();
		response.setStatus(ResponseStatusCode.UPDATE_USER);
		response.setMessage(ResponseMessage.UPDATE_USER);
		response.setData(user);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity= 
				new ResponseEntity<Response>(response,httpHeaders,HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@Operation(summary = "회원탈퇴")
	@LoginCheck
	@DeleteMapping("/{userId}")
	public ResponseEntity<Response> user_delete_action(@PathVariable(name = "userId") String userId,HttpSession session) throws Exception{
		userService.remove(userId);
		session.invalidate();
		
		Response response=new Response();
		response.setStatus(ResponseStatusCode.DELETE_USER);
		response.setMessage(ResponseMessage.DELETE_USER);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity= 
				new ResponseEntity<Response>(response,httpHeaders,HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@Operation(summary = "회원로그인")
	@PostMapping("/login")
	public ResponseEntity<Response> user_login_action(@RequestBody User user,HttpSession session) throws Exception{
		userService.login(user.getUserId(), user.getPassword());
		session.setAttribute("sUserId",user.getUserId());
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.LOGIN_SUCCESS);
		response.setMessage(ResponseMessage.LOGIN_SUCCESS);
		response.setData(user);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
	}
	
	@Operation(summary = "회원로그아웃")
	@LoginCheck
	@PostMapping("/logout")
	public ResponseEntity<Response> user_logout_action(HttpSession session) throws Exception{
		session.invalidate();
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.LOGOUT_USER);
		response.setMessage(ResponseMessage.LOGOUT_USER);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
	}
	
	@Operation(summary = "회원정보보기")
	@LoginCheck
	@GetMapping("/{userId}")
	public ResponseEntity<Response> user_view(@PathVariable(name = "userId") String userId) throws Exception{
		User loginUser = userService.findUser(userId);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_USER);
		response.setMessage(ResponseMessage.READ_USER);
		response.setData(loginUser);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
	}
	
	@ExceptionHandler(value = ExistedUserException.class)
	public ResponseEntity<Response> user_existed_exception_handler(ExistedUserException e){
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATE_FAIL_EXISTED_USER);
		response.setMessage(ResponseMessage.CREATE_FAIL_EXISTED_USER);
		response.setData(e.getData());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
	}
	@ExceptionHandler(value = PasswordMismatchException.class)
	public ResponseEntity<Response> user_password_mismatch_exception_handler(PasswordMismatchException e){
		Response response = new Response();
		response.setStatus(ResponseStatusCode.LOGIN_FAIL_PASSWORD_MISMATCH_USER);
		response.setMessage(ResponseMessage.LOGIN_FAIL_PASSWORD_MISMATCH_USER);
		response.setData(e.getData());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
	}
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Response> user_not_found_exception_handler(UserNotFoundException e){
		Response response = new Response();
		response.setStatus(ResponseStatusCode.LOGIN_FAIL_NOT_FOUND_USER);
		response.setMessage(ResponseMessage.LOGIN_FAIL_NOT_FOUND_USER);
		response.setData(e.getData());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType("application","json",Charset.forName("UTF-8")));
		return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
	}
	
	
	
	
	
}
