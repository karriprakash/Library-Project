package com.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LibraryDAO;
import com.util.BookBorrowedException;

/**
 * Servlet implementation class GetBookServlet
 */
@WebServlet("/verify")
public class GetBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /*
     * 
     * Create a collection that has book titles and number of copies(hardcode these values). 

  Make sure that there are no duplicate books. 

  When someone borrows a book to read, the person’s name and the book title should be saved in a collection and the number of copies must be reduced. 

 When the person returns the book, the entry must be removed from the collection and number of copies must be added. 

Throw an exception if person borrows a book that he/she has already borrowed and not returned.

 *  Also make sure that the number of copies for each book is same when the application closes.
 * 
 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("user");
//		System.out.println(username);
		String books = request.getParameter("books");
//		System.out.println(books);		
		LibraryDAO lb = new LibraryDAO();
		
		try {
			String res = lb.getBook(username, books);
			response.getWriter().println(
					"<script>"
					+ "alert('"
							+res
							+ "')"
							+ "</script>"
					);
			request.getRequestDispatcher("thank.html").include(request, response);
		} catch (BookBorrowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println(
					"<script>"
					+ "alert('"
							+ username
							+" already having book,"
							+ " please return it to get another book')"
							+ "</script>"
					);
			request.getRequestDispatcher("return.html").forward(request, response);
			
			
		}
	}

}
