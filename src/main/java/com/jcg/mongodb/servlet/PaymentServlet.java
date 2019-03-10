package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcg.mongodb.util.PaymentSuccess;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Reading post parameters from the request
		  String param1 = req.getParameter("nameoncard"), 
			   	 param2 = req.getParameter("cardnum"),
			   	 param3 = req.getParameter("expdate"),
			   	 param5 = req.getParameter("store");
		  String myNum="";
		  resp.setContentType("text/html");
		  HttpSession session=req.getSession(false);  
		  if(req.getSession(false)==null) {
			  req.setAttribute("error_message","Session expired. Login again!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
		  }
	      myNum = session.getAttribute("mobile").toString();
	      
		// Checking for User data
	    String u_id = PaymentSuccess.searchUserInDb(myNum);
		if(param5.equalsIgnoreCase("on")) {
			  
			boolean b= PaymentSuccess.addCardDetails(myNum,param1,param2,param3);
			if(b) {
				req.setAttribute("error_message", "Card details saved to your account.");
				session.setAttribute("u_id", u_id);
				req.getRequestDispatcher("/success.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("error_message", "Payment not complete. Try again");
			req.getRequestDispatcher("/payment.jsp").forward(req, resp);
		}	
				
	}
}
