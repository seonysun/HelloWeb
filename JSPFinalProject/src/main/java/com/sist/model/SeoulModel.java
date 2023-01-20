package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class SeoulModel {
	@RequestMapping("seoul/seoul_list.do")
	public String seoul_list(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		String type=request.getParameter("type");
		SeoulDAO dao=new SeoulDAO();
		ArrayList<SeoulVO> list=dao.seoulListData(curpage, Integer.parseInt(type));
		if(type.equals("5")) {
			for(SeoulVO vo:list) {
				vo.setPoster("https://korean.visitseoul.net"+vo.getPoster());
			}
		}
		int totalpage=dao.seoulTotalPage(Integer.parseInt(type));
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		request.setAttribute("list", list);
		request.setAttribute("type", type);
		request.setAttribute("curpage", curpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalpage", totalpage);
		String[] title= {"","서울 명소","서울 자연 & 관광","서울 쇼핑","서울 호텔","서울 게스트하우스"};
		request.setAttribute("title", title[Integer.parseInt(type)]);
		request.setAttribute("main_jsp", "../seoul/seoul_list.jsp");
		return "../main/main.jsp";
	}
}
