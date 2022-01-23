package com.util;

public class BookBorrowedException extends Exception {

	@Override
	public String getMessage() {
		return "You have already take the book";
	}
}
