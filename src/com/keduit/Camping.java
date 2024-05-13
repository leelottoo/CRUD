package com.keduit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Camping {

	public static void main(String[] args) {

		String result = "";

		try {
			URL url = new URL(
					"https://apis.data.go.kr/B551011/GoCamping/basedList?numOfRows=100&pageNo=2&MobileOS=ETC&MobileApp=appApp&serviceKey=Ot%2FVN7htdd792TA4adu9HTHTJuJhxem%2BcT%2FdBJSGbSvGxTKDmTB8dDrqVPMprxpSq1tpWL2KSetgVEslXoVL7Q%3D%3D&_type=json");
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
//			System.out.println(result);
//			System.out.println();

			JSONParser paser = new JSONParser();
			JSONObject jsonObj = (JSONObject) paser.parse(result);
			JSONObject response = (JSONObject) jsonObj.get("response");
			JSONObject body = (JSONObject) response.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");

			System.out.println("------ 캠핑장 안내 ------");
			for (int i = 0; i < item.size(); i++) {
				JSONObject camping = (JSONObject) item.get(i);
				String facltNm = (String) camping.get("facltNm");
				System.out.println("이름: " + facltNm);
				String lineIntro = (String) camping.get("lineIntro");
				System.out.println("소개: " + lineIntro);
				String addr1 = (String) camping.get("addr1");
				System.out.println("주소: " + addr1);
				System.out.println("-----------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
