package com.springboot.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.data.dto.UserDto;
import com.springboot.security.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/user")
@Tag(name = "회원")
public class UserController {
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Operation( description = "로그인",summary = "로그인")
	@PostMapping(value = "/login")
	public UserDto login(@RequestParam(name = "id", required = true) String id,
			@RequestParam(value = "Password", required = true) String password) throws RuntimeException {
		LOGGER.info("[login] 로그인을 시도하고 있습니다. id : {}, pw : ****", id);
		UserDto loginResultDto = userService.login(id, password);
		if (loginResultDto.getCode() == 0) {
			LOGGER.info("[login] 정상적으로 로그인되었습니다. id : {}, token : {}", id, loginResultDto.getToken());
		}
		return loginResultDto;
	}
	@Operation( description = "회원가입",summary = "회원가입")
	@PostMapping(value = "/join")
	public UserDto join (
			 @RequestParam(value = "id", required = true) String id,
			 @RequestParam(value = "password", required = true) String password,
			 @RequestParam(value = "name", required = true) String name,
			 @RequestParam(value = "role", required = true) String role) {
		LOGGER.info("[join] 회원가입을 수행합니다. id : {}, password : ****, name : {}, role : {}", id, name, role);
		UserDto joinResultDto = userService.join(id, password, name, role);
		LOGGER.info("[join] 회원가입을 완료했습니다. id : {}", id);
		return joinResultDto;
	}

	@GetMapping(value = "/exception")
	public void exceptionTest() throws RuntimeException {
		throw new RuntimeException("접근이 금지되었습니다.");
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		LOGGER.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());
		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", "에러 발생");
		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}

}