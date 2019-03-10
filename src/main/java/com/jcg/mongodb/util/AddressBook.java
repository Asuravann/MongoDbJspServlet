package com.jcg.mongodb.util;

import java.io.IOException;

//import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


@WebServlet("/addressBook")
public class AddressBook extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	String  myName = "";
		    	
    	HttpSession session = req.getSession(false);
    	myName = session.getAttribute("mobile").toString();
    	//myName="9842223755";
    	resp.setContentType("text/html");
    	
    	
    	
			
		  
			String list = AddAddress.getAddress(myName);
			if(list==null || list=="" || list==" ") {
				req.setAttribute("error_message", "No address has been saved in the adddress book");
			}else {
				session.setAttribute("booklist", list);
			}
				
			req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
			 
		}		
	
}

