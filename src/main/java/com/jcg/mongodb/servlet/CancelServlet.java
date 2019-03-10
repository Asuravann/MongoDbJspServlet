package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcg.mongodb.util.AddAddress;
@WebServlet("/remove")
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Reading post parameters from the request
		//String  param1 = req.getParameter("address1");
				
		String  myName = "";
		    	
		HttpSession session=req.getSession(false);  
		  if(req.getSession(false)==null) {
			  req.setAttribute("error_message","Session expired. Login again!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
		  }
    	myName = session.getAttribute("mobile").toString();
    	resp.setContentType("text/html");
    	
    	String n=AddAddress.removeAddress(myName);
    	if(n!="" || n!=" " || n!=null) {
    		req.setAttribute("error_message", "Last transaction has been cancelled!");
			req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
    	}else {
    		req.setAttribute("error_message", "Last transaction was not cancelled!");
			req.getRequestDispatcher("/payment.jsp").forward(req, resp);
    	}
    		
		 	
	}
}
