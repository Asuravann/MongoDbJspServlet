<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>History</title>
<style>
body {
   height:100%;
   width:100%;
   background-image:history.jpg ;/*your background image*/  
   background-repeat:repeat;/*we want to have one single image not a repeated one*/  
   background-size:1000px*300px;
   }
</style>
</head>
<body background="history.jpg">
<input type="button" onclick=back() value="Back">
	<%
		String hist_list = null;
       out.print("<center>");
        if(session.getAttribute("user") == null){
    		response.sendRedirect("index.jsp");
    	}else{
    		hist_list = (String) session.getAttribute("hist");
    		
    	}
      
        	String[] list=hist_list.split("END");	
        	for(int i=list.length-2;i>=0;i--){
 				String[] add_list=list[i].split(",");
         		
         		out.print("<h4>Transaction id : ");out.print(add_list[0]);out.print("</h4>");
         		out.print("Transaction date : ");out.print(add_list[2]);
         		out.print("<br>");out.print("<br>");
         		out.print("Amount Paid : ");out.print(add_list[1]);out.print("<br>");
         		out.print("<br>");
         		
         		for(int j=3;j<add_list.length-1;j++){
         			if(!add_list[j].isEmpty()){
             			out.print("Address ");out.print(j-2);out.print(": ");out.print(add_list[j]);
             			out.print("<br>");
             		}	
         		}
         		out.print("<hr>");
         		
         	}
        	out.print("</center>");
       
       %>
 	<h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>
<script>
function back(){
	window.location("welcome.jsp");
}

</script>
</body>
</html>