package com.itwill.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class IfTag extends TagSupport{
	private boolean test;
	public IfTag() {
		System.out.println("0.IfTag기본생성자");
	}
	public void setTest(boolean test) {
		System.out.println("1.setTest("+test+")");
		this.test = test;
	}

	@Override
	public int doStartTag() throws JspException {
		if(test) {
			return TagSupport.EVAL_BODY_INCLUDE;
		}else {
			return TagSupport.SKIP_BODY;
		}
	}
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return TagSupport.EVAL_PAGE;
	}
}
