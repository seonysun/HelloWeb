package com.sist.model;
import java.util.*;
import java.text.*;
import com.sist.vo.*;
import com.sist.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//달력 설정 받아오기, 초기값 오늘 날짜
		String syear=request.getParameter("year");
		if(syear==null) syear=strYear;
		String smonth=request.getParameter("month");
		if(smonth==null) smonth=strMonth;
		
		//전송할 날짜값 변수 설정
		int year=Integer.parseInt(syear);
		int month=Integer.parseInt(smonth);
		int day=Integer.parseInt(strDay);
		
		//달력 설정
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1); //월은 1 빼기
		cal.set(Calendar.DATE, 1); //날짜는 1일부터이므로 1
		String[] strWeek= {"일","월","화","수","목","금","토"};
		int week=cal.get(Calendar.DAY_OF_WEEK); //1일의 요일
		int lastday=cal.getActualMaximum(Calendar.DATE);
		
		//예약가능일 출력
		ReserveDAO dao=new ReserveDAO();
		String fno=request.getParameter("fno");
		String rdates=dao.reserveDayData(Integer.parseInt(fno)); //fno에 저장된 reserve_day
		String[] temp=rdates.split(",");
		int[] rdays=new int[32]; //31일까지 날짜 배열 생성(0~31)
		for(String s:temp) {
			if(Integer.parseInt(s)>day) {
				//예약가능일(reserve_day) 중 오늘보다 큰 날짜만 예약 가능하도록 배열에 데이터(1) 설정
				rdays[Integer.parseInt(s)]=1;
			}
		}
		
		//데이터 전송
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("lastday", lastday);
		request.setAttribute("week", week-1);
		request.setAttribute("strWeek", strWeek);
		request.setAttribute("rdays", rdays);
		return "../reserve/reserve_date.jsp";
	}
	
	@RequestMapping("reserve/reserve_time.do")
	public String reserve_time(HttpServletRequest request, HttpServletResponse response) {
		String day=request.getParameter("day"); //rdate
		ReserveDAO dao=new ReserveDAO();
		String rtime=dao.reserveTimeData(Integer.parseInt(day));
		StringTokenizer st=new StringTokenizer(rtime,",");
		List<String> rtimes=new ArrayList<String>();
		while(st.hasMoreTokens()) {
			String ss=dao.reserveTimeRealData(Integer.parseInt(st.nextToken()));
			rtimes.add(ss);
		}
		request.setAttribute("rtimes", rtimes);
		return "../reserve/reserve_time.jsp";
	}
	
	@RequestMapping("reserve/reserve_inwon.do")
	public String reserve_inwon(HttpServletRequest request, HttpServletResponse response) {
		return "../reserve/reserve_inwon.jsp";
	}
	
	@RequestMapping("reserve/reserve_ok.do")
	public String reserve_ok(HttpServletRequest request, HttpServletResponse response) {
		String fno=request.getParameter("reservefno");
		String rdate=request.getParameter("reserveday");
		String rtime=request.getParameter("reservetime");
		String inwon=request.getParameter("reserveinwon");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String reserve_no=sdf.format(date)+fno;
		
		ReserveDAO dao=new ReserveDAO();
		ReserveVO vo=new ReserveVO();
		vo.setFno(Integer.parseInt(fno));
		vo.setRdate(rdate);
		vo.setRtime(rtime);
		vo.setInwon(Integer.parseInt(inwon));
		vo.setId(id);
		vo.setReserve_no(reserve_no);
		dao.reserveOk(vo);
		return "redirect:../mypage/reserve.do";
	}
	
	@RequestMapping("reserve/reserve_delete.do")
	public String reserve_delete(HttpServletRequest request, HttpServletResponse response) {
		String rno=request.getParameter("rno");
		ReserveDAO dao=new ReserveDAO();
		dao.reserveDelete(Integer.parseInt(rno));
		return "redirect:../mypage/reserve.do";
	}
	
	
}
