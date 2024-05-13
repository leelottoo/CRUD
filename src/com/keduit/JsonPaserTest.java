package com.keduit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// 라이브러리 사용법
public class JsonPaserTest {

	public void jsonparsing1() throws ParseException {
		// 단순한 구조의 json을 파싱하기
		String jsonString = "{" + "\"title\": \"how to get stroage size\","
				+ "\"url\":\"https://section.cafe.naver.com/ca-fe/home\"," + "\"draft\":false," + "\"star\": 10" + "}";

		// jsonQbject를 가져와서 읽어옴.
		JSONParser paser = new JSONParser();
		Object obj = paser.parse(jsonString); // parse()메서드 파싱해준다.
		JSONObject jsonObj = (JSONObject) obj;

		String title = (String) jsonObj.get("title");
		String url = (String) jsonObj.get("url");
		Boolean draft = (Boolean) jsonObj.get("draft");
		Long star = (Long) jsonObj.get("star"); // int로 불가능하다.

		System.out.println("title: " + title + "\nurl: " + url + "\ndraft: " + draft + "\nstar: " + star);

	}

	public void jsonPasing2() throws ParseException {
		String jsonString = "{" + "\"post1\": {" + "\"title\": \"how to get stroage size\","
				+ "\"url\": \"https://code.com/ko/get-free-and-total-size-of-volumes-in-android/\","
				+ "\"draft\": false" + "  }," + "\"post2\": {" + "\"title\": \"Android Q, Scoped Storage\","
				+ "\"url\": \"https://code.com/ko/android-q-scoped-storage/\"," + "\"draft\": false" + "}" + "}";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonString);
		// obj를 JSONObject로 cast
		JSONObject jsonObj = (JSONObject) obj;
		System.out.println(jsonObj);

		Object post1Obj = jsonObj.get("post1");
		JSONObject post1 = (JSONObject) post1Obj;

		String title = (String) post1.get("title");
		String url = (String) post1.get("url");
		boolean draft = (boolean) post1.get("draft");

		System.out.println("title: " + title);
		System.out.println("url: " + url);
		System.out.println("draft:" + draft);
		System.out.println("--------------------");

		Object post2Obj = jsonObj.get("post2");
		JSONObject post2 = (JSONObject) post2Obj;

		String title2 = (String) post2.get("title"); // get("title") 할 때에는 제이슨에서 가지고 오는 것이기 때문에 이름을 바꿔주면 안됨.
		String url2 = (String) post2.get("url");
		boolean draft2 = (boolean) post2.get("draft");

		System.out.println("title: " + title2);
		System.out.println("url: " + url2);
		System.out.println("draft: " + draft2);

	}

	public void jsonParsing3() throws ParseException {
		String jsonString = "{" + "\"posts\": [" + "{" + "\"title\": \"how to get stroage size\","
				+ "\"url\": \"https://code.com/ko/get-free-and-total-size-of-volumes-in-android/\","
				+ "\"draft\": false" + "}," + "{" + "\"title\": \"Android Q, Scoped Storage\","
				+ "\"url\": \"https://code.com/ko/android-q-scoped-storage/\"," + "\"draft\": false" + "}," + "{"
				+ "\"title\": \"How to parse JSON in android\","
				+ "\"url\": \"https://code.com/ko/how-to-parse-json-in-android/\"," + "\"draft\": true" + "}" + "]"
				+ "}";
		// 제이슨 형식의 String을 파싱하자.
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(jsonString);

		// 최상위의 posts를 가지고 오자. (배열)이기 때문에 jsonArray
		JSONArray jsonArr = (JSONArray) jsonObj.get("posts");

		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jobj = (JSONObject) jsonArr.get(i);
			String title = (String) jobj.get("title");
			String url = (String) jobj.get("url");
			boolean draft = (boolean) jobj.get("draft");
			System.out.println("title(" + i + "): " + title);
			System.out.println("url(" + i + "): " + url);
			System.out.println("draft(" + i + "):" + draft);
		}

	}

	public static void main(String[] args) throws ParseException {

		JsonPaserTest jpt = new JsonPaserTest();
		jpt.jsonparsing1();
		System.out.println("---------------------------");
		jpt.jsonPasing2();
		System.out.println("---------------------------");
		jpt.jsonParsing3();

	}

}
