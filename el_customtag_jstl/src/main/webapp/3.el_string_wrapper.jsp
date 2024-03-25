<%@page import="com.itwill.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("sUserId", "guard[request]");
	session.setAttribute("sUserId", "guard[session]");
	session.setAttribute("sUser", new User("guard","1111","김수미","sumi@naver.com"));
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL String,Wrapper객체(기본형객체) 출력</h1>
<ol>
	<li>${sUserId}</li>
	<li>${requestScope.sUserId}</li>
	<li>${sessionScope.sUserId}</li>
	<li>${sUser.userId}</li>
	<li>${sUser.password}</li>
	<li>${sUser.email}</li>
</ol>
</body>
</html>










