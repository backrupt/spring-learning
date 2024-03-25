<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%
pageContext.setAttribute("path","/page.jsp");
request.setAttribute("path","/request.jsp");
session.setAttribute("path","/session.jsp");
application.setAttribute("path","/application.jsp");


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 내장객체(implicit object) 타입은맵이다.</h1><hr>
<ul>
	<li>---pageScope,requestScope,sessionScope,applicationScope---
		<ul>
			<li>${path}</li>
			<li>${pageScope.path}</li>
			<li>${requestScope.path}</li>
			<li>${sessionScope.path}</li>
			<li>${applicationScope.path}</li>
		
		</ul>
	
	</li>
	<li>--------param---------
		<ul>
			<li>${param.name}</li>
			<li>${param['id']}</li>
			<li>${param.hobby}</li>
		</ul>
	</li>
	
	
	<li>--------paramValues---------
		<ul>
			<li>${paramValues}</li>
			<li>${paramValues.name[0]}</li>
			<li>${paramValues.id[0]}</li>
			<li>${paramValues.hobby[0]}</li>
			<li>${paramValues.hobby[1]}</li>
			<li>${paramValues.hobby[2]}</li>
			
		</ul>
	
	</li>
	
	<li>--------cookie---------
		<ul>
			<li>${cookie}</li>
			<li>${cookie.JSESSIONID}</li>
			<li>${cookie.JSESSIONID.name}</li>
			<li>${cookie.JSESSIONID.value}</li>
		</ul>
	</li>
	
	<li>--------pageContext[빈객체]---------
		<ul>
			<li>${pageContext.request}</li>		
			<li>${pageContext.request.method}</li>		
			<li>${pageContext.request.requestURI}</li>		
			<li>${pageContext.out}</li>		
			<li>${pageContext.session}</li>		
			<li>${pageContext.response}</li>		
			<li>${pageContext.servletContext}</li>		
			<li>${pageContext.servletConfig}</li>		
		</ul>
	</li>
	
</ul>	


</body>
</html>