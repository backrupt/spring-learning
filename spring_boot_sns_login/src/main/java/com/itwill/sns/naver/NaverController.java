package com.itwill.sns.naver;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NaverController {
	@Value("${api.naver.client_id}")
	private String client_id;
	
	@Value("${api.naver.client_secret}")
	private String client_secret;
	
	@Value("${api.naver.redirect_url}")
	private String redirect_uri;
	
    @Autowired
    private NaverService service;
    /*
    naver_main.html 페이지보여주기
    */
    @GetMapping("/naver_main")
    public String index(Model model){
    	model.addAttribute("client_id",client_id);
    	model.addAttribute("client_secret",client_secret);
    	model.addAttribute("redirect_uri",redirect_uri);
    	
        return "naver_main";
    }

    @GetMapping(value="/naver_login_action",produces = "application/json;charset=UTF-8")
    public  String naver_login_action(@RequestParam Map<String, String> resValue,
    									HttpServletRequest request,HttpServletResponse response){
        // code 를 받아오면 code 를 사용하여 access_token를 발급받는다.
    	System.out.println(resValue);
        final NaverLoginVo naverLoginVo = service.requestNaverLoginAcceccToken(resValue, "authorization_code");
        // access_token를 사용하여 사용자의 고유 id값을 가져온다.
        final NaverLoginProfile naverLoginProfile = service.requestNaverLoginProfile(naverLoginVo);
        /*
         * 이미가입한사용자라면 로그인진행
         * 미가입사용자라면 회원가입진행
         */
        System.out.println(naverLoginProfile);
        HttpSession session=request.getSession();
        session.invalidate();
        // 위 코드는 session객체에 담긴 정보를 초기화 하는 코드.
        session=request.getSession();
        
        session.setAttribute("naverProfile", naverLoginProfile);
        request.setAttribute("naverProfile", naverLoginProfile);
        //Cookie authorize_access_token=new Cookie("authorize-access-token", access_token);
        //response.addCookie(authorize_access_token);
        return "naver_main";
    }

}
