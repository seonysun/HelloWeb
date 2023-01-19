package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class SeoulModel {
	@RequestMapping("seoul/seoul.do")
	public String seoul(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../seoul/.jsp");
		return "../main/main.jsp";
	}
}
