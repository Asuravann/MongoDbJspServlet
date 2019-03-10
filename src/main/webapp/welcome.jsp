<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>MongoDb Servlet Example</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- CSS File -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
<link type="text/css" href="resource/css/csspage.css" rel="stylesheet">
</head>
<body onload="book()">
	
       <%
		String user = null;
       String book_list = null;
       String[] list=null;
       int l=0;
        if((request.getSession(false))==null){
        	response.sendRedirect("index.jsp");
        }else{
        

        if(session.getAttribute("user") == null){
    		response.sendRedirect("index.jsp");
    	}else{
    		user = (String) session.getAttribute("user");
    		if(session.getAttribute("booklist")==null){
    			
    		}else{
    			book_list = (String)session.getAttribute("booklist");
    			list=book_list.split(",");
           	 	l=list.length;
    		}
    	}
        }
        
    	
        
       %>

	<div id="first">
		<form method=post action="logout">
			<button type="submit">Logout</button>
		</form>
	<form method=post action="history">
 			<button type="submit" >View History</button>
 		</form>
	
		<h2 align="center" class="text-success">Welcome <%out.print(user);  %>!</h2>    
		
	</div>
	
	
   <div id="two">
    <form name="fom" method="post" action="demo">
        	<input type = "radio" name = "subject"  id="l1" value = "less" required > Less than 5<br>
         	<input type = "radio" name = "subject"  id="l2" value = "more" required> More than  5<br><br>
         	<input type="submit" onclick="return address()"  value="Proceed">
    </form>
         	<h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>
    	
    	
    	
    	<span id="myForm"></span>
    	
    	<h4 id="errMsg" >${content}</h4>
    	
  </div>
  
  <div id="three">
		
		<span id="formN"></span>
  </div>

      
	
<script>
function address(){

	
	if(document.getElementById('l2').checked){

	
		document.getElementById("errMsg").innerHTML="For bulk service please contact our office. Contact XXXXXXXXXX.";
	
		
		
	
	}
	return true;
}


var i = 0; /* Set Global Variable i */
var k = 0;
var l=0;
var j=null;
var b=false;



function book(){
	       		
   			var l='<%=l%>';
   			if( l!=0){
    			for(k=0;k<l;k++){
    			var r = document.createElement('span');
    			var e = document.createElement("p");
    			var y = document.createElement("INPUT");
    			
    			y.setAttribute("type", "checkbox");
    			y.setAttribute("onclick", "add("+k+")");
    			y.setAttribute("name", "add_check"+(k+1));
    			r.appendChild(e);
    			r.appendChild(y);
    			r.setAttribute("id", "id_" + k);
    			document.getElementById("errMsg").innerHTML="select the address(es) you want to add";
    			
    			document.getElementById("formN").appendChild(r);
     			}
   			}	
    
	
}


function add(j){


	// nameFunction(j);
	document.getElementById("address"+j).value=j; 
}


</script>
</body>
</html>