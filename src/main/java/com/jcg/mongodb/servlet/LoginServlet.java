package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.*;

import com.jcg.mongodb.util.LoginUtil;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Reading post parameters from the request
		  String param1 = req.getParameter("mobile"), 
			   	 param2 = req.getParameter("login_pwd");

		  HttpSession session=req.getSession(false);  
		  if(req.getSession(false)==null) {
			  req.setAttribute("error_message","Session expired. Login again!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
		  }
	      
		// Checking for User data
		String UserFound = LoginUtil.searchUserInDb(param1, param2);
		if(UserFound!="Mobile Number not registered. Please SignUp!" && UserFound!="Username or Password is wrong") {
			
			session.setAttribute("user", UserFound);  
			session.setAttribute("mobile", param1);
			req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
		} else {
			req.setAttribute("error_message",UserFound);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}	
				
	}
}