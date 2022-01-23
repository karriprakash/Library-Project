package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.BookBean;
import com.library.PersonBean;
import com.util.BookBorrowedException;

public class LibraryDAO {

	 List<BookBean> list;
	static Map<PersonBean, BookBean> pb = new HashMap<PersonBean, BookBean>();;
	
	//Store books data
	{
			
			list = new ArrayList<BookBean>();
			list.add(new BookBean("Harry Potter",10));
			list.add(new BookBean("Java OCA Exam",20));
			list.add(new BookBean("Be a Software Engineer",30));
			list.add(new BookBean("Once upon a training",5));
			list.add(new BookBean("Success",8));
			list.add(new BookBean("Kirack Party",10));
				
	}	
	
	//Return book 
	/*
	 * In the case of return book 
	 * then user data should be deleted from collection
	 *  and book copies should increase
	 * */
	public static boolean returnBook(String personName) {
		
		for(Map.Entry<PersonBean,BookBean> m : pb.entrySet()) {
			if(m.getKey().getName().equalsIgnoreCase(personName)) {
				m.getValue().setCopies(m.getValue().getCopies()+1);
				pb.remove(m.getKey());
				return true;
			}
		}
		return false;
	}
	
	
	//Check book quantity
	
	public  boolean fetchQty(String name) {
		
		for(BookBean book : list) {
			if(name.equals(book.getTitle())) {
				if(book.getCopies()>0) {
					return true;
				}
				else {
					return false;
				}
			}			
		}
		return false;
	}
	
	 
	public  String getBook(String personName,String bookName) throws BookBorrowedException{
		BookBean bb = null;
		
		//Check bookObject in list and give to local reference 
		for(BookBean b : list) {
			if(b.getTitle().equalsIgnoreCase(bookName)) {
				bb =b;
//				System.out.println("Done!");
			}
		}
		
		//If person already taken book kindly throw an exception
		if(!pb.isEmpty()) {
		for(PersonBean b : pb.keySet()) {
			if(b.getName().equalsIgnoreCase(personName)) {
				throw new BookBorrowedException();
			}
		}
		}
		//Check the book quantity available
		boolean check = new LibraryDAO().fetchQty(bookName);
		
		/*If books are available 
		 * and
		 * Person not having any book with him/borrowed
		 * Then create new Person Object and combine with book object
		 * */
		
		if(check) {		
			PersonBean person = new PersonBean(personName); 
			pb.put(person, bb);
			bb.setCopies(bb.getCopies()-1);
			return "Thanks for borowing book" + bookName;
		}
		
		else {
			return bookName + " is not avaliable please try another book";
		}
		
	}
	
}
