<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>주문확인</h1><hr>
<form  action="session_order_create">
<div>
	주문데이타1[이름]${order.name}<br>
	주문데이타2[카드번호]${order.cardNo}<br>
	주문데이타3[배송지]${order.address}<br>
</div>
<input type="submit" value="위정보로 주문생성[DB]">
</form>
</body>
</html>