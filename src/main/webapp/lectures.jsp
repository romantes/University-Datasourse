<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="style.css" rel="stylesheet" type="text/css">
	<title>University ScheduleBoard</title>
</head>
<body bgcolor="grey">
	<center><img src="logoUniv.jpg" /></center>
	<center>
	<b class="regular">
		 
		Welcome to schedule board <%
		if (request.getAttribute("person") != null) {
		Person person = (Person) request.getAttribute("person");
		out.print(person.getFirstName() + " " + person.getSecondName());
		}
	%>
	</b>
	<p class="regular"> Today's date: <% out.print(new Date()); %>
	<b class="regular"> <center> Requested schedule </center> </b>>
	<table border=1 align=center>
	
		<thead>
			<tr>
			<th>id</th>
			<th>date</th>
			<th>subject</th>
			<th>professor</th>
			<th>group</th>
			<th>room</th>
		</thead>
		<tbody>
			<%
				List<Lecture> lectures = (ArrayList)request.getAttribute("lectures");
				for (Lecture lecture : lectures) {
					Long id = lecture.getId();
					String date = lecture.getDate().toString();
					String subject = lecture.getSubject().toString();
					String professor = lecture.getProfessor().toString();
					String group = lecture.getGroup().toString();
					String room = lecture.getRoom().toString();
					out.print("<tr>" + "<td>" + id + "</td>"
									+ "<td>" + date + "</td>" 
									+ "<td>" + subject + "</td>"
									+ "<td>" + professor + "</td>"
									+ "<td>" + group + "</td>"
									+ "<td>" + room + "</td>"
									+ "<td><a href='UniversityController?action=delete&id="+ id +"'>Delete</a> </td>"
							+ "</tr> \n" );
				}	
			%>
		</tbody>
	</table>	
	<div id = "text">
		<p class="superBig">
			<a href="about.jsp">About Us</a>
			<a href="personal.jsp">Get Personal Schedule</a>
			<a href= "edit.jsp">Add Lecture</a>
			<a href="UniversityController?action=viewall">View all</a>					
		</p> 
		<p class="copyright"> &copy; 2015 MentatNemchisky. All rights reserved </p>
	</div>
	
</center>
</body>

</html>