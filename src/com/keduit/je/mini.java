package com.keduit.je;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class mini {

	Connection conn;

	public static void main(String[] args) throws Exception {
		String key = "Ot/VN7htdd792TA4adu9HTHTJuJhxem+cT/dBJSGbSvGxTKDmTB8dDrqVPMprxpSq1tpWL2KSetgVEslXoVL7Q==";
		String result = "";
		List<MiniProject> ml = new ArrayList<>();
		MiniList minilist = new MiniList("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/mini", "root",
				"1234");

		URL url;
		try {
			url = new URL(
					"https://api.odcloud.kr/api/15037875/v1/uddi:412c57bc-afc5-43e6-a76c-a4358f45e4d5?page=1&perPage=100&returnType=json&serviceKey=Ot%2FVN7htdd792TA4adu9HTHTJuJhxem%2BcT%2FdBJSGbSvGxTKDmTB8dDrqVPMprxpSq1tpWL2KSetgVEslXoVL7Q%3D%3D");
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = br.readLine();
//			System.out.println(result);

			JSONParser paser = new JSONParser();
			JSONObject jobj = (JSONObject) paser.parse(result);
			JSONArray data = (JSONArray) jobj.get("data");

			for (int a = 0; a < data.size(); a++) {
				JSONObject arr = (JSONObject) data.get(a);
				String BusinessName = (String) arr.get("사업장명");
				String AddressRoad = (String) arr.get("소재지도로명주소");
				String AddressJibun = (String) arr.get("소재지지번주소");
				String BusinessType = (String) arr.get("업종명");
				String ContactNumber = (String) arr.get("연락처");
//				System.out.println("사업자명: " + BusinessName + "\n도로명: " + AddressRoad + "\n동주소: " + AddressJibun
//						+ "\n업종명: " + BusinessType + "\n연락처: " + ContactNumber);
				MiniProject mp = new MiniProject(BusinessName, AddressRoad, AddressJibun, BusinessType, ContactNumber);
				ml.add(mp);
//				MiniList ml = new MiniList();
//				ml.insertCustomer(mp);
			}

			for (MiniProject mini : ml) {
				minilist.insertCustomer(mini);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
