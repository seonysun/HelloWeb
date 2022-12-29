package com.sist.service;
import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {
	public void htmlGetData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\Users\\user\\git\\repository\\DataCollectionProject\\src\\main\\webapp\\data\\data1.html"));
							 //connect() : URL 연결, parse() : 파일 열기
			Elements h1=doc.select("div h1");
									//selector : 태그 안에서 가져올 값을 지정하는 역할
				//Element : 1개 -> 상세보기, Elements : 여러개 -> 목록
			System.out.println(h1.toString());
								//태그, 속성, 데이터 전체 가져옴
			for(int i=0;i<h1.size();i++) {
				System.out.println(h1.get(i).text()); 
									//태그 안에 있는 데이터만 가져옴
			}
			System.out.println();
			Element name=doc.select("div.a h1").get(2);
												//인덱스 0부터 시작
			System.out.println(name.text());
		} catch(Exception ex) {}
	}
	//구분이 없는 경우 데이터 가져오기 <table> => <td>
	public void foodDetailData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\Users\\user\\git\\repository\\DataCollectionProject\\src\\main\\webapp\\data\\data2.html"));
			Elements detail=doc.select("table.info tbody tr th");
			String address="", tel="",type="",price="",parking="",time="",menu="";
				//데이터가 없는 경우도 있으므로 기본값 공백으로 설정
			for(int i=0;i<detail.size();i++) {
				String label=detail.get(i).text();
				if(label.equals("주소")) {
					Element e=doc.select("table.info tbody tr td").get(i);
					address=e.text();
				}
				else if(label.equals("전화번호")) {
					Element e=doc.select("table.info tbody tr td").get(i);
					tel=e.text();
				}
				else if(label.equals("음식 종류")) {
					Element e=doc.select("table.info tbody tr td").get(i);
					type=e.text();
				}
				else if(label.equals("가격대")) {
					Element e=doc.select("table.info tbody tr td").get(i);
					price=e.text();
				}
				else if(label.equals("주차")) {
					Element e=doc.select("table.info tbody tr td").get(i);
					parking=e.text();
				}
				else if(label.equals("영업시간")) {
					Element e=doc.select("table.info tbody tr td").get(i);
					time=e.text();
				}
				else if(label.equals("메뉴")) {
					Element e=doc.select("table.info tbody tr td").get(i);
					menu=e.text();
				}
			}
			String addr1=address.substring(0,address.indexOf("지"));
			String addr2=address.substring(address.indexOf("지")+3);
			System.out.println("주소:"+addr1);
			System.out.println("지번:"+addr2);
			System.out.println("전화번호:"+tel);
			System.out.println("음식 종류:"+type);
			System.out.println("가격대:"+price);
			System.out.println("주차:"+parking);
			System.out.println("영업시간:"+time);
			StringTokenizer st=new StringTokenizer(menu,"원");
			while(st.hasMoreTokens()) {
				System.out.println(st.nextToken()+"원");
			}
		} catch(Exception ex) {}
	}
	//속성값 읽기
	public void foodAttributeData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\Users\\user\\git\\repository\\DataCollectionProject\\src\\main\\webapp\\data\\data3.html"));
			Elements image=doc.select("div.list-photo_wrap img.center-croping");
			System.out.println(image.toString());
			for(int i=0;i<image.size();i++) {
				System.out.println(image.get(i).attr("src"));
										//속성값 출력 attr()
										//image 태그 내의 src 속성만 추출
			}
		} catch(Exception ex) {}
	}
	//HTML 읽기
	public void htmlData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\Users\\user\\git\\repository\\DataCollectionProject\\src\\main\\webapp\\data\\data4.html"));
			Element div=doc.selectFirst("div.a");
			/* 
			 <div class="a">
			  	<span>Hello</span>
			  	<span>
			  		<p>HTML/CSS</p>
			  	</span>
			  
			  => doc.select("div.a") text() => Hello, HTML/CSS
			  => doc.select("div.a") html() => <span>Hello</span>
											  	<span>
											  		<p>HTML/CSS</p>
											  	</span>
			 */
			System.out.println(div.text());
								//데이터 출력 text()
			System.out.println(div.html());
								//HTML 출력 html()
		} catch(Exception ex) {}
	}
	//자바스크립트 읽기
	public void scriptData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\Users\\user\\git\\repository\\DataCollectionProject\\src\\main\\webapp\\data\\data5.html"));
			Element script=doc.selectFirst("script#reviewCountInfo");
			System.out.println(script.data());
								//스크립트 데이터 출력 data()
			
			/* 1. HTML 데이터 추출 : HTMLParser -> Jsoup
			 * 2. JSON 데이터 추출 : JSONParser
			 * 	- ArrayList []
			 * 	- VO {}
			 * 3. XML 데이터 추출 : DocumentBuilder
			 * */
			String s=script.data();
			JSONParser jp=new JSONParser();
			JSONArray arr=(JSONArray)jp.parse(s);
					//스크립트 시작 기호 : [] Array, {} Object
			for(int i=0;i<arr.size();i++) {
				JSONObject obj=(JSONObject)arr.get(i);
				if(i==0)
					System.out.print("별로에요("+obj.get("count")+")");
				else if(i==1)
					System.out.print("괜찮아요("+obj.get("count")+")");
				else if(i==2)
					System.out.print("좋아요("+obj.get("count")+")");
			}
		} catch(Exception ex) {}
	}
	public static void main(String[] args) {
		HTMLParser hp=new HTMLParser();
//		hp.htmlGetData();
//		hp.foodDetailData();
//		hp.foodAttributeData();
//		hp.htmlData();
//		hp.scriptData();
	}
}
