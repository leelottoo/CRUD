package com.keduit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerList {

	static Connection conn;

	public CustomerList() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sqldb";
		String userid = "root";
		String pwd = "1234";

		Class.forName(driver);
		System.out.println("연결 성공~");
		System.out.println("연결 준비중..");
		conn = DriverManager.getConnection(url, userid, pwd);
		System.out.println("연결 성공~!");
	}

	private void selectAll() throws SQLException {
		String sql = "select custid,name,address,phone from customer";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.print("\t" + rs.getInt(1));
			System.out.print("\t" + rs.getString(2));
			System.out.print("\t" + rs.getString(3));
			System.out.print("\t" + rs.getString(4));

			System.out.println();
		}
		rs.close();
		stmt.close();
//		conn.close();
	}

	private void selectOne() throws SQLException {
		String sql = "select custid,name,address,phone from customer where name like '박지성'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.print("\t" + rs.getInt(1));
			System.out.print("\t" + rs.getString(2));
			System.out.print("\t" + rs.getString(3));
			System.out.print("\t" + rs.getString(4));

			System.out.println();
		}
		rs.close();
		stmt.close();
//		conn.close();
	}

	private void insert() throws Exception {
		Statement stmt;
		String sql = "insert into customer(custid,name,address,phone)" + "values(null,'권영중','대한민국 서울','010-3674-3015')";
		stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		if (result == 1) {
			System.out.println("레코드 추가 성공!");
		} else {
			System.out.println("레코드 추가 실패..");
		}
		stmt.close();
	}

	private void insertCustomer(Customer customer) throws SQLException {
		String sql = "insert into customer(custid,name,address,phone)" + " values(?,?,?,?)";
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, customer.getCustid());
		pstmt.setString(2, customer.getName());
		pstmt.setString(3, customer.getAddress());
		pstmt.setString(4, customer.getPhone());
		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println(customer.getName() + "성공!");
		} else {
			System.out.println(customer.getName() + "실패ㅠ");
		}
		pstmt.close();
	}

	private void deleteNaum(int num) throws Exception {
		String sql = "delete from customer where custid=?";
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println(num + "번 삭제 성공!");
		} else {
			System.out.println(num + "번 삭제 실패..");
		}
		pstmt.close();
	}

	private void updateCustomer() throws Exception {

		String sql = "update customer set name='이지은' where name='박지성'";
		Statement stmt;
		stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		if (result == 1) {
			System.out.println("수정 성공!");
		} else {
			System.out.println("수정 실패");
		}
		stmt.close();

	}

	public static void main(String[] args) throws Exception {

		CustomerList cl = new CustomerList();

		System.out.println("1. 목록 조회하기");
		cl.selectAll();
//		System.out.println("2. 1건 조회하기");
//		cl.selectOne();
//		System.out.println("3. 레코드 추가하기");
//		cl.insert();
//		cl.selectAll();
//		System.out.println("4. 삭제하기");
//		cl.deleteNaum(7);
//		System.out.println("5. 이름 수정하기");
//		cl.updateCustomer();
//		cl.selectAll();

		Scanner sc = new Scanner(System.in); // 1.스캐너로 입력받을 때에 스캐너 생성해주고

		System.out.print("name을 입력하세요:\n"); // 2. 작성해주고
		String name = sc.nextLine();
		System.out.println("address를 입력하세요:\n");
		String address = sc.nextLine();
		System.out.print("phone을 입력하세요:\n");
		String phone = sc.nextLine();

		Customer customer = new Customer(0, name, address, phone); // 3. 객체 생성을 해준 후 (위치 중요 객체 생성이 위로 올라가있으며 안됨)
		cl.insertCustomer(customer); // 4. 호출을 해줘야함.
		cl.selectAll();

	}
}
