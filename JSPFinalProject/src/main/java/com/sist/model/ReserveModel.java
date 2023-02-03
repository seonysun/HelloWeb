package com.sist.model;
import java.util.*;
import java.text.*;
import com.sist.vo.*;
import com.sist.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
@Controller
public class ReserveModel {
	@RequestMapping("reserve/reserve_main.do")
	public String reserve_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../reserve/reserve_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("reserve/food_list.do")
	public String reserve_food_list(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String fd=request.getParameter("fd");
		ReserveDAO dao=new ReserveDAO();
		List<FoodVO> list=dao.reserveFoodListData(fd);
		request.setAttribute("list", list);
		return "../reserve/reserve_food.jsp";
	}
	
	@RequestMapping("reserve/reserve_date.do")
	public String reserve_date(HttpServletRequest request, HttpServletResponse response) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
		String today=sdf.format(date);
		StringTokenizer st=new StringTokenizer(today,"-");
		String strYear=st.nextToken();
		String strMonth=st.nextToken();
		String strDay=st.nextToken();
		
		//초기값 오늘 날짜로 설정
		String syear=request.getParameter("year");
		String smonth=request.getParameter("month");
		if(syear==null) syear=strYear;
		if(smonth==null) smonth=strMonth;
		
		//전송된 날짜값 설정
		int year=Integer.parseInt(syear);
		int month=Integer.parseInt(smonth);
		int day=Integer.parseInt(strDay);
		
		//요일 설정
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK); //1일의 요일
		String[] strWeek= {"일","월","화","수","목","금","토"};
		
		//마지막날
		int lastday=cal.getActualMaximum(Calendar.DATE);
		
		//데이터 전송
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("lastday", lastday);
		request.setAttribute("week", week-1);
		request.setAttribute("strWeek", strWeek);
		return "../reserve/reserve_date.jsp";
	}
}
