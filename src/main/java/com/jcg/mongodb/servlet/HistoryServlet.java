package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcg.mongodb.util.History;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

			
		String myNum="";
		resp.setContentType("text/html");
		HttpSession session=req.getSession(false);  
		  if(req.getSession(false)==null) {
			  req.setAttribute("error_message","Session expired. Login again!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
		  }
		myNum=session.getAttribute("mobile").toString();
		
	    	String u_id=null;
	    	u_id = History.searchHistInDb(myNum);
			
			if(u_id=="No History of Addresses") {
				req.setAttribute("error_message", u_id);
				session.setAttribute("hist","No History of Addresses");
			}else {
				session.setAttribute("hist", u_id);
			}
			req.getRequestDispatcher("/history.jsp").forward(req, resp);
		
	}
}
