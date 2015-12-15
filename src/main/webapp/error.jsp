<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>University ScheduleBoardError</title>
</head>
<body>
	<center>
		<%
			String mes = (String) request.getAttribute("errorMessage");
			out.println(mes);
		%>
	</center>
	<p>
		<a href="index.jsp"> <b>Return</b>
		</a>
</body>
</html>