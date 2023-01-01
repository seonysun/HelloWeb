package com.sist.service;
import com.sist.dao.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLConnector_goods {
	public void goodsAllData() {
		try {
			for(int i=1;i<=238;i++) {
				Document doc=Jsoup.connect("https://shop.10000recipe.com/index.php?path=category&sort=popular&page="+i).get();
				Elements link=doc.select("div.s_list_wrap_vt li.common2_sp_list_li a.common2_sp_link");
				/*
				<div class="s_list_wrap_vt">
        		<ul class="common2_sp_list_ul ea3">
				<li class="common2_sp_list_li"><a href="https://shop.10000recipe.com/goods/goods_view.php?goodsNo=1000032512&utm_source=10k_web&utm_medium=category_list&utm_campaign=g1000032512" class="common2_sp_link"></a><div class="common2_sp_thumb"><img src="https://recipe1.ezmember.co.kr/cache/data/goods/22/12/51/1000032512/1000032512_detail_056.jpg"></div>
                  <div class="common2_sp_caption">
                      <div class="common2_sp_caption_tit line2">[美친특가] 테이스티나인 t9 최현석셰프의 스테이크&구이 특가/블랙앵거스 1+1 클리어런스 세일!</div>
                      <div class="common2_sp_caption_price_box"><span class="common2_sp_caption_per"><b>72</b>%</span> <strong class="common2_sp_caption_price"><span>4,900</span><small>원</small></strong></div><div class="common2_sp_caption_rv2">
                            <span class="caption_rv2_img"></span><b class="caption_rv2_pt">4.3</b><span class="caption_rv2_ea">(20)</span>
                        </div><div class="common2_sp_caption_icon"><span class="icon_free">무료배송</span> </div></div></li> 
				*/
				for(int j=0;j<link.size();j++) {
					System.out.println(link.get(j).attr("href"));
					Document doc2=Jsoup.connect(link.get(j).attr("href")).get();
					Element poster=doc2.selectFirst("div.s_view_pic div.s_view_pic_zoom img");
					System.out.println(poster.attr("src"));
					Element title=doc2.selectFirst("div.s_view_info h3 p");
					System.out.println(title.text());
				}
			}
		} catch(Exception ex) {}
	}
	public static void main(String[] args) {
		HTMLConnector_goods hcg=new HTMLConnector_goods();
		hcg.goodsAllData();
	}
}
