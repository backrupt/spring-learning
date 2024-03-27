package com.itwill.view;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component/*("XMLView") 생략가능 생략하면 XMLView클래스이름*/
public class XMLView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<String> friendsList = (List<String>)model.get("friendList");
		
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		out.println("<friends>");
		for(String friend:friendsList) {
			out.println("<friend>");			
			out.println(friend);
			out.println("</friend>");
		}
		out.println("</friends>");
	}

}
