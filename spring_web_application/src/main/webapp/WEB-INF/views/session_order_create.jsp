<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>주문결과</h1><hr>
<form  action="">
<div>
	세션주문데이타1[이름]${order.name}<br>
	세션주문데이타2[카드번호]${order.cardNo}<br>
	세션주문데이타3[배송지]${order.address}<br>
	<hr/>
	곧 배송이 시작됩니다.
</div>

</form>
</body>
</html>