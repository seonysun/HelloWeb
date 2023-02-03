package com.sist.model;
import com.sist.vo.*;
import com.sist.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class JjimModel {
	@RequestMapping("jjim/jjim_insert.do")
	public String jjim_insert(HttpServletRequest request, HttpServletResponse response) {
		String fno=request.getParameter("fno");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		JjimDAO dao=new JjimDAO();
		JjimVO vo=new JjimVO();
		vo.setNo(Integer.parseInt(fno));
		vo.setId(id);
		dao.jjimInsert(vo);
		return "redirect:../food/food_detail.do?fno="+fno;
	}
}
