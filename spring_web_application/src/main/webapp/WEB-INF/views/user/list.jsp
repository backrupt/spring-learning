<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>user/list.jsp</h1><hr>
<ol>
	<li><a href="view">KIM</a></li>
	<li><a href="view">LEE</a></li>
	<li><a href="view">SIM</a></li>
</ol>
<hr>
<ol>
	<li><a href="/spring_web_application/user/view">KIM</a></li>
	<li><a href="/spring_web_application/user/view">LEE</a></li>
	<li><a href="/spring_web_application/user/view">SIM</a></li>
</ol>
<hr>
<ol>
	<li><a href="<c:url value='/user/view'/>">KIM</a></li>
	<li><a href="<c:url value='/user/view'/>">LEE</a></li>
	<li><a href="<c:url value='/user/view'/>">SIM</a></li>
</ol>
</body>
</html>