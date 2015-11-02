<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
HashMap nvp = (HashMap<String,String>)session.getAttribute("nvp");
%>
<body>
<p>PurchaseConfirm</p>
<p>購入者 <%= nvp.get("LASTNAME") %> <%= nvp.get("FIRSTNAME") %> さん</p>
<p>PAYER STATUS <%= nvp.get("PAYERSTATUS") %>

<form action="<%= request.getContextPath() %>/DoExpress" method="GET">
	<input type="submit" value="購入する">
</form>


</body>
</html>