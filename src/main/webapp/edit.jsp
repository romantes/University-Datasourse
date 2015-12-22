<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
<title>University ScheduleBoard</title>
</head>
<body bgcolor="grey">
	<center>
		<img src="logoUniv.jpg" />
	</center>
	<form action='Servlet' method="get">
		<center>
		<p class="regular">Note: that date&time should be in
			"yyyy.MM.dd.HH.mm" format
		<p>
		<input name="action" value=edit type=hidden>
		<input name="inputDate" type="text" id='datetimepicker'/>
			<%
				ScheduleBoard sc = new ScheduleBoard();
				List<Subject> subjectList = sc.getAllSubjects();
				List<Professor> professorList = sc.getAllProfessors();
				List<Group> groupList = sc.getAllGroups();
				List<Room> roomList = sc.getAllRooms();

				out.print("<select  name='inputSubject'>"
						+ "<option disabled> choose subject </option>");
				for (Subject s : subjectList) {
					String subject = s.getSubjectTitle();
					out.print("<option value='" + subject + "'>" + subject
							+ "</option>");
				}
				out.print("</select>");

				out.print("<select name='inputProfessor'>" + "<option disabled> choose professor </option>");
				for (Professor p : professorList) {
					Long professorid = p.getPersonId();
					String professorSecondName = p.getSecondName();
					String professorFirstName = p.getFirstName();
					out.print("<option value='" + professorid + "' >"
							+ professorFirstName + " " + professorSecondName
							+ "</option>");
				}
				out.print("</select>");

				out.print("<select  name='inputGroup'>" + "<option disabled> choose group </option>");
				for (Group g : groupList) {
					String group = g.getGroupNumber();
					out.print("<option value='" + group + "' >" + group
							+ "</option>");
				}
				out.print("</select>");

				out.print("<select name='inputRoom'>" + "<option disabled> choose room </option>");
				for (Room r : roomList) {
					String room = r.getRoomNumber();
					out.print("<option value='" + room + "' >" + room + "</option>");
				}
				out.print("</select>");
			%>
			<input type="submit" Value="submit">
		</center>
	</form>
	<center>
		<div id="text">
			<p class="superBig">
				<a href="about.jsp">About Us</a> <a href="personal.jsp">Get
					Personal Schedule</a> <a href="edit.jsp">Add Lecture</a> <a
					href="Servlet?action=viewall">View all</a>
			</p>
			<p class="copyright">&copy; 2015 MentatNemchisky. All rights
				reserved</p>
		</div>
	</center>
</body>
</html>