<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h1>스프링Controller[@Controller]</h1><hr/>
		<a href='./index.jsp'>메인메뉴</a>
		<ol>
			<li><a href='hello'>hello[annotation]</a></li>
			<li><a href='hello1'>hello1[annotation]</a></li>
			<li><a href='hello2'>hello2[annotation]</a></li>
			<li>HelloAnnotationMultiRequestMappaingController 객체의 메쏘드여러개매핑</li>
			<li><a href='hello3'>hello3[annotation]</a></li>
			<li><a href='hello4'>hello4[annotation]</a></li>
			<li><a href='hello5'>hello5[annotation]</a></li>
			<li><a href='hello_redirect_servlet'>
			            hello_redirect_servlet[servlet redirect]
			    </a>
			</li>
			<li><a href='hello_servlet_forward'>
			            hello_servlet_forward[servlet forward]
			    </a>
			</li>
		</ol>
	</body>
</html>