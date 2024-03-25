<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>hello1.jsp</h1>
<hr/>
<ol>
	<li>/hello1요청</li>
	<li>
		DispatcherServlet이 HandlerMapping객체로부터 요청url[/hello1]에 해당하는 
	    HelloAnnotationController1 객체얻기
	 </li>
	 <li>
	 	DispatcherServlet이 HelloAnnotationController1 객체의 hello1() 메쏘드 호출	
	 </li> 
	 <li>
	 	DispatcherServlet이 HelloAnnotationController1 객체의 hello1() 메쏘드 호출후 
	 	반환된 forwardPath로 forwarding..
	 </li>  
	 <li>
	 	/WEB-INF/views/hello1.jsp 응답
	 </li>
	 
	 <li>Controller객체에서 요청객체에 설정된 속성객체출력 --> ${msg}</li>
	
</ol>
</body>
</html>