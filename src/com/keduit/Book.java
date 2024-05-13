package com.keduit;

public class Book {

	private int bookid;
	private String bookname;
	private String publisher;
	private int price;

	Book(int bookid, String bookname, String publisher, int price) {
		this.bookid = bookid;
		this.bookname = bookname;
		this.publisher = publisher;
		this.price = price;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookname() {
		return bookname;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Book[bookid=" + bookid + ",bookname=" + bookname + ",publisher=" + publisher + ",price=" + price + "]";
	}

}
