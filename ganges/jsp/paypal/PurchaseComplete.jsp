<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	HashMap nvp = (HashMap<String,String>)session.getAttribute("nvp");
	Set keys = nvp.keySet();
	Iterator<String> ite = keys.iterator();
%>
<body>
<p>取引コードは<%= nvp.get("PAYMENTINFO_0_TRANSACTIONID") %>
<table>
<%while(ite.hasNext()){
	String key = ite.next();
%><tr><td><%= key %></td><td><%= nvp.get(key) %></td></tr>

<%}%>


</table>
</body>
</html>