<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Edit Profile

<div id="password">
 	<form method=post action="" name="form">
 		
 		Enter new password : <input type="password" name="newpassword" required><br>
 		Confirm password : <input type="password" name="confirmpassword" required><br>
 		<button type="submit" onclick="return validate()">Change Password</button>
 	</form>
</div>
<h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>


<script>
function validate(){
	var pwd=document.form.newpassword.value;  
	var confirm_pwd=document.form.confirmpassword.value;  
	var regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	var status=false;  
  
	if(!(pwd==confirm_pwd)){  
		document.getElementById("errMsg").innerHTML=  
		"  New Password and Confirm Password should be same ";  
		status=false;  
	}else if(pwd.length<8){
		document.getElementById("errMsg").innerHTML=  
			"  Password should contain minimum 8 characters ";  
			status=false;    
	}else if(!regex.test(pwd)){
		document.getElementById("errMsg").innerHTML="Password must contain atleast one digit, one small and one capital letter and one symbol ";  
		status=false;
		}else{
			document.getElementById("errMsg").innerHTML=" ";  
		status=true;
		}
return status; 
}
</script>

</body>
</html>