
package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcg.mongodb.util.AddUser;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Reading post parameters from the request
		String param1 = req.getParameter("login_id"), 
			   param2 = req.getParameter("login_pwd"),
			   param3 = req.getParameter("phone");
		
		HttpSession session=req.getSession(false);  
		  if(req.getSession(false)==null) {
			  req.setAttribute("error_message","Session expired. Login again!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
		  }
		
		/*		
		String pattern1 = "[0-9]+";
		String pattern2 = "[a-z]+";
		String pattern3 = "(\\W+)";
		
		
		    // Checking for null and empty values
		if(param2.contains(pattern1) && param2.contains(pattern2) ) {*/
			boolean isUserFound = AddUser.searchUserInDb(param1, param2, param3);
			if(isUserFound) {		
				 
				session.setAttribute("user", param1);  
				session.setAttribute("mobile", param3);
				req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
			} else {
				req.setAttribute("error_message", "Number has been registered already. Try logging in.");
				req.getRequestDispatcher("/signup.jsp").forward(req, resp);
			}
		/*}else {
			req.setAttribute("error_message", "Password must contain atleast one digit and one alphabet ");
			req.getRequestDispatcher("/signup.jsp").forward(req, resp);
		}*/
				
	}
}