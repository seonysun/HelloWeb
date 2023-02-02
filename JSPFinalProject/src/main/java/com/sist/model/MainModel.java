package com.sist.model;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller 
	//구분자를 올려줘야 Controller가 인식해서 DispatcherServlet이 찾을 수 있음
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request, HttpServletResponse response) {
		FoodDAO dao=new FoodDAO();
		ArrayList<CategoryVO> list=dao.foodCategoryData();
		request.setAttribute("list", list);

		//쿠키 전송
		Cookie[] cookies=request.getCookies();
		List<FoodVO> cList=new ArrayList<FoodVO>();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		if(cookies!=null) {
			if(id==null) {
				for(int i=cookies.length-1;i>=0;i--) { //최근 쿠키부터 순차적으로 출력
					if(cookies[i].getName().startsWith("guest_food")) {
						String fno=cookies[i].getValue();
						FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
						cList.add(vo);
					}
				}
			} else {
				for(int i=cookies.length-1;i>=0;i--) {
					if(cookies[i].getName().startsWith(id+"_food")) {
						String fno=cookies[i].getValue();
						FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
						cList.add(vo);
					}
				}
			}
		}
		request.setAttribute("cList", cList);
		
		request.setAttribute("main_jsp", "../main/home.jsp"); //include할 파일명 전송
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
}
