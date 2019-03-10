<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login Page-testyogapriya</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- CSS File -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
body {
   height:100%;
   width:100%;
    
   background-repeat:no-repeat;/*we want to have one single image not a repeated one*/  
   background-size:1150px 630px;/*this sets the image to fullscreen covering the whole screen*/ 
   }
</style>
</head>
<body >



    <div id="mongoDbServlet" class="container">
        <h1 align="center" class="text-primary">Welcome to this Page!</h1>
        <hr />
		<br><br>
		 
        <!------ MONGODB JSP & SERVLET EXAMPLE ------>
        <div id="login_form">
            <form id="user_login_form" name="loginForm" method="post" action="loginServlet" onsubmit="return validate()">
                <!----- LOGIN FORM ------>
                <div class="input-group">
                
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <span id="nameloc">
                    <input type="text" class="form-control" id="mobile"  name="mobile" maxlength="20" placeholder="Enter the registered Mobile Number ...">
                    </span>
                </div>
                <div>&nbsp;</div>
                <div class="input-group">
                
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <span id="passwordloc">
                    <input type="password" class="form-control" id="login_pwd"  name="login_pwd" placeholder="Enter the password ...">
                    </span>
                </div>

                <!----- SUBMIT BUTTON ------>
                <div>&nbsp;</div>
                <button id="submit_btn" type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
        <br>
        <br>
        New to this Page?
        <button type="button" class="btn btn-primary" onclick="signup();">SignUp</button>
        <h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>
    </div>
    <script >
    function signup(){
    	window.location("signup.jsp");
    }
    function validate(){  
    	var name=document.loginForm.mobile.value;  
    	var password=document.loginForm.login_pwd.value;  
    	var status=false;  
    	  
    	if(name.length!=10){  
    		document.getElementById("errMsg").innerHTML=  
    		"  Please enter the registered mobile number";  
    		status=false;  
    	}else if(password.length<8){  
    		document.getElementById("errMsg").innerHTML=  
    		"  Please enter the correct Password";  
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
