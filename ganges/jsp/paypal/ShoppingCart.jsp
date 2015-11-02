<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<!-- ArrayList<商品>をもらって、テーブルといて構成する。  -->
<body>
<form action="<%= request.getContextPath() %>/SetExpress" METHOD='GET'>
	<input type="hidden" name="paymentAmount" value="10000"><!-- < = arrayList.sum() のようにする -->
	<input type='image' name='submit' src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif' border='0' align='top' alt='Check out with PayPal'/>
</form>

</body>
</html>