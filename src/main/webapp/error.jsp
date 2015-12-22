<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="style.css" rel="stylesheet" type="text/css">
<title>University ScheduleBoardError</title>
</head>
<body bgcolor="grey">
	<div class="regular">
	<center>
		<%
			String mes = (String) request.getAttribute("errorMessage");
			out.println(mes);
		%>
	<p>
	</center>
	</div>
	<center>
		<div id = "text">
		<p class="superBig">
			<a href="about.jsp">About Us</a>
			<a href="personal.jsp">Get Personal Schedule</a>
			<a href= "edit.jsp">Add Lecture</a>
			<a href="UniversityController?action=viewall">View all</a>					
		</p> 
	</div>
	</center>	
<p class="copyright"> &copy; 2015 MentatNemchisky. all rights reserved </p>
</body>
</html>