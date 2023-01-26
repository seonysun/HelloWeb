package com.sist.manager;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MusicGetData {
	public static void main(String[] args) {
		try {
			//데이터 크롤링
			Document doc=Jsoup.connect("https://www.genie.co.kr/chart/top200").get();
			Elements title=doc.select("table.list-wrap a.title");
			Elements singer=doc.select("table.list-wrap a.artist");
			Elements album=doc.select("table.list-wrap a.albumtitle");
			Elements poster=doc.select("div.newest-list table.list-wrap a.cover img");
			//크롤링한 데이터 JSON 배열에 담기
			JSONArray arr=new JSONArray();
			for(int i=0;i<title.size();i++) {
				/*
				System.out.println(title.get(i).text()+" "
						+singer.get(i).text()+" "
						+album.get(i).text()+" "
						+poster.get(i).attr("src")+" "
						+youtubeGetKey(title.get(i).text()));
				 */
				JSONObject obj=new JSONObject();
				obj.put("no", i+1);
				obj.put("title", title.get(i).text());
				obj.put("singer", singer.get(i).text());
				obj.put("album", album.get(i).text());
				obj.put("poster", poster.get(i).attr("src"));
				obj.put("key", youtubeGetKey(title.get(i).text()));
				arr.add(obj);
			}
			//JSON Stirng으로 출력
			System.out.println(arr.toJSONString());
		} catch(Exception ex) {}
	}
	public static String youtubeGetKey(String title) {
		String key="";
		try {
			Document doc=Jsoup.connect("https://www.youtube.com/results?search_query="+URLEncoder.encode(title, "UTF-8")).get();
			String data=doc.toString();
			Pattern p=Pattern.compile("/watch\\?v=[^가-힣]+");
			Matcher m=p.matcher(data);
			while(m.find()) {
				String s=m.group();
				key=s.substring(s.indexOf("=")+1,s.indexOf("\""));
				break;
			}
		} catch(Exception ex) {}
		return key;
	}
}
