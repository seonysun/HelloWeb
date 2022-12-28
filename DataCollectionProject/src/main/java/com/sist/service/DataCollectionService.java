//데이터 크롤링
/*
			text() <태그명>값</태그명>
			attr() <태그명 속성="값>
			data() <script>값</script>
			html() <태그명>
						<태그명>
						값
						</태그명>
					</태그명>
					
			ex.	<div class="a">
					<ul>
						<li>
							제목 ..
						</li>
						<li>
							제목 ..
						</li>
					</ul>
				</div>
			-> 데이터 긁을 때 li의 데이터 긁을 것이지만 li만 주면 걸리는 태그가 너무 많아질 수 있음
				-> 모아진 div부터 차례로 설정해서 읽기
 */
package com.sist.service;
import com.sist.dao.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCollectionService {
	public void foodCategoryGetData() {
		FoodDAO dao=new FoodDAO();
		try {
			//사이트 연결 -> HTML 페이지 소스 읽기(Document에 저장)
			Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
//			System.out.println(doc.toString());	//소스 toString()으로 확인
			Elements title=doc.select("div.top_list_slide div.info_inner_wrap span.title");
				//Elements : 해당 태그의 속성 여러개 모두 모아주는 것 -> span 태그의 클래스명 title(.title)인 속성 
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
	public void foodDetailData() {
		FoodDAO dao=new FoodDAO();
		try {
			ArrayList<CategoryVO> list=dao.foodCategoryInfoData();
			for(CategoryVO vo:list) {
				Document doc=Jsoup.connect(vo.getLink()).get();
				Elements link=doc.select("section#contents_list span.title a");
					//링크가 사진과 제목 2개 동일하게 데이터 걸려있음 -> a태그 그냥 가져오면 중복으로 2개 가져오므로 span.title 소속 처리 필요
				for(int i=0;i<link.size();i++) {
//					System.out.println(link.get(i).attr("href"));
					//링크 가져오기 -> 링크 안에서 상세 정보 가져옴
					Document doc2=Jsoup.connect("http://www.mangoplate.com"+link.get(i).attr("href")).get();
					//이미지
					Elements image=doc2.select("div.list-photo_wrap img.center-croping");
					String poster="";
					for(int j=0;j<image.size();j++) {
						String s=image.get(j).attr("src");
						poster+=s+"^";
					}
					poster=poster.substring(0,poster.lastIndexOf("^"));
					poster=poster.replace("&", "#");
//					System.out.println(poster);
						//sql에서 &는 입력창이므로 다른 문자로 바꿔서 들여온 다음 다시 바꿔서 연결 필요
					//맛집명,평점
					Element name=doc2.selectFirst("span.title h1.restaurant_name");
					Element score=doc2.selectFirst("strong.rate-point span");
//					System.out.println(name.text()+" "+score.text());
					//주소,전화,음식종류,가격대,시간,메뉴,주차
					String address="no",tel="no",type="no",price="no",time="no",menu="no",parking="no";
					try {
						//th:eq(1)
						Element addr=doc2.selectFirst("table.info tbody tr td");
//						System.out.println(addr.text());
						Element te=doc2.select("table.info tbody tr td").get(1);
						System.out.println(te.text());
						Element ty=doc2.select("table.info tbody tr td").get(2);
						System.out.println(ty.text());
					} catch(Exception ex) {}
				}
			}
		} catch(Exception ex) { }
	}
	public static void main(String[] args) {
		DataCollectionService ds=new DataCollectionService();
		ds.foodDetailData();
	}
}
