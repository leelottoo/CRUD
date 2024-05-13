package com.keduit.je;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MiniList {

	Connection conn;

	public MiniList(String driver, String url, String userid, String pwd) throws Exception {

		Class.forName(driver);
		System.out.println("드라이버 연결 성공");
		System.out.println("드라이버 연결 준비...");
		conn = DriverManager.getConnection(url, userid, pwd);
		System.out.println("드라이버 연결 성공!");

	}

	void insertCustomer(MiniProject miniproject) throws SQLException { // 데이터 보내기
		String sql = "insert into minitable (BusinessName,AddressRoad,AddressJibun,BusinessType,ContactNumber)"
				+ " values(?,?,?,?,?)";
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, miniproject.getBusinessName());
		pstmt.setString(2, miniproject.getAddressRoad());
		pstmt.setString(3, miniproject.getAddressJibun());
		pstmt.setString(4, miniproject.getBusinessType());
		pstmt.setString(5, miniproject.getContactNumber());
		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println(miniproject.getBusinessName() + "성공!");
		} else {
			System.out.println(miniproject.getBusinessName() + "실패ㅠ");
		}
		pstmt.close();
	}

	void insert() { // 추가하기

		Scanner sc = new Scanner(System.in);

		System.out.print("사업장명을 입력하세요:\n");
		String businessName = sc.nextLine();

		System.out.print("도로명주소를 입력하세요:\n");
		String addressRoad = sc.nextLine();

		System.out.print("지번주소를 입력하세요:\n");
		String addressJibun = sc.nextLine();

		System.out.print("업종명을 입력하세요:\n");
		String businessType = sc.nextLine();

		System.out.print("연락처를 입력하세요:\n");
		String contactNumber = sc.nextLine();

		// SQL 쿼리문을 작성합니다.
		String sql = "insert into minitable (BusinessName,AddressRoad" + ",AddressJibun,BusinessType,ContactNumber)"
				+ " values(?,?,?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 사용자가 입력한 값이 null이 아닌 경우에만 값을 설정합니다.
			// 값이 null인 경우 setNull 메서드를 사용하여 데이터베이스에 null 값을 넣습니다.
			if (businessName != null) {
				pstmt.setString(1, businessName);
			} else {
				pstmt.setNull(1, java.sql.Types.VARCHAR);
			}

			if (addressRoad != null) {
				pstmt.setString(2, addressRoad);
			} else {
				pstmt.setNull(2, java.sql.Types.VARCHAR);
			}

			if (addressJibun != null) {
				pstmt.setString(3, addressJibun);
			} else {
				pstmt.setNull(3, java.sql.Types.VARCHAR);
			}

			if (businessType != null) {
				pstmt.setString(4, businessType);
			} else {
				pstmt.setNull(4, java.sql.Types.VARCHAR);
			}

			if (contactNumber != null) {
				pstmt.setString(5, contactNumber);
			} else {
				pstmt.setNull(5, java.sql.Types.VARCHAR);
			}

			// SQL 문을 실행하여 데이터베이스에 새로운 레코드를 추가합니다.
			int result = pstmt.executeUpdate();

			// 결과에 따라 성공 또는 실패 메시지를 출력합니다.
			if (result == 1) {
				System.out.println(businessName + " 추가 성공!");
			} else {
				System.out.println(businessName + " 추가 실패ㅠ");
			}
		} catch (SQLException e) {
			System.out.println("접근 권한이 없습니다.");
		}
	}

	void selectOne() throws SQLException { // 한개 조회
		Scanner sc = new Scanner(System.in);

		System.out.print("조회할 사업장명을 입력하세요:\n");
		String searchBusinessName = sc.nextLine();

		String sql = "select BusinessName,AddressRoad,AddressJibun,BusinessType,"
				+ "ContactNumber from minitable where BusinessName like ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + searchBusinessName + "%");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("\t사업자명: " + rs.getString(1));
				System.out.println("\t소재지도로명주소1: " + rs.getString(2));
				System.out.println("\t소재지지번주소: " + rs.getString(3));
				System.out.println("\t업종명: " + rs.getString(4));
				System.out.println("\t연락처: " + rs.getString(5));

				System.out.println();
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("조회 가능한 사업장이 없습니다.");
		}

	}

	void updateCustomer() throws Exception { // 수정하기

		Scanner sc = new Scanner(System.in);

		System.out.print("수정할 사업장 명을 입력하세요:\n");
		String searchBusinessName = sc.nextLine();
		System.out.println();

		// 수정 전에 해당 사업장의 정보 조회
//	    System.out.println("수정 전 정보:");
//	    displayCustomerInfo(searchBusinessName);

		// 사용자로부터 수정할 필드와 새로운 값 입력
		System.out.print("새로운 사업장명을 입력하세요 (수정하지 않을 경우 엔터):\n");
		String newBusinessName = sc.nextLine();

		System.out.print("새로운 도로명주소를 입력하세요 (수정하지 않을 경우 엔터):\n");
		String newAddressRoad = sc.nextLine();

		System.out.print("새로운 지번주소를 입력하세요 (수정하지 않을 경우 엔터):\n");
		String newAddressJibun = sc.nextLine();

		System.out.print("새로운 업종명을 입력하세요 (수정하지 않을 경우 엔터):\n");
		String newBusinessType = sc.nextLine();

		System.out.print("새로운 연락처를 입력하세요 (수정하지 않을 경우 엔터):\n");
		String newContactNumber = sc.nextLine();

		StringBuilder sqlbuilder = new StringBuilder("update minitable set");

		if (!newBusinessName.isEmpty()) {
			sqlbuilder.append(" BusinessName=?,");
		}
		if (!newAddressRoad.isEmpty()) {
			sqlbuilder.append(" AddressRoad=?,");
		}
		if (!newAddressJibun.isEmpty()) {
			sqlbuilder.append(" AddressJibun=?,");
		}
		if (!newBusinessType.isEmpty()) {
			sqlbuilder.append(" BusinessType=?,");
		}
		if (!newContactNumber.isEmpty()) {
			sqlbuilder.append(" ContactNumber=?,");
		}
		// 마지막에 추가된 쉼표 제거
		sqlbuilder.deleteCharAt(sqlbuilder.length() - 1);
		// 수정할 대상을 지정하기 위해 where절 추가.
		sqlbuilder.append(" where BusinessName=?");

		try (PreparedStatement pstmt = conn.prepareStatement(sqlbuilder.toString())) {
			int parameterIndex = 1;

			// 사용자가 입력한 값이 비어있지 않은 경우에만 해당 필드의 값을 설정
			if (!newBusinessName.isEmpty()) {
				pstmt.setString(parameterIndex++, newBusinessName);
			}
			if (!newAddressRoad.isEmpty()) {
				pstmt.setString(parameterIndex++, newAddressRoad);
			}
			if (!newAddressJibun.isEmpty()) {
				pstmt.setString(parameterIndex++, newAddressJibun);
			}
			if (!newBusinessType.isEmpty()) {
				pstmt.setString(parameterIndex++, newBusinessType);
			}
			if (!newContactNumber.isEmpty()) {
				pstmt.setString(parameterIndex++, newContactNumber);
			}

			// WHERE 절에 해당하는 값을 설정
			pstmt.setString(parameterIndex, searchBusinessName);

			// SQL 문 실행
			int result = pstmt.executeUpdate();

			// 결과에 따라 성공 또는 실패 메시지 출력
			if (result == 1) {
				System.out.println("수정 성공!");
			} else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			System.out.println("접근 권한이 없습니다.");
		}
	}

	void deleteNum() throws SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.print("수정할 사업장 명을 입력하세요:\n");
		String deleteName = sc.nextLine();
		System.out.println();

		String sql = "delete from minitable where BusinessName=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, deleteName);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println(deleteName + "삭제 성공!");
			} else {
				System.out.println(deleteName + "삭제 실패..");
			}
		} catch (SQLException e) {
			System.out.println("접근 권한이 없습니다.");
		}
	}

	private static boolean login(String username, String password) {

		return "ljelje8964".equals(username) && "1234".equals(password);
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("로그인 모드를 선택하세요.");
		System.out.println("1.관리자 모드");
		System.out.println("2.일반 모드");
		int mode = sc.nextInt();

		if (mode == 1) {

			System.out.println("로그인을 하세요");
			System.out.print("아이디: ");
			String username = sc.next();
			System.out.print("비밀번호: ");
			String password = sc.next();
			if (login(username, password)) {
				System.out.println("관리자 모드로 들어갑니다.");
				MiniList mini = new MiniList("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/mini", "root",
						"1234");
				int ch;
				do {
					System.out.println("\n메뉴를 선택하세요.");
					System.out.println("\u001B[36m┌──────────────────────────────────────────────────────┐");
					System.out.print("\u001B[36m|\u001B[91m 1.조회하기 \u001B[36m|\u001B[0m");
					System.out.print("\u001B[36m|\u001B[91m 2.수정하기 \u001B[36m|\u001B[0m");
					System.out.print("\u001B[36m|\u001B[91m 3.등록하기 \u001B[36m|\u001B[0m");
					System.out.print("\u001B[36m|\u001B[91m 4.삭제하기 \u001B[36m|\u001B[0m");
					System.out.println("\u001B[36m|\u001B[91m 5.나가기 \u001B[36m|\u001B[0m");
					System.out.println("\u001B[36m└──────────────────────────────────────────────────────┘");

					System.out.print("\u001B[0m선택: ");
					ch = sc.nextInt();

					// 로그인 성공 시 관리자 모드로 진입
					switch (ch) {
					case 1:
						mini.selectOne();
						break;

					case 2:
						mini.updateCustomer();
						break;

					case 3:
						mini.insert();
						break;

					case 4:
						mini.deleteNum();
						break;

					case 5:
						System.out.println("시스템이 종료됩니다.");
						break;
					default:
						System.out.println("로그인에 실패했습니다. 다시 실행해주세요.");

					}
				} while (ch != 5);

			} else {
				System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
			}
		} else if (mode == 2) {
			System.out.println("일반 모드로 진입합니다.");

			MiniList mini = new MiniList("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/mini", "root",
					"1234");
			mini.selectOne(); // 일반 모드에서 selectOne() 메서드 호출
		}
	}
}