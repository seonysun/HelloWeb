package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeoulModel {
	public void seoulListData(HttpServletRequest request, HttpServletResponse response) {
		SeoulDAO dao=new SeoulDAO();
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		ArrayList<SeoulVO> list=dao.seoulListData(curpage);
		request.setAttribute("list", list);
	}
}
