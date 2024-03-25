package com.itwill.filter;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(value = "/*",initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class EncodingFilter implements Filter {
	private String encoding = null;

	/**web.xml에서 전달된 인코딩 값을 초기화하는 메써드.
		<filter>
			<filter-name>Encoding Filter</filter-name>
			<filter-class>com.itwill.filter.EncodingFilter</filter-class>
			<init-param>
				<param-name>encoding</param-name>
				<param-value>UTF-8</param-value>
			</init-param>
		</filter>
		
		<filter-mapping>
			<filter-name>Encoding Filter</filter-name>
			<url-pattern>/*</url-pattern>
		</filter-mapping>
	 ********************************************************/
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
		System.out.println("생성직후 단한번호출 init(): encoding parameter-->" + encoding);
	}

	/**
	 * ServletRequest객체에 web.xml에서 전달된 인코딩을 설정하는 메써드.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		//System.out.println("요청시마다 호출 doFilter():" + req.getRequestURI());

		if (request.getCharacterEncoding() == null) {
			if (encoding != null) {
				request.setCharacterEncoding(this.encoding);
			}
		}
		
		// 클라이언트 요청리소스로의요청
		chain.doFilter(request, response);
		
		/*
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print("난 필터!! 넌 누구!!!");
		*/
		

	}

	public void destroy() {
		// System.out.println("필터객체가 메모리에서 해지될때호출 destroy()");
		this.encoding = null;
	}

}
