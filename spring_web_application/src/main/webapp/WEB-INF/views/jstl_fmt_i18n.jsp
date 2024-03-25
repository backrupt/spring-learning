<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%
	request.setAttribute("id", "guard");
	request.setAttribute("pass", "1111");
	request.setAttribute("name", "가아드");

%>
<%-- 1.Locale객체설정(JSTL)--%>  
<fmt:setLocale value="${pageContext.request.locale.language}"/>
<%-- 2.ResourceBundle 의 properties파일지정(JSTL) 페이지전역  --%>
<fmt:setBundle  basename="messages/messages"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSTL I18N[Internationalization]국제화</h1><hr/>
<ol>
	<li>
		<fmt:message key="user.existed.exception">
			<fmt:param value="${id}"/>
		</fmt:message>
		
	</li>
	<li>
		<fmt:message key="user.notfound.exception">
			<fmt:param value="${id}"/>
		</fmt:message>
	</li>
	<li>
		<fmt:message key="password.mismatch.exception">
			<fmt:param value="${id}" />
			<fmt:param value="${pass}"/>
			<fmt:param value="${name}"/>
		</fmt:message>
		
	</li>
</ol>

</body>
</html>