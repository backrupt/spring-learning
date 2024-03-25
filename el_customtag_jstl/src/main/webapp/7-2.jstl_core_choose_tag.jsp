<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//session.setAttribute("sUserId", "admin");
	session.setAttribute("sUserId", "guard");
	request.setAttribute("level", 56);//레벨은 1~5사이의정수
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSTL core choose tag</h1><hr>
<c:choose>
	<c:when test="${!empty sUserId}">
		<a href=''>${sUserId } 님로그아웃</a><br>
		<a href=''>상품리스트</a><br>
		<c:if test="${sUserId=='admin'}">
			<a href=''>상품등록</a><br>
		</c:if>
	</c:when>
	<c:otherwise>
		<a href=''>로그인</a><br>
	</c:otherwise>
</c:choose>
<br>
<br>
<c:choose>
	<c:when test="${level==1}">
		초보시군요[${level }]<br>
	</c:when>
	<c:when test="${level==2}">
		중수시군요[${level }]<br>
	</c:when>
	<c:when test="${level>=3 && level<=5}">
		고수시군요[${level }]<br>
	</c:when>
	<c:otherwise>
		레벨은 1~5사이의정수입니다.[${level}]
	</c:otherwise>
</c:choose>












</body>
</html>