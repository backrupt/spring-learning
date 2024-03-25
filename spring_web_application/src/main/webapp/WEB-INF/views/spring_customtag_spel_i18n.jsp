<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %> 
<%
	request.setAttribute("id", "가드");
	request.setAttribute("page", "index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Spring I18N[Internationalization]국제화</h1><hr>
<xmp>
	1. ApplicationConfig.java 에 MessageSource빈설정
	
	 	@Bean("messageSource")
		public MessageSource messageSource() {
			ResourceBundleMessageSource resourceBundleMessageSource=
					new ResourceBundleMessageSource();
			resourceBundleMessageSource.setBasenames("messages/messages","messages/user");
			return resourceBundleMessageSource;
	}
</xmp>
<ol>
	<li><s:message code="page.title"/> </li>
	<li><s:message code="button.login"/></li>
	<!-- 
		<< user_ko.properties>>
		exception.userexisted={0} 사용자가 존재합니다..!!
		<< user_en.properties>>
		exception.userexisted = {0} user existed...!!
	 -->
	<li><s:message 
			code="exception.userexisted"  
			arguments="${id}" /></li>
	
	<!-- 
	<< user_ko.properties>>
		nv.bc=홈 < {0} < {1}
	<< user_en.properties>>
		nv.bc=HOME < {0} < {1}
	 -->
	 <li>
	 <s:message code="nv.bc" 
	 	           arguments="user,list"
	 	           argumentSeparator=","/>
	 <%--	           
	 <s:message code="nv.bc" 
	 	           arguments="유저,리스트"
	 	           argumentSeparator=","/>
	 --%>
	 </li> 
	
	 <li>Spring Container에 등록된 MessageSource 빈객체를 @를 이용해서 JSP에서직접호출</li>
	 <li><s:eval expression="@messageSource.getMessage('nv.bc',new Object[]{'유저','리스트'},new java.util.Locale('en','US'))"/></li> 
	 <li>Controller에서 생성한 메세지:</li>
	 <li>Service에서 생성한 메세지:</li>
</ol>

</body>
</html>