<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="sample">
<form name="form" method="post" action="demo">
<input type="radio" name="typ" value="1"> One<br>
<input type="radio" name="typ" value="2"> Two
<input type="submit" value="select">
</form>
</div>

<h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>
</body>
</html>