package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	    HttpSession session=req.getSession(false); 
	    if(req.getSession(false)==null) {
	    	req.setAttribute("error_message", "You are logged out.");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);	
	    }
		session.invalidate();
		
		req.setAttribute("error_message", "You are logged out.");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
}
