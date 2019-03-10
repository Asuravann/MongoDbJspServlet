
<!DOCTYPE html>
<html>
<head>
<title>Create Dynamic form Using JavaScript</title>

<link href="form.css" rel="stylesheet" type="text/css">
</head>
<body onload="book()">
<%
String s=null;
String[] list=null;
int l=0;
if(session.getAttribute("booklist")!=null){
	 s=(String)session.getAttribute("booklist");
	 list=s.split(",");
	 l=list.length;
}
	
%>
<div class="main_content">
<!--
========================================================================================
Header Div.
========================================================================================
-->
<div class="first">

</div>
<!--
======================================================================================
This Div is for the Buttons. When user click on buttons, respective field will appear.
=======================================================================================
-->
<div class="two">
<form method="post" action="addressBook">
<button type="submit">Address Book</button>

</form>

</div>
<!--
========================================================================================
This Div is meant to display final form.
========================================================================================
-->
<div class="three">
<h2>Enter recipient's address(es)</h2>
<button onclick="nameFunction()">Add address</button><br>
 <h4 id="errMsg" class="text-danger" align="center">${error_message}</h4>
<input type="text" name="address1" placeholder="Address1">
<form action="#" id="mainform" method="get" name="mainform">

<span id="myForm"></span>
<p></p><input type="submit" value="Submit">
</form>

<form action="#" id="mainform" method="get" name="mainform">

<span id="formN"></span>
</form>

</div>
<!--
========================================================================================
Footer Div.
========================================================================================
-->
<div class="four">

</div>
</div>



<script>

var i = 0; /* Set Global Variable i */
var k = 0;
var l=0;
var b=false;

function increment(){
i += 1; /* Function for automatic increment of field's "Name" attribute. */
}
/*
---------------------------------------------

Function to Remove Form Elements Dynamically
---------------------------------------------

*/
function removeElement(parentDiv, childDiv){
if (childDiv == parentDiv){
alert("The parent div cannot be removed.");
}
else if (document.getElementById(childDiv)){
var child = document.getElementById(childDiv);
var parent = document.getElementById(parentDiv);
parent.removeChild(child);
i=i-1;
}
else{
alert("Child div has already been removed or does not exist.");
return false;
}
}
/*
----------------------------------------------------------------------------

Functions that will be called upon, when user click on the Name text field.

----------------------------------------------------------------------------
*/
function nameFunction(){
	if(i<4){
var r = document.createElement('span');
var y = document.createElement("INPUT");
y.setAttribute("type", "text");
y.setAttribute("placeholder", "Address "+(i+2));
var g = document.createElement("IMG");
g.setAttribute("src", "delete.png");
increment();
y.setAttribute("Name", "address" + i);
r.appendChild(y);
g.setAttribute("onclick", "removeElement('myForm','id_" + i + "')");
r.appendChild(g);
r.setAttribute("id", "id_" + i);
document.getElementById("myForm").appendChild(r);
}else{
	document.getElementById("errMsg").innerHTML="You can only add upto five addresses";  
}
}




function book(){
	
   			
   			var bool='<%=s%>';
   			
   		
   		if( bool!= null){
   			var n='<%=s%>';
   			var list='<%=l%>';
    	for(k=0;k<list;k++){
    		var r = document.createElement('span');
    		var e = document.createElement("p");
    		var y = document.createElement("INPUT");
    		y.setAttribute("type", "checkbox");
    		y.setAttribute("name", ""+(k+1));
    		r.appendChild(y);
    		r.setAttribute("id", "id_" + k);
    		<%out.print(list[0]);%>
    		document.getElementById("formN").appendChild(r);
     	}
   		}
   	//	}
    
	
}


</script>

</body>
</html>