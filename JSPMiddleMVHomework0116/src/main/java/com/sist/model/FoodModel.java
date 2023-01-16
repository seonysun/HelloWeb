package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FoodModel {
	public void foodListData(HttpServletRequest request, HttpServletResponse response) {
		FoodDAO dao=new FoodDAO();
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		ArrayList<FoodVO> list=dao.foodListData(curpage);
		request.setAttribute("list", list);
	}
}
