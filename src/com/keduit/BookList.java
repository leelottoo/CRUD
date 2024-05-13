package com.keduit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookList {

	Connection conn;

	public BookList() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sqldb";
		String userid = "root";
		String pwd = "1234";

		Class.forName(driver);
		System.out.println("드라이버 연결 성공");
		System.out.println("드라이버 연결 준비...");
		conn = DriverManager.getConnection(url, userid, pwd);
		System.out.println("드라이버 연결 성공!");

	}

	private void selectAll() throws SQLException {
		String sql = "select bookid,bookname,publisher,price from book";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.print("\t" + rs.getInt(1));
			System.out.print("\t" + rs.getString(2));
			System.out.print("\t" + rs.getString(3));
			System.out.print("\t" + rs.getInt(4));
			System.out.println();
		}
		rs.close();
		stmt.close();
//		conn.close();
	}

	private void insert() throws Exception {
		Statement stmt;
		String sql = "insert into book(bookid,bookname,publisher,price)" + "values(null,'홍길동전','관악출판',25000)";
		stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		if (result == 1) {
			System.out.println("레코드 추가 성공");
		} else {
			System.out.println("레코드 추가 실패");
		}
		stmt.close();
	}

	private void delete() throws Exception {
		String sql = "delete from book where bookid=9";
		Statement stmt;
		stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		if (result == 1) {
			System.out.println("레코드 삭제 성공");
		} else {
			System.out.println("레코드 삭제 실패..");
		}
		stmt.close();
	}

	private void deleteNum(int num) throws Exception {
		String sql = "delete from book where bookid=?";
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println(num + "번 레코드 삭제 성공");
		} else {
			System.out.println(num + "번 레코드 삭제 실패잉ㅇ");
		}
		pstmt.close();
	}

	private void insertBook(Book book) throws Exception {
		String sql = "insert into book(bookid,bookname,publisher,price)" + " values(?,?,?,?)";
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, book.getBookid());
		pstmt.setString(2, book.getBookname());
		pstmt.setString(3, book.getPublisher());
		pstmt.setInt(4, book.getPrice());
		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println(book.getBookname() + "성공 했다이!");
		} else {
			System.out.println(book.getBookname() + "실패해따잉...ㅠ");
		}
		pstmt.close();

	}

	public static void main(String[] args) throws Exception {

		BookList bl = new BookList();
		bl.selectAll();
//		bl.insert();
//		bl.selectAll();
//		bl.delete();
//		bl.deleteNum(3);

		Book book = new Book(0, "데이터베이스", "한정교출판사", 15000);
		bl.insertBook(book);
		bl.selectAll();

	}

}
