package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReBoardModel {
	public void boardListData(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		ReBoardDAO dao=new ReBoardDAO();
		ArrayList<ReBoardVO> list=dao.boardListData(curpage);
		request.setAttribute("list", list);
		request.setAttribute("today", new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		
		int count=dao.boardRowCount();
		request.setAttribute("count", count);
	}
}
