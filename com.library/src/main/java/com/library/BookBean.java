package com.library;



public class BookBean {

	String title;
	int copies;
	
	public BookBean(String title, int copies) {
		super();
		this.title = title;
		this.copies = copies;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	
	
}
