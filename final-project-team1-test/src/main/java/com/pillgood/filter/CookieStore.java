package com.pillgood.filter;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;

public class CookieStore {
	private List<Cookie> cookieList;
	
	public CookieStore() {
		this.cookieList = new ArrayList<>();
	}
	
	public void addCookies(Cookie[] cookies) {
		for(Cookie cookie:cookies) {
			cookieList.add(cookie);
		}
	}
	
	public Cookie findCookieByKey(String key) {
		for(Cookie cookie:cookieList) {
			if(cookie.getName().equals(key)) {
				return cookie;
			}
		}
		return null;
	}
}
