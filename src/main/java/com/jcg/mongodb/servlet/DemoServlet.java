package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	    
	    String ans=req.getParameter("subject");
		
		if(ans.equalsIgnoreCase("less")) {
req.setAttribute("content", "<form method=post action=\"address\">  \r\n" + 
		"         <div id=\"subform\">\r\n" + 
		"         <h4>Enter Pick-Up Location</h4>\r\n" + 
		"         From : <input type=\"text\" name=\"from_address\" required><br><br>\r\n" + 
		"         <h4 >Enter Recipient's Address(es)</h4> \r\n" + 
		"         Address 1 <input type=\"text\" name=\"address1\"> <input type=\"checkbox\" name=\"add1\"><br>\r\n" + 
		"         Address 2 <input type=\"text\" name=\"address2\"> <input type=\"checkbox\" name=\"add2\"><br>\r\n" + 
		"         Address 3 <input type=\"text\" name=\"address3\"> <input type=\"checkbox\" name=\"add3\"><br>\r\n" + 
		"         Address 4 <input type=\"text\" name=\"address4\"> <input type=\"checkbox\" name=\"add4\"><br>\r\n" + 
		"         Address 5 <input type=\"text\" name=\"address5\"> <input type=\"checkbox\" name=\"add5\"><br>\r\n" + 
		"         <br>\r\n" + 
		"         <br>\r\n" + 

		"         <h4>Choose a time slot for pick-up</h4>\r\n" + 
		"         <input type=\"radio\" name=\"timeslot\" value=\"nine-eleven\" required> 9am-11am<br>\r\n" + 
		"         <input type=\"radio\" name=\"timeslot\" value=\"eleven-one\"> 11am-1pm<br>\r\n" + 
		"         <input type=\"radio\" name=\"timeslot\" value=\"one-three\"> 1pm-3pm<br>\r\n" + 
		"         <input type=\"radio\" name=\"timeslot\" value=\"three-five\"> 3pm-5pm<br>\r\n" + 
		"         <br>\r\n" + 
		"         <input type=\"checkbox\" name=\"sign\"> Recipient's signature required upon delivery. <br><br>\r\n" + 
		"         <button type=\"submit\" >Go</button>\r\n" + 
		"         </div><br>\r\n" + 
		"    </form>");

req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
		}
		else {
		req.setAttribute("error_message", "For bulk service please contact our office. Contact XXXXXXXXXX.");
		req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
		}
	}
}