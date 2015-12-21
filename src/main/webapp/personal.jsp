<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>University Personal ScheduleBoard</title>
<link href="style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</head>
<body bgcolor="grey">
<center><img src="logoUniv.jpg" /></center>
<form action="Servlet" method="get">	
	<center> <input name = "action"/ value=personal type=hidden> </center>
	<p>
	<center><input name = "inputDate" type="text" id='datepicker'/></center>
	<p>
	<center>	<b class="regular">input person id </b>  </center>
	<p>
	<center><input name = "personalId"/> </center>
	<p>
	<center> <input type = "submit" / > </center>
</form>
<center>
<div id = "text">
		<p class="superBig">
			<a href="about.jsp">About Us</a>
			<a href="personal.jsp">Get Personal Schedule</a>	
			<a href= "edit.jsp">Add Lecture</a>
			<a href="Servlet?action=viewall">View all</a>					
		</p> 
		<p class="copyright"> &copy; 2015 MentatNemchisky. All rights reserved </p>
	</div>
</center>
</body>
</html>