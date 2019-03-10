<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Success</title>
</head>
<body>
	

	<%
	
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("user") == null){
		response.sendRedirect("index.jsp");
	}else {
	    userName=session.getAttribute("u_id").toString();
	}
	%>
	
	Your Order has been placed successfully!<br>
	Your Order number is "<%out.print(userName); %>"<br><br>

	<h5 id="errMsg" class="text-danger" >"${error_message}"</h5><br><br>
<div id="hist">
 	<form method=post action="history">
 		<button type="submit" >View History</button>
 	</form>
    </div>
	<button type="button" onclick="home()">Go to Home Page</button><br><br>

	<div id="logout">
		<form method=post action="logout">
			<button type="submit">Logout</button>
		</form>
	</div>

	<script>
		function home(){
			window.location("welcome.jsp");
		}
	</script>
</body>
</html>