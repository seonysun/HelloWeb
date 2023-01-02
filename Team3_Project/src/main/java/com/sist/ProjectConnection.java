package com.sist;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProjectConnection {
	public void exibDetailData() {
		try {
			Document doc=Jsoup.connect("https://www.showala.com/ex/ex_list.php").get();
			Elements link=doc.select("div.gnb_sub_category ul.inner_list_wrap li a.menu_dep3_link");
			for(int i=0;i<=link.size();i++) {
				String s="https://www.showala.com"+link.get(i).attr("href");
				System.out.println(s);
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
		ProjectConnection pc=new ProjectConnection();
//		pc.picData();
		pc.exibDetailData();
	}

}
