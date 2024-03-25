<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>hello.jsp</h1><hr>
<ol>
	<li>/hello 요청</li>
	<li>
		DispatcherServlet이 HandlerMapping객체로부터 요청url [/hello]에 해당하는 HelloController객체얻기
	</li>
	<li>
	    DispatcherServlet이 HelloController객체의 handleRequest메쏘드호출<br>
	    A. ### HelloController.handleRequest()호출<br>
	    B. ### HelloService.helloList()호출<br>
	    C. ### HelloDao.helloList()호출<br>
	    	
	</li>
	<li>
		DispatcherServlet이 HelloController객체의 handleRequest메쏘드 호출후 반환된 forwardPath로 forward[/WEB-INF/views/hello.jsp ]
	</li>
	<li>/WEB-INF/views/hello.jsp 응답
		<ol type="a">
			<li>Controller객체에서 요청객체에설정된속성객체출력{msg}</li>
			<li>속성객체출력
				${msg}
			</li>
		</ol>
	</li>
	
</ol>
</body>
</html>