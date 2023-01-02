/* 데이터 크롤링
 *	ex.	<div class="a">
 *			<ul>
 *				<li>
 *					제목 ..
 *				</li>
 *				<li>
 *					제목 ..
 *				</li>"
 *			</ul>
 *		</div>
 *	-> 데이터 긁을 때 li의 데이터 긁을 것이지만 li만 주면 걸리는 태그가 너무 많아질 수 있음
 *	-> 모아진 div부터 차례로 설정해서 읽기
 */
package com.sist.service;
import com.sist.dao.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLConnector_food {
	public void foodCategoryGetData() {
		FoodDAO dao=new FoodDAO();
		try {
			//사이트 연결 -> HTML 페이지 소스 읽기(Document에 저장)
			Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
								//connect() : 주소 연결, get() : 소스 데이터 가져오기
//			System.out.println(doc.toString());	//소스 toString()으로 확인
			Elements title=doc.select("div.top_list_slide div.info_inner_wrap span.title");
				//Elements : 해당 태그의 속성 여러개 모두 모아주는 것 ex. span 태그의 클래스명 title(.title)인 속성 
			Elements subject=doc.select("div.top_list_slide div.info_inner_wrap p.desc");
			Elements poster=doc.select("div.top_list_slide img.center-croping");
			Elements link=doc.select("div.top_list_slide a");
				//a 태그는 클래스가 정의되지 않았으므로 그냥 태그명만 쓰면 됨, 이때 해당 div 안에 a 태그 또 있는지 확인
			for(int i=0;i<title.size();i++) { 
				//데이터 확인용 출력
				System.out.println(i+1);
				System.out.println(title.get(i).text());
				System.out.println(subject.get(i).text());
				System.out.println(poster.get(i).attr("data-lazy"));
				System.out.println(link.get(i).attr("href"));
				System.out.println();
				//DB에 데이터 입력
				CategoryVO vo=new CategoryVO();
				vo.setTitle(title.get(i).text());
				vo.setSubject(subject.get(i).text());
				vo.setPoster(poster.get(i).attr("data-lazy"));
				vo.setLink(link.get(i).attr("href"));
				dao.foodCategoryInsert(vo);
			}
		} catch(Exception ex) { }
	}
	public void foodDetailGetData() {
		FoodDAO dao=new FoodDAO();
		try {
			ArrayList<CategoryVO> list=dao.foodCategoryInfoData();
			for(CategoryVO vo:list) {
				//참조키 설정
				FoodVO fvo=new FoodVO();
				fvo.setCno(vo.getCno());
				System.out.println(vo.getCno()+"."+vo.getTitle());
				//맛집목록 링크 가져오기
				Document doc=Jsoup.connect(vo.getLink()).get();
				Elements link=doc.select("section#contents_list span.title a");
								//목록에서 상세페이지 접근하는 링크 3개(사진, 제목, 더보기 클릭 시) -> 하나만 연결되도록 태그 소속 상세하게 설정 필요
				for(int i=0;i<link.size();i++) {
					//맛집상세 링크 가져오기
					System.out.println(link.get(i).attr("href"));
					Document doc2=Jsoup.connect("http://www.mangoplate.com"+link.get(i).attr("href")).get();
					
					//이미지
					Elements image=doc2.select("div.list-photo_wrap img.center-croping");
					String poster="";
					for(int j=0;j<image.size();j++) {
						String s=image.get(j).attr("src");
						poster+=s+"^";
								//이미지 링크 주소에 없는 기호로 각 이미지 구분
					}
					poster=poster.substring(0,poster.lastIndexOf("^"));
					poster=poster.replace("&", "#");
								//sql에서 &는 입력창이므로 다른 문자로 바꿔서 들여온 다음 다시 바꿔서 연결 필요
					System.out.println(poster);
					fvo.setPoster(poster);
					
					//맛집명,평점
					Element name=doc2.selectFirst("span.title h1.restaurant_name");
					Element score=doc2.selectFirst("strong.rate-point span");
					System.out.println(name.text()+" "+score.text());
					fvo.setName(name.text());
					fvo.setScore(Double.parseDouble(score.text()));
					
					//주소,전화,음식종류,가격대,시간,메뉴,주차
					String address="no",tel="no",type="no",price="no",time="no",menu="no",parking="no";
					Elements detail=doc2.select("table.info tbody tr th");
					for(int j=0;j<detail.size();j++) {
						String label=detail.get(j).text();
						if(label.equals("주소")) {
							Element e=doc2.select("table.info tbody tr td").get(j);
							address=e.text();
						}
						else if(label.equals("전화번호")) {
							Element e=doc2.select("table.info tbody tr td").get(j);
							tel=e.text();
						}
						else if(label.equals("음식 종류")) {
							Element e=doc2.select("table.info tbody tr td").get(j);
							type=e.text();
						}
						else if(label.equals("가격대")) {
							Element e=doc2.select("table.info tbody tr td").get(j);
							price=e.text();
						}
						else if(label.equals("주차")) {
							Element e=doc2.select("table.info tbody tr td").get(j);
							parking=e.text();
						}
						else if(label.equals("영업시간")) {
							Element e=doc2.select("table.info tbody tr td").get(j);
							time=e.text();
						}
						else if(label.equals("메뉴")) {
							Element e=doc2.select("table.info tbody tr td").get(j);
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
					System.out.println("메뉴:"+menu);
					fvo.setAddress(address);
					fvo.setTel(tel);
					fvo.setType(type);
					fvo.setPrice(price);
					fvo.setParking(parking);
					fvo.setTime(time);
					fvo.setMenu(menu);
					/*
					데이터 컬럼 순서를 알고 있을 경우 인덱스 넘버로 그냥 설정할 수도 있음
					Element addr=doc2.selectFirst("table.info tbody tr td");
					System.out.println(addr.text());
					Element te=doc2.select("table.info tbody tr td").get(1);
					System.out.println(te.text());
					Element ty=doc2.select("table.info tbody tr td").get(2);
					System.out.println(ty.text());
					 */
					
					//리뷰
					Element script=doc2.selectFirst("script#reviewCountInfo");
					String s=script.data();
					JSONParser jp=new JSONParser();
					JSONArray arr=(JSONArray)jp.parse(s);
					for(int j=0;j<arr.size();j++) {
						JSONObject obj=(JSONObject)arr.get(j);
						if(j==0) {
							System.out.print("별로에요("+obj.get("count")+")");
							String ss=String.valueOf(obj.get("count"));
							fvo.setBad(Integer.parseInt(ss));
						}
						else if(j==1) {
							System.out.print("괜찮아요("+obj.get("count")+")");
							String ss=String.valueOf(obj.get("count"));
							fvo.setSoso(Integer.parseInt(ss));
						}
						else if(j==2) {
							System.out.print("좋아요("+obj.get("count")+")");
							String ss=String.valueOf(obj.get("count"));
							fvo.setGood(Integer.parseInt(ss));
						}
					}
					//데이터 DB에 입력
					dao.foodDetailInsert(fvo);
				}
			}
		} catch(Exception ex) { }
	}
	public static void main(String[] args) {
		HTMLConnector_food hcf=new HTMLConnector_food();
//		hcf.foodCategoryGetData();
//		hcf.foodDetailGetData();
	}
}
