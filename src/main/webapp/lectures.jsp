<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>University ScheduleBoard</title>
</head>
<body>
	<center>
	<b>
	Welcome to your personal schedule board <%
		Person person = (Person) request.getAttribute("person");
		out.print(person.getFirstName() + " " + person.getSecondName());
	%>
	</b>
	<p> Today's date: <% out.print(new Date()); %>
	<b> <center> Requested schedule </center> </b>>
	<table border=1 align=center>
		<thead>
			<tr>
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
					String date = lecture.getDate().toString();
					String subject = lecture.getSubject().toString();
					String professor = lecture.getProfessor().toString();
					String group = lecture.getGroup().toString();
					String room = lecture.getRoom().toString();
					out.print("<tr>" + "<td>" + date + "</td>" 
									+ "<td>" + subject + "</td>"
									+ "<td>" + professor + "</td>"
									+ "<td>" + group + "</td>"
									+ "<td>" + room + "</td>"
							+ "</tr> \n" );
				}
			%>
		</tbody>
	</table>	
	<p>
	<a href = "index.jsp"> <b>Return</b> </a>
</center>
</body>

</html>