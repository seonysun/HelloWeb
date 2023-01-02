package com.sist.service;
import java.util.*;
import com.sist.dao.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCollect_Trial {
	public void exhibitionGetData() {
		ExhibitionDAO dao=new ExhibitionDAO();
		try {
			Document doc=Jsoup.connect("https://www.showala.com/ex/ex_list.php").get();
			Elements link=doc.select("div.gnb_sub_category ul.inner_list_wrap li a.menu_dep3_link");
			for(int i=0;i<=link.size();i++) {
				ExhibitionVO vo=new ExhibitionVO();
//				if(i==107 || i==478 || i==611 || i==982 ) continue; // 오류나는부분 스킵
	            
				System.out.println(link.get(i).attr("href"));
				Document doc2=Jsoup.connect("https://www.showala.com"+link.get(i).attr("href")).get();
				
				Element image=doc2.selectFirst("div.exb_img_wrap");
				int start=image.attr("style").indexOf("'")+1;
				int end=image.attr("style").lastIndexOf("'");
				String poster=image.attr("style").substring(start, end);
				System.out.println(poster);
//				vo.setPoster(poster);
				
				Element title=doc2.selectFirst("li.kor_tit");
				Element title2=doc2.selectFirst("li.eng_tit");
				Element kind=doc2.selectFirst("li.ex_type");
				System.out.println(title.text()+"\n"+title2.text()+"\n"+kind.text());
//				vo.setTitle(title.text());
//				vo.setTitle2(title2.text());
//				vo.setKind(kind.text());
				
				String period="",url="",loc="",loc2="",area="",area2="",item="",host="";
				Elements detail=doc2.select("p.tit");
				for(int j=0;i<detail.size();j++) {
					String label=detail.get(j).text();
					if(label.equals("전시기간")) {
						Element e=doc2.selectFirst("p.des");
						period=e.text();
					} else if(label.equals("개최장소")) {
						Element e=doc2.select("p.dimish").get(0);
						loc=e.text();
					} else if(label.equals("세부장소")) {
						Element e=doc2.select("p.dimish").get(1);
						loc2=e.text();
					} else if(label.equals("산업분야")) {
						Element e=doc2.select("p.dimish").get(2);
						area=e.text();
					} else if(label.equals("전시분야")) {
						Element e=doc2.select("p.dimish").get(3);
						area2=e.text();
					} else if(label.equals("전시품목")) {
						Element e=doc2.selectFirst("p.dimish03");
						item=e.text();
					} else if(label.equals("홈페이지")) {
						Element e=doc2.selectFirst("p.dimish a");
						url=e.attr("href");
					} else if(label.equals("주 최")) {
						Element e=doc2.select("p.dimish").get(5);
						host=e.text();
					}
					System.out.println(period);
					System.out.println(loc+" "+loc2);
					System.out.println(area+" "+area2);
					System.out.println(item);
					System.out.println(url);
					System.out.println(host);
					System.out.println();
				}
				/*
				<div class="container ex_container_top">
			<div class="exb_img_sns">
				<div class="exb_img_wrap" style="background-image:url('https://www.showala.com/data/ex/XqEuQarMD1')">
				</div>
								<div class="exb_sns_wrap">
					<p class="tit">공식채널</p>
											<a href="http://www.ddp.or.kr/index.html?menuno=230&siteno=" class="icn_home" target="_blank">홈페이지</a>
										
										
										
											<a href="https://www.instagram.com/p/CkNZhxttY2K/?utm_sourc" class="icn_insta" target="_blank">인스타그램</a>
									</div>
							</div>
	 	  <div class="ba_info">
			 <ul>
				<li class="kor_tit"><우주 백패킹> 2022 서울라이트 차세대 미디어아티스트 전시</li>
				<li class="eng_tit">Backpacking to the Universe</li>
				<li class="etc_tit">우주 백패킹</li>
			 				<li class="ex_type">일반전시회 (B2C)</li> 
						<li class="ba_info_br"></li>
							<li class="date clearfix">
						<p class="tit">전시기간</p>
						<p class="des">2022-11-01 ~ 2023-01-29</p>
				</li>
				<li class="where clearfix">
						<p class="tit">개최장소</p>
						<p class="des dimish">동대문디자인플라자 (DDP)</p>
				</li>
				<li class="where clearfix">
						<p class="tit">세부장소</p>
						<p class="des dimish">DDP 디자인랩 1층 미디어아트갤러리, 투명 미디어월</p>
				</li>
				<li class="work_cate clearfix">
						<p class="tit">산업분야</p>
						<p class="des dimish">문화/예술</p>
				</li>
				<li class="ex_cate clearfix">
						<p class="tit">전시분야</p>
						<p class="des dimish">이벤트산업|문화행사</p>
				</li>
				<li class="ex_merc clearfix">
						<p class="tit">전시품목</p>
						<p class="des pre_wrap  dimish03"></p>
				</li>
				<li class="homp clearfix">
						<p class="tit">홈페이지</p>
						<p class="des dimish">
							<a href="http://www.ddp.or.kr/index.html?menuno=230&siteno=" target="_blank" class="des exdetail_li_link">http://www.ddp.or.kr/index.html?menuno=230&siteno=</a>
						</p>
				</li>
				<li class="opener clearfix">
						<p class="tit">주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;최</dt>
						<p class="des dimish"> 서울디자인재단 Seoul Design Foundation</p>
				</li>
				<li class="opener2 clearfix">
						<p class="tit">주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;관</dt>
						<p class="des dimish"></p>
				</li>
				<li class="found clearfix">
						<p class="tit">후&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;원</dt>
						<p class="des dimish"></p>
				</li>
			 </ul>
				*/
			}
		} catch(Exception ex) {}
	}
	public void picData() {
		try {
			for(int i=0;i<=769;i++) { //총 770pg(0~769) 데이터 46211개
				Document doc=Jsoup.connect("https://www.opengallery.co.kr/discover/?p="+i+"&f_ts=&f_ss=&f_os=&f_ps=&f_ra=false&f_pa=false&r_ex=0&").get();
				Elements link=doc.select("div.discoverCard a.discoverCard-a");
				/*
				<div class="discoverCard" data-discover-index="0">
                        <a class="discoverCard-a" href="/artwork/A0172-0114/" data-code="A0172-0114">
				*/
				for(int j=0;j<link.size();j++) {
					String s="https://www.opengallery.co.kr"+link.get(j).attr("href");
					System.out.println(s);
					Document doc2=Jsoup.connect(s).get();
					Element pic=doc2.selectFirst("div.og-container div.artwork-detail-viewinroom-view img.artwork-detail-viewinroom-view-img");
					System.out.println(pic.attr("src"));
					/*
				 <div class="og-container artwork-detail-left">
                    <div class="artwork-detail-left-section">
                        <h3 class="artwork-detail-left-section-title">작품 걸어보기</h3>
                        <div class="artwork-detail-viewinroom">
                            <div class="artwork-detail-viewinroom-left">
                                <div class="artwork-detail-viewinroom-view ">
                                    <img src="https://og-data.s3.amazonaws.com/media/artworks/half/A0172/A0172-0114.jpg" class="artwork-detail-viewinroom-view-img"
                                         style="width: 28.228228%;"
                                    />
					*/
					Element title=doc2.selectFirst("div.og-container h2.artwork-detail-info-title");
					System.out.println(title.text());
					Elements detail=doc2.select("div.og-container div.artwork-detail-info-row-left");
					System.out.println(detail.text());
					String artist="",work="",code="";
					for(int k=0;k<detail.size();k++) {
						String label=detail.get(k).text();
						if(label.equals("작가")) {
							Element e=doc.select("div.og-container a.artwork-detail-info-artist-link").get(k);
							artist=e.text();
						}
						else if(label.equals("작품정보")) {
							Element e=doc.select("div.og-container div.artwork-detail-info-row-right").get(k);
							work=e.text();
						}
						else if(label.equals("작품코드")) {
							Element e=doc.select("div.og-container div.artwork-detail-info-row-right").get(k);
							code=e.text();
						}
					}
					System.out.println("작가:"+artist);
					System.out.println("작품정보:"+work);
					System.out.println("작품코드:"+code);
					/*
					 <div class="og-container artwork-detail-info">
	                
	                    <div class="artwork-detail-info-status blue">렌탈/전시중</div>
	                
	                <h2 class="artwork-detail-info-title">공간질료(matter of space) no.101</h2>
	                <div class="artwork-detail-info-row">
	                    <div class="artwork-detail-info-row-left">작가</div>
	                    <div class="artwork-detail-info-row-right">
	                        <a href="/artist/A0172/"
	                           class="artwork-detail-info-artist-link"
	                        >
	                            단이상
	                        </a>
	                    </div>
	                </div>
	                <div class="artwork-detail-info-row">
	                    <div class="artwork-detail-info-row-left">작품정보</div>
	                    <div class="artwork-detail-info-row-right">
	                        리넨에 유채<br />
	                        120x94cm (50호), 2018
	                    </div>
	                </div> 
					*/
				}
			}
		} catch(Exception ex) {}
	}
	public static void main(String[] args) {
		DataCollect_Trial pc=new DataCollect_Trial();
//		pc.picData();
		pc.exhibitionGetData();
	}

}
