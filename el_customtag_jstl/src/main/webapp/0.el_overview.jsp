<%@page import="java.util.HashMap"%>
<%@page import="com.itwill.bean.User"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.bean.Guest"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*************String Wrapper속성객체************/
	request.setAttribute("a", new String("String 속성객체1"));
	request.setAttribute("b", new Integer(2));
	request.setAttribute("c", new Boolean(true));
	request.setAttribute("d", "String 속성객체2");
	request.setAttribute("e", 2);
	request.setAttribute("f", true);
	/***********자바빈 속성객체**********************/
	request.setAttribute("guest", new Guest(1,"KIM","2023/10/02","guard@gmail.com","http://www.gmail.com","타이틀","컨텐트"));
	request.setAttribute("user", new User("guard","1111","가아드","guard@gmail.com"));
	/***********List(array)속성객체*****************/
	List<Guest> guestList=new ArrayList<Guest>();
	guestList.add(new Guest(1,"KIM","2023/10/02","guard1@gmail.com","http://www.gmail.com","타이틀1","컨텐트1"));
	guestList.add(new Guest(2,"GIM","2023/10/03","guard2@gmail.com","http://www.gmail.com","타이틀2","컨텐트2"));
	guestList.add(new Guest(3,"FIM","2023/10/04","guard3@gmail.com","http://www.gmail.com","타이틀3","컨텐트3"));
	request.setAttribute("guestList", guestList);
	/***********Map 속성객체************************/
	Map<String,User> userMap=new HashMap<String,User>();
	userMap.put("guard1", new User("guard1","1111","가드1","guard1@gmail.com"));
	userMap.put("guard2", new User("guard2","2222","가드2","guard2@gmail.com"));
	userMap.put("guard3", new User("guard3","3333","가드3","guard3@gmail.com"));
	request.setAttribute("userMap", userMap);
	/***********JSP local변수[EL출력대상객체아님]***********************/
	String str1="난 JSP로칼변수";
	int int1=1234;
	User user1=new User("KING","1111","왕","king@king.com");
	/*
	속성(attribute)객체
		- 속성객체이름: a,b,c,d,e,f,guest,user,guestList,userMap
	*/
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL(Expression Language)</h1><hr/>
<ul>
	<li>############# NO EL(EL사용하지않았을때)###</li>
	<li>*************[1]String,Wrapper************</li>
	<li><%=request.getAttribute("a")%></li>
	<li><%=request.getAttribute("d")%></li>
	<li><%=request.getAttribute("c")%></li>
	<li><%=request.getAttribute("d")%></li>
	<li><%=request.getAttribute("e")%></li>
	<li><%=request.getAttribute("f")%></li>
	<li>*************[2]자바빈 속성객체출력*******</li>
	<li><%=request.getAttribute("guest") %></li>
	<li><%=((Guest)request.getAttribute("guest")).getGuest_no()%></li>
	<li><%=((Guest)request.getAttribute("guest")).getGuest_name()%></li>
	<li><%=((Guest)request.getAttribute("guest")).getGuest_email()%></li>
	<li><%=((Guest)request.getAttribute("guest")).getGuest_homepage()%></li>
	<li><%=((Guest)request.getAttribute("guest")).getGuest_content()%></li>
	<li>*************[3]List(array) 속성객체출력*******</li>
	<li><%=request.getAttribute("guestList")%></li>
	<li><%=((List<Guest>)request.getAttribute("guestList")).get(0)%></li>
	<li><%=((List<Guest>)request.getAttribute("guestList")).get(0).getGuest_no()%></li>
	<li><%=((List<Guest>)request.getAttribute("guestList")).get(0).getGuest_name()%></li>
	<li><%=((List<Guest>)request.getAttribute("guestList")).get(0).getGuest_email()%></li>
	<li><%=((List<Guest>)request.getAttribute("guestList")).get(0).getGuest_homepage()%></li>
	<li><%=((List<Guest>)request.getAttribute("guestList")).get(0).getGuest_title()%></li>
	<li><%=((List<Guest>)request.getAttribute("guestList")).get(0).getGuest_content()%></li>
	<li>*************[4]Map 속성객체출력*******</li>
	<li><%=request.getAttribute("userMap") %></li>
	<li><%=((Map<String,User>)request.getAttribute("userMap")).get("guard1") %></li>
	<li><%=((Map<String,User>)request.getAttribute("userMap")).get("guard1").getUserId() %></li>
	<li><%=((Map<String,User>)request.getAttribute("userMap")).get("guard1").getPassword() %></li>
	<li><%=((Map<String,User>)request.getAttribute("userMap")).get("guard1").getName() %></li>
	<li><%=((Map<String,User>)request.getAttribute("userMap")).get("guard1").getEmail() %></li>
	<li>***********[5]JSP service 메쏘드안에 선언된 local변수 출력*******</li>
	<li><%=str1%></li>
	<li><%=int1%></li>
	<li><%=user1%></li>
	<li><%=user1.getUserId()%></li>
	<li><%=user1.getPassword()%></li>
	
	<li>############## EL 사용 ###################</li>
	<li>*************[1]String,Wrapper************</li>
	<li>${a}</li>
	<li>${b}</li>
	<li>${c}</li>
	<li>${d}</li>
	<li>${e}</li>
	<li>${f}</li>
	<li>*************[2]자바빈 속성객체출력*******</li>
	<li>${guest}</li>
	<li>${guest.guest_no}</li>
	<li>${guest.guest_name}</li>
	<li>${guest.guest_homepage}</li>
	<li>${guest.guest_title}</li>
	<li>${guest.guest_content}</li>
	<li>*************[3]List(array) 속성객체출력*******</li>
	<li>${guestList}</li>
	<li>${guestList[0].guest_no}</li>
	<li>${guestList[0].guest_name}</li>
	<li>${guestList[0].guest_email}</li>
	<li>${guestList[0].guest_homepage}</li>
	<li>${guestList[0].guest_title}</li>
	<li>${guestList[0].guest_content}</li>
	<li>***********[4]Map 속성객체출력*******</li>
	<li>${userMap}</li>
	<li>${userMap['guard1']}</li>
	<li>${userMap['guard1'].userId}</li>
	<li>${userMap['guard1'].password}</li>
	<li>${userMap['guard1'].name}</li>
	<li>${userMap['guard1'].getEmail()}</li>
	<li>***********[5]JSP service 메쏘드안에 선언된 local변수 EL로 출력불가능</li>
	<li>${str1}</li>
	<li>${int1}</li>
	<li>${user1}</li>
	<li>${user1.getUserId()}</li>
	<li>${user1.userId}</li>
	
	
</ul>
</body>
</html>