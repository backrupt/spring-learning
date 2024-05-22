package com.pillgood.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pillgood.shop.user.service.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter{

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		if(path.startsWith("/api/user/join")) {
			return true;
		}
		
		return false;
	}
	//private final CookieStore cookieStore;  cookie crud 파싱 ....
	//	private final TokenStore tokenStore;  jwt 파싱,디코딩,verify 
	
	//authfilter 만들고 cookiestore tokenstore 만들고 
	// 페이로드 만들고 argumentrevoler만들고 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Cookie authCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies==null) {
			sendUnauthorizedResponse(response);
			return;
		}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("pillyJwt")) {
				authCookie=cookie;
				break;
			}
		}
		
		if(authCookie!=null) { 
			sendUnauthorizedResponse(response);
			return;
		}
				
		String authToken = authCookie.getValue();
		System.out.println(authToken);
		authToken = authToken.substring(7); // Bearer 부분 제거
			
		Claims claims = Jwts.parser()
				.setSigningKey("secretkey넣어야댐")
				.parseClaimsJws(authToken)
				.getBody();
			
		try {
			validateToken(claims); //토큰 검증
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String userId = claims.getSubject();
		request.setAttribute("userId", userId);
		
		filterChain.doFilter(request, response);
		
	}
	private void validateToken(Claims claims) throws Exception {
        // 토큰 만료 검증
        if (claims.getExpiration().before(new Date())) {
            throw new Exception("Token has expired");
        }
	}
	
	private void sendUnauthorizedResponse(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
