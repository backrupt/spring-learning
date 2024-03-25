package com.itwill.tag;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class HelloAttributeTag extends TagSupport{
	private String irum;
	
	public void setIrum(String irum) {
		System.out.println("2.setIrum("+irum+")메쏘드호출");
		this.irum = irum;
	}
	public HelloAttributeTag() {
		System.out.println("1.HelloAttributeTag기본생성자호출");
	}
	@Override
	public int doStartTag() throws JspException {
		System.out.println("3.doStartTag()메쏘드호출");
		String msg="";
		if(irum.equals("김경호")) {
			msg="잘생긴 "+irum+" 님 안녕하세요<br>";
		}else {
			msg=irum+" 님 안녕하세요<br>";
		}
		try {
			pageContext.getOut().println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	@Override
	public int doEndTag() throws JspException {
		System.out.println("4.doEndTag()메쏘드호출");
		try {
			pageContext.getOut().println("<hr>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
