package com.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LibraryDAO;

/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/return")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("user");
		
		boolean result = LibraryDAO.returnBook(username);
		
		if(result) {
			response.getWriter().println(
					"<script>"
					+ "alert('\""
					+ username
					+"\" thanks for returning book"
					+ ", now you can take another book')"
					+ "</script>"
					);
			request.getRequestDispatcher("index.html").include(request, response);
		}
		else {
			response.getWriter().println(
					"<script>"
					+ "alert('\""
					+ username
					+"\" sorry your name is not found"
					+ ", in our database kindly take a book')"
					+ "</script>"
					);
			request.getRequestDispatcher("index.html").include(request, response);
		}
	}

}
