package com.keduit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Movie {

	// 동물관련업 현황 데이터

	public static void main(String[] args) {
		String key = "30e0e2573ab7893747efbf5c807662b2";
		String result = "";

		try {
			URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
					+ key + "&movieCd=20124039");

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			System.out.println(result);

			JSONParser paser = new JSONParser();
			JSONObject movie = (JSONObject) paser.parse(result);
			JSONObject movieInfoResult = (JSONObject) movie.get("movieInfoResult");
			JSONObject movieInfo = (JSONObject) movieInfoResult.get("movieInfo");
			String movieCd = (String) movieInfo.get("movieCd");
			System.out.println("영화코드: " + movieCd);

			String movieNm = (String) movieInfo.get("movieNm");
			System.out.println("영화명(한글): " + movieNm);
			String movieNmEn = (String) movieInfo.get("movieNmEn");
			System.out.println("영화명(영어): " + movieNmEn);
			String showTm = (String) movieInfo.get("showTm");
			System.out.println("재생시간 : " + showTm);
			String openDt = (String) movieInfo.get("openDt");
			System.out.println("개봉일: " + openDt);
//
			JSONArray nations = (JSONArray) movieInfo.get("nations");
			JSONArray directors = (JSONArray) movieInfo.get("directors");
			JSONArray genres = (JSONArray) movieInfo.get("genres");
			JSONArray actors = (JSONArray) movieInfo.get("actors");

			for (int i = 0; i < nations.size(); i++) {
				JSONObject nation = (JSONObject) nations.get(i);
				String nationNm = (String) nation.get("nationNm");
				System.out.println("제작국명:" + nationNm);
			}
			for (int j = 0; j < directors.size(); j++) {
				JSONObject director = (JSONObject) directors.get(0);
				String peopleNm = (String) director.get("peopleNm");
				System.out.println("감독이름: " + peopleNm);
			}
			for (int a = 0; a < genres.size(); a++) {
				JSONObject genre = (JSONObject) genres.get(a);
				String genreNm = (String) genre.get("genreNm");
				System.out.println("장르: " + genreNm);
			}
			System.out.print("배우이름: ");
			for (int b = 0; b < actors.size(); b++) {
				JSONObject actor = (JSONObject) actors.get(b);
				String peopleNm = (String) actor.get("peopleNm");
				System.out.print(peopleNm + " ");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
