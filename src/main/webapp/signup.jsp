<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>SignUp</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- CSS File -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div id="mongoDbServlet" class="glyphicon glyphicon-user">
   		<form id="myform" name="myform" method="post" action="signup" onsubmit="return validate()">
     		<p>Name:    <input type="text"  name="login_id" /><br></p>
     		<p>Mobile:    <input type="text"  name="phone" /><br></p>
     		<p>Password: <input type="password"   name="login_pwd" /></p>
     		<p>Confirm Password: <input type="password"   name="confirm_password" /></p>    
     		<input type="submit" value="go" onclick="login.jsp" />
   		</form>
   		<h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>
    </div>

	<script >
    	function validate(){  
    		var name=document.myform.login_id.value;
    		var mobile=document.myform.phone.value;
    		var pwd=document.myform.login_pwd.value;
    		var confirm_pwd=document.myform.confirm_password.value;
    		var status=false;  
    		var regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    		
    		if(name.length<2){  
    			document.getElementById("errMsg").innerHTML=  
    			"  Please enter your Full Name";  
    			status=false;  
    		}else if(mobile.length!=10){
    			document.getElementById("errMsg").innerHTML=  
    	    	"  Mobile Number must be 10 digit long  ";  
    	    	status=false; 
    		}else if(pwd.length<8 ){  
    			document.getElementById("errMsg").innerHTML=  
    			"  Password must be at least 8 char long  ";  
    			status=false;  
    		}else if(!(pwd==confirm_pwd)){  
        		document.getElementById("errMsg").innerHTML=  
        		"  Password and Confirm Password should be same ";  
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