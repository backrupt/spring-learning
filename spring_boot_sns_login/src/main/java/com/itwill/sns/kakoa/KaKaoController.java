package com.itwill.sns.kakoa;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KaKaoController {
	@Value("${api.kakao.rest_api_key}")
	private String client_id;
	
	@Value("${api.kakao.redirect_url}")
	private String redirect_url;
	
	@Value("${api.kakao.logout_redirect_uri}")
	private String logout_redirect_uri;
	@Autowired
	private KaKaoService kakaoService;
	/*
    kakao_main.html 페이지보여주기
    */
	@GetMapping("/kakao_main")
	public String kakao_main(Model model) {
		model.addAttribute("client_id",client_id);
		model.addAttribute("redirect_url",redirect_url);
		model.addAttribute("logout_redirect_uri",logout_redirect_uri);
		return "kakao_main";
	}
	
	/*
    - redirection url로 등록된 요청
    - kakao가 발행한 code를 파라메타로 붙혀서 kakao가 호출해준다.
    - ajax방식으로는 불가능하다.
     */
	@RequestMapping(value = "/kakao_login_action", method = RequestMethod.GET)
	public String kakao_login_action(@RequestParam(value = "code", required = false) String code,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("1.code:"+code);
		JSONObject tokenObject= kakaoService.getToken(code);
		System.out.println(tokenObject);
		String authorize_access_token=(String)tokenObject.get("access_token");
		int refresh_token_expires_in=(Integer)tokenObject.get("refresh_token_expires_in");
		String refresh_token=(String)tokenObject.get("refresh_token");
		System.out.println("2.access_token:"+authorize_access_token);
   		
		KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(authorize_access_token);
		System.out.println("3.kakao_profile:"+kakaoProfile);
   		System.out.println("************************************");
   		System.out.println("<<< 이미가입한사용자라면 로그인진행 >>>");
   		System.out.println("<<< 미가입사용자라면 회원가입진행후 로그인   >>>");
   		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		System.out.println("카카오 닉네임 : " + kakaoProfile.getKakao_account().getProfile().getNickname());
   		System.out.println("************************************");
   		/*
          * 이미가입한사용자라면 로그인진행
          * 미가입사용자라면 회원가입진행
          */
		/*
		create table kakao_table (
		   k_number bigint auto_increment,
		   k_name varchar(20) not null,
		   k_email varchar(50) not null,
		   constraint primary key(k_number)
		 );
		 
		-----------------------------------------
		user_id | platform_type | 		email
		-----------------------------------------
		0001    |   kakao1      | test@gmail.com
		0012    |   google      | test@gmail.com
		0023    |   facebook    | test@gmail.com
		5300    |   kakao2      | test01@gmail.com
		*/
   		
       
         
         Cookie authorize_access_token_cookie=new Cookie("authorize_access_token", authorize_access_token);
         Cookie refresh_token_expires_in_cookie=new Cookie("refresh_token_expires_in", refresh_token_expires_in+"");
         Cookie refresh_token_cookie=new Cookie("refresh_token", refresh_token);
         
         response.addCookie(authorize_access_token_cookie);
         response.addCookie(refresh_token_expires_in_cookie);
         response.addCookie(refresh_token_cookie);
         
         
         request.setAttribute("kakaoProfile", kakaoProfile);
         request.setAttribute("authorize_access_token", authorize_access_token);
         request.setAttribute("refresh_token_expires_in", refresh_token_expires_in);
         request.setAttribute("refresh_token", refresh_token);
         
         request.setAttribute("client_id",client_id);
         request.setAttribute("redirect_url",redirect_url);
         request.setAttribute("logout_redirect_uri",logout_redirect_uri);
		return "kakao_main";
	}	
	
	
	@ResponseBody
	@GetMapping("/kakao_userinfo_with_token")
	public KakaoProfile getKakaoUserInfoWithToken(String authorize_access_token, HttpSession session) throws Exception {
		System.out.println(authorize_access_token);
		KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(authorize_access_token);
		return kakaoProfile;
	}
}