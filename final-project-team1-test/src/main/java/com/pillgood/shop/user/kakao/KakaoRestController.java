package com.pillgood.shop.user.kakao;

import java.nio.charset.Charset;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.shop.user.controller.BaseResponse;
import com.pillgood.shop.user.controller.ResponseMessage;
import com.pillgood.shop.user.controller.ResponseStatusCode;
import com.pillgood.shop.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class KakaoRestController {
	
	@Autowired
	private KaKaoService kakaoService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/kakao_login_action") 
	public String kakao_login_action(@RequestParam(value = "code",required = false) String code) throws Exception {
		//토큰요청 https://kauth.kakao.com/oauth/token
		JSONObject tokenObject = kakaoService.getToken(code);
		
		String access_token=(String)tokenObject.get("access_token");
		int refresh_token_expires_in=(Integer)tokenObject.get("refresh_token_expires_in");
		String refresh_token=(String)tokenObject.get("refresh_token");
		
		KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(access_token);
		
		
		
		if(userService.isExist(kakaoProfile.getKakao_account().getEmail())) {
			BaseResponse response=new BaseResponse();
			//response.setStatus(ResponseStatusCode.FIND_USERID);
			response.setMessage(ResponseMessage.FIND_USERID);
			response.setData(new HashMap<>());
			
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		}else {
			
		}
		return null;
	}
}
