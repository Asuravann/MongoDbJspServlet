<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Payment page</title>
</head>
<body>
	<%
	out.print("<h4>Order Summary</h4>");
	String user = null;
    String details = null;
    if(session.getAttribute("user") == null || session.getAttribute("details")==null ){
		response.sendRedirect("index.jsp");
	}else{
		user = (String) session.getAttribute("user");
		details = (String)session.getAttribute("details");
		String[] list=details.split(",");
		out.print("<h5>From Address : ");out.print(list[6]);out.print("</h5>");
    	out.print("<h5>Receipient's Address(es) : </h5>");
    	int j=0;
    	for(int i=0;i<5;i++){
     		list[i].replace(",", "");
     		if(list[i]!=null && list[i]!="" && list[i]!=" " && !list[i].isEmpty()){
     			out.print("Address ");out.print(i+1);out.print(" : ");out.print(list[i]);out.print("<br>");
     			j+=1;
     		}
       	}
    	out.print("<br>");
    	out.print("Time slot for pick-up : ");out.print(list[8]);
    	out.print("<br>");
    	if(list[5].equalsIgnoreCase("true")){
    		out.print("<h5>Requires signature from receiver upon delivery.</h5>");	
    	}
    	out.print("Price for one package is Rs.15");out.print("<br>");
    	out.print(j);out.print("*");out.print(list[7]);out.print(" = ");out.print(j*15);
    	out.print("<br>");
    	out.print("<h4>Total amount is Rs.");out.print(j*15);out.print("<h4>");
    	out.print("Transaction date : ");out.print(list[9]);out.print("<br>");
	}
    
	%>
	<hr>
	<h4>Select Payment Method</h4>

	<form>
         <input type = "radio" name = "subject" value = "cash" onclick="cash()"> Cash
         <input type = "radio" name = "subject" value = "card" onclick="card()"> Credit/Debit card
         <br><br>
         <h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>
    </form>
    <br><br>
    <form name="form" method="post" action="payment">  
         
         <h4 >Enter the Card Details</h4>
         Card Number <input type="text" name="cardnumber"><br>
         Name on Card <input type="text" name="nameoncard"><br>
         Expiry Date <input type="text" name="expdate"><br>
         CVV <input type="text" name="cvv"><br>
         <br>
         save this card information<input type="checkbox" name="store">
         <button type="submit" onclick="return validate()">Proceed</button>
         <br>
    </form>
    <br><br>
    <div id="rem">
    <form method="post" action="remove">
    
    <input type="submit" onclick="back()" value="Cancel Transaction">
      </form>
      </div>
	<script>
		function cash(){
			document.getElementById("errMsg").innerHTML=  
    			"  Your selected Payment method is Cash  ";  
			//window.location("success.jsp");
		}
		function card(){
			document.getElementById("errMsg").innerHTML=  
    			"  Your selected Payment method is Card  "; 	
		}
		function validate(){
			var name=document.form.nameoncard.value;  
    		var num=document.form.cardnumber.value;  
    		var status=false;  
    	  
    		if(name.length<5){  
    			document.getElementById("errMsg").innerHTML=  
    			"  Please enter the full name";  
    			document.form.nameoncard.focus();
    			status=false;  
    		}else if(num.length!=16){  
    			document.getElementById("errMsg").innerHTML=  
    			"  Please enter the full number";
    			document.form.cardnumber.focus();
    			status=false;  
    		}else{  
    			document.getElementById("errMsg").innerHTML=" ";  
    			status=true;
    			if(confirm('Do you want to proceed with this transaction?')){
    				status=true;
    			}else{
    				status=false;
    			}
    		}  
    	return status;  
    	}  
	
		function back(){
			if (confirm('Are you sure you want to cancel this transaction?')) {
			    // Save it!
			    
			    window.location("welcome.jsp");
			} else {
			    // Do nothing!
			}
			
		}
	</script>

</body>
</html>