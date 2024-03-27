package com.itwill.view;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomView3 extends AbstractView{
	private String redirectPath;
	
	public String getRedirectPath() {
		return redirectPath;
	}
	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.sendRedirect(redirectPath);
	}

}
