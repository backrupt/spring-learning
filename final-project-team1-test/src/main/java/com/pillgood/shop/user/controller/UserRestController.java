package com.pillgood.shop.user.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.resolver.AuthUser;
import com.pillgood.shop.user.dto.UserDto;
import com.pillgood.shop.user.dto.UserLoginRequestDto;
import com.pillgood.shop.user.repository.UserRepository;
import com.pillgood.shop.user.service.JwtUtil;
import com.pillgood.shop.user.service.MailService;
import com.pillgood.shop.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	@Autowired
	private JwtUtil jwtUtil;
	
	/*
	public ResponseEntity<BaseResponse> writePost(
			
			@AuthUser User loggedInUser
			) {
		// ...
		return null;
	}
	
	@Operation(summary = "아이디중복체크")
	@GetMapping(value = "/check_id/{id}")
	public ResponseEntity<BaseResponse> idDuplicatedCheck(@PathVariable("id") String id){
		BaseResponse response=new BaseResponse();
		
		log.info("id = {}", id);
		response.setStatus(ResponseStatusCode.CREATE_FAIL_EXISTED_USER);
 		response.setMessage(ResponseMessage.CREATE_FAIL_EXISTED_USER);
		response.setData(userService.isExist(id));
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
		return responseEntity; 
	}
	*/
	
	@Operation(summary = "회원가입")
	@PostMapping(value = "/join")
	public ResponseEntity<String> createUser(@RequestBody UserDto userJoinRequestDto){
		userService.createUser(userJoinRequestDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	/*
	@Operation(summary = "회원로그인세션방식")
	@PostMapping(value = "/login/session")
	public ResponseEntity<BaseResponse> login(@RequestBody UserLoginRequestDto userLoginRequestDto,HttpSession session){
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
			
		if(userService.login(userLoginRequestDto)) {			
			session.setAttribute("sUserId",userLoginRequestDto.getId());
			
			BaseResponse.ok(ResponseMessage.LOGIN_SUCCESS);
		
			
			ResponseEntity<BaseResponse> responseEntity= ResponseEntity.ok(response);
			return responseEntity;
		} else {
			BaseResponse response=new BaseResponse();
			response.setStatus(ResponseStatusCode.LOGIN_FAIL_NOT_FOUND_USER);
			response.setMessage(ResponseMessage.LOGIN_FAIL_NOT_FOUND_USER);
			response.setData(new HashMap<>());
			
			ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
			return responseEntity;
		}
	}
	*/
	@Operation(summary = "회원로그인토큰방식")
	@PostMapping("/login/token")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto userLoginRequestDto,HttpServletResponse response) {
        if(userService.login(userLoginRequestDto)) {
        	String token = jwtUtil.generateToken(userLoginRequestDto.getId());
        	
        	Cookie cookie = new Cookie("pillyJwt",token);
        	cookie.setHttpOnly(true);
        	cookie.setDomain("localhost");
        	cookie.setPath("/");
        	cookie.setMaxAge(60*60*2); //시간
        	response.addCookie(cookie);
   
        	return ResponseEntity.status(HttpStatus.OK).body("Success");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
	/*
	@Operation(summary = "토큰검증")
	@GetMapping("/validate")
    public ResponseEntity<BaseResponse> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
		
        // "Bearer jwtToken" 형태의 헤더에서 jwtToken 부분을 추출합니다.
        String jwtToken = authorizationHeader.substring(7);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        if (jwtUtil.validateToken(jwtToken, jwtUtil.extractClaims(jwtToken).getSubject())) {
    		
    		BaseResponse response=new BaseResponse();
    		response.setStatus(ResponseStatusCode.LOGIN_SUCCESS);
        	response.setMessage(ResponseMessage.LOGIN_SUCCESS);
        	response.setData("Valid Token");
    		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
        	return responseEntity;
        } else {
        	BaseResponse response=new BaseResponse();
    		response.setStatus(ResponseStatusCode.UNAUTHORIZED_USER);
        	response.setMessage(ResponseMessage.UNAUTHORIZED_USER);
        	response.setData("Invalid Token");
    		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
        	return responseEntity;
        }
    }

	
	@Operation(summary = "회원아이디찾기",parameters = {@Parameter(name="phone")})
	@GetMapping(value = "/find_id/{phone}")
	public ResponseEntity<BaseResponse> findUserId(@PathVariable("phone") String phone){
		UserDto userResponseDto = userService.findUserId(phone);
		
		BaseResponse response=new BaseResponse();
		response.setStatus(ResponseStatusCode.FIND_USERID);
		response.setMessage(ResponseMessage.FIND_USERID);
		response.setData(userResponseDto);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
		return responseEntity; 
	}
	/*
	@Operation(summary = "회원비밀번호찾기")
	@GetMapping(value = "/find_password/{id}")
	public ResponseEntity<Response> findUserPassword(@PathVariable String id){
		UserDto userResponseDto = userService.findUserPassword(id);
		System.out.println("userResponseDto"+userResponseDto);
		Response response=new Response();
		response.setStatus(ResponseStatusCode.FIND_USERPASSWORD);
		response.setMessage(ResponseMessage.FIND_USERPASSWORD);
		response.setData(userResponseDto);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity=new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
		return responseEntity; 
	}
	*/
	/*
	@Operation(summary = "회원비밀번호찾기",parameters = {@Parameter(name="id")})
	@GetMapping(value = "/find_password/{id}")
	public ResponseEntity<BaseResponse> findUserPassword(@PathVariable("id") String id){
		String tempPassword = userService.updateUserPassword(id);
		String body="회원님의 임시 비밀번호 >> "+tempPassword;
		mailService.sendEmail(id, body);
		
		BaseResponse response=new BaseResponse();
		response.setStatus(ResponseStatusCode.FIND_USERPASSWORD);
		response.setMessage(ResponseMessage.FIND_USERPASSWORD);
		response.setData(new HashMap<>());
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
		return responseEntity; 
	}
	
	@Operation(summary = "회원상세보기",parameters = {@Parameter(name="id")})
	@GetMapping(value = "{id}")
	public ResponseEntity<BaseResponse> userView(@PathVariable("id") String id){
		UserDto userResponseDto = userService.userView(id);
		
		BaseResponse response=new BaseResponse();
		response.setStatus(ResponseStatusCode.READ_USER);
		response.setMessage(ResponseMessage.READ_USER);
		response.setData(userResponseDto);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
		return responseEntity; 
	}
	
	@Operation(summary = "회원수정")
	@PutMapping
	public ResponseEntity<BaseResponse> userUpdate(@RequestBody UserDto userRequestDto,@CookieValue(name = "jwtToken")String cookie){//생략하면 value
		
		UserDto userResponseDto = userService.updateUser(userRequestDto);
		
		BaseResponse response=new BaseResponse();
		response.setStatus(ResponseStatusCode.UPDATE_USER);
		response.setMessage(ResponseMessage.UPDATE_USER);
		response.setData(userResponseDto);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
		return responseEntity; 
	}
	
	@Operation(summary = "회원탈퇴")
	@DeleteMapping(value = "/{no}")
	public ResponseEntity<BaseResponse> userDelete(@PathVariable Long no){
		userService.removeUser(no);
		
		BaseResponse response=new BaseResponse();
		response.setStatus(ResponseStatusCode.DELETE_USER);
		response.setMessage(ResponseMessage.DELETE_USER);
		response.setData(new HashMap<>());
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		ResponseEntity<BaseResponse> responseEntity=new ResponseEntity<BaseResponse>(response,httpHeaders,HttpStatus.OK);
		return responseEntity; 
	}
	
	@Operation(summary = "로그인 상태 확인")
	@GetMapping(value = "/session")
	public ResponseEntity<BaseResponse> checkSession(HttpSession session) {
	    String userId = (String) session.getAttribute("sUserId");
	    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    
	    BaseResponse response = new BaseResponse();
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	    
	    if (userId != null) {
	    	UserDto userDto = userService.userView(userId);
	        response.setStatus(ResponseStatusCode.LOGIN_SUCCESS);
	        response.setMessage(ResponseMessage.LOGIN_SUCCESS);
	        response.setData(userDto);
	        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	    } else {
	        response.setStatus(ResponseStatusCode.UNAUTHORIZED_USER);
	        response.setMessage(ResponseMessage.UNAUTHORIZED_USER);
	        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
	    }
	}
	
	*/
}
