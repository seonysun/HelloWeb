package com.sist.service;
import com.sist.dao.*;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCollect {
   public static void main(String[] args) {
      DataCollect dc = new DataCollect();
//      dc.pictureGetData();
//      dc.exhibitionGetData();
   }
   public void pictureGetData() {
     ExhibitionDAO dao=new ExhibitionDAO(); 
      try {
         for(int i = 0; i < 50; i++) {
            Document doc = Jsoup.connect("https://www.opengallery.co.kr/discover/?p= " + i+ " &f_ts=&f_ss=&f_os=&f_ps=&f_ra=false&f_pa=false&r_ex=0&").get();
            Elements link = doc.select("div#discoverList a.discoverCard-a");
            for(int j = 0; j < link.size(); j++) {
               String s = "https://www.opengallery.co.kr" + link.get(j).attr("href");
               Document doc2 = Jsoup.connect(s).get();
               //Element pic = doc2.selectFirst("div.artwork-detail-viewinroom-view img.artwork-detail-viewinroom-view-img");
               Elements pic = doc2.select("img.artwork-detail-image-carousel-image");
               Element title = doc2.selectFirst("h2.artwork-detail-info-title");               
               Element name = doc2.selectFirst("a.artwork-detail-info-artist-link");
               Elements content1 = doc2.select("div.artwork-detail-left-section p");
               
               String s2 = content1.get(0).text();
               if(s2.equals("첫 렌탈이라면 무조건 33,000원!")) {
                  s2 = content1.get(1).text();
               }
               System.out.println(pic.get(0).attr("src"));                  
               System.out.println(title.text());
               System.out.println(name.text());
               System.out.println(s2);        
               System.out.println();
               PictureVO vo = new PictureVO();            
               vo.setImage(pic.get(0).attr("src"));
               vo.setTitle(title.text());
               vo.setName(name.text());
               vo.setContent(s2);
               dao.pictureDetailInsert(vo);
            }
            System.out.println();
         }
      } catch (Exception e) {}
   }
   public void exhibitionGetData() {
      ExhibitionDAO dao= new ExhibitionDAO();
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      try {
         Document doc = Jsoup.connect("https://www.showala.com/ex/ex_list.php").get();
         Elements src = doc.select("a.menu_dep3_link");
         
         for(int i = 1; i < src.size(); i++) {
            if(i == 107 || i == 478 || i == 611 || i == 982 ) continue; // 오류나는부분 스킵
            ExhibitionVO vo = new ExhibitionVO();
            StringTokenizer st = new StringTokenizer("https://www.showala.com" + src.get(i).attr("href"));
            //String s = "https://www.showala.com" + src.get(i).attr("href");
            Document doc2 = Jsoup.connect(st.nextToken()).get();
            
            Element image = doc2.selectFirst("div.exb_img_wrap");
            bw.write(image.attr("style").substring(image.attr("style").indexOf("'") + 1, image.attr("style").lastIndexOf("'"))+"\n");
            vo.setPoster(image.attr("style").substring(image.attr("style").indexOf("'") + 1, image.attr("style").lastIndexOf("'")));
            // System.out.println(image.attr("style").substring(image.attr("style").indexOf("'") + 1, image.attr("style").lastIndexOf("'")));
            Element title = doc2.selectFirst("li.kor_tit");
            bw.write(title.text()+"\n");
            vo.setTitle(title.text());
            // System.out.println(title.text());
            Element title2 = doc2.selectFirst("li.eng_tit");
            bw.write(title2.text()+"\n");
            vo.setTitle2(title2.text());
            // System.out.println(title2.text());
            Element kind = doc2.selectFirst("li.ex_type");
            bw.write(kind.text()+"\n");
            vo.setKind(kind.text());
            //System.out.println(kind.text());
            Element period = doc2.selectFirst("p.des");
            bw.write(period.text()+"\n");
            vo.setPeriod(period.text());
            //System.out.println(period.text());
            Element url = doc2.selectFirst("a.icn_home");
            bw.write(url.attr("href")+"\n");
            vo.setUrl(url.attr("href"));
            //System.out.println(url.attr("href"));            
            
            String loc = "n", loc2 = "n", area = "n", area2 = "n", item = "n", host = "n";
            Elements detail = doc2.select("p.tit");
            for(int j = 2; j < detail.size(); j++) {
               String label = detail.get(j).text();
               if(label.equals("개최장소")) {
                  Element a = doc2.select("p.dimish").get(0);
                  loc = a.text();                  
               } else if (label.equals("사전등록기간")) {
                  continue;
               } else if (label.equals("세부장소")) {
                  Element a = doc2.select("p.dimish").get(1);
                  loc2 = a.text();
               } else if (label.equals("산업분야")) {
                  Element a = doc2.select("p.dimish").get(2);
                  area = a.text();
               } else if (label.equals("전시분야")) {
                  Element a = doc2.select("p.dimish").get(3);
                  area2 = a.text();
               } else if (label.equals("전시품목")) {
                  Element a = doc2.selectFirst("p.dimish03");
                  item = a.text();
               } else if (label.equals("주 최")) {
                  Element a = doc2.select("p.dimish").get(5);
                  host = a.text();
               }
            }
            bw.write(loc + "\n");
            bw.write(loc2 + "\n");
            bw.write(area + "\n");
            bw.write(area2 + "\n");
            bw.write(item + "\n");
            bw.write(host + "\n");
            vo.setLoc(loc);
            vo.setLoc2(loc2);
            vo.setArea(area);
            vo.setArea2(area2);
            vo.setItem(item);
            vo.setHost(host);
            Elements price = doc2.select("p.pre_wrap");
            bw.write(price.get(1).text() + "\n");
            vo.setPrice(price.get(1).text());
            // System.out.println(price.get(1).text());
            Element time = doc2.selectFirst("pre.pre_des");
            bw.write(time.text() + "\n");
            vo.setTime(time.text());
            //System.out.println(time.text());
            Element hashtag = doc2.selectFirst("div.tag_wrap");
            bw.write(hashtag.text() + "\n");
            vo.setHashtag(hashtag.text());
            Element content = doc2.selectFirst("div.tab_con_edit1");
            bw.write(content.text() + "\n");
            vo.setContent(content.text());
            bw.write(i + "\n");
            bw.flush();            
            bw.newLine();
            dao.ExhibitionDetailInsert(vo);
         }
      } catch (Exception e) {}
   }
} 