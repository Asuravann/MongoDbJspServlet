package com.jcg.mongodb.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.*;

import com.jcg.mongodb.util.AddAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


@WebServlet("/address")
public class AddressServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Reading post parameters from the request
		String  param1 = req.getParameter("address1"), 
				param2 = req.getParameter("address2"),
				param3 = req.getParameter("address3"),
				param4 = req.getParameter("address4"),
			    param5 = req.getParameter("address5"),
			    from_add = req.getParameter("from_address");
		String  check1 = req.getParameter("add1"),
				check2 = req.getParameter("add2"),
				check3 = req.getParameter("add3"),
				check4 = req.getParameter("add4"),
				check5 = req.getParameter("add5"),
				sign = req.getParameter("sign"),
				timeslot = req.getParameter("timeslot");
		String  myName = "";
		String  slot = null;
		String details="";
		int price=15;
		Boolean b=null;
		
		HttpSession session=req.getSession(false);  
		  if(req.getSession(false)==null) {
			  req.setAttribute("error_message","Session expired. Login again!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
		  }
		  
		  
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar cal = Calendar.getInstance();
	        Date date = cal.getTime();
	        cal.setTime(date);
	        cal.add(Calendar.DAY_OF_MONTH,0);
	        date = cal.getTime();
	        String today=dateFormat.format(date);
		
		slot=timeslot.equals("nine-eleven")?"9am-11am":timeslot.equals("eleven-one")?
                "11am-1pm":timeslot.equals("three-five")?"3pm-5pm":"1pm-3pm";
		if(sign!=null && sign.equalsIgnoreCase("on")) {
			b=true;
		}else {
			b=false;
		}
		
		
    	
    	myName = session.getAttribute("mobile").toString();
    	resp.setContentType("text/html");
    	
    	
    	if(check1!=null && check1.equalsIgnoreCase("on")) {
    		AddAddress.addressBook(myName, param1);
    	}
    	if(check2!=null && check2.equalsIgnoreCase("on")) {
    		AddAddress.addressBook(myName, param2);
    	}
    	if(check3!=null && check3.equalsIgnoreCase("on")) {
    		AddAddress.addressBook(myName, param3);
    	}
    	if(check4!= null && check4.equalsIgnoreCase("on")) {
    		AddAddress.addressBook(myName, param4);
    	}
    	if(check5!= null && check5.equalsIgnoreCase("on")) {
    		AddAddress.addressBook(myName, param5);
    	}
    	
    	
			boolean isUserFound = AddAddress.addAddress(myName,param1, param2, param3,param4,param5,b,from_add,slot);
			if(isUserFound) {	
				details=param1+","+param2+","+param3+","+param4+","+param5+","+b+","+from_add+","+price+","+slot+","+today;
				session.setAttribute("details", details);
				req.getRequestDispatcher("/payment.jsp").forward(req, resp);
			} else {
				req.setAttribute("error_message", "Session does not exist");
				req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
			}
				
	}
}
